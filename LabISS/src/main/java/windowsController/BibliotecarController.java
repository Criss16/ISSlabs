package windowsController;

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
import services.Service;

import java.io.IOException;

public class BibliotecarController {
    private Service service;

    @FXML
    public TextField TitluTxt;
    @FXML
    public TextField AutorTxt;
    @FXML
    public TextField CodExTxt;
    @FXML
    public TextField CategorieTxt;
    @FXML
    public ListView<Text> ListaInitiala;
    @FXML
    public AnchorPane mainAnchorPane;

    private Utilizator currentUser;

    public void setCurrentUser(Utilizator currentUser) {
        this.currentUser = currentUser;
    }


    public void initialize(){
    }

    @FXML
    public void loadTableCarti(){
        ListaInitiala.getItems().clear();
        System.out.println("Incep incarcarea cartilor");
        for (Carte c : service.getAllCarti())
        {
                Text text = new Text();
                text.setText(createInstance(c));
                ListaInitiala.getItems().add(text);
        }
    }

    public String createInstance(Carte c)
    {
        return c.getId() + " / " + c.getTitlu() + " / " + c.getAutor() + " / " + c.getCodExemplar() + " / " + c.getCategorie() + " / " + c.getStare() + " / " + c.getData() + " / ";
    }

    @FXML
    public void visualiseBooks(ActionEvent actionEvent) throws IOException{
        this.loadTableCarti();
    }

    @FXML
    public void addBook(ActionEvent actionEvent) throws IOException{
        String titlu = TitluTxt.getText();
        String autor = AutorTxt.getText();
        String sirCodEx = CodExTxt.getText();

        String categorie = CategorieTxt.getText();

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
        if (categorie.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Categoria nu este corecta!");
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
        service.addCarte(titlu,autor,codEx,categorie);
        ListaInitiala.getItems().clear();
    }

    @FXML
    public void deleteBook(ActionEvent actionEvent) throws IOException{
        String[] selection = ListaInitiala.getSelectionModel().getSelectedItem().toString().split(" / ");
        Integer bookID = Integer.parseInt(selection[0].substring(11));

        service.deleteCarte(bookID);
        ListaInitiala.getItems().clear();
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
