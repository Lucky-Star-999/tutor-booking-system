package com.example.baked.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "StudentAuthentication")
public class StudentAuthentication {
    @Id
    private String id;
    private String student_id;
    private String username;
    private String password;



    public StudentAuthentication() {
    }


    public StudentAuthentication(String student_id, String username, String password) {
        this.student_id = student_id;
        this.username = username;
        this.password = password;
    }



    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_id() {
        return this.student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", student_id='" + getStudent_id() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

}
