package SuperPrincess.view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	Button closeButtonYes = new Button("Yes");
	Button closeButtonNo = new Button("No");
	Button OkButton = new Button("OK");

	boolean answer;


	public AlertBox() {
	}
	
	// Creates a alert box when you try to escape the game
	public boolean replyAlertBox(){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Escape Game");
		window.setMinWidth(500);
		
		VBox layout = new VBox(10);
		Scene scene = new Scene(layout);


		Label label = new Label();
		closeButtonYes.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ENTER:  		
				answer = true;
				window.close();;
				default:
					break;
				}	
			}});

		label.setText("Do you want to leave the game?");
		closeButtonYes.setOnAction(e ->{
			answer = true;
			window.close();
		});
		closeButtonNo.setOnAction(e ->{
			answer = false;
			window.close();
		});		
		closeButtonNo.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ENTER:  		
				answer = false;
				window.close();;
				default:
					break;
				}	
			}});
		layout.getChildren().addAll(label,closeButtonYes,closeButtonNo);
		layout.setAlignment(Pos.CENTER);

		window.setScene(scene);
		window.showAndWait();

		return answer;
		}
	
	
	// Creates an alert box if you dont enter the name in the character screen
	public void nameAlertBox(){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("No Name");
		window.setMinWidth(500);
		VBox layout = new VBox(10);

		Scene scene = new Scene(layout);
		Label label = new Label();
		
		OkButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ENTER:  		
					window.close();;
				default:
					break;
				}	
			}});
		
		OkButton.setOnAction(e ->{
			window.close();
		});

		label.setText("Please enter a name to continue.");
		
		layout.getChildren().addAll(label,OkButton);
		layout.setAlignment(Pos.CENTER);

		window.setScene(scene);
		window.showAndWait();
	}
}
