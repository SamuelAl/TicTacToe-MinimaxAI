package com.samalarco.tictactoe;

public class GameModel {
	
	public static final int EMPTY = -1;
	public static final int O = 0;
	public static final int X = 1;
	
	private int[][] boardArray;
	
	@SuppressWarnings("unused")
	private GameController controller;
	
	public GameModel(GameController controller)
	{
		this.controller = controller;
		boardArray = new int[3][3];
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				boardArray[i][j] = EMPTY;
			}
		}
	}
	
	public void updateBoard(int row, int column, int player)
	{
		try 
		{
			if (boardArray[row][column] == EMPTY )
			{
				boardArray[row][column] = player;
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.err.println("Illegal index used");
		}
	}
	
	public boolean checkWin(int player)
	{
		boolean win = true;
		//check rows
		for (int i = 0; i < 3; i++)
		{
			win = true;
			for (int j = 0; j < 3; j++)
			{
				if (boardArray[i][j] != player)
					win = false;
			}
			if (win)
				return true;
		}
		
		//check columns
		for (int j  = 0; j < 3; j++)
		{
			win = true;
			for (int i = 0; i < 3; i++)
			{
				if (boardArray[i][j] != player)
					win = false;
			}
			if (win)
				return true;
		}
		
		//check diagonal \
		win = true;
		for (int i = 0, j = 0; i < 3; i++, j++)
		{
			if (boardArray[i][j] != player)
				win = false;
		}
		
		if (win)
			return true;
		
		//check diagonal /
		for (int i = 0; i < 3; i++)
		{
			win = true;
			for (int j = 2; j >= 0; j--)
			{
				if (boardArray[i][j] != player)
					win = false;
			}
			if (win)
				return true;
		}
		
		return win;
	}

}
