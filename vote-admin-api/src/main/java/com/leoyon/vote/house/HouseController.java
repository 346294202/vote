package com.leoyon.vote.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.doc.ApiDocAnnotation;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.util.M;

@RestController
@Scope("prototype")
public class HouseController extends AuthenticationController {
	
	@Autowired
	private HouseService houseService;

	@GetMapping(value="/house", name="查询房屋")
	@ApiDocAnnotation(params={
			"areaId 小区id，整数，可选", 
			"building 楼栋号，整数，可选", 
			"unit 单元，整数，可选", 
			"number 房号，整数，可选", 
			"page 页数，整数，可选，缺省0", 
			"psize 每页个数，整数，可选，缺省20"
	})
	public JsonResponse find(FindHouseRequest reqst) {
		return JsonResponse.sucess(new M<>()
				.put("items", houseService.find(reqst))
				.build());	
	}
	
	@PostMapping(value="/house", name="新增房屋")
	@ApiDocAnnotation(params={
			"areaId 小区id，整数，必须",
			"building 楼栋，整数，必须",
			"unit 单元，整数，必须",
			"number 房号，整数，必须",
			"houseType 房屋类型，整数，必须",
			"houseStatus 房屋状态，整数，必须",
			"remark 备注，字符串，可选，最大255",
	})
	public JsonResponse add(@RequestBody House house) {
		houseService.add(house);
		return JsonResponse.sucess();
	}
	
	@PostMapping(value="/house/{id}", name="修改房屋")
	@ApiDocAnnotation(params={
			"areaId 小区id，整数",
			"building 楼栋，整数",
			"unit 单元，整数",
			"number 房号，整数",
			"houseType 房屋类型，整数",
			"houseStatus 房屋状态，整数",
			"remark 备注，字符串，最大255",
	})
	public JsonResponse update(
			@PathVariable(value="id") Long id,
			@RequestBody House house
			) {
		house.setId(id);
		houseService.update(house);
		return JsonResponse.sucess();
	}
}
