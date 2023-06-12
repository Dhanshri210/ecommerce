package com.bikkadit.ecommerce.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    @NotBlank
    @Size(min=5,max=15,message = "Title Name Start With Capital Letters")
    private String categoryTitle;

    @NotBlank
    @Size(min = 5,max=100,message = "Write Few Lines Your Titles")
    private String categoryDescription;

    @NotBlank(message = "Cover Image Required")
    private String coverImage;
}
