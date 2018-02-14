package com.leoyon.vote.command.dao;

import java.util.List;

import com.leoyon.vote.command.FindSysCommandRequest;
import com.leoyon.vote.command.SysCommand;

public interface SysCommandDao {

	List<SysCommand> listByUser(Long uid);

	List<SysCommand> all();

	void update(SysCommand command);

	void add(SysCommand command);

	List<SysCommand> find(FindSysCommandRequest rqst);

	Long existed(Long id);

}
