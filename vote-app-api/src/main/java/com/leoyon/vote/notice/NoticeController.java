package com.leoyon.vote.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.FindPagedRequest;
import com.leoyon.vote.api.ListResponse;

@RestController("通知")
@Scope("prototype")
public class NoticeController extends AuthenticationController {
	
	@Autowired
	private NoticeService noticeService;

	@GetMapping(value="/notice", name="获得通知")
	public ListResponse<Notice> list(
			@RequestParam(name="page", required=false, defaultValue="0") Integer page,
			@RequestParam(name="psize", required=false, defaultValue="20") Integer psize
			) {
		return ListResponse.success(noticeService.list(new FindPagedRequest(page, psize)));
	}
}
