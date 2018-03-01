package com.leoyon.vote.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.DefauleResponse;
import com.leoyon.vote.api.ListResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.user.User;

@RestController("地址管理")
@Scope("prototype")
public class AddressController extends AuthenticationController {
	
	@Autowired
	private AddressService addressService;

	@GetMapping(value="/address", name="获得地址")
	public ListResponse<Address> getAddresses() throws ResponseException {
		User user = getLogin(false);
		return ListResponse.success(addressService.listAddressByUser(user.getId()));
	}
	
	@DeleteMapping(value="/address/{id}", name="删除地址")
	public DefauleResponse deleteAddress(@PathVariable("id") Long id) throws ResponseException {
		User user = getLogin(false);
		Address entity = new Address();
		entity.setId(id);
		entity.setUserId(user.getId());
		addressService.delete(entity);
		return DefauleResponse.sucess();
	}
	
	@PostMapping(value="/address", name="新增地址")
	public DefauleResponse add(@RequestBody Address entity) throws ResponseException {
		User user = getLogin(false);
		entity.setUserId(user.getId());
		addressService.add(entity);
		return DefauleResponse.sucess();
	}
	
	@PostMapping(value="/address/{id}", name="修改地址")
	public DefauleResponse update(@PathVariable("id") Long id, @RequestBody Address entity) throws ResponseException {
		User user = getLogin(false);
		entity.setId(id);
		entity.setUserId(user.getId());
		addressService.update(entity);
		return DefauleResponse.sucess();
	}
	
}
