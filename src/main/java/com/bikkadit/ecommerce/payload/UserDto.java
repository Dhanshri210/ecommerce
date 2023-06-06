package com.bikkadit.ecommerce.payload;
import com.bikkadit.ecommerce.entity.BaseEntity;
import jakarta.validation.constraints.*;
import lombok.*;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String userId;

    @Size(min = 5, max = 20, message = "First letter should be capital")
    @NotBlank
    private String userName;

    @Size(min = 5, max = 30, message = "Fill Proper Address")
    private String userAdd;

    @Email(message = "Invalid Email Id")
    private String userEmail;

     @NotEmpty(message = "First letter Should Be Capital")
     @Pattern(regexp = "[0-9][@#$%][a-z][A-Z]")
    private String userPassword;

    @NotBlank
    private String gender;

    @Size(min = 10, max = 50, message = "Write About Yourself")
    @NotEmpty
    private String userAbout;

    private String imageName;

}

//pattrn
//@custom