package com.oc.service.jpa.impl;

import com.oc.dao.jpa.BaseDao;
import com.oc.dao.jpa.BloggerDao;
import com.oc.entity.jpa.Blogger;
import com.oc.service.jpa.BloggerService;
import com.oc.utils.security.AuthUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service("bloggerServiceImpl")
public class BloggerServiceImpl extends BaseServiceImpl<Blogger, Long> implements BloggerService {

    @Resource(name = "bloggerDaoImpl")
    private BloggerDao bloggerDao;

    @Override
    @Resource(name = "bloggerDaoImpl")
    protected void setBaseDao(BaseDao<Blogger, Long> baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    public Blogger getCurrent() {
        return bloggerDao.findByUsername(AuthUtils.getCurrentUsername());
    }
}
