package com.leoyon.vote.house;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.leoyon.vote.util.Parses;

public class FindHouseRequest {
	
	public static class ArgumentResolver implements HandlerMethodArgumentResolver {

		@Override
		public boolean supportsParameter(MethodParameter parameter) {			
			return parameter.getParameterType().equals(FindHouseRequest.class);
		}

		@Override
		public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
				NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
			
			return new FindHouseRequest(
					Parses.parse(webRequest.getParameter("areaId"), Long.class, null),
					Parses.parse(webRequest.getParameter("building"), Integer.class, null),
					Parses.parse(webRequest.getParameter("unit"), Integer.class, null),
					Parses.parse(webRequest.getParameter("number"), Integer.class, null),
					Parses.parse(webRequest.getParameter("page"), Integer.class, 0),
					Parses.parse(webRequest.getParameter("psize"), Integer.class, 20),				
					Parses.parse(webRequest.getParameter("houseType"), Integer.class, null),
					Parses.parse(webRequest.getParameter("houseStatus"), Integer.class, null)	
					);
		}
		
	}
	
	private Long areaId;
	private Integer building;
	private Integer unit;
	private Integer number;
	private Integer page;
	private Integer psize;
	private Integer houseType;
	private Integer houseStatus;
	
	public FindHouseRequest(Long areaId, Integer building, Integer unit, Integer number, Integer page, Integer psize,
			Integer houseType, Integer houseStatus) {
		super();
		this.areaId = areaId;
		this.building = building;
		this.unit = unit;
		this.number = number;
		this.page = page;
		this.psize = psize;
		this.houseType = houseType;
		this.houseStatus = houseStatus;
	}

	public Integer getHouseType() {
		return houseType;
	}

	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}

	public Integer getHouseStatus() {
		return houseStatus;
	}

	public void setHouseStatus(Integer houseStatus) {
		this.houseStatus = houseStatus;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPsize() {
		return psize;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPsize(Integer psize) {
		this.psize = psize;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Integer getBuilding() {
		return building;
	}

	public void setBuilding(Integer building) {
		this.building = building;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
