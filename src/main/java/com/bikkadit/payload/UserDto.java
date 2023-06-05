package com.bikkadit.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;



    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class UserDto {

        @NotEmpty
        private String userId;

        @Size(min = 5, max = 20, message = "First letter should be capital")
        private String userName;

        @Size(min = 15, max = 30, message = "Fill Proper Address")
        private String userAdd;

        @Email(message = "Invalid Email Id")
        private String userEmail;

        @NotEmpty(message = "First letter Should Be Capital")
        private String userPassword;

        @NotEmpty
        private String Gender;

        @Size(min = 100, max = 10000, message = "Write About Yourself")
        private String userAbout;

        private String imageName;

    }

//pattrn
//@custom

