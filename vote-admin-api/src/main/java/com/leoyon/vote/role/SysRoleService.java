package com.leoyon.vote.role;

import java.util.List;

public interface SysRoleService {

	List<SysRole> find(FindSysRoleRequest rqst);

	void add(SysRole role);

	void update(SysRole role);

	List<SysRole> all();

	boolean existed(Long id);

	void delete(SysRole entity);
}
