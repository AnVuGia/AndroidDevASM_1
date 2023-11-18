package com.example.asm1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asm1.Interface.IIngredient;
import com.example.asm1.R;
import com.example.asm1.model.ButtonsChangeHelper;
import com.example.asm1.model.ImageHelper;
import com.example.asm1.model.Ingredient.withGuideAndAmount;
import com.example.asm1.model.Recipe;
import com.example.asm1.model.UserSingleton;

import java.io.Serializable;
import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity {
    private static final String TAG = "AddRecipeActivity";
    private ArrayList<Recipe> recipes = UserSingleton.getInstance().getRecipes();
    private ArrayList<IIngredient> ingredients = UserSingleton.getInstance().getIngredients();
    private ArrayList<withGuideAndAmount> recipeIngredients = new ArrayList<>();
    private ArrayList<String> ingredientNames = new ArrayList<>();
    private Spinner ingredientSpinner;
    private Button toIngredientForm, addRecipe;
    private LinearLayout recipe_ingredient_container;
    private ImageButton breakfast_button, lunch_button, dinner_button;
    private ButtonsChangeHelper buttonsChangeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        ingredientSpinner = findViewById(R.id.ingredient_spinner);
        toIngredientForm = findViewById(R.id.to_ingerdient_form);
        recipe_ingredient_container = findViewById(R.id.recipe_ingredient_container);
        breakfast_button = findViewById(R.id.breakfast_btn);
        lunch_button = findViewById(R.id.lunch_btn);
        dinner_button = findViewById(R.id.dinner_btn);
        buttonsChangeHelper = new ButtonsChangeHelper(breakfast_button, lunch_button, dinner_button);
        addRecipe = findViewById(R.id.add_recipe_button);
        addRecipe.setOnClickListener(v -> onAddRecipe());
        for(IIngredient ingredient : ingredients) {
            ingredientNames.add(ingredient.getName());
        }
        String[] ingredientNamesArray = ingredientNames.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ingredientNamesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ingredientSpinner.setAdapter(adapter);
        toIngredientForm.setOnClickListener(v -> setToIngredientForm());
        Intent receivedIntent = getIntent();
        if (receivedIntent.getExtras() != null) {
            recipeIngredients = (ArrayList<withGuideAndAmount>) receivedIntent.getSerializableExtra("recipeIngredients");
            renderIngredients();
        }
    }
    private void setToIngredientForm() {
        Log.d(TAG, "onAddIngredient: ");
        Intent intent = new Intent(this, RecipeIngredientForm.class);
        IIngredient temp = ingredients.get(ingredientSpinner.getSelectedItemPosition());
        intent.putExtra("ingredient", (Serializable) temp);
        intent.putExtra("recipeIngredients", recipeIngredients);
        startActivity(intent);
    }
    private View createIngredientCardView(withGuideAndAmount ingredient) {
        View ingredientView = getLayoutInflater().inflate(R.layout.recipe_ingredient_card, null);
        TextView ingredientName = ingredientView.findViewById(R.id.ingredient_card_name);
        TextView ingredientGuide = ingredientView.findViewById(R.id.ingredient_card_guide);
        TextView ingredientAmount = ingredientView.findViewById(R.id.ingredient_card_amount);
        ImageView ingredientImage = ingredientView.findViewById(R.id.ingredient_card_img);
        ingredientName.setText(ingredient.getName());
        ingredientGuide.setText(ingredient.getGuide());
        ingredientAmount.setText(String.valueOf(ingredient.getAmount()));
        ImageHelper.setImageToView(ingredient.getImg(), ingredientImage);
        ingredientView.findViewById(R.id.ingredient_card_remove).setOnClickListener(v -> onRemoveIngredient(ingredient, ingredientView));
        return ingredientView;
    }
    private void renderIngredients() {
        recipe_ingredient_container.removeAllViews();
        for (withGuideAndAmount ingredient : recipeIngredients) {
            Log.d(TAG, "in render Ingredient: " + ingredient.getName());
            View ingredientView = createIngredientCardView(ingredient);
            recipe_ingredient_container.addView(ingredientView);
        }
    }
    private void onRemoveIngredient(withGuideAndAmount ingredient, View view) {
        recipeIngredients.remove(ingredient);
        recipe_ingredient_container.removeView(view);
    }
    private void onAddRecipe(){
        int trueIndex = buttonsChangeHelper.getClickedIndex();
        if(trueIndex == -1) {
            error_noti();
            return;
        }
        Log.d(TAG, "onAddRecipe: " + trueIndex);
    }
    private void error_noti() {
        Log.d(TAG, "error_noti: ");
    }
}