package com.oc.javaconfig.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
//@EnableMongoRepositories(basePackages = {"com.oc"})
public class MongoDBConfig {

    private final Environment env;

    @Autowired
    public MongoDBConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        //此处认证的database是指use的所属的数据库
        MongoCredential credential = MongoCredential.createCredential(env.getProperty("mongodb.username"), "admin", env.getProperty("mongodb.password").toCharArray());
        mongo.setCredentials(new MongoCredential[]{credential});
        mongo.setHost(env.getProperty("mongodb.host"));
        return mongo;
    }

    @Bean
    public MongoOperations mongoTemplate(MongoClient mongo) {
        return new MongoTemplate(mongo, env.getProperty("mongodb.database"));
    }

}
