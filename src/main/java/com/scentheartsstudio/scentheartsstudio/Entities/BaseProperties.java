package com.scentheartsstudio.scentheartsstudio.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseProperties {
    @Column(nullable = false)
    private Long created_by;

    @Column(nullable = false)
    private Date created_on;
    @Column
    private Long modified_by;
    @Column
    private Date modified_on;
    @Column
    private Long deleted_by;
    @Column
    private Date deleted_on;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean is_delete = false;

    public Long getCreated_by() {
        return this.created_by;
    }

    public void setCreated_by(Long created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_on() {
        return this.created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Long getModified_by() {
        return this.modified_by;
    }

    public void setModified_by(Long modified_by) {
        this.modified_by = modified_by;
    }

    public Date getModified_on() {
        return this.modified_on;
    }

    public void setModified_on(Date modified_on) {
        this.modified_on = modified_on;
    }

    public Long getDeleted_by() {
        return this.deleted_by;
    }

    public void setDeleted_by(Long deleted_by) {
        this.deleted_by = deleted_by;
    }

    public Date getDeleted_on() {
        return this.deleted_on;
    }

    public void setDeleted_on(Date deleted_on) {
        this.deleted_on = deleted_on;
    }

    public Boolean isIs_delete() {
        return this.is_delete;
    }

    public Boolean getIs_delete() {
        return this.is_delete;
    }

    public void setIs_delete(Boolean is_delete) {
        this.is_delete = is_delete;
    }

}
