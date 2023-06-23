package com.bikkadit.ecommerce.entity;

import com.bikkadit.ecommerce.payload.BaseEntityDto;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name="CAT_TITLE",length = 50,nullable = false)
    private String categoryTitle;

    @Column(name="CAT_DESCRIPT",length = 50)
    private  String categoryDescription;

    private String coverImage;

}
