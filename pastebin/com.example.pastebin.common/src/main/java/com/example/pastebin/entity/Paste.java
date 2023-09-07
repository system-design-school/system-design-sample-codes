package com.example.pastebin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "pastebin")
public class Paste {
    @Id
    private String id;
    private Date createTime;
    @Transient
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Paste(String id, Date createTime, String text) {
        this.id = id;
        this.createTime = createTime;
        this.text = text;
    }

    public Paste() {
    }
}
