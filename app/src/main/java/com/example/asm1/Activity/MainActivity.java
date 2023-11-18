package com.example.asm1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asm1.Interface.IIngredient;
import com.example.asm1.Interface.IObserverUser;
import com.example.asm1.R;
import com.example.asm1.model.CreateCardViewHelper;
import com.example.asm1.model.ImageHelper;
import com.example.asm1.model.Ingredient.Ingredient;
import com.example.asm1.model.Ingredient.withGuideAndAmount;
import com.example.asm1.model.Recipe;
import com.example.asm1.model.UserSingleton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IObserverUser {

    private static final String TAG = "MainActivity";
    private  ArrayList<Recipe> recipes ;
    private ArrayList<IIngredient> ingredients ;
    private LinearLayout recipe_container;
    private LinearLayout ingredient_container;
    private Button toRecipesList;
    private Button toIngredientsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        recipes = UserSingleton.getInstance().getRecipes();
        ingredients = UserSingleton.getInstance().getIngredients();
        toRecipesList = findViewById(R.id.to_recipe);
        toIngredientsList = findViewById(R.id.to_ingredient);
        toRecipesList.setBackgroundColor(getResources().getColor(R.color.add_recipe_button));
        toIngredientsList.setBackgroundColor(getResources().getColor(R.color.add_ingredient_button));
        UserSingleton.getInstance().register(this);
        //add some data
        Ingredient ingredient1 = new Ingredient("Bread", 100, "Bread","flour");
        Ingredient ingredient2 = new Ingredient("Egg", 50, "Egg", "meat");
        Ingredient ingredient4 = new Ingredient("Tomato", 50, "Tomato", "vegetable");

        withGuideAndAmount ingredient_with_guide_and_amount1 = new withGuideAndAmount(ingredient1, "g", 100);
        withGuideAndAmount ingredient_with_guide_and_amount2 = new withGuideAndAmount(ingredient2, "g", 50);
        withGuideAndAmount ingredient_with_guide_and_amount4 = new withGuideAndAmount(ingredient4, "g", 50);

        Recipe recipe1 = new Recipe("Bread with Egg", "breakfast");
        recipe1.addIngredient(ingredient_with_guide_and_amount1);
        recipe1.addIngredient(ingredient_with_guide_and_amount2);
        recipe1.addIngredient(ingredient_with_guide_and_amount4);

        recipes.add(recipe1);
        recipes.add(recipe1);
        recipes.add(recipe1);
        recipes.add(recipe1);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient4);
        recipe_container = findViewById(R.id.main_recipe_container);
        ingredient_container = findViewById(R.id.main_ingredient_container);
        renderView(recipes, ingredients);
        //Button to ingredient list
        toIngredientsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IngredientListActivity.class);
                startActivity(intent);
            }
        });
        //Button to recipe list
        toRecipesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void update() {
        recipes = UserSingleton.getInstance().getRecipes();
        ingredients = UserSingleton.getInstance().getIngredients();
        renderView(recipes, ingredients);
    }
    private void renderView(ArrayList<Recipe> recipes, ArrayList<IIngredient> ingredients) {
        renderRecipeViews(recipes);
        renderIngredientViews(ingredients);
    }

    private void renderRecipeViews(ArrayList<Recipe> recipes) {
        recipe_container.removeAllViews();
        for (Recipe recipe : recipes) {
            View recipeView = CreateCardViewHelper.createBaseRecipeCardView(this,recipe);
            recipe_container.addView(recipeView);
        }
    }

    private void renderIngredientViews(ArrayList<IIngredient> ingredients) {
        ingredient_container.removeAllViews();
        for (IIngredient ingredient : ingredients) {
            View ingredientView = CreateCardViewHelper.createBaseIngredientCardView(this,ingredient);
            ingredient_container.addView(ingredientView);
        }
    }

}