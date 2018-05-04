import com.oc.entity.mongo.Article;
import com.oc.utils.MongoEntityHelp;
import org.junit.Test;

import java.beans.IntrospectionException;

public class TestMain {

    @Test
    public void test() throws IntrospectionException {
        Article article = new Article();
        article.setAuthor("test");
        article.setContent("testContent");
        article.setTitle("title");
        MongoEntityHelp.update(article,"id");
    }

}
