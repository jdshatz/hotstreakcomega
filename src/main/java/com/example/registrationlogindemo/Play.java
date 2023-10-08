package com.example.registrationlogindemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class Play extends Application {
	MultiLinePlayer player = new MultiLinePlayer(10000);

	MultiLineGameAllVersions game = new MultiLineGameAllVersions(player);

    public Play() throws IOException, URISyntaxException {
    }

    @Override
	public void start(Stage stage)
			throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(Play.class.getResource("hello-view.fxml"));
		stage.setTitle("Hot Streak - Prototype");
		Scene scene = new Scene(game, 2000, 1000);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
/*
public class HelloApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 320, 240);
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}*/