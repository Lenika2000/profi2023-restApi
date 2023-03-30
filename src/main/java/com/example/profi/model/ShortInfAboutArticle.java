package com.example.profi.model;

public class ShortInfAboutArticle {
    private Long id;
    private String name;
    private String description;
    private Long category_id;

    public ShortInfAboutArticle(Long id, String name, String description, Long category_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category_id = category_id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
}
