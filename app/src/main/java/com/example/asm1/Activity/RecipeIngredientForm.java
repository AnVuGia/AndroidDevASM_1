package com.example.asm1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asm1.Interface.IIngredient;
import com.example.asm1.R;
import com.example.asm1.model.ImageHelper;
import com.example.asm1.model.Ingredient.withGuideAndAmount;

import java.util.ArrayList;

public class RecipeIngredientForm extends AppCompatActivity {
   private IIngredient ingredient;
   private ArrayList<IIngredient> ingredients;
   private TextView ingredientName, ingredientCalories;
   private ImageView ingredientImg;
   private EditText ingredientAmount, ingredientGuide;
   private Button addIngredient;
   private static final String TAG = "RecipeIngredientForm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredient_form);
        Intent receivedIntent = getIntent();
        if (receivedIntent != null) {
            ingredient = (IIngredient) receivedIntent.getSerializableExtra("ingredient");
            ingredients = (ArrayList<IIngredient>) receivedIntent.getSerializableExtra("recipeIngredients");
        }
        Log.d(TAG, "onCreate: " + ingredient.getName());
        ingredientName = findViewById(R.id.ingredient_form_name);
        ingredientCalories = findViewById(R.id.ingredient_form_calories);
        ingredientImg = findViewById(R.id.ingredient_form_image);
        ingredientAmount = findViewById(R.id.ingredient_form_ammount);
        ingredientGuide = findViewById(R.id.ingredient_form_guide);

        ingredientName.setText(ingredient.getName());
        ingredientCalories.setText(String.valueOf(ingredient.getCalories()));
        ImageHelper.setImageToView(ingredient.getImg(), ingredientImg);

        addIngredient = findViewById(R.id.form_add_ingredient);
        addIngredient.setOnClickListener(v -> setAddIngredient());
    }

    private void setAddIngredient() {
        Log.d(TAG, "setAddIngredient: ");
        if (error()){
            Intent intent = new Intent(this, AddRecipeActivity.class);
            withGuideAndAmount temp = new withGuideAndAmount(ingredient, ingredientGuide.getText().toString(), Integer.parseInt(ingredientAmount.getText().toString()));
            ingredients.add(temp);
            intent.putExtra("recipeIngredients", ingredients);
            startActivity(intent);
            finish();
        }
    }
    private boolean error() {
     if (ingredientAmount.getText().toString().isEmpty() || ingredientGuide.getText().toString().isEmpty()) {
         ingredientAmount.setError("Please fill in this field");
         ingredientGuide.setError("Please fill in this field");
            return false;
     }
        return true;
    }

}