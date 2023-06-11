package com.bikkadit.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Categories")
public class Category {

    @Id
    @Column(name="CAT_ID")
    private String categoryId;

    @Column(name="CAT_TITLE",length = 50,nullable = false)
    private String categoryTitle;

    @Column(name="CAT_DESCRIPT",length = 50)
    private  String categoryDescription;

    private String coverImage;

}
