package com.leoyon.vote.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.role.dao.SysRoleDao;

@Service
public class SysRoleServiceImp implements SysRoleService {
	
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

}
