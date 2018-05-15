package com.oc.controller.user.blogger;

import com.oc.entity.mongo.Article;
import com.oc.service.mongo.ArticleService;
import com.oc.system.message.JsonMessage;
import com.oc.system.page.Page;
import com.oc.system.page.PageInfo;
import com.oc.utils.security.AuthUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文章管理 - controller
 */
@RestController
@RequestMapping("/blogger/article")
public class ArticleController {

    @Resource(name = "articleServiceImpl")
    private ArticleService articleService;

    /**
     * 保存
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMessage save(Article article) {
        article.setAuthor(AuthUtils.getCurrentUsername());
        String id = articleService.save(article);
        return JsonMessage.success(id);
    }

    /**
     * 更新
     */
    @RequestMapping(method = RequestMethod.PUT)
    public JsonMessage update(Article article) {
        if (StringUtils.isEmpty(article.getId())) {
            return JsonMessage.error("id不能为空");
        }
        article.setAuthor(AuthUtils.getCurrentUsername());
        String id = articleService.save(article);
        return JsonMessage.success(id);
    }

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonMessage delete(String id) {
        if (StringUtils.isEmpty(id)) {
            return JsonMessage.error("id不能为空");
        }
        articleService.remove(id);
        return JsonMessage.success();
    }

    /**
     * 查找分页信息
     */
    @RequestMapping(method = RequestMethod.GET)
    public JsonMessage page(PageInfo pageInfo, Long classificationId) {
        Page<Article> page = articleService.findIdAndTitlePage(pageInfo, classificationId);

        return JsonMessage.success(
                "pageNumber", page.getCurrentPage(),
                "pageSize", page.getPageTotal(),
                "total", page.getTotal(),
                "content", JsonMessage.extract(page.getContent(),
                        "id", "title", "author", "classificationId", "classificationName"
                )
        );
    }

}
