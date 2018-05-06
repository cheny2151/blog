package com.oc.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 文章
 */
@Document
public class Article extends MongoBaseEntity {

    private static final long serialVersionUID = 6678930490787393665L;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者username
     */
    private String author;

    /**
     * 分类id
     */
    private Long classificationId;

    /**
     * 分类名称
     */
    private String classificationName;

    /**
     * 内容
     */
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }
}
