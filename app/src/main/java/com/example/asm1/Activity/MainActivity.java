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
import com.example.asm1.model.ImageHelper;
import com.example.asm1.model.Ingredient.Ingredient;
import com.example.asm1.model.Recipe;
import com.example.asm1.model.UserSingleton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IObserverUser {

    private static final String TAG = "MainActivity";
    private  ArrayList<Recipe> recipes = UserSingleton.getInstance().getRecipes();
    private ArrayList<IIngredient> ingredients = UserSingleton.getInstance().getIngredients();
    private LinearLayout recipe_container;
    private LinearLayout ingredient_container;
    private Button toRecipesList;
    private Button toIngredientsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: " + recipes.size());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        toRecipesList = findViewById(R.id.to_recipe);
        toIngredientsList = findViewById(R.id.to_ingredient);
        toRecipesList.setBackgroundColor(getResources().getColor(R.color.add_recipe_button));
        toIngredientsList.setBackgroundColor(getResources().getColor(R.color.add_ingredient_button));
        UserSingleton.getInstance().register(this);
        //add some data
        Ingredient ingredient1 = new Ingredient("Bread", 100, "Bread","flour");
        Ingredient ingredient2 = new Ingredient("Egg", 50, "Egg", "meat");
        Ingredient ingredient4 = new Ingredient("Tomato", 50, "Tomato", "vegetable");

        Recipe recipe1 = new Recipe("Bread with Egg", "Bread with Egg", 150, "breakfast");
        recipe1.addIngredient(ingredient1);
        recipe1.addIngredient(ingredient2);
        recipe1.addIngredient(ingredient4);

        recipes.add(recipe1);
        recipes.add(recipe1);
        recipes.add(recipe1);
        recipes.add(recipe1);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient4);
        recipe_container = findViewById(R.id.recipe_container);
        ingredient_container = findViewById(R.id.ingredient_container);
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
                Intent intent = new Intent(MainActivity.this, AddRecipeActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void update() {
        Log.d(TAG, "update: ");
        recipes = UserSingleton.getInstance().getRecipes();
        ingredients = UserSingleton.getInstance().getIngredients();
        renderView(recipes, ingredients);
    }
    private void renderView(ArrayList<Recipe> recipes, ArrayList<IIngredient> ingredients){
        recipe_container.removeAllViews();
        ingredient_container.removeAllViews();
        for (Recipe recipe : recipes) {
            View recipeView = createRecipeCardView(recipe);
            recipe_container.addView(recipeView);
        }
        for (IIngredient ingredient : ingredients) {
            View ingredientView = createIngredientCardView(ingredient);
            ingredient_container.addView(ingredientView);
        }

    }
    private View createRecipeCardView(Recipe recipe) {
        View recipeView = getLayoutInflater().inflate(R.layout.card, null);
        TextView recipeName = recipeView.findViewById(R.id.card_name);
        TextView recipeDescription = recipeView.findViewById(R.id.card_desc);
        TextView recipeCalories = recipeView.findViewById(R.id.card_calories);
        ImageView recipeImage = recipeView.findViewById(R.id.card_image);
        ImageHelper.setImageToView(recipe.getType(), recipeImage);
        recipeName.setText(recipe.getName());
        recipeDescription.setText(recipe.getDescription());
        recipeCalories.setText(String.valueOf(recipe.getCalories()));
        return recipeView;
    }
    private View createIngredientCardView(IIngredient ingredient) {
        View ingredientView = getLayoutInflater().inflate(R.layout.card, null);
        TextView ingredientName = ingredientView.findViewById(R.id.card_name);
        TextView ingredientDescription = ingredientView.findViewById(R.id.card_desc);
        TextView ingredientCalories = ingredientView.findViewById(R.id.card_calories);
        ImageHelper.setImageToView(ingredient.getImg(), ingredientView.findViewById(R.id.card_image));
        ingredientName.setText(ingredient.getName());
        ingredientDescription.setText(ingredient.getDescription());
        ingredientCalories.setText(String.valueOf(ingredient.getCalories()));
        return ingredientView;
    }
}