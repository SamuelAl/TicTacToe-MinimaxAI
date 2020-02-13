package com.samalarco.tictactoe;

import javafx.stage.Stage;

public class GameController {
	

	public static final int STATUS_PLAYING = 1;
	public static final int STATUS_GAME_END = 0;
	
	private Stage window;
	private GameModel model;
	private GameView view;
	
	private boolean playerTurn = true;
	private int playerMark = GameModel.X;
	private int computerMark = GameModel.O;
	private int gameStatus = STATUS_PLAYING;

	public GameController(Stage window)
	{
		 this.window = window;
		 this.model = new GameModel(this);
		 this.view = new GameView(window, this);
	}
	
	public void openGameWindow()
	{
		view.showGameWindow();
	}
	
	public void handleTileClicked(int row, int column)
	{
		if (gameStatus == STATUS_PLAYING)
		{
			int player = playerTurn ? playerMark : computerMark;
			System.out.println("Player: " + (playerTurn ? "Player" : "Computer"));
			view.updateTile(row, column,  player);
			model.updateBoard(row, column, player);
			
			if(model.checkWin(player))
			{
				System.out.println("Player " + player + " wins" );
				gameStatus = STATUS_GAME_END;
			}
			else if(!model.isMoveLeft())
			{
				System.out.println("Draw");
				gameStatus = STATUS_GAME_END;
			}
			playerTurn = !playerTurn;
			if (!playerTurn && gameStatus == STATUS_PLAYING)
			{
				model.aiMove(computerMark);
			}
		}
		
	}
	
	
	
	
	
}
