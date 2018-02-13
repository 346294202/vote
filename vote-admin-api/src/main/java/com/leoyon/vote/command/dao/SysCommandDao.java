package com.leoyon.vote.command.dao;

import java.util.List;

import com.leoyon.vote.command.SysCommand;

public interface SysCommandDao {

	List<SysCommand> listByUser(Long uid);

	List<SysCommand> all();

}
