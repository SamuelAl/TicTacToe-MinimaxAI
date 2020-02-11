module hellofx {
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.base;

	
	opens com.samalarco.tictactoe to javafx.fxml;
	exports com.samalarco.tictactoe;
}