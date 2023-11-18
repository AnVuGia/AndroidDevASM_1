package com.example.asm1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.asm1.Interface.IIngredient;
import com.example.asm1.R;
import com.example.asm1.model.Ingredient.withGuideAndAmount;

public class CreateCardViewHelper {
    private static View addPaddingAndElevation(View view) {
        view.setPadding(5, 5, 5, 5);
        view.setElevation(10);
        return view;
    }
    public static View createBaseIngredientCardView (Context context, IIngredient ingredient){
        View ingredientView = LayoutInflater.from(context).inflate(R.layout.card, null);
        TextView ingredientName = ingredientView.findViewById(R.id.card_name);
        TextView ingredientDescription = ingredientView.findViewById(R.id.card_desc);
        TextView ingredientCalories = ingredientView.findViewById(R.id.card_calories);
        ImageHelper.setImageToView(ingredient.getImg(), ingredientView.findViewById(R.id.card_image));
        ingredientName.setText(ingredient.getName());
        ingredientDescription.setText(ingredient.getDescription());
        ingredientCalories.setText("Calories: "+ String.valueOf(ingredient.getCalories()));
        return ingredientView;
    }
    public static View createBaseRecipeCardView (Context context, Recipe recipe){
        View recipeView = LayoutInflater.from(context).inflate(R.layout.card, null);
        TextView recipeName = recipeView.findViewById(R.id.card_name);
        TextView recipeDescription = recipeView.findViewById(R.id.card_desc);
        TextView recipeCalories = recipeView.findViewById(R.id.card_calories);
        ImageHelper.setImageToView(recipe.getType(), recipeView.findViewById(R.id.card_image));
        recipeName.setText(recipe.getName());
        recipeDescription.setText(recipe.getDescription());
        recipeCalories.setText(String.valueOf("Calories: "+ recipe.getCalories()));
        return recipeView;
    }
    public static View createRecipeIngredientCardView(Context context, withGuideAndAmount ingredient) {
        View ingredientView = LayoutInflater.from(context).inflate(R.layout.recipe_ingredient_card, null);
        TextView ingredientName = ingredientView.findViewById(R.id.ingredient_card_name);
        TextView ingredientGuide = ingredientView.findViewById(R.id.ingredient_card_guide);
        TextView ingredientAmount = ingredientView.findViewById(R.id.ingredient_card_amount);
        ImageHelper.setImageToView(ingredient.getImg(), ingredientView.findViewById(R.id.ingredient_card_img));
        ingredientName.setText(ingredient.getName());
        ingredientGuide.setText(ingredient.getGuide());
        ingredientAmount.setText(String.valueOf(ingredient.getAmount()));
        return addPaddingAndElevation(ingredientView);
    }
    public static View createRecipeListRow(Context context, Recipe recipe) {
        View recipeView = LayoutInflater.from(context).inflate(R.layout.row, null);
        TextView recipeName = recipeView.findViewById(R.id.row_name);
        TextView recipeDescription = recipeView.findViewById(R.id.row_desc);
        TextView recipeCalories = recipeView.findViewById(R.id.row_calories);
        recipeName.setText(recipe.getName());
        recipeDescription.setText("Note: "+ recipe.getDescription());
        recipeCalories.setText("Calories: "+ String.valueOf(recipe.getCalories()));
        ImageHelper.setImageToView(recipe.getType(), recipeView.findViewById(R.id.row_image));
        return recipeView;
    }
    public static View createIngredientListRow(Context context, IIngredient ingredient) {
        View ingredientView = LayoutInflater.from(context).inflate(R.layout.row, null);
        TextView ingredientName = ingredientView.findViewById(R.id.row_name);
        TextView ingredientDescription = ingredientView.findViewById(R.id.row_desc);
        TextView ingredientCalories = ingredientView.findViewById(R.id.row_calories);
        ingredientName.setText(ingredient.getName());
        ingredientDescription.setText("Note: "+ ingredient.getDescription());
        ingredientCalories.setText("Calories: "+ String.valueOf(ingredient.getCalories()));
        ImageHelper.setImageToView(ingredient.getImg(), ingredientView.findViewById(R.id.row_image));
        return ingredientView;
    }

}
