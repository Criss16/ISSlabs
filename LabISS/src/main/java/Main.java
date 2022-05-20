import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.AbonatRepo;
import repository.BibliotecarRepo;
import repository.CarteRepo;
import repository.ImprumutRepo;
import services.Service;
import windowsController.LoginController;
import windowsController.ServerController;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main extends Application {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        launch(args);
    }

    static void initializeFactory() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exceptie "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void closeFactory() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    private Service setService(){
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        initializeFactory();
        ImprumutRepo imprumutRepo = new ImprumutRepo(sessionFactory);

        AbonatRepo abonatRepo = new AbonatRepo(props);
        BibliotecarRepo bibliotecarRepo = new BibliotecarRepo(props);
        CarteRepo carteRepo = new CarteRepo(props);

        Service serv = new Service(abonatRepo,bibliotecarRepo,carteRepo, imprumutRepo);

        //closeFactory();
        return serv;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Service service = setService();
        primaryStage.setTitle("Server Biblioteca");
        FXMLLoader loader = new FXMLLoader();
        Pane pane = loader.load(getClass().getResourceAsStream("fxml/serverWindow.fxml"));

        ServerController serverController = loader.getController();

        serverController.setServer(service);

        loader.setController(serverController);

        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
