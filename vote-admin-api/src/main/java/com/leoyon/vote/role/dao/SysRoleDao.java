package com.leoyon.vote.role.dao;

import java.util.List;

import com.leoyon.vote.role.FindSysRoleRequest;
import com.leoyon.vote.role.SysRole;

public interface SysRoleDao {

	List<SysRole> find(FindSysRoleRequest rqst);

	void add(SysRole role);

	void update(SysRole role);

	List<SysRole> all();

	Long existed(Long id);

}
