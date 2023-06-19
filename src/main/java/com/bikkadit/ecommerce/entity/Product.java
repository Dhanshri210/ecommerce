package com.bikkadit.ecommerce.entity;

import jakarta.persistence.*;
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
    private String size;

    @Column(name="LIVE")
    private boolean live;

    @Column(name="STOCK")
    private boolean stock;

    @Column(name="PRODUCT_IMAGE")
    private String productImage;

    @Column(name="PRODUCT_COLOUR")
    private String productColour;

}
