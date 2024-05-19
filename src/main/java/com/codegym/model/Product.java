package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class Product {
    private int id;

    private String name;

    private String information;

    private String image;

    public Product() {

    }

    public Product(int id, String name, String information, String image) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
