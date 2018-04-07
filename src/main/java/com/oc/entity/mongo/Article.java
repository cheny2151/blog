package com.oc.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Article extends MongoBaseEntity {

    private static final long serialVersionUID = 6678930490787393665L;

    /**
     * 修改时间
     */
    private Date createDate;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

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
