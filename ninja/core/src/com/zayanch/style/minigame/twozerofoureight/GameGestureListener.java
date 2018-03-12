package com.zayanch.style.minigame.twozerofoureight;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class GameGestureListener implements GestureDetector.GestureListener {

    private TwoZeroFourEightMainScreen tzfeScreen;
    float startX,startY;


    public GameGestureListener(TwoZeroFourEightMainScreen tzfeScreen){
        this.tzfeScreen=tzfeScreen;

    }
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        startX=x;
        startY=y;
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        float moveX=x-startX;
        float moveY=y-startY;
        if(Math.abs(moveX)>Math.abs(moveY)){
            if(moveX<0){
                tzfeScreen.shift(TwoZeroFourEightMainScreen.Direction.LEFT);
                System.out.println(TwoZeroFourEightMainScreen.Direction.LEFT);

            }else{
                tzfeScreen.shift(TwoZeroFourEightMainScreen.Direction.RIGHT);
                System.out.println(TwoZeroFourEightMainScreen.Direction.RIGHT);
            }
        }else{
            if(moveY<0){
                tzfeScreen.shift(TwoZeroFourEightMainScreen.Direction.UP);
                System.out.println(TwoZeroFourEightMainScreen.Direction.UP);
            }else{
                tzfeScreen.shift(TwoZeroFourEightMainScreen.Direction.DOWN);
                System.out.println(TwoZeroFourEightMainScreen.Direction.DOWN);
            }

        }

        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
