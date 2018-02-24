package com.leoyon.vote.command;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

import com.leoyon.doc.ApiParam;
import com.leoyon.vote.AbstractResource;

public class SysCommand extends AbstractResource<SysCommand> {
	
	private long parentId;//	bigint	20	0	-1	0	0	0	0		0	上级命令id				0	0
	private String name;//	varchar	255	0	0	0	0	0	0		0	名称	utf8mb4	utf8mb4_unicode_ci		0	0
	private String url;//	varchar	255	0	-1	0	0	0	0		0		utf8mb4	utf8mb4_unicode_ci		0	0
	private Integer icon;//	int	11	0	-1	0	0	0	0	1	0	图标编码：1，2，3，4，5				0	0
	private Integer so;
	private Integer level;
	
	@ApiParam(desc="排序号")
	public Integer getSo() {
		return so;
	}
	public void setSo(Integer so) {
		this.so = so;
	}
	
	public long getParentId() {
		return parentId;
	}
	@ApiParam(desc="上级菜单id")
	public SysCommand setParentId(long parentId) {
		this.parentId = parentId;
		return this;
	}
	
	@ApiParam(desc="名称")
	public String getName() {
		return name;
	}
	public SysCommand setName(String name) {
		this.name = name;
		return this;
	}
	
	@ApiParam(desc="路径")
	public String getUrl() {
		return url;
	}
	public SysCommand setUrl(String url) {
		this.url = url;
		return this;
	}
	
	@ApiParam(desc="图标，1，2，3，4")
	public Integer getIcon() {
		return icon;
	}
	public SysCommand setIcon(Integer icon) {
		this.icon = icon;
		return this;
	}
	
	public static void colleatLeafs(Long id, List<SysCommand> commands, List<Long> leafs) {
		
		for(SysCommand c:commands) {
			if(id.equals(c.getId())) {
				if(!StringUtils.isBlank(c.getUrl())) {//is leaf
					leafs.add(id);
				} else if(id.equals(c.getParentId())) {
					colleatLeafs(c.getId(), commands, leafs);
				}
			}
		}
		
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}


}
