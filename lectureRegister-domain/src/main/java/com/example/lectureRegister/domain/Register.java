package com.example.lectureRegister.domain;

import javax.persistence.*;

@Entity
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {

        return "<a href='./" + this.getClass().getSimpleName().toLowerCase() + "'" + ">"
                + this.getClass().getSimpleName() + "</a>";
    }

}
