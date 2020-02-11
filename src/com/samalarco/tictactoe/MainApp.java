package com.samalarco.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		primaryStage.setTitle("Tic Tac Toe");
		GameController controller = new GameController(primaryStage);
		controller.openGameWindow();

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	
	
	

}

