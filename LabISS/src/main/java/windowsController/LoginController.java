package windowsController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Utilizator;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.AbonatRepo;
import repository.BibliotecarRepo;
import repository.CarteRepo;
import repository.ImprumutRepo;
import services.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LoginController {
    private Service service;
//    private static SessionFactory sessionFactory;

    @FXML
    public TextField UsernameTxtF;
    @FXML
    public TextField PasswordTxtF;
    @FXML
    public AnchorPane mainAnchorPane;


    public void initialize(){
        //this.setService();
    }

    @FXML
    public void login(ActionEvent actionEvent) throws IOException {
        if (PasswordTxtF.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Parola nu este corecta!");
            alert.showAndWait();
            return;
        }
        if (UsernameTxtF.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Username-ul nu este corect!");
            alert.showAndWait();
            return;
        }
        try{
            Utilizator user = new Utilizator(PasswordTxtF.getText(),Integer.parseInt(UsernameTxtF.getText()));
            try {

                System.out.println("Deschid fereastra noua");
                Stage mainStage = new Stage();
                mainStage.setTitle("Biblioteca");
                String which = service.login(user);
                if (which.equals("bibliotecar"))
                {
                    FXMLLoader loader = new FXMLLoader();
                    Pane pane = loader.load(getClass().getResourceAsStream("/fxml/bibliotecarWindow.fxml"));
                    BibliotecarController controller = loader.getController();
                    controller.setServer(service);
                    controller.setCurrentUser(user);
                    Scene scene = new Scene(pane, 800, 600);
                    mainStage.setScene(scene);
                    mainStage.show();

                    Stage currentStage = (Stage) mainAnchorPane.getScene().getWindow();
                    currentStage.close();
                }
                else
                {
                    FXMLLoader loader = new FXMLLoader();
                    Pane pane = loader.load(getClass().getResourceAsStream("/fxml/abonatWindow.fxml"));
                    AbonatController controller = loader.getController();
                    controller.setServer(service);
                    controller.setCurrentUser(user);
                    Scene scene = new Scene(pane, 800, 600);
                    mainStage.setScene(scene);
                    mainStage.show();

                    Stage currentStage = (Stage) mainAnchorPane.getScene().getWindow();
                    currentStage.close();
                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(e.toString());
                alert.showAndWait();
            }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(e.toString());
            alert.showAndWait();
        }
    }

    @FXML
    public void signUp(){
        try {
            System.out.println("Deschid fereastra noua");
            Stage mainStage = new Stage();
            mainStage.setTitle("Bibliotecar");
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResourceAsStream("/fxml/signupWindow.fxml"));
            RegisterController controller = loader.getController();
            controller.setServer(service);
            Scene scene = new Scene(pane, 800, 600);
            mainStage.setScene(scene);
            mainStage.show();

            Stage currentStage = (Stage) mainAnchorPane.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }

//    static void initializeFactory() {
//        // A SessionFactory is set up once for an application!
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure() // configures settings from hibernate.cfg.xml
//                .build();
//        try {
//            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
//        }
//        catch (Exception e) {
//            System.err.println("Exceptie "+e);
//            StandardServiceRegistryBuilder.destroy( registry );
//        }
//    }
//
//    static void closeFactory() {
//        if ( sessionFactory != null ) {
//            sessionFactory.close();
//        }
//    }

//    private void setService(){
//        Properties props=new Properties();
//        try {
//            props.load(new FileReader("bd.config"));
//        } catch (IOException e) {
//            System.out.println("Cannot find bd.config "+e);
//        }
//        initializeFactory();
//        ImprumutRepo imprumutRepo = new ImprumutRepo(sessionFactory);
//
//        AbonatRepo abonatRepo = new AbonatRepo(props);
//        BibliotecarRepo bibliotecarRepo = new BibliotecarRepo(props);
//        CarteRepo carteRepo = new CarteRepo(props);
//
//        Service serv = new Service(abonatRepo,bibliotecarRepo,carteRepo, imprumutRepo);
//
//        service = serv;
//        //closeFactory();
//    }

    public void setServer(Service server)
    {
        this.service = server;
    }
}
