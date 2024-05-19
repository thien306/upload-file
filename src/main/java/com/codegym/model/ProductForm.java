package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {

    private int id;

    private String name;

    private String information;

    private MultipartFile image;

    public ProductForm() {

    }

    public ProductForm(int id, String name, String information, MultipartFile image) {
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
