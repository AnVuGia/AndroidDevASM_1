package com.example.asm1.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asm1.Interface.IIngredient;
import com.example.asm1.R;
import com.example.asm1.model.ButtonsChangeHelper;
import com.example.asm1.model.CreateCardViewHelper;
import com.example.asm1.model.ImageHelper;
import com.example.asm1.model.Ingredient.withGuideAndAmount;
import com.example.asm1.model.Recipe;
import com.example.asm1.model.UserSingleton;

import java.io.Serializable;
import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity {
    private static final String TAG = "AddRecipeActivity";
    private final ArrayList<IIngredient> ingredients = UserSingleton.getInstance().getIngredients();
    private ArrayList<withGuideAndAmount> recipeIngredients = new ArrayList<>();
    private final ArrayList<String> ingredientNames = new ArrayList<>();
    private Spinner ingredientSpinner;
    private LinearLayout recipe_ingredient_container;
    private ButtonsChangeHelper buttonsChangeHelper;
    private EditText recipe_name_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: " + "in add recipe");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Add Recipe");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //populate data
        ingredientSpinner = findViewById(R.id.ingredient_spinner);
        Button toIngredientForm = findViewById(R.id.to_ingerdient_form);
        recipe_ingredient_container = findViewById(R.id.recipe_ingredient_container);
        recipe_name_input = findViewById(R.id.recipe_name_input);
        renderSpinner();
        //render buttons
        ImageButton breakfast_button = findViewById(R.id.breakfast_btn);
        ImageButton lunch_button = findViewById(R.id.lunch_btn);
        ImageButton dinner_button = findViewById(R.id.dinner_btn);
        buttonsChangeHelper = new ButtonsChangeHelper(breakfast_button, lunch_button, dinner_button);
        Button addRecipe = findViewById(R.id.add_recipe_button);
        //set on click
        addRecipe.setOnClickListener(v -> onAddRecipe());
        //map ingredient to spinner
        for(IIngredient ingredient : ingredients) {
            ingredientNames.add(ingredient.getName());
        }
        toIngredientForm.setOnClickListener(v -> setToIngredientForm());
        //check if there is data
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
        someActivityResultLauncher.launch(intent);
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        recipeIngredients = (ArrayList<withGuideAndAmount>) data.getSerializableExtra("recipeIngredients");
                        renderIngredients();
                    }
                }
            });


    private void renderIngredients() {
        recipe_ingredient_container.removeAllViews();
        for (withGuideAndAmount ingredient : recipeIngredients) {
            Log.d(TAG, "in render Ingredient: " + ingredient.getName());
            View ingredientView = CreateCardViewHelper.createRecipeIngredientCardView(this, ingredient);
            ingredientView.findViewById(R.id.ingredient_card_remove).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRemoveIngredient(ingredient, ingredientView);
                }
            });
            recipe_ingredient_container.addView(ingredientView);
        }
    }
    private void onRemoveIngredient(withGuideAndAmount ingredient, View view) {
        recipeIngredients.remove(ingredient);
        recipe_ingredient_container.removeView(view);
    }
    private void onAddRecipe(){
        int trueIndex = buttonsChangeHelper.getClickedIndex();
        if (!error_noti()) {
            return;
        }
        String type = "";
        switch (trueIndex) {
            case 0:
                type = "breakfast";
                break;
            case 1:
                type = "lunch";
                break;
            case 2:
                type = "dinner";
                break;
        }
        String name = recipe_name_input.getText().toString();
        Recipe recipe = new Recipe(name,  type);
        for (withGuideAndAmount ingredient : recipeIngredients) {
            recipe.addIngredient(ingredient);
        }
        UserSingleton.getInstance().addRecipe(recipe);
        finish();
        Log.d(TAG, "onAddRecipe: " + trueIndex);
    }
    private boolean error_noti() {
        if(buttonsChangeHelper.getClickedIndex() == -1) {
            recipe_name_input.setError("Please choose a type");
            return false;
        }
        if (recipe_name_input.getText().toString().isEmpty()) {
            recipe_name_input.setError("Please enter recipe name");
            return false;
        }
        return true;
    }
    private void renderSpinner() {
        String[] ingredientNamesArray = ingredientNames.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ingredientNamesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ingredientSpinner.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}