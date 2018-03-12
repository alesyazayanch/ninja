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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zayanch.style.NinjaGame;
import com.zayanch.style.button.ButtonIMAGE;

public class MainScreen implements Screen {

    final NinjaGame game;
    private Stage stage;
    final float width;
    final float height;
    public Texture background;
    public BitmapFont font;
    private SpriteBatch batch;
    public Image ninjaImage;

    public MainScreen(final NinjaGame ninjaGame) {
        game=ninjaGame;
        stage = new Stage(new ScreenViewport());

        width=stage.getWidth();
        height=stage.getHeight();

        //устанавливаем сцену основным процессом
        Gdx.input.setInputProcessor(stage);



        batch=new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));

        background=new Texture(Gdx.files.internal("background/fon.png"));

        Texture ninjaTexture=new Texture(Gdx.files.internal("ninjabody/newninja.png"));
        ninjaImage= new Image(ninjaTexture);
        ninjaImage.setPosition(width/2-ninjaImage.getWidth()/2,height/10);


        // создаем кнопку картинку buttonGamepad,разместим справа в низу экрана и добавим на stage
        Texture textureUp=new Texture(Gdx.files.internal("ImageButton/button_game/gamepad_up.png"));
        Texture textureDown=new Texture(Gdx.files.internal("ImageButton/button_game/gamepad_down.png"));
        Texture textureChecked= new Texture(Gdx.files.internal("ImageButton/button_game/gamepad_up.png"));
        ButtonIMAGE buttonGamepad= new ButtonIMAGE(textureUp,textureDown,textureChecked,width/5,height/5,0);
        buttonGamepad.positionbuttonMenuForOneBottomRight();

        // при нажататии на кнопку buttonGamepad переходим в класс GameScreen
        buttonGamepad.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        stage.addActor(ninjaImage);
        stage.addActor(buttonGamepad);

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
                Gdx.app.exit();
        }

        batch.begin();
        font.setColor(Color.WHITE);
        batch.draw(background,0,0,width,height);
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
        stage.dispose();
        game.dispose();
        batch.dispose();
    }
}
