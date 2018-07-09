import com.oc.dao.mongo.ArticleMongo;
import com.oc.dao.mybatis.BloggerDao;
import com.oc.entity.mongo.Article;
import com.oc.entity.pojo.Blogger;
import com.oc.javaconfig.spring.RootConfig;
import com.oc.system.page.Page;
import com.oc.system.page.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BloggerDao bloggerDao;

    @Test
    public void test() {
        Blogger admin = bloggerDao.getUserByUsername("admin");
        System.out.println(admin);
    }

}
