package windowsController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Utilizator;
import services.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AbonatController {
    private Service service;

    @FXML
    public TextField NumeTxtF;
    @FXML
    public TextField LocuriTxtF;
    @FXML
    public ListView<Text> ListaInitiala;
    @FXML
    public ListView<String> ListaCauta;
    @FXML
    public javafx.scene.control.DatePicker DatePicker;
    @FXML
    public AnchorPane mainAnchorPane;

    private Utilizator currentUser;

    public void setCurrentUser(Utilizator currentUser) {
        this.currentUser = currentUser;
    }


    public void initialize(){

    }

    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Biblioteca");
        FXMLLoader loader = new FXMLLoader();
        Pane pane = loader.load(getClass().getResourceAsStream("/fxml/loginWindow.fxml"));
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage currentStage = (Stage) mainAnchorPane.getScene().getWindow();
        currentStage.close();
    }

    public void setServer(Service server)
    {
        this.service = server;
    }


}
