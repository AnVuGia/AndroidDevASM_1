package com.example.asm1.model;

import com.example.asm1.Interface.IIngredient;
import com.example.asm1.model.Ingredient.Ingredient;

import java.util.ArrayList;

public class Recipe {
    private ArrayList<IIngredient> ingredients = new ArrayList<>();
    private String name;
    private String description;
    private int calories;
    private String type;
    public Recipe(String name, String description, int calories, String type) {
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
    //Getter and Setter
    public ArrayList<IIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<IIngredient> ingredients) {
        this.ingredients = ingredients;
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

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
