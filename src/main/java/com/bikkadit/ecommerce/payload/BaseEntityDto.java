package com.bikkadit.ecommerce.payload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntityDto implements Serializable {

    @NotEmpty
    @Size(min=5 ,max=14,message = "Write Your First & Last Name")
    private String createdBy;

    private boolean isActive=true;

    private LocalDateTime addedDateBy;

    @NotEmpty
    @Size(min=5 ,max = 20,message = "Update Details")
    private String updatedBy;

}


