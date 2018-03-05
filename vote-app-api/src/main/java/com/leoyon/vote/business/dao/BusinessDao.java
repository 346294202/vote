package com.leoyon.vote.business.dao;

import java.util.Collection;

import com.leoyon.vote.business.Business;
import com.leoyon.vote.business.FindBizRequest;
import com.leoyon.vote.picture.Picture;

public interface BusinessDao {

	Collection<Business> find(FindBizRequest rqst);

	Collection<Picture> getPictures(Long id);

}
