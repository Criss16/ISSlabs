package windowsController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.Service;

public class RegisterController {
    private Service service;

    @FXML
    public TextField CNPTxt;
    @FXML
    public TextField NumeTxt;
    @FXML
    public TextField AdresaTxt;
    @FXML
    public TextField TelefonTxt;
    @FXML
    public TextField CodUserTxt;
    @FXML
    public TextField ParolaTxt;
    @FXML
    public RadioButton RAbonat;

    @FXML
    public RadioButton RBibliotecar;
    @FXML
    public AnchorPane signupAnchorPane;

    @FXML
    public void continua(){
        try{
            String cnp = CNPTxt.getText();
            String nume = NumeTxt.getText();
            String adresa = AdresaTxt.getText();
            String telefon = TelefonTxt.getText();
            String sirCodUser = CodUserTxt.getText();
            String parola = ParolaTxt.getText();
            Integer codAng = -1;
            Integer nrImpr = -1;

            if (cnp.equals("") || !cnp.matches("[0-9]{13}"))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("CNP-ul nu este corect!");
                alert.showAndWait();
                return;
            }
            if (nume.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Numele nu este corect!");
                alert.showAndWait();
                return;
            }
            if (adresa.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Adresa nu este corecta!");
                alert.showAndWait();
                return;
            }
            if (telefon.equals("") || !telefon.matches("[0-9]{10}"))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Telefonul nu este corect!");
                alert.showAndWait();
                return;
            }
            if(sirCodUser.equals("") || (!sirCodUser.matches("[0-9]{4}") && RAbonat.isSelected()) || (!sirCodUser.matches("[0-9]{2}") && RBibliotecar.isSelected()))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Cod User nu este corect! Bibliotecarii au cod de doua cifre, iar abonatii cod de 4 cifre.");
                alert.showAndWait();
                return;
            }
            if (parola.equals(""))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Parola nu este corecta!");
                alert.showAndWait();
                return;
            }

            Integer codUser = Integer.parseInt(sirCodUser);
            if (RAbonat.isSelected())
            {
                service.register(cnp,nume,adresa,telefon,parola,codUser,codAng,0);
                Stage mainStage = new Stage();
                mainStage.setTitle("Biblioteca");
                FXMLLoader loader = new FXMLLoader();
                Pane pane = loader.load(getClass().getResourceAsStream("/fxml/loginWindow.fxml"));
                LoginController controller = loader.getController();
                controller.setServer(service);
                Scene scene = new Scene(pane, 800, 600);
                mainStage.setScene(scene);
                mainStage.show();

                Stage currentStage = (Stage) signupAnchorPane.getScene().getWindow();
                currentStage.close();
            }
            else
            if (RBibliotecar.isSelected())
            {
                service.register(cnp,nume,adresa,telefon,parola,codUser,codUser,nrImpr);
                Stage mainStage = new Stage();
                mainStage.setTitle("Biblioteca");
                FXMLLoader loader = new FXMLLoader();
                Pane pane = loader.load(getClass().getResourceAsStream("/fxml/loginWindow.fxml"));
                LoginController controller = loader.getController();
                controller.setServer(service);
                Scene scene = new Scene(pane, 800, 600);
                mainStage.setScene(scene);
                mainStage.show();

                Stage currentStage = (Stage) signupAnchorPane.getScene().getWindow();
                currentStage.close();
            }
            else
                System.out.println("Selectati Abonat sau Bibliotecar!");
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void initialize(){

    }

    public void setServer(Service server)
    {
        this.service = server;
    }

}
