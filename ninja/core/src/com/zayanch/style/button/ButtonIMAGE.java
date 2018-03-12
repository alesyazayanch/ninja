package com.zayanch.style.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Администратор on 30.10.2017.
 */

public class ButtonIMAGE extends ImageButton {
    public float width,height;
    public int number;
    public float x,y;

    public ButtonIMAGE(Texture texture_up, Texture texture_down, Texture texture_checked,float width,float height,int number) {
        super(new SpriteDrawable(new Sprite(texture_up)),new SpriteDrawable(new Sprite(texture_down)), new SpriteDrawable(new Sprite(texture_checked)));
        this.number=number;
        this.width=width;
        this.height=height;
        setSize(width,width);//квадратная кнопка

    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    //для расположения 3 кнопок внизу экрана
    public void positionbuttonMenuForThreeBottom(){
      float[] x= new float[3];
      float y;
        x[0]=5*width/6-width/2;
        for(int i=1;i<3;i++){
            x[i]=x[i-1]+5*width/3;
        }
        y=5*(height/4-height/16)/2-height/4;
        this.x=x[number];
        this.y=y;
        setPosition(x[number],y);

    }

    //для расположения 1 кнопоки внизу экрана
    public void positionbuttonMenuForOneBottomRight(){

        float x,y;
        x=5*width-5*width/6-width/2;
        y=5*(height/4-height/16)/2-height/4;
        this.x=x;
        this.y=y;

        setPosition(x,y);


    }

}
