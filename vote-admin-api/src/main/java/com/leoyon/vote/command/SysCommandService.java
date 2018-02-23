package com.leoyon.vote.command;

import java.util.List;

import com.leoyon.vote.user.SysUser;

public interface SysCommandService {
	
	void update(SysCommand command);

	void add(SysCommand command);

	List<SysCommand> find(FindSysCommandRequest rqst);

	List<SysCommand>  all();

	boolean existed(Long id);

	void delete(SysCommand entity);

	int count(FindSysCommandRequest rqst);

}
