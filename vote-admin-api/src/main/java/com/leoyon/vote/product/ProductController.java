package com.leoyon.vote.product;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.util.M;

@RestController
@Scope("prototype")
public class ProductController extends AuthenticationController {
	
	@Autowired
	private ProductService productService;

	@GetMapping(value="/basic/product", name="查询产品")
	public JsonResponse find(FindProductRequest req) {
		List<Product> items = productService.find(req);
		return JsonResponse.sucess(new M<>()
				.put("items", items)
				.build());	
	}
	
	@PostMapping(value="/basic/product", name="新增产品")
	public JsonResponse add(@RequestBody Product entity) throws ResponseException {
		entity.setUpdateUid(getLogin(false).getId());
		entity.setUpdateTime(new Date());
		productService.add(entity);
		return JsonResponse.sucess();
	}
	
	@PostMapping(value="/basic/product/{id}", name="修改产品")
	public JsonResponse update(
			@PathVariable Long id,
			@RequestBody Product entity
			) throws ResponseException {
		entity.setId(id);
		entity.setUpdateUid(getLogin(false).getId());
		entity.setUpdateTime(new Date());
		productService.update(entity);
		return JsonResponse.sucess();
	}
	
	@DeleteMapping(value="/basic/product/{id}", name="删除产品")
	public JsonResponse delete(
			@PathVariable(value="id") Long id) throws ResponseException {
		Product entity = new Product();
		entity.setUpdateUid(getLogin(false).getId());
		entity.setId(id);
		productService.delete(entity);
		return JsonResponse.sucess();
	}
}
