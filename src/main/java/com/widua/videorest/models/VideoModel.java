package com.widua.videorest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Document(collection = "spring-projects.Videos")
public class VideoModel {

    @Id
    private String id;
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/zagreb")
    @Past(message = "Date must be from past")
    private LocalDate production;
    @NotNull
    @Max(value = 5, message = "Maximum rating value is 5")
    @Min(value = 0, message = "Minimum rating value is 0")
    private int rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getProduction() {
        return production;
    }

    public void setProduction(LocalDate production) {
        this.production = production;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public VideoModel() {
    }

    public VideoModel(String title, @Past LocalDate production, @Max(5) @Min(0) @NotNull int rating) {
        this.title = title;
        this.production = production;
        this.rating = rating;
    }
}
