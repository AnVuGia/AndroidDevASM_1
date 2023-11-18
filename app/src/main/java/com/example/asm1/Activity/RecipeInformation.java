package com.example.asm1.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asm1.Interface.IIngredient;
import com.example.asm1.R;
import com.example.asm1.model.ImageHelper;
import com.example.asm1.model.Ingredient.withGuideAndAmount;
import com.example.asm1.model.Recipe;

public class RecipeInformation extends AppCompatActivity {

    private static final String TAG = "RecipeInformation";
    private Recipe recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_information);
        ActionBar actionBar = getSupportActionBar();
        recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        if (actionBar != null) {
            actionBar.setTitle(recipe.getName() + " Information");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        renderInformation();
    }
    private void renderInformation() {
        ImageView view = findViewById(R.id.recipe_information_img);
        ImageHelper.setImageToView(recipe.getName(), view);

        TextView name = findViewById(R.id.recipe_information_name);
        name.setText(recipe.getName());

        TextView calories = findViewById(R.id.recipe_information_calories);
        calories.setText(recipe.getCalories());
        renderIngredients();
    }
    private void renderIngredients() {
        LinearLayout ingredient_container = findViewById(R.id.recipe_ingredient_container);
        ingredient_container.removeAllViews();
        for (IIngredient ingredient : recipe.getIngredients()) {
            withGuideAndAmount temp = (withGuideAndAmount) ingredient;
            ingredient_container.addView(createIngredientView(temp));
        }
    }
    private View createIngredientView(withGuideAndAmount ingredient) {
        View ingredientView = getLayoutInflater().inflate(R.layout.recipe_ingredient_card, null);
        TextView ingredientName = ingredientView.findViewById(R.id.ingredient_card_name);
        TextView ingredientGuide = ingredientView.findViewById(R.id.ingredient_card_guide);
        TextView ingredientAmount = ingredientView.findViewById(R.id.ingredient_card_amount);
        Button removeIngredient = ingredientView.findViewById(R.id.ingredient_card_remove);

        ingredientName.setText(ingredient.getName());
        ingredientGuide.setText(ingredient.getGuide());
        ingredientAmount.setText(ingredient.getAmount());
        removeIngredient.setVisibility(View.GONE);
        return ingredientView;
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}