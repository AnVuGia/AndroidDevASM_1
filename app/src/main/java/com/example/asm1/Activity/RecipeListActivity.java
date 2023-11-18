package com.example.asm1.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.asm1.Interface.IObserverUser;
import com.example.asm1.R;
import com.example.asm1.model.CreateCardViewHelper;
import com.example.asm1.model.Recipe;
import com.example.asm1.model.UserSingleton;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipeListActivity extends AppCompatActivity implements IObserverUser {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        UserSingleton.getInstance().register(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Recipes List");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        renderView();
        Button toAddRecipe = findViewById(R.id.to_add_recipe);
        toAddRecipe.setBackgroundColor(getResources().getColor(R.color.add_recipe_button));
        toAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecipeListActivity.this, AddRecipeActivity.class));
            }
        });
    }
    private void renderView() {
        ArrayList<Recipe> recipes = UserSingleton.getInstance().getRecipes();
        LinearLayout recipe_list_container = findViewById(R.id.recipe_list_container);
        for(Recipe recipe : recipes) {
            View view = CreateCardViewHelper.createRecipeListRow(this, recipe);
            recipe_list_container.addView(view);
            Button remove_btn = view.findViewById(R.id.row_remove);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RecipeListActivity.this, RecipeInformation.class);
                    intent.putExtra("recipe", (Serializable) recipe);
                    startActivity(intent);
                }
            });
            remove_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recipes.remove(recipe);
                    recipe_list_container.removeView(view);
                    UserSingleton.getInstance().notifyObservers();
                }
            });
        }

    }

    @Override
    public void update() {
        renderView();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        UserSingleton.getInstance().unregister(this);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}