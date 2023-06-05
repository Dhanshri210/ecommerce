package com.bikkadit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="ELECTRONICS")
@Entity
public class User {
    @Id
    private String userId;
    @Column(name="USER_NAME")
    private String userName;
    @Column(name="USER_ADD",length = 500)
    private String userAdd;
    @Column(name="USER_EMAIL",unique = true)
    private String userEmail;
    @Column(name="USER_ABOUT",length = 20000)
    private String userAbout;
    @Column(name="USER_PASSWORD",length = 20)
    private String userPassword;
    @Column(name="GENDER")
    private String gender;
    @Column(name="IMAGE_NAME")
    private String imageName;
}
