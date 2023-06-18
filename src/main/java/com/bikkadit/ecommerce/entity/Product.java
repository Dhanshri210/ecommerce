package com.bikkadit.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Products")
public class Product {

    @Id
    private String productId;

    @Column(name="TITLE",length=20)
    private String title;

    @Column(name="DESCRIPTION",length=10000)
    private String description;

    @Column(name="PRICE")
    private Integer price;

    @Column(name="DISCOUNT_PRICE")
    private Integer discountPrice;

    @Column(name="QUANTITY")
    private Integer quantity;

    @Column(name= "SIZE")
    private Integer size;

    @Column(name="LIVE")
    private boolean live;

    @Column(name="STOCK")
    private boolean stock;

}
