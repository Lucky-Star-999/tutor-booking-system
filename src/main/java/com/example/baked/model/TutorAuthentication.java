package com.example.baked.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TutorAuthentication")
public class TutorAuthentication {
    @Id
    private String id;
    private String tutor_id;
    private String username;
    private String password;



    public TutorAuthentication() {
    }


    public TutorAuthentication(String tutor_id, String username, String password) {
        this.tutor_id = tutor_id;
        this.username = username;
        this.password = password;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTutor_id() {
        return this.tutor_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
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
            ", tutor_id='" + getTutor_id() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }


}
