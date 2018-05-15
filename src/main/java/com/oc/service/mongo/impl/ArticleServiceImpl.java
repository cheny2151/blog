package com.oc.service.mongo.impl;

import com.oc.dao.mongo.ArticleMongo;
import com.oc.dao.mongo.BaseMongo;
import com.oc.entity.mongo.Article;
import com.oc.service.mongo.ArticleService;
import com.oc.system.page.Page;
import com.oc.system.page.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("articleServiceImpl")
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {

    @Resource(name = "articleMongoImpl")
    private ArticleMongo articleMongo;

    @Resource(name = "articleMongoImpl")
    @Override
    protected void setBaseMongo(BaseMongo<Article> baseMongo) {
        super.setBaseMongo(baseMongo);
    }

    @Override
    public Page<Article> findIdAndTitlePage(PageInfo pageInfo, Long classificationId) {
        return articleMongo.findIdAndTitlePage(pageInfo, classificationId);
    }

}
