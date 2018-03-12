package com.zayanch.style.minigame.twozerofoureight;


public class Constants {
    // размер одной ячейки
    public static final int CellSize=64;

    // количество ячеек по горизонтали и вертикали
    public static final int CountCellsX=4;
    public static final int CountCellsY=CountCellsX;

    // ширина и выота поля
    public static final int Width=CountCellsX*CellSize;
    public static final int Height=CountCellsY*CellSize;

    // шанс того, что появится 4, а не 2
    public static final int ChanceOfFore=15;

    //значения новых клеток
    public static final int InitialCallState=2;
    public static final int InitialCallStateChanceOfFore=4;

    //количество начальных ячеек
    public static final int CountInitialCells=2;

}
