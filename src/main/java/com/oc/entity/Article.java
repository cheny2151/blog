package com.oc.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

/**
 * 文章
 */
@Entity
@Table(name = "m_article", indexes = {@Index(columnList = "bloggerId"), @Index(columnList = "classificationId")})
public class Article extends BaseEntity {

    private static final long serialVersionUID = 3059543850654309594L;

    /**
     * 标题
     */
    private String title;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 博主id
     */
    private Long bloggerId;

    /**
     * 博主username
     */
    private String username;

    /**
     * 分类id
     */
    private Long classificationId;

    /**
     * 分类名称
     */
    private String classificationName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(Long bloggerId) {
        this.bloggerId = bloggerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
