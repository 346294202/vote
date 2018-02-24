package test.com.leoyon.vote;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.leoyon.vote.command.Menu;
import com.leoyon.vote.command.SysCommand;

public class MenuTests {

	@Test
	public void build() {
		
		List<Menu> menus = Menu.build(Arrays.asList());
		
		Assert.assertTrue(menus.isEmpty());
		
		List<SysCommand> commands = Arrays.asList(
				new SysCommand().setId(1L).setParentId(0L),
				new SysCommand().setId(2L).setParentId(0L),
				new SysCommand().setId(3L).setParentId(0L),
				new SysCommand().setId(4L).setParentId(0L)
				);
		
		menus = Menu.build(commands);
		
		Assert.assertEquals(commands.size(), menus.size());
		
		commands = Arrays.asList(
				new SysCommand().setId(1L).setParentId(0L),
				new SysCommand().setId(2L).setParentId(1L),
				new SysCommand().setId(3L).setParentId(1L),
				new SysCommand().setId(4L).setParentId(0L)
				);
		menus = Menu.build(commands);
		
		Assert.assertEquals(commands.size()-2, menus.size());		
		
		Assert.assertEquals(2, menus.get(0).getMenus().size());	
	}
}
