package com.leoyon.vote.business;

import java.util.Collection;

public interface BusinessService {

	Collection<Business> find(FindBizRequest rqst);

}
