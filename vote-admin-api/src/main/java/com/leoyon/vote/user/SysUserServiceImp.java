package com.leoyon.vote.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leoyon.vote.api.Token;
import com.leoyon.vote.role.SysRole;
import com.leoyon.vote.role.SysRoleService;
import com.leoyon.vote.user.dao.SysUserRoleDao;

@Service
public class SysUserServiceImp implements SysUserService, SysUserRoleService {
	
	@Autowired
	private com.leoyon.vote.user.dao.SysUserDao userDao;
	
	@Autowired
	private com.leoyon.vote.AppConfig appConfig;

	@Override
	public Token fetchToken(SysUser user) {
		return Token.build(user.getId(), appConfig.getTokenExpirSeconds());
	}

	@Override
	public SysUser get(String username) {
		return userDao.getUser(username);
	}

	@Override
	public SysUser get(Long id){
		return userDao.getUserById(id);
	}

	@Override
	public List<com.leoyon.vote.user.FindSysUserResponse> find(FindSysUserRequest req) {
		if(req.getPsize() < 1)
			req.setPsize(appConfig.getPageSize());
		req.setPage(req.getPage()*req.getPsize());
		
		List<FindSysUserResponse> ret = userDao.findUser(req);
		for(FindSysUserResponse i:ret) {
			i.setRoles(sysUserRoleDao.getUserRoles(i.getId()));
		}
		return ret;
	}

	@Override
	public void add(SysUser user) {
		userDao.addUser(user);		
	}

	@Override
	public void update(SysUser user) {
		userDao.updateUser(user);
	}

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public List<SysRole> getUserRoles(Long uid) {
		return sysUserRoleDao.getUserRoles(uid);
	}
	
	@Autowired
	private SysRoleService sysRoleService;

	@Override
	 @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void setUserRoles(Long uid, List<Long> roleIds) throws Exception {
		
		for(Long id:roleIds) {
			if(!sysRoleService.existed(id)) {
				throw new Exception("角色ID"+id+"无效");
			}
		}
		
		sysUserRoleDao.clearUserRoles(uid);
		
		roleIds.forEach(id -> {
			sysUserRoleDao.addUserRole(uid, id);
		});
	}
}
