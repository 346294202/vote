package com.leoyon.vote.command;

import java.util.List;
import java.util.Vector;

/**
 * 作为菜单命令返回客户端
 * @author WangJiang
 *
 */
public class Menu {

	private String name;//:"<菜单名称>",
    private Integer icon;//:<图标>
    private String url;//:"<相对路径>"
    private List<Menu> menus;//:[<与上级相同>]
    
    public String getName() {
		return name;
	}

	public Menu setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getIcon() {
		return icon;
	}

	public Menu setIcon(Integer icon) {
		this.icon = icon;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public Menu setUrl(String url) {
		this.url = url;
		return this;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public Menu setMenus(List<Menu> menus) {
		this.menus = menus;
		return this;
	}
	
	public static List<Menu> build(List<SysCommand> commands) {
		return build(commands, 0);
	}

	private static List<Menu> build(List<SysCommand> commands, long parentId) {
    	final Vector<Menu> ret = new Vector<>();
    	
    	commands.forEach(i -> {
    		if(i.getParentId() == parentId) {
    			List<Menu> menus = build(commands, i.getId());
    			ret.add(new Menu().setName(i.getName()).setIcon(i.getIcon()).setUrl(i.getUrl()).setMenus(menus));
    		}
    	});
    	
    	return ret;
    }
}
