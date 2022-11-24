//cd .\out\production\test3
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = Loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Login");
        primaryStage.setMaxWidth(300);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
