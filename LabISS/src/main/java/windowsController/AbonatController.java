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
import model.Carte;
import model.Utilizator;
import observer.Observer;
import services.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AbonatController implements Observer {
    private Service service;

    @FXML
    public TextField TitluTxt;
    @FXML
    public TextField AutorTxt;
    @FXML
    public TextField CodTxt;
    @FXML
    public ListView<Text> ListaDisponibile;
    @FXML
    public ListView<Text> ListaImprumuturi;
    @FXML
    public AnchorPane mainAnchorPane;

    private Utilizator currentUser;

    public void setCurrentUser(Utilizator currentUser) {
        this.currentUser = currentUser;
        this.currentUser.setId(service.getUserId(currentUser.getCodUser(), currentUser.getParola()));
        service.addObserver(this);
    }


    public void initialize(){
    }


    @FXML
    public void visualiseDisponibile(){
        this.loadTableCartiDisponibile();
    }

    @FXML
    public void visualiseImprumuturi(){
        this.loadTableCartiImprumutate();
    }

    @FXML
    public void loadTableCartiDisponibile(){
        ListaDisponibile.getItems().clear();
        System.out.println("Incep incarcarea cartilor");
        for (Carte c : service.getAllCartiDisponibile())
        {
            Text text = new Text();
            text.setText(createInstance(c));
            ListaDisponibile.getItems().add(text);
        }
    }

    @FXML
    public void loadTableCartiImprumutate(){
        ListaImprumuturi.getItems().clear();
        System.out.println("Incep incarcarea cartilor");
        for (Carte c : service.getAllCartiImprumutate(currentUser.getId()))
        {
            Text text = new Text();
            text.setText(createInstance(c));
            ListaImprumuturi.getItems().add(text);
        }
    }

    public String createInstance(Carte c)
    {
        return c.getId() + " / " + c.getTitlu() + " / " + c.getAutor() + " / " + c.getCodExemplar() + " / " + c.getCategorie() + " / " + c.getStare() + " / " + c.getData() + " / ";
    }

    @FXML
    public void imprumutaCarte()
    {
        String titlu = TitluTxt.getText();
        String autor = AutorTxt.getText();
        String sirCodEx = CodTxt.getText();

        if (titlu.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Titlul nu este corect!");
            alert.showAndWait();
            return;
        }
        if (autor.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Autorul nu este corect!");
            alert.showAndWait();
            return;
        }
        if (sirCodEx.equals("") || !sirCodEx.matches("[0-9]*"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Codul de exemplar nu este corect!");
            alert.showAndWait();
            return;
        }
        Integer codEx = Integer.parseInt(sirCodEx);

        try{
            service.addImprumut(titlu,autor,codEx,currentUser.getId());
            this.loadTableCartiDisponibile();
            this.loadTableCartiImprumutate();
        }catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(ex.toString());
            alert.showAndWait();
        }

    }

    @FXML
    public void returneazaCarte()
    {
        String titlu = TitluTxt.getText();
        String autor = AutorTxt.getText();
        String sirCodEx = CodTxt.getText();

        if (titlu.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Titlul nu este corect!");
            alert.showAndWait();
            return;
        }
        if (autor.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Autorul nu este corect!");
            alert.showAndWait();
            return;
        }
        if (sirCodEx.equals("") || !sirCodEx.matches("[0-9]*"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Codul de exemplar nu este corect!");
            alert.showAndWait();
            return;
        }
        Integer codEx = Integer.parseInt(sirCodEx);

        try{
            service.returnareCarte(titlu,autor,codEx,currentUser.getId());
            this.loadTableCartiDisponibile();
            this.loadTableCartiImprumutate();
        }catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(ex.toString());
            alert.showAndWait();
        }
    }

    @FXML
    public void prelungesteTermen()
    {
        String titlu = TitluTxt.getText();
        String autor = AutorTxt.getText();
        String sirCodEx = CodTxt.getText();

        if (titlu.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Titlul nu este corect!");
            alert.showAndWait();
            return;
        }
        if (autor.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Autorul nu este corect!");
            alert.showAndWait();
            return;
        }
        if (sirCodEx.equals("") || !sirCodEx.matches("[0-9]*"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Codul de exemplar nu este corect!");
            alert.showAndWait();
            return;
        }
        Integer codEx = Integer.parseInt(sirCodEx);

        try{
            service.prelungireTermen(titlu,autor,codEx);
            this.loadTableCartiDisponibile();
            this.loadTableCartiImprumutate();
        }catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(ex.toString());
            alert.showAndWait();
        }
    }

    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        service.removeObserver(this);
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


    @Override
    public void update() {
        System.out.println("Ajung in functia Update din Abonat");
        loadTableCartiDisponibile();
        loadTableCartiImprumutate();
    }
}
