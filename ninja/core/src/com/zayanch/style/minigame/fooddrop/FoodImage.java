package com.zayanch.style.minigame.fooddrop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;


public class FoodImage extends
        Image {

    public Image carrot,apple,donut,lemon,pear,raspberries;

    public ArrayList<Image> listfood;




    public FoodImage() {

        carrot = new Image(new Texture(Gdx.files.internal("food/carrot1.png")));
        carrot.setWidth(carrot.getWidth()/2); carrot.setHeight(carrot.getHeight()/2);

        apple= new Image(new Texture(Gdx.files.internal("food/apple.png")));
        apple.setWidth(apple.getWidth()/2); apple.setHeight(apple.getHeight()/2);

        donut= new Image(new Texture(Gdx.files.internal("food/donut.png")));
        donut.setWidth(2*donut.getWidth()/3); donut.setHeight(2*donut.getHeight()/3);

        lemon= new Image(new Texture(Gdx.files.internal("food/lemon.png")));
        lemon.setWidth(lemon.getWidth()/2); lemon.setHeight(lemon.getHeight()/2);

        pear= new Image(new Texture(Gdx.files.internal("food/pear.png")));
        pear.setWidth(pear.getWidth()/2); pear.setHeight(pear.getHeight()/2);
        raspberries= new Image(new Texture(Gdx.files.internal("food/raspberries.png")));

        this.listfood= new ArrayList<Image>();
        listfood.add(carrot);
        //listfood.add(raspberries);
        listfood.add(apple);
        listfood.add(donut);
        listfood.add(lemon);
        listfood.add(pear);



    }

    @Override
    public boolean remove() {
        return super.remove();
    }
}
