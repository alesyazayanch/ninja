package com.zayanch.style.minigame.freefall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.zayanch.style.NinjaGame;
import com.zayanch.style.screens.GameScreen;

import java.util.ArrayList;


public class FreeFallMainScreen implements Screen {

    final NinjaGame game;
    private Stage stage;
    final float width;
    final float height;
    private Image ninjaImage;
    private CloudImage  cloudImage;
    private ArrayList<Image> cloudImageArray=new ArrayList<Image>();
    public int score,exitscore;
    public BitmapFont font;
    public Texture scoreN;
    private SpriteBatch batch;
    public Texture gameover;
    public Texture background;

    private float renderX;

    public FreeFallMainScreen(final NinjaGame ninjaGame) {

        game=ninjaGame;
        stage = new Stage(new ScreenViewport());

        //устанавливаем сцену основным процессом
        Gdx.input.setInputProcessor(stage);

        // получаем размер экрана телефона (ширину и высоту)
        width=stage.getWidth();
        height=stage.getHeight();

        score=0;
        background=new Texture(Gdx.files.internal("background/sky1.jpg"));

        boolean available = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
        System.out.println(available);

        Input.Orientation nativeOrientation=Gdx.input.getNativeOrientation();
        System.out.println(nativeOrientation);
        batch=new SpriteBatch();

        scoreN=new Texture(Gdx.files.internal("texture/field.png"));

        font = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));

        gameover= new Texture("texture/over.png");

        Texture ninjaTexture=new Texture(Gdx.files.internal("ninjabody/newninja.png"));
        ninjaImage= new Image(ninjaTexture);
        ninjaImage.setWidth(width/4);
        ninjaImage.setHeight(height/5);
        ninjaImage.setPosition(width/2-ninjaImage.getWidth()/2,height-ninjaImage.getHeight()-height/100);
        ninjaImage.setOrigin(ninjaImage.getWidth()/2,ninjaImage.getHeight()/2);

        stage.addActor(ninjaImage);

        renderX=width/2-ninjaImage.getWidth()/2;

        createcloud();

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

        ninjaImage.rotateBy(-ninjaImage.getRotation());

        renderX-=5*Gdx.input.getAccelerometerX();

        if(ninjaImage.getX()>renderX){

            ninjaImage.rotateBy(4f);

        }
        if(ninjaImage.getX()<renderX){

            ninjaImage.rotateBy(-4f);

        }

        if(renderX<0){
            renderX=0;
        }
        if(renderX>width-ninjaImage.getWidth()){
            renderX=width-ninjaImage.getWidth();
        }

        ninjaImage.setPosition(renderX,height-ninjaImage.getHeight()-height/10);
        System.out.println(renderX);

        changeposition();

        batch.begin();
        font.setColor(Color.WHITE);
        batch.draw(background,0,0,width,height);

        font.draw(batch,"Hit: "+Integer.toString(exitscore)+"/10",(float) (width*0.032), (float) (height*0.1));
        font.draw(batch,"Score: "+Integer.toString(score),(float) (width*0.032), (float) (height*0.05));
        if(exitscore==10){
         //   batch.draw(gameover,(float) (width*0.032), (float) (height*0.86));
            game.setScreen(new GameScreen(game));
        }
        batch.end();



        //рисуем сцену
        stage.act();
        stage.draw();


    }

    public void changeposition(){
        for (int i=0;i<cloudImageArray.size();i++) {

            cloudImageArray.get(i).setPosition(cloudImageArray.get(i).getX(), cloudImageArray.get(i).getY() + 20);
            stage.addActor(cloudImageArray.get(i));
            if (cloudImageArray.get(i).getY() > height+cloudImageArray.get(i).getHeight()) {
                System.out.println(cloudImageArray.size());
                cloudImageArray.get(i).remove();
                cloudImageArray.remove(i);
                System.out.println(cloudImageArray.size());
                if (cloudImageArray.size() == 0) {
                    createcloud();
                }
                score+=1;
            }

            if ((cloudImageArray.get(i).getY()>ninjaImage.getY()+ninjaImage.getHeight()/25)
                    && (cloudImageArray.get(i).getY()<ninjaImage.getY()+23*ninjaImage.getHeight()/25)){


                if(cloudImageArray.get(i).getX()>(ninjaImage.getX()+ninjaImage.getWidth()/4)
                        && cloudImageArray.get(i).getX()<(ninjaImage.getX()+2*ninjaImage.getWidth()/3)){
                    cloudImageArray.get(i).remove();
                    cloudImageArray.remove(i);
                    exitscore+=1;
                }else if((cloudImageArray.get(i).getX()+cloudImageArray.get(i).getWidth()/2)> (ninjaImage.getX()+ninjaImage.getWidth()/4)
                        && (cloudImageArray.get(i).getX())<(ninjaImage.getX()+2*ninjaImage.getWidth()/3)) {
                    cloudImageArray.get(i).remove();
                    cloudImageArray.remove(i);
                    exitscore+=1;
                }else if((cloudImageArray.get(i).getX()+cloudImageArray.get(i).getWidth())> (ninjaImage.getX()+ninjaImage.getWidth()/4)
                        && (cloudImageArray.get(i).getX())<(ninjaImage.getX()+2*ninjaImage.getWidth()/3)) {
                    cloudImageArray.get(i).remove();
                    cloudImageArray.remove(i);
                    exitscore+=1;
                }

                if(cloudImageArray.size()==0){
                    createcloud();

                }

            }
        }

    }

    public void createcloud(){
        cloudImage = new CloudImage();
        int number=MathUtils.random(0,cloudImage.cloudArray.size()-1);
        cloudImage.cloudArray.get(number).setOrigin(cloudImage.cloudArray.get(number).getHeight()/2,cloudImage.cloudArray.get(number).getHeight()/2);

        float cloudX=MathUtils.random(0, width/2);

        cloudImage.cloudArray.get(number).setPosition( cloudX,-cloudImage.cloudArray.get(number).getHeight());

/*
        if (cloudX>width-cloudImage.cloudArray.get(number).getWidth()){
            cloudImage.cloudArray.get(number).setPosition( width - cloudImage.cloudArray.get(number).getWidth()-width/10,-cloudImage.cloudArray.get(number).getHeight());

        }else{
            cloudImage.cloudArray.get(number).setPosition(cloudX,-cloudImage.cloudArray.get(number).getHeight());

        }*/
        cloudImageArray.add(cloudImage.cloudArray.get(number));

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
