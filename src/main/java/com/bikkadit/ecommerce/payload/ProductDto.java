package com.bikkadit.ecommerce.payload;

import com.bikkadit.ecommerce.utils.ImageNameValid;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {


    private String productId;

    @NotBlank
    @Size(min=5,max=20,message ="Title Should Be Max Twenty Charachters")
    private String title;

    @NotBlank
    @Size(min=5,max=1000,message ="Product Description ")
    private String description;

    @NotBlank
    private Integer price;

    @NotBlank(message = "Product Is Already in Discounted")
    private Integer discountPrice;

    @NotBlank
    private Integer quantity;

    @NotBlank(message = "All Size Available")
    private String size;

    @NotBlank
    private boolean live;

    @NotBlank
    private boolean stock;

    @ImageNameValid
    private String productImage;

    @NotBlank
    private String productColour;

}

