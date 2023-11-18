package com.example.asm1.Interface;

import java.io.Serializable;

public interface IIngredient extends Serializable {
    public String getName();
    public String getDescription();
    public int getCalories();
    public String getImg();
}
