package com.example.asm1.model;

import android.widget.ImageView;

import com.example.asm1.R;

public class ImageHelper {
    public static void setImageToView(String imgName, ImageView view){

        switch (imgName) {
            case "flour":
                view.setImageResource(R.drawable.flour);
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
                case "meat":
                view.setImageResource(R.drawable.meat);
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
                case "vegetable":
                view.setImageResource(R.drawable.vegetable);
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
                case "dairy":
                view.setImageResource(R.drawable.dairy);
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
                case "breakfast":
                view.setImageResource(R.drawable.breakfast);
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
                case "lunch":
                view.setImageResource(R.drawable.lunch);
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
                case "dinner":
                view.setImageResource(R.drawable.dinner);
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
        }
    }
}
