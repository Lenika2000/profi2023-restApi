package com.example.profi.model;

public class ShortInfAboutCategory {
    private Long id;
    private String name;
    private String description;
    private Long parent_id;

    public ShortInfAboutCategory(Long id, String name, String description, Long parent_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parent_id = parent_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
}
