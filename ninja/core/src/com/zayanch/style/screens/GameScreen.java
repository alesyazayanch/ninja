package com.zayanch.style.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zayanch.style.NinjaGame;
import com.zayanch.style.button.ButtonIMAGE;
import com.zayanch.style.minigame.fooddrop.FoodDropMainScreen;
import com.zayanch.style.minigame.freefall.FreeFallMainScreen;
import com.zayanch.style.minigame.twozerofoureight.TwoZeroFourEightMainScreen;

public class GameScreen implements Screen     {

    final NinjaGame game;
    private Stage stage;
    public BitmapFont font;
    private SpriteBatch batch;
    final float width;
    final float height;
    public Texture background;

    public GameScreen(final NinjaGame ninjaGame) {
        game=ninjaGame;
        stage = new Stage(new ScreenViewport());
        width=stage.getWidth();
        height=stage.getHeight();

        background=new Texture(Gdx.files.internal("background/gameback.jpg"));

        Texture textureUp=new Texture(Gdx.files.internal("ImageButton/button_game/raspberry.png"));
        Texture textureDown=new Texture(Gdx.files.internal("ImageButton/button_game/raspberrydown.png"));
        Texture textureChecked= new Texture(Gdx.files.internal("ImageButton/button_game/raspberry.png"));
        ButtonIMAGE foodDropButton= new ButtonIMAGE(textureUp,textureDown,textureChecked,width/5,height/5,0);


        textureUp=new Texture(Gdx.files.internal("ImageButton/button_game/sun-cloud.png"));
        textureDown=new Texture(Gdx.files.internal("ImageButton/button_game/sun-cloud down.png"));
        ButtonIMAGE freeFallButton= new ButtonIMAGE(textureUp,textureDown,textureUp,width/5,height/5,0);

        textureUp=new Texture(Gdx.files.internal("ImageButton/button_game/2048up.png"));
        textureDown=new Texture(Gdx.files.internal("ImageButton/button_game/2048down.png"));
        ButtonIMAGE tzfeButton= new ButtonIMAGE(textureUp,textureDown,textureUp,width/5,height/5,0);

        float tableImageButtonWidth=width/3-width/24;

        Table container=new Table();
        container.setFillParent(true);

        Table table=new Table();

        final ScrollPane scrollPane=new ScrollPane(table);

        table.pad(width/50).defaults().pad(width/100).width(tableImageButtonWidth).height(tableImageButtonWidth);
        table.add(foodDropButton);
        table.add(freeFallButton);
        table.add(tzfeButton);

        batch=new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));


        container.add(scrollPane);
        stage.addActor(container);
        System.out.println();

        foodDropButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new FoodDropMainScreen(game));

            }
        });

        freeFallButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new FreeFallMainScreen(game));

            }
        });

        tzfeButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new TwoZeroFourEightMainScreen(game));

            }
        });

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
            game.setScreen(new MainScreen(game));
        }

        batch.begin();
        font.setColor(Color.WHITE);
        batch.draw(background,0,0,width,height);
        font.draw(batch,"GAME",(float) (width*0.38), (float) (height*0.98));
        batch.end();

        //рисуем сцену
        stage.act();
        stage.draw();
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
        batch.dispose();
        font.dispose();
        stage.dispose();
        game.dispose();

    }
}
