package com.pieterjd.devoxx26.clevercloud;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PissedOffSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mood;

    protected PissedOffSelection() {
    }

    public PissedOffSelection(String mood) {
        this.mood = mood;
    }

    public Long getId() {
        return id;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
