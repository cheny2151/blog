package com.oc.dao.mongo.impl;

import com.oc.dao.mongo.ArticleMongo;
import com.oc.entity.mongo.Article;
import com.oc.system.page.Page;
import com.oc.system.page.PageInfo;
import com.oc.utils.security.AuthUtils;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

@Repository("articleMongoImpl")
public class ArticleMongoImpl extends BaseMongoImpl<Article> implements ArticleMongo{


    @Override
    public Page<Article> findIdAndTitlePage(PageInfo pageInfo, Long classificationId) {
        String author = AuthUtils.getCurrentUsername();
        Document queryBson = new Document();
        queryBson.put("author", author);
        if (classificationId != null) {
            queryBson.put("classificationId", classificationId);
        }
        Document fieldBason = new Document();
        fieldBason.put("title", 1);
        fieldBason.put("author", 1);
        fieldBason.put("classificationId", 1);
        fieldBason.put("classificationName", 1);
        BasicQuery query = new BasicQuery(queryBson, fieldBason);
        return super.findPage(query, pageInfo, Sort.Order.desc("createDate"));
    }

}
