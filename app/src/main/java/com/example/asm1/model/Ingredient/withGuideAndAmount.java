package com.example.asm1.model.Ingredient;

import com.example.asm1.Interface.IIngredient;

import java.io.Serializable;

public class withGuideAndAmount implements IIngredient, Serializable {
    private IIngredient ingredient;
    private String guide;
    private int amount;
    public withGuideAndAmount(IIngredient ingredient, String guide, int amount) {
        this.ingredient = ingredient;
        this.guide = guide;
        this.amount = amount;
    }
    @Override
    public String getName() {
        return ingredient.getName();
    }

    @Override
    public String getDescription() {
        return ingredient.getDescription();
    }

    @Override
    public int getCalories() {
        return ingredient.getCalories();
    }

    @Override
    public String getImg() {
        return ingredient.getImg();
    }

    public String getGuide() {
        return guide;
    }


    public int getAmount() {
        return amount;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
