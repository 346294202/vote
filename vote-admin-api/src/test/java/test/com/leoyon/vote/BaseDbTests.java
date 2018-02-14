package test.com.leoyon.vote;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.leoyon.vote.dao.DbUtil;
import com.leoyon.vote.util.M;

public class BaseDbTests {
	
	protected Long defUID = 1L;

	@Autowired
	protected DbUtil dbUtil;

	public BaseDbTests() {
		super();
	}

	protected List<Map<String, Object>> buildCommands() {
		List<Map<String, Object>> list = Arrays.asList(
				new M<String,Object>()
				.put("id", 1L)
				.put("parent_id", 0L)
				.put("name", "foo")
				.put("url", "1")
				.build(),
				new M<String,Object>()
				.put("id", 2L)
				.put("parent_id", 1L)
				.put("name", "fo10o")
				.put("url", "1")
				.build(),
				new M<String,Object>()
				.put("id", 3L)
				.put("parent_id", 1L)
				.put("name", "foo10")
				.put("url", "1")
				.build(),
				new M<String,Object>()
				.put("id", 4L)
				.put("parent_id", 0L)
				.put("name", "10foo")
				.put("url", "1")
				.build()
				);
		return list;
	}

	protected List<Map<String, Object>> buildSysUsers() {
		List<Map<String, Object>> list = Arrays.asList(
				M.map()
				.put("username", "wj")
				.put("password", "111")
				.put("salt", "asas")
				.put("active", 1)
				.build(),
				M.map()
				.put("username", "w10j")
				.put("password", "111")
				.put("salt", "asas")
				.put("active", 0)
				.build(),
				M.map()
				.put("username", "wj10")
				.put("password", "111")
				.put("salt", "asas")
				.put("active", 1)
				.build(),
				M.map()
				.put("username", "10wj")
				.put("password", "111")
				.put("salt", "asas")
				.put("active", 0)
				.build()
				);
		return list;
	}

}