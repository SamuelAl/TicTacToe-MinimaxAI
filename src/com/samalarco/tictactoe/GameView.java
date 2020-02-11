package com.samalarco.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameView 
{
	private static final int TILE_LENGTH = 200;

	private Stage primaryStage;
	private GameController controller;
	private Tile[][] tileArray;

	public GameView(Stage primaryStage, GameController controller)
	{
		this.controller = controller;
		this.primaryStage = primaryStage;
		tileArray = new Tile[3][3];
	}

	private Parent generateContent()
	{
		Pane root = new Pane();
		root.setPrefSize(TILE_LENGTH * 3, TILE_LENGTH * 3);

		for (int i= 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				Tile tile = new Tile(i, j, TILE_LENGTH);
				tile.setTranslateX(i * TILE_LENGTH);
				tile.setTranslateY(j * TILE_LENGTH);
				tile.setOnMouseClicked(e -> tileClicked(tile));
				tileArray[i][j] = tile;
				root.getChildren().add(tile);
			}
		}
		return root;
	}

	public void showGameWindow()
	{
		primaryStage.setScene(new Scene(generateContent()));
		primaryStage.show();
	}
	
	private void tileClicked(Tile tile)
	{
		controller.handleTileClicked(tile.getRow(), tile.getColumn());
	}
	
	public void updateTile(int row, int column, int player)
	{
		tileArray[row][column].setMarking(player);
		System.out.println("Tile " + row + ":" + column + " clicked");
	}

	private class Tile extends StackPane
	{
		private int row, column;
		private Text symbol;
		private int marking = GameModel.EMPTY;

		public Tile(int row, int column, int length)
		{	
			this.row = row;
			this.column = column;
			
			symbol =  new Text("");
			symbol.setFont(Font.font("Roboto Condensed", 100));
			
			Rectangle borders = new Rectangle(length, length);
			borders.setFill(null);
			borders.setStroke(Color.BLACK);

			this.setAlignment(Pos.CENTER);
			this.getChildren().addAll(borders, symbol);
		}
		
		public void printIJ()
		{
			System.out.println("Row: " + row + "; Column: " + column);
		}
		
		public int getMarking()
		{
			return marking;
		}
		
		public void setMarking(int mark)
		{
			symbol.setText(mark == GameModel.X ? "X" : "O");
			symbol.setFill(mark == GameModel.X ? Color.RED : Color.BLUE);
			marking = mark;
		}

		public int getRow() {
			return row;
		}


		public int getColumn() {
			return column;
		}
		


	}

}
