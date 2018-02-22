package com.leoyon.vote.command;

import java.util.List;

import com.leoyon.vote.user.SysUser;

public interface SysCommandService {

	List<SysCommand> listByUser(SysUser user);

	void update(SysCommand command);

	void add(SysCommand command);

	List<SysCommand> find(FindSysCommandRequest rqst);

	List<SysCommand>  all();

	boolean existed(Long id);

	void delete(SysCommand entity);

}
