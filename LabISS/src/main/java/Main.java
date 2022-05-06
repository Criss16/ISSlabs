import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import repository.AbonatRepo;
import repository.BibliotecarRepo;
import repository.CarteRepo;
import services.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Biblioteca");
        FXMLLoader loader = new FXMLLoader();
        Pane pane = loader.load(getClass().getResourceAsStream("fxml/loginWindow.fxml"));
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
