package test.com.leoyon.doc;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.leoyon.doc.MethodItem;

public class MethodItemTests {
	
	public class User {
		private String username;
		private String password;
		private Boolean active;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Boolean getActive() {
			return active;
		}
		public void setActive(Boolean active) {
			this.active = active;
		}
		
	}
	
	public class MyController1 {
		
		@GetMapping(value="/user", name="Query User")
		public void findUser(
				@RequestParam(value="username") String un,
				@RequestParam(value="active", required=false) Boolean active
				) {
			
		}

	}
	
	public class MyController2 {
		
		@PostMapping(value="/user", name="Add user")
		public void add(@RequestBody User user) {
			
		}
	}
	
	public class MyController3 {
		
		@PostMapping(value="/user/{id}", name="Update user")
		public void add(
				@PathVariable(value="id") Long id,
				@RequestBody User user) {
			
		}
	}

	@Test
	public void generate() {
		
		List<MethodItem> ms = MethodItem.generate(Arrays.asList(
				new MyController1(), 
				new MyController2(), 
				new MyController3()));
		
		assertEquals(3, ms.size());
		int i = 0;
		assertEquals("GET", ms.get(i).getMethod());
		assertEquals("/user", ms.get(i).getPath());
		assertEquals("Query User", ms.get(i).getName());
		assertEquals(2, ms.get(i).getParams().size());
		++i;
		
		assertEquals("POST", ms.get(i).getMethod());
		assertEquals("/user", ms.get(i).getPath());
		assertEquals("Add user", ms.get(i).getName());
		assertEquals(3, ms.get(i).getParams().size());
		++i;
		
		assertEquals("POST", ms.get(i).getMethod());
		assertEquals("/user/{id}", ms.get(i).getPath());
		assertEquals("Update user", ms.get(i).getName());
		assertEquals(4, ms.get(i).getParams().size());
	}

}
