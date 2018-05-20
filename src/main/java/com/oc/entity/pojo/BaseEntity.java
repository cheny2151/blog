package com.oc.entity.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * entity基类
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 9043258922225188076L;

    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
