package com.example.asm1.model;

import com.example.asm1.Interface.IIngredient;
import com.example.asm1.model.Ingredient.Ingredient;
import com.example.asm1.model.Ingredient.withGuideAndAmount;

import java.util.ArrayList;

public class Recipe {
    private ArrayList<IIngredient> ingredients = new ArrayList<>();
    private String name;
    private String type;
    public Recipe(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addIngredient(IIngredient ingredient) {
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
                String description = "";
                for(IIngredient ingredient : ingredients) {
                    withGuideAndAmount temp = (withGuideAndAmount) ingredient;
                    description += temp.getName() + " " + temp.getAmount() + " " + temp.getGuide() + "\n";
                }
                return description;
    }


    public int getCalories() {
            int totalCalories = 0;
            for(IIngredient ingredient : ingredients) {
                withGuideAndAmount temp = (withGuideAndAmount) ingredient;
                totalCalories += temp.getCalories() *  temp.getAmount() / 100;
            }
            return totalCalories;
    }


}
