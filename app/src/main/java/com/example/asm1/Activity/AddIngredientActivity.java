package com.example.asm1.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.asm1.R;
import com.example.asm1.model.ButtonsChangeHelper;
import com.example.asm1.model.Ingredient.Ingredient;
import com.example.asm1.model.UserSingleton;

public class AddIngredientActivity extends AppCompatActivity {
    ImageButton flour_button , meat_button, vegetable_button;

    EditText ingredient_name, ingredient_calories, ingredient_description;
    Button add_ingredient;
    ButtonsChangeHelper buttonsChangeHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Add Ingredient");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //set up button
        flour_button = findViewById(R.id.flour_button);
        meat_button = findViewById(R.id.meat_button);
        vegetable_button = findViewById(R.id.vegetable_button);
        buttonsChangeHelper = new ButtonsChangeHelper(flour_button, meat_button, vegetable_button);

        //set up edit text
        ingredient_name = findViewById(R.id.add_ingri_name);
        ingredient_calories = findViewById(R.id.add_ingre_calo);
        ingredient_description = findViewById(R.id.add_ingri_desc);
        add_ingredient = findViewById(R.id.add_ingredient_button);
        add_ingredient.setOnClickListener(v -> addIngredient());
    }
//    private void setButtonColor(ImageButton button, int color) {
//        button.setBackgroundColor(getResources().getColor(color));
//    }
//    private void setButtonColor(ImageButton button, Boolean isClicked) {
//        if (isClicked) {
//            setButtonColor(button, R.color.add_ingredient_button);
//        } else {
//            setButtonColor(button, R.color.white);
//        }
//    }
//    private void onClickButton(ImageButton button, int index) {
//        for(int i = 0; i < 3; i++) {
//            isClicked[i] = false;
//        }
//        isClicked[index] = !isClicked[index];
//        renderButton();
//    }
//    private void renderButton() {
//        setButtonColor(flour_button, isClicked[0]);
//        setButtonColor(meat_button, isClicked[1]);
//        setButtonColor(vegetable_button, isClicked[2]);
//    }
    private void addIngredient() {
        String name = ingredient_name.getText().toString();
        String calories = ingredient_calories.getText().toString();
        String description = ingredient_description.getText().toString();
        if (name.isEmpty() || calories.isEmpty() || description.isEmpty()) {
            error_noti();
            return;
        }

        int index = buttonsChangeHelper.getClickedIndex();
        String temp = "";
        switch (index) {
            case 0:
                temp = "flour";
                break;
            case 1:
                temp = "meat";
                break;
            case 2:
                temp = "vegetable";
                break;
        }

        try {
            Ingredient ingredient = new Ingredient(name, Integer.parseInt(calories), description, temp);
            UserSingleton.getInstance().addIngredient(ingredient);
            finish();
        } catch (Exception e) {
            error_noti();
        }
    }
    private void error_noti() {
        if (ingredient_name.getText().toString().isEmpty()) {
            ingredient_name.setError("Please enter ingredient name");
        }
        if (ingredient_calories.getText().toString().isEmpty()) {
            ingredient_calories.setError("Please enter ingredient calories");
        }
        if (ingredient_description.getText().toString().isEmpty()) {
            ingredient_description.setError("Please enter ingredient description");
        }
        if (buttonsChangeHelper.getClickedIndex() == -1) {
            ingredient_name.setError("Please choose ingredient image");
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}