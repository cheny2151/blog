package com.oc.dao.mongo;

import com.oc.entity.mongo.Article;
import com.oc.system.page.Page;
import com.oc.system.page.PageInfo;

/**
 * 文章mongo基类
 */
public interface ArticleMongo extends BaseMongo<Article>{

    /**
     * 分页查找文章的id和title
     *
     * @param pageInfo 分页信息
     * @return
     */
    Page<Article> findIdAndTitlePage(PageInfo pageInfo,Long classificationId);

}
