package com.example.asm1.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.asm1.model.UserSingleton;

import java.util.ArrayList;

public class IngredientListActivity extends AppCompatActivity implements IObserverUser {
    private static final String TAG = "IngredientListActivity";
    private ArrayList<IIngredient> ingredients = UserSingleton.getInstance().getIngredients();
    private LinearLayout ingredient_list_container;
    private Button toAddIngredient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);
        UserSingleton.getInstance().register(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Ingredients");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ingredient_list_container = findViewById(R.id.ingredient_list);
        toAddIngredient = findViewById(R.id.add_ingredient);
        toAddIngredient.setBackgroundColor(getResources().getColor(R.color.add_ingredient_button));
        toAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(IngredientListActivity.this, AddIngredientActivity.class));
            }
        });
        renderList(ingredients);
    }
    private void removeIngredient(IIngredient ingredient, View view) {
        ingredients.remove(ingredient);
        ingredient_list_container.removeView(view);
        UserSingleton.getInstance().notifyObservers();
    }
    private void renderList(ArrayList<IIngredient> ingredients) {
        ingredient_list_container.removeAllViews();
        for (IIngredient ingredient : ingredients) {
            View ingredientView = CreateCardViewHelper.createIngredientListRow(this, ingredient);
            ingredientView.findViewById(R.id.row_remove).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeIngredient(ingredient, ingredientView);
                }
            });
            ingredient_list_container.addView(ingredientView);
        }
    }

    @Override
    public void update() {
        ingredients = UserSingleton.getInstance().getIngredients();
        renderList(ingredients);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}