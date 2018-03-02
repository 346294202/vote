package com.leoyon.vote.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.DataResponse;
import com.leoyon.vote.api.ListResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.payment.Payment;
import com.leoyon.vote.user.User;

@RestController("产品")
@Scope("prototype")
public class ProductController extends AuthenticationController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(value="/product/payment", name="生成支付数据")
	public DataResponse<Payment> addPayment(
			@RequestBody PaymentRequest rqst
			) throws ResponseException {
		User user = getLogin(false);
		rqst.setUserId(user.getId());
		return DataResponse.sucess(productService.addPayment(rqst));
	}
	
	@GetMapping(value="/product/{type}", name="获得产品")
	public ListResponse<Product> list(
			@PathVariable("type") Integer type,
			@RequestParam(value="page", required=false, defaultValue="0") Integer page,
			@RequestParam(value="psize", required=false, defaultValue="20") Integer psize
			) {
		return ListResponse.success(productService.list(new ListProductRequest(type, page, psize)));
	}
	

}
