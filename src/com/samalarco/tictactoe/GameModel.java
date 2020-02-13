package com.samalarco.tictactoe;

public class GameModel {
	
	public static final int EMPTY = -1;
	public static final int O = 0;
	public static final int X = 1;
	
	private int maximizingPlayer = O;
	private int minimizingPlayer = X;
	
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
		return checkWin(player, boardArray);
	}
	
	public boolean checkWin(int player, int[][] board)
	{
		boolean win = true;
		//check rows
		for (int i = 0; i < 3; i++)
		{
			win = true;
			for (int j = 0; j < 3; j++)
			{
				if (board[i][j] != player)
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
				if (board[i][j] != player)
					win = false;
			}
			if (win)
				return true;
		}
		
		//check diagonal \
		win = true;
		for (int i = 0, j = 0; i < 3; i++, j++)
		{
			if (board[i][j] != player)
				win = false;
		}
		
		if (win)
			return true;
		
		//check diagonal /
		win = true;
		for (int i = 0; i < 3; i++)
		{
			
			int j = 2 - i;
			if (board[i][j] != player)
				win = false;
		}
		
		return win;
	}
	
	public void aiMove(int player)
	{
		if (isMoveLeft(boardArray))
		{
			BoardMove nextMove = findBestMove(player);
			controller.handleTileClicked(nextMove.getRow(), nextMove.getColumn());
		}
		
	}

	public boolean isMoveLeft(int[][] board)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (board[i][j] == EMPTY)
					return true;
			}
		}
		
		return false;
	}
	
	public boolean isMoveLeft()
	{
		return isMoveLeft(boardArray);
	}
	
	private BoardMove findBestMove(int player)
	{
		BoardMove bestMove = new BoardMove(-1, -1);
		maximizingPlayer = player;
		if (player == X)
			minimizingPlayer = O;
		else
			minimizingPlayer = X;
		int depth = 0;
		int score = -50;
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (boardArray[i][j] == EMPTY)
				{
					int[][] newBoard = copyBoard(boardArray);
					newBoard[i][j] = maximizingPlayer;
					int moveScore = minimax(newBoard, depth+1, false);
					System.out.println("Try Move: " + i + "-" + j + " score:" + moveScore);
					if (moveScore > score)
					{
						score = moveScore;
						bestMove.setRow(i);
						bestMove.setColumn(j);
						System.out.println("Move: " + i + "-" + j + " score:" + score);
					}	
				}
			}
		}
		
		return bestMove;
	}
	
	private int minimax(int[][] board, int depth, boolean maximize)
	{
		int maximizingPlayerWinScore = 10;
		int minimizingPlayerWinScore = -10;
		
		if (checkWin(maximizingPlayer, board))
			return maximizingPlayerWinScore - depth;
		
		else if(checkWin(minimizingPlayer, board))
			return minimizingPlayerWinScore + depth;
		
		else if(!isMoveLeft(board))
			return 0;
		
		if (maximize)
		{
			int bestScore = -50;
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					if (board[i][j] == EMPTY)
					{
						int[][] newBoard = copyBoard(board);
						newBoard[i][j] = maximizingPlayer;
						int moveScore = minimax(newBoard, depth+1, false);
						if (moveScore > bestScore)
						{
							bestScore = moveScore;
						}

					}
				}
			}
			return bestScore;
		}
		else
		{
			int bestScore = 50;
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					if (board[i][j] == EMPTY)
					{
						int[][] newBoard = copyBoard(board);
						newBoard[i][j] = minimizingPlayer;
						int moveScore = minimax(newBoard, depth+1, true);
						if (moveScore < bestScore)
						{
							bestScore = moveScore;
						}

					}
				}
			}
			return bestScore;
		}
		
	}
	
	private int[][] copyBoard(int[][] board)
	{
		int[][] boardCopy = new int[3][3];
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				boardCopy[i][j] = board[i][j];
			}
		}
		
		return boardCopy;
	}
	
	private class BoardMove
	{
		private int row, column;
		
		public BoardMove(int row, int column)
		{
			this.row = row;
			this.column = column;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getColumn() {
			return column;
		}

		public void setColumn(int column) {
			this.column = column;
		}
	}
}
