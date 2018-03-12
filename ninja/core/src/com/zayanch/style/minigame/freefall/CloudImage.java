package com.zayanch.style.minigame.freefall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

public class CloudImage {

    public Image cloud;
    public ArrayList<Image> cloudArray;
    public CloudImage(){
        cloud=new Image(new Texture(Gdx.files.internal("cloud/cloud.png")));
        cloud.setWidth(cloud.getWidth()/2);
        cloud.setHeight(cloud.getHeight()/2);
        cloudArray=new ArrayList<Image>();
        cloudArray.add(cloud);


    }

}
