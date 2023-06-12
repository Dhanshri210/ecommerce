package com.bikkadit.ecommerce.payload;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntityDto implements Serializable {

    @Size(min=5 ,max=14,message = "Write Your First & Last Name")
    private String createdBy;

    private boolean isActive=true;

    private LocalDateTime addedDateBy;

    @Size(min=5 ,max = 20,message = "Update Details")
    private String updatedBy;

}


