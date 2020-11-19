package com.project.aggregator.api.useraccountservice.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50, name = "name")
    private String name;

    @Column(nullable = false, length = 50, name = "surname")
    private String surname;

    @Column(nullable = false, length = 120, name = "email", unique = true)
    private String email;

    @Column(nullable = false, length = 50, name = "password")
    private String encryptedPassword;

    @Column(nullable = false, length = 50, name = "userID",unique = true)
    private String userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
