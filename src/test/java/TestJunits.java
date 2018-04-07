import com.oc.dao.mongo.ArticleMongo;
import com.oc.javaconfig.spring.RootConfig;
import com.oc.service.UserService;
import com.oc.system.order.OrderFactory;
import com.oc.system.page.Pageable;
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
@ActiveProfiles("test")
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
        Pageable pageable = new Pageable();
        pageable.getOrders().add(OrderFactory.asc("createDate"));
        System.out.println(userService.findPage(pageable).getContent().size());
    }
}
