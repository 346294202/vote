package com.leoyon.vote.command;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.command.dao.SysCommandDao;
import com.leoyon.vote.user.SysUser;

@Service
public class SysCommandServiceImp implements SysCommandService {
	
	@Autowired
	private SysCommandDao commandDao; 

	@Override
	public List<SysCommand> listByUser(SysUser user) {	
		List<SysCommand> all = commandDao.all();
		
		List<SysCommand> byUser = commandDao.listByUser(user.getId());
		
		Vector<SysCommand> ret = new Vector<>();
		ret.addAll(byUser);
		for(SysCommand c:byUser) {
			collect(c, all, ret);
		}
		return ret;
	}

	private void collect(SysCommand child, List<SysCommand> all, List<SysCommand> ret) {
		for(SysCommand c:all) {
			if(c.getId() == child.getParentId()) {
				ret.add(c);
				if(c.getParentId() > 0) {
					collect(c, all, ret);
				}
			}
		}
	}

}
