package com.nexos.storagesystem.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.UUID;

@Entity
@Audited
@DynamicUpdate
@Table(name = "commodities")
@EntityListeners(
        value = {
                AuditingEntityListener.class
        }
)
public class Commodity {

    @Id
    public UUID uuid = UUID.randomUUID();

    @Column(unique = true)
    public String name;

    @Column
    @Min(0)
    public Integer amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_uuid")
    public User user;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    public Date createdAt;

    @Column(name = "last_modified_at", updatable = false)
    @LastModifiedDate
    public Date lastModifiedAt;

    public Commodity() {}

    public Commodity(UUID uuid, String name, @Min(0) Integer amount, User user, Date createdAt, Date lastModifiedAt) {
        this.uuid = uuid;
        this.name = name;
        this.amount = amount;
        this.user = user;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
