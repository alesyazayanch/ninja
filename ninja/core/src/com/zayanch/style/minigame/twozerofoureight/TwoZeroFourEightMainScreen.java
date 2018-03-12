package com.zayanch.style.minigame.twozerofoureight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zayanch.style.NinjaGame;
import com.zayanch.style.screens.GameScreen;

public class TwoZeroFourEightMainScreen implements Screen {

    public enum Direction {
        AWAITING, UP, DOWN, LEFT, RIGHT
    }


    final NinjaGame game;
    private Stage stage;
    final float width;
    final float height;
    public GameField gameField;
    private SpriteBatch batch;
    Texture field,blank,gameOver;
    Direction direction;


    public NumberImage numberImage;
    public Constants c;

    public BitmapFont font;
    public Texture scoreN;
    private Dialog dialog;
    public TwoZeroFourEightMainScreen(final NinjaGame ninjaGame){

        game=ninjaGame;
        stage = new Stage(new ScreenViewport());

        //устанавливаем сцену основным процессом
        Gdx.input.setInputProcessor(stage);

        // получаем размер экрана телефона (ширину и высоту)
        width = stage.getWidth();
        height=stage.getHeight();

        batch = new SpriteBatch();

        direction=Direction.AWAITING;
        gameField=new GameField();

        numberImage=new NumberImage();
        field = new Texture(Gdx.files.internal("texture/field.png"));
        blank = new Texture(Gdx.files.internal("texture/blank.png"));
        gameOver= new Texture(Gdx.files.internal("texture/over.png"));
        scoreN=new Texture(Gdx.files.internal("texture/field.png"));

        font = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));


        InputMultiplexer inputMultiplexer=new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(new GestureDetector(new GameGestureListener(this)));

        //устанавливаем сцену основным процессом
        Gdx.input.setInputProcessor(inputMultiplexer);



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

        batch.begin();
            batch.draw(field, (float) (width * 0.05), (float) (width * 0.3),
                    (float) (width * 0.9), (float) (width * 0.9));

            for (int i = 0; i < c.CountCellsX; i++) {
                for (int j = 0; j < c.CountCellsX; j++) {
                    batch.draw(blank, (float) (width * 0.05 + (width * 0.02 * (i + 1)) + (width * 0.2 * i)),
                            (float) (width * 0.3 + (width * 0.02 * (j + 1)) + (width * 0.2 * j)),
                            (float) (width * 0.2), (float) (width * 0.2));
                }
            }

            for (int i = 0; i < c.CountCellsX; i++) {
                int[] column=gameField.getColumn(i);
                int e=c.CountCellsX-1;
                for (int j = 0; j < c.CountCellsX; j++) {
                    batch.draw(numberImage.textureByNumber.get(column[e]), (float) (width * 0.05 + (width * 0.02 * (i + 1)) + (width * 0.2 * i)),
                            (float) (width * 0.3 + (width * 0.02 * (j + 1)) + (width * 0.2 * j)),
                            (float) (width * 0.2), (float) (width * 0.2));
                    e--;
                }
            }

        font.setColor(Color.WHITE);
        batch.draw(scoreN,(float) (width*0.03), (float) (height*0.8),(float) (width-width*0.06), (float) (width*0.15));
        font.draw(batch,"Score: "+Integer.toString(gameField.score),(float) (width*0.032), (float) (height*0.86));

        batch.end();

        if(gameField.is2048()==true){
            //game.setScreen(new GameScreen(game));
        }
        if(gameField.is8192()==true){
             game.setScreen(new GameScreen(game));
        }else if(gameField.ifTheFielsIsBusy==true){
            game.setScreen(new GameScreen(game));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new GameScreen(game));
        }

        stage.act();
        stage.draw();
    }




    public void shift(Direction direction){

        if(direction==Direction.LEFT){
            gameField.leftupdate();
        }else if(direction==Direction.RIGHT){
            gameField.rightupdate();
        }else if(direction==Direction.UP){
            gameField.upupdate();
        }else if(direction==Direction.DOWN){
            gameField.downupdate();
        }
        gameField.generateNewCell();
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
        stage.dispose();
        game.dispose();

        font.dispose();

    }

}
