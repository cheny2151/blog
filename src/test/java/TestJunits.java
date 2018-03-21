import com.oc.javaconfig.spring.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 单元测试类
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class TestJunits {

    @PersistenceContext
    public EntityManager manager;

    @Test
    public void test() {
        Object[] result = (Object[]) manager.createNativeQuery("select * from auth_user where id = 1").getSingleResult();
        System.out.println(result[0]);
    }
}
