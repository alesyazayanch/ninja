package com.zayanch.style.minigame.fooddrop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zayanch.style.*;
import com.zayanch.style.screens.GameScreen;

import java.util.ArrayList;


public class FoodDropMainScreen implements Screen {

    final NinjaGame game;
    private Stage stage;
    final Image ninjaImage;
    final float width;
    final float height;
    private FoodImage allFood;
    private ArrayList<Image> foodImages= new ArrayList<Image>();
    private float timeCounter;
    private float lastFoodTime=0;
    private float foodForSeconds=1;
    private float elapseTimeFood=0.0225f;
    public int score,exitscore;

    public BitmapFont font;
    public Texture scoreN;
    private SpriteBatch batch;
    public Texture gameover;
    public Texture background;

    Preferences preferences=Gdx.app.getPreferences("bestscore");;

    public FoodDropMainScreen(final NinjaGame ninjaGame) {

        game=ninjaGame;
        stage = new Stage(new ScreenViewport());

        //устанавливаем сцену основным процессом
        Gdx.input.setInputProcessor(stage);

        // получаем размер экрана телефона (ширину и высоту)
        width=stage.getWidth();
        height=stage.getHeight();

        score=0;
        exitscore=0;

        batch=new SpriteBatch();

        background=new Texture(Gdx.files.internal("background/kitchen1.jpg"));


        scoreN=new Texture(Gdx.files.internal("texture/field.png"));

        font = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));

        gameover= new Texture("texture/over.png");

        Texture ninjaTexture=new Texture(Gdx.files.internal("ninjabody/newninja.png"));
        ninjaImage= new Image(ninjaTexture);
        ninjaImage.setWidth(ninjaImage.getWidth()-ninjaImage.getWidth()/2);
        ninjaImage.setHeight(ninjaImage.getHeight()-ninjaImage.getHeight()/2);
        ninjaImage.setPosition(width/2-ninjaImage.getWidth()/2,height/100);
        stage.addActor(ninjaImage);

        createfood();
        //устанавливаем сцену основным процессом
        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void render(float delta) {

        //очищаем экран и устанавлеваем цвет фона белым
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // если на телефоне нажата кнопка Back, то мы выходим из приложения
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new GameScreen(game));

        }

        //если нажато на экран,то мы
        if(Gdx.input.isTouched()){
            ninjaImage.setPosition(Gdx.input.getX()-(ninjaImage.getWidth()/2),height/100);
        }


         // меняет позицию картинки по оси у, и удаляет при достижении конкретной области
        for (int i=0;i<foodImages.size();i++) {

            foodImages.get(i).setPosition(foodImages.get(i).getX(), foodImages.get(i).getY() -5);
            foodImages.get(i).rotateBy(4f);
            stage.addActor(foodImages.get(i));
            System.out.println(foodImages.size());

            // если доходит до низа экрана, то мы удаляем
            if(foodImages.get(i).getY()<0-foodImages.get(i).getHeight()){
                System.out.println(foodImages.size());
                foodImages.get(i).remove();
                foodImages.remove(i);
                System.out.println(foodImages.size());
                if (foodImages.size()==0){
                    createfood();
                }
                exitscore+=1;
            }
            if(foodImages.get(i).getY() < (ninjaImage.getHeight() - ninjaImage.getHeight() / 3)
                    && foodImages.get(i).getY() > (ninjaImage.getY() + ninjaImage.getHeight() / 6)){
                        if(foodImages.get(i).getX() > (ninjaImage.getX() + ninjaImage.getWidth() / 3)
                            && foodImages.get(i).getX() < (ninjaImage.getX() + 2 * ninjaImage.getWidth() / 3)){
                            foodImages.get(i).remove();
                            foodImages.remove(i);
                            score+=1;
                        }else if((foodImages.get(i).getX() + foodImages.get(i).getWidth() / 2) > (ninjaImage.getX() + ninjaImage.getWidth() / 3)
                             && foodImages.get(i).getX() < (ninjaImage.getX() + 2 * ninjaImage.getWidth() / 3)){
                            foodImages.get(i).remove();
                            foodImages.remove(i);
                            score+=1;
                        }else if((foodImages.get(i).getX() + foodImages.get(i).getWidth()) > (ninjaImage.getX() + ninjaImage.getWidth() / 3)
                            && foodImages.get(i).getX() < (ninjaImage.getX() + 2 * ninjaImage.getWidth() / 3)){
                            foodImages.get(i).remove();
                            foodImages.remove(i);
                            score+=1;
                        }
                if (foodImages.size()==0){
                    createfood();
                }

            }


        }
        if (timeCounter - lastFoodTime >  foodForSeconds / (timeCounter * elapseTimeFood)) {
            createfood();
        }

        batch.begin();
        font.setColor(Color.WHITE);
        batch.draw(background,0,0,width,height);
        font.draw(batch,"Hit: "+Integer.toString(exitscore)+"/8",(float) (width*0.032),(float)(height*0.90));
        font.draw(batch,"Score: "+Integer.toString(score),(float)(width*0.032),(float)(height*0.96));
        if(exitscore==8){
            game.setScreen(new GameScreen(game));
        }
        batch.end();

       //рисуем сцену
        stage.act();
        stage.draw();
        timeCounter += delta;


    }

    public void createfood(){
        allFood= new FoodImage();;
        int number = MathUtils.random(0, allFood.listfood.size() - 1);
        allFood.listfood.get(number).setOrigin(allFood.listfood.get(number).getHeight()/2,allFood.listfood.get(number).getHeight()/2);
        double position=MathUtils.random(0, width);
        if(position>width || position>width-allFood.listfood.get(number).getWidth()){
            allFood.listfood.get(number).setPosition(width-allFood.listfood.get(number).getWidth(), height);

        }else {
            allFood.listfood.get(number).setPosition(MathUtils.random(0, width) - allFood.listfood.get(number).getWidth() / 2, height);
        }
        foodImages.add(allFood.listfood.get(number));
        System.out.println(foodImages.size());
        lastFoodTime = timeCounter;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        stage.dispose();
        game.dispose();
        batch.dispose();
        font.dispose();
    }




}
