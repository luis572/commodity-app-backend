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
@Table(name = "users")
@EntityListeners(
        value = {
                AuditingEntityListener.class
        }
)
public class User {

    @Id
    public UUID uuid = null;

    @Column(unique = true)
    public String name;

    @Column
    @Min(0)
    public int age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_uuid")
    public Position position;

    @Column(name = "last_name",unique = true)
    public String lastName;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    public Date createdAt;

    @Column(name = "last_modified_at", updatable = false)
    @LastModifiedDate
    public Date lastModifiedAt;

    public User() {}

    public User(UUID uuid, String name, @Min(0) int age, Position position, String lastName, Date createdAt, Date lastModifiedAt) {
        this.uuid = uuid;
        this.name = name;
        this.age = age;
        this.position = position;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
