package com.example.service.id_generation.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "id_generation")
public class IDGeneration {
    @javax.persistence.Id
    private Long Id;
    private Integer lastUsedNum;
    private Date updateTime;

    public IDGeneration() {
    }

    public IDGeneration(Long id, Integer lastUsedNum, Date updateTime) {
        Id = id;
        this.lastUsedNum = lastUsedNum;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getLastUsedNum() {
        return lastUsedNum;
    }

    public void setLastUsedNum(Integer lastUsedNum) {
        this.lastUsedNum = lastUsedNum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
