package windowsController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.Service;

import java.io.IOException;

public class ServerController {

    private Service service;

    public void setServer(Service serv){
        this.service = serv;
    }

    @FXML
    public void deschideLogin() throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Biblioteca");
        FXMLLoader loader = new FXMLLoader();
        Pane pane = loader.load(getClass().getResourceAsStream("/fxml/loginWindow.fxml"));
        LoginController controller = loader.getController();
        controller.setServer(service);
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
