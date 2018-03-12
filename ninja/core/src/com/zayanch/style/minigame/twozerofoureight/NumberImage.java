package com.zayanch.style.minigame.twozerofoureight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class NumberImage {

    public Texture callEmpty,call2,call4,call8,call16,call32,call64,call128,call256,call512,call1024,call2048,call4096,call8192;
    public HashMap<Integer,Texture> textureByNumber=new HashMap<Integer, Texture>();

    public NumberImage(){

        callEmpty=new Texture(Gdx.files.internal("texture/blank.png"));
        call2=new Texture(Gdx.files.internal("texture/2.png"));
        call4=new Texture(Gdx.files.internal("texture/4.png"));
        call8=new Texture(Gdx.files.internal("texture/8.png"));
        call16=new Texture(Gdx.files.internal("texture/16.png"));
        call32=new Texture(Gdx.files.internal("texture/32.png"));
        call64=new Texture(Gdx.files.internal("texture/64.png"));
        call128=new Texture(Gdx.files.internal("texture/128.png"));
        call256=new Texture(Gdx.files.internal("texture/256.png"));
        call512=new Texture(Gdx.files.internal("texture/512.png"));
        call1024=new Texture(Gdx.files.internal("texture/1024.png"));
        call2048=new Texture(Gdx.files.internal("texture/2048.png"));
        call4096=new Texture(Gdx.files.internal("texture/4096.png"));
        call8192=new Texture(Gdx.files.internal("texture/8192.png"));

        textureByNumber.put(0,callEmpty);
        textureByNumber.put(2,call2);
        textureByNumber.put(4,call4);
        textureByNumber.put(8,call8);
        textureByNumber.put(16,call16);
        textureByNumber.put(32,call32);
        textureByNumber.put(64,call64);
        textureByNumber.put(128,call128);
        textureByNumber.put(256,call256);
        textureByNumber.put(512,call512);
        textureByNumber.put(1024,call1024);
        textureByNumber.put(2048,call2048);
        textureByNumber.put(4096,call4096);
        textureByNumber.put(8192,call8192);

    }



}
