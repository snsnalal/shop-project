package com.shop.projectlion.domain.base;


import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseEntity extends BaseTimeEntity{

    @CreatedBy
    @Column(updatable = false, nullable = false)
    String createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    String modifiedBy;

}
