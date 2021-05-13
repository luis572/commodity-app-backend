package com.nexos.storagesystem.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Audited
@DynamicUpdate
@Table(name = "positions")
@EntityListeners(
        value = {
                AuditingEntityListener.class
        }
)
public class Position {

    @Id
    public UUID uuid = UUID.randomUUID();

    @Column(unique = true)
    public String name;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    public Date createdAt;

    @Column(name = "last_modified_at", updatable = false)
    @LastModifiedDate
    public Date lastModifiedAt;

    public Position() {}

    public Position(UUID uuid, String name, Date createdAt, Date lastModifiedAt) {
        this.uuid = uuid;
        this.name = name;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}
