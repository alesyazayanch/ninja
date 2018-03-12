package com.zayanch.style.minigame.twozerofoureight;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.zayanch.style.screens.GameScreen;


public class GameField {

    // только для n*n
    private int[][] theField;
    Constants c;
    public int n=c.CountCellsX;
    public int score;
    public boolean ifTheFielsIsBusy;

    public GameField(){
        theField=new int[n][n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                theField[i][j]=0;
            }
        }
        createInitialCells();
        ifTheFielsIsBusy=false;
    }

    public void Ifthefielsisbusy(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n-1;j++){
                for(int k=i;k<n-1;k++){
                    if(getState(k,j)==getState(k+1,j)){
                        ifTheFielsIsBusy=false;
                    }
                }
                if(getState(i,j)==getState(i,j+1)){
                    ifTheFielsIsBusy=false;
                }
            }
        }
    }

    public boolean is2048(){
        boolean is2048=false;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if(theField[i][j]==2048){
                    is2048=true;
                }

            }
        }
        return is2048;
    }
    public boolean is8192(){
        boolean is8192=false;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if(theField[i][j]==8192){
                    is8192=true;
                }

            }
        }
        return is8192;
    }

    private void fieldupdate(int[][] a){

        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                setState(i,j,a[i][j]);
            }
        }
    }


    public void leftupdate(){
        shift();
    }

    public void rightupdate(){
        changeright();
        shift();
        changeright();

    }

    public void upupdate(){
        changeup();
        shift();
        changeup();


    }

    public void downupdate(){
        changeup();
        changeright();
        shift();
        changeright();
        changeup();

    }

   private void changeright(){
        int[][] rightmas=new int[n][n];
        for(int i=0;i<n;i++){
            int e=n-1;
            for(int j=0;j<n;j++){
                rightmas[i][j]=getState(i,e);
                e--;
            }
        }
        fieldupdate(rightmas);
    }


   private void changeup(){
        int[][] upmas=new int[n][n];
       for(int i=0;i<n;i++){
           for(int j=0;j<n;j++){
               upmas[i][j]=getState(j,i);
           }
       }
        fieldupdate(upmas);
   }

   private void shift(){

        boolean[] change= new boolean[n];

        for (int i=0;i<n;i++){
            int[] arg = getLine(i);
            for (int j=0;j<n;j++){
                for(int e=0;e<n-j-1;e++){
                    if(arg[e]==0){
                        int temp=arg[e];
                        arg[e]=arg[e+1];
                        arg[e+1]=temp;
                    }
                }
            }
            setLine(i,arg);
        }
        for(int j=0;j<n-1;j++){
            for(int i=0;i<n;i++){
                if(getState(i,j)==getState(i,j+1)){
                    setState(i,j,2*getState(i,j));
                    for(int e=j;e<n-2;e++){
                        setState(i,e+1,getState(i,e+2));
                    }
                    setState(i,n-1,0);
                    change[i]=true;
                }else{
                    change[i]=false;
                }
            }

        }

   }

    //изменяем i строку
    private void setLine(int i, int[] newLine){
         for (int j=0;j<n;j++){
             theField[i][j]=newLine[j];
         }
    }

     //возвращаем значение ячеек i строки
    private int[] getLine(int i) {
        int[] ret = new int[n];
         for(int j=0;j<n;j++){
             ret[j]=theField[i][j];
        }
        return ret;
    }


    public int getState(int x,int y){
        return theField[x][y];

    }


    public int[] getColumn(int j){
        int[] ret = new int[n];
        for(int i=0;i<n;i++){
            ret[i]=theField[i][j];
        }
        return ret;
    }


   private void setState(int x,int y, int state){
        theField[x][y]=state;

   }



   public void generateNewCell(){


        int state;
        if(MathUtils.random(0,100)<=c.ChanceOfFore){
            state=c.InitialCallStateChanceOfFore;
        }else{
            state=c.InitialCallState;
        }

        int randomX,randomY;
        randomX=MathUtils.random(0,c.CountCellsX-1);
        int currentX=randomX;
        randomY=MathUtils.random(0,c.CountCellsY-1);
        int currentY=randomY;

        boolean placed=false;

        while(!placed){
            if(getState(currentX,currentY)==0){
                setState(currentX,currentY,state);
                score+=state;
                placed=true;
            }else {
                if (currentX+1 < c.CountCellsX) {
                    currentX++;
                } else{
                    currentX=0;
                    if(currentY+1<c.CountCellsY){
                        currentY++;
                    }else{
                        currentY=0;
                    }
                }

                if((currentX==randomX)&&(currentY==randomY)){
                    placed=true;
                    ifTheFielsIsBusy=true;
                    Ifthefielsisbusy();

                }

            }

        }

       System.out.println(score);

   }


   private void createInitialCells(){
        for (int i=0;i<c.CountInitialCells;i++){
            generateNewCell();
        }
   }

}
