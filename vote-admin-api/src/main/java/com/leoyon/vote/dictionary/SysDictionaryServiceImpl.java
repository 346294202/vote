package com.leoyon.vote.dictionary;

import com.leoyon.vote.dictionary.dao.SysDictionaryDao;
import com.leoyon.vote.user.FindSysUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Thinkpad on 2018/2/24.
 */
@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {

    @Autowired
    private com.leoyon.vote.dictionary.dao.SysDictionaryDao  sysDictionaryDao;

    @Autowired
    private com.leoyon.vote.AppConfig appConfig;

    @Override
    public List<FindSysDictionaryResponse> find(FindSysDictionaryRequest rqst) {
        List<FindSysDictionaryResponse> ret = sysDictionaryDao.find(rqst);
        return ret;
    }

    @Override
    public void add(SysDictionary dictionary) {
        sysDictionaryDao.add(dictionary);
    }

    @Override
    public void update(SysDictionary dictionary) {
        sysDictionaryDao.update(dictionary);
    }

    @Override
    public void delete(SysDictionary entity) {
        sysDictionaryDao.delete(entity);
    }
}
