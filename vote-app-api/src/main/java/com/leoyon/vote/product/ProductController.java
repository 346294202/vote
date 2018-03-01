package com.leoyon.vote.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.ListResponse;

@RestController("产品")
@Scope("prototype")
public class ProductController extends AuthenticationController {
	
	@Autowired
	private ProductService productService;

	@GetMapping(value="/product/{type}", name="获得产品")
	public ListResponse<Product> list(
			@PathVariable("type") Integer type,
			@RequestParam(value="page", required=false, defaultValue="0") Integer page,
			@RequestParam(value="psize", required=false, defaultValue="20") Integer psize
			) {
		return ListResponse.success(productService.list(new ListProductRequest(type, page, psize)));
	}
}
