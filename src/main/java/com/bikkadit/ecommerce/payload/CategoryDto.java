package com.bikkadit.ecommerce.payload;

import com.bikkadit.ecommerce.utils.ImageNameValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private String categoryId;

    @NotBlank
    @Size(min=5,max=15,message = "Title Name Start With Capital Letters")
    private String categoryTitle;

    @NotBlank
    @Size(min = 5,max=100,message = "Write Few Lines Your Titles")
    private String categoryDescription;

    @ImageNameValid(message="Image Name is Default")
    private String coverImage;
}
