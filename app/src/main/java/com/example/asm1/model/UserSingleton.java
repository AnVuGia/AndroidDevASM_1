package com.example.asm1.model;

import com.example.asm1.Interface.IIngredient;
import com.example.asm1.Interface.IObservableUser;
import com.example.asm1.Interface.IObserverUser;
import com.example.asm1.model.Ingredient.Ingredient;

import java.util.ArrayList;

public class UserSingleton implements IObservableUser {
    private static UserSingleton instance;
    ArrayList<Recipe> recipes = new ArrayList<>();
    ArrayList<IIngredient> ingredients = new ArrayList<>();
    ArrayList<IObserverUser> observers = new ArrayList<>();
    private UserSingleton() {
    }
    public static UserSingleton getInstance() {
        if (instance == null) {
            instance = new UserSingleton();
        }
        return instance;
    }
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
    public ArrayList<IIngredient> getIngredients() {
        return ingredients;
    }
    public void addIngredient(IIngredient ingredient) {
        ingredients.add(ingredient);
        notifyObservers();
    }

    @Override
    public void register(IObserverUser observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(IObserverUser observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserverUser observer : observers) {
            observer.update();
        }
    }
}
