package com.bikkadit.entity;

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
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@MappedSuperclass
@Embeddable
public class BaseEntity {

    @Column(name="IS_ACTIVE")
    private String isActive;

    @Column(name="CREATED_BY")
    @CreatedBy
    private String createdBy;

    @Column(name="CREATE_DATE",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name="MODIFIED_BY")
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name="UPDATE_DATE",updatable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedOn;
}
