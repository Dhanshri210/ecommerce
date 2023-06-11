package com.bikkadit.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@MappedSuperclass
@Embeddable
public class BaseEntity implements Serializable {

    @Column(name="IS_ACTIVE")
    private boolean isActive=true;

    @Column(name="CREATED_BY")
    @CreatedBy
    private String createdBy;

    @Column(name="ADDED_DATE",updatable = false)
    @CreationTimestamp
    private LocalDateTime addedDateBy;

    @Column(name="UPDATE_DATE",updatable = false)
    private String updatedBy;
}
