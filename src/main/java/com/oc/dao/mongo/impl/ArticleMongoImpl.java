package com.oc.dao.mongo.impl;

import com.oc.dao.mongo.ArticleMongo;
import com.oc.entity.mongo.Article;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleMongoImpl extends BaseMongoImpl<Article> implements ArticleMongo{
}
