package com.bank.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Calendar;

@MappedSuperclass
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long creationDate;

    public AbstractEntity() {
        super();
        Calendar today = Calendar.getInstance();
        this.creationDate = today.getTimeInMillis();
    }

    public AbstractEntity(Long id) {
        super();
        this.id = id;
        Calendar today = Calendar.getInstance();
        this.creationDate = today.getTimeInMillis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }
}
