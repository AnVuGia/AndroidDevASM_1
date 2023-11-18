package com.example.asm1.model;





import android.widget.ImageButton;

import com.example.asm1.R;

public class ButtonsChangeHelper {
    private ImageButton firstButton, secondButton, thirdButton;
    private Boolean[] isClicked = new Boolean[3];
    public ButtonsChangeHelper(ImageButton firstButton, ImageButton secondButton, ImageButton thirdButton) {
        this.firstButton = firstButton;
        this.secondButton = secondButton;
        this.thirdButton = thirdButton;
        for(int i = 0; i < 3; i++) {
            isClicked[i] = false;
        }
        firstButton.setOnClickListener(v -> onClickButton(firstButton, 0));
        secondButton.setOnClickListener(v -> onClickButton(secondButton, 1));
        thirdButton.setOnClickListener(v -> onClickButton(thirdButton, 2));
    }
    private void setButtonColor(ImageButton button, int color) {
        button.setBackgroundColor(button.getResources().getColor(color));
    }
    private void setButtonColor(ImageButton button, Boolean isClicked) {
        if (isClicked) {
            setButtonColor(button, R.color.add_ingredient_button);
        } else {
            setButtonColor(button, R.color.white);
        }
    }
    private void onClickButton(ImageButton button, int index) {
        for(int i = 0; i < 3; i++) {
            isClicked[i] = false;
        }
        isClicked[index] = !isClicked[index];
        renderButton();
    }
    private void renderButton() {
        setButtonColor(firstButton, isClicked[0]);
        setButtonColor(secondButton, isClicked[1]);
        setButtonColor(thirdButton, isClicked[2]);
    }
    public Boolean[] getIsClicked() {
        return isClicked;
    }
    public int getClickedIndex() {
        for(int i = 0; i < 3; i++) {
            if(isClicked[i]) {
                return i;
            }
        }
        return -1;
    }
}
