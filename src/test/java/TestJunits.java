import com.oc.dao.mongo.ArticleMongo;
import com.oc.entity.mongo.Article;
import com.oc.javaconfig.spring.RootConfig;
import com.oc.service.jpa.UserService;
import com.oc.system.page.Page;
import com.oc.system.page.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 单元测试类
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@ActiveProfiles("dev")
public class TestJunits {

    @PersistenceContext
    public EntityManager manager;
    @Resource(name = "articleMongoImpl")
    private ArticleMongo articleMongo;
    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "profilesBean")
    private String profile;

    @Test
    public void test() {
        Page<Article> idAndTitlePage = articleMongo.findIdAndTitlePage(new PageInfo(),1L);
        for (Article a:idAndTitlePage.getContent()){
            System.out.println(a);
        }
    }

    @Test
    public void test2() {
        for (int i = 0; i < 3; i++) {
            Article article = new Article();
            article.setAuthor("author2");
            article.setTitle("title");
            article.setClassificationId(1L);
            article.setContent("content");
            articleMongo.save(article);
        }
    }

}
