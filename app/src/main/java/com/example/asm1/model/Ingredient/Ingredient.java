package com.example.asm1.model.Ingredient;

import com.example.asm1.Interface.IIngredient;

import java.io.Serializable;

public class Ingredient implements IIngredient, Serializable {
    private String name;
    private int calories;
    private String description;
    private String img;
    public Ingredient(String name, int calories, String description, String img) {
        this.name = name;
        this.calories = calories;
        this.description = description;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
