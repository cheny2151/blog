package com.oc.service.mongo;


import com.oc.entity.mongo.Article;
import com.oc.system.page.Page;
import com.oc.system.page.PageInfo;

/**
 * 文章 - service
 */
public interface ArticleService extends BaseService<Article> {

    /**
     * 分页查找文章的id和title
     *
     * @param pageInfo 分页信息
     * @return
     */
    Page<Article> findIdAndTitlePage(PageInfo pageInfo, Long classificationId);

}
