package com.leoyon.vote.role;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leoyon.vote.command.SysCommand;
import com.leoyon.vote.command.SysCommandService;
import com.leoyon.vote.role.dao.SysRoleCommandDao;
import com.leoyon.vote.role.dao.SysRoleDao;

@Service
public class SysRoleServiceImp implements SysRoleService, SysRoleCommandService {
	
	@Autowired
	private SysRoleDao dao;

	@Override
	public List<SysRole> find(FindSysRoleRequest rqst) {
		return dao.find(rqst);
	}

	@Override
	public void add(SysRole role) {
		dao.add(role);
	}

	@Override
	public void update(SysRole role) {
		dao.update(role);
	}
	
	@Autowired
	private SysRoleCommandDao sysRoleCommandDao;

	@Override
	public List<SysCommand> getRoleCommands(Long roleId) {		
		return sysRoleCommandDao.getRoleCommands(roleId);
	}
	
	@Autowired
	private SysCommandService sysCommandService;
	
	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void setRoleCommands(Long roleId, List<Long> commandIds) throws Exception {
		
		for(Long id:commandIds) {
			if(!sysCommandService.existed(id)) {
				throw new Exception("命令ID"+id+"无效");
			}
		}
		
		sysRoleCommandDao.clearRoleCommands(roleId);
		
		commandIds.forEach(id -> {
			sysRoleCommandDao.addRoleCommand(roleId, id);
		});
	}

	@Override
	public List<SysRole> all() {
		return dao.all();
	}

	@Override
	public boolean existed(Long id) {
		Long ret = dao.existed(id);		
		return ret != null && ret.equals(id);
	}

	@Override
	public void delete(SysRole entity) {
		dao.delete(entity);
	}

	@Override
	public List<SysCommand> collectCommands(List<SysRole> roles) {
		List<SysCommand> all = sysCommandService.all();
		LinkedHashSet<SysCommand> byRoles = new LinkedHashSet<>();
		roles.forEach(i -> {
			byRoles.addAll(sysRoleCommandDao.getRoleCommands(i.getId()));
		});
		
		Vector<SysCommand> ret = new Vector<>(byRoles);
		for(SysCommand c:byRoles) {
			collectParents(c, all, ret);
			collectChilds(c, all, ret);
		}
		return ret;
	}

	private void collectChilds(SysCommand parent, List<SysCommand> all, Vector<SysCommand> ret) {
		for(SysCommand c:all) {
			if(ret.contains(c))
				continue;
			if(c.getParentId() == parent.getId()) {
				ret.add(c);
				collectChilds(c, all, ret);
			}
		}
	}

	private void collectParents(SysCommand child, List<SysCommand> all, List<SysCommand> ret) {
		for(SysCommand c:all) {
			if(ret.contains(c))
				continue;
			if(c.getId() == child.getParentId()) {
				ret.add(c);
				if(c.getParentId() > 0) {
					collectParents(c, all, ret);
				}
			}
		}
	}

}
