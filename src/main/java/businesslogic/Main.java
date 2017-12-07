package businesslogic;

import gui.controllers.*;
import gui.facade.Facade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    public static Facade facade = new Facade();
    private static Stage myStage;

    @Override
    public void start(Stage stage) throws Exception{
        myStage = stage;

        //Start gui
        String fxmlFile = "/StartWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(Main.class.getClass().getResourceAsStream(fxmlFile));
        Scene scene = new Scene(root);
        myStage.setScene(scene);
        myStage.show();

    }

    public static void main(String[] args) throws Exception {

        launch(args);
    }

    public static void showStart() throws IOException {
        Stage stage = new Stage();
        String fxmlFile = "/StartWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(Main.class.getClass().getResourceAsStream(fxmlFile));
        StartWindowController swc = loader.getController();
        //swc.init();
        Scene scene = new Scene(root);
        myStage.setScene(scene);
    }
    public static void showErrorWindow(int code) throws Exception {
        Stage stage = new Stage();
        String fxmlFile = "/ErrorWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(Main.class.getClass().getResourceAsStream(fxmlFile));
        ErrorWindowController ewc = loader.getController();
        ewc.init(code, stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void showRegistrationWindow() throws IOException {
        String fxmlFile = "/RegistrationWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(Main.class.getClass().getResourceAsStream(fxmlFile));
        RegistrationWindowController rwc = loader.getController();
        rwc.init();
        Scene scene = new Scene(root);
        myStage.setScene(scene);
    }

    public static void showClientWindow(String login) throws Exception {
        String fxmlFile = "/ClientWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(Main.class.getClass().getResourceAsStream(fxmlFile));
        ClientWindowController cwc = loader.getController();
        cwc.init(login);
        Scene scene = new Scene(root);
        myStage.setScene(scene);
    }

    public static void showManagerWindow(String login) throws Exception {
        String fxmlFile = "/ManagerWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(Main.class.getClass().getResourceAsStream(fxmlFile));
        ManagerWindowController mwc = loader.getController();
        mwc.init(login);
        Scene scene = new Scene(root);
        myStage.setScene(scene);
    }

    public static void showProviderWindow(String login) throws Exception {
        String fxmlFile = "/ProviderWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(Main.class.getClass().getResourceAsStream(fxmlFile));
        ProviderWindowController pwc = loader.getController();
        pwc.init(login);
        Scene scene = new Scene(root);
        myStage.setScene(scene);
    }

    public static void showCreateClientOrderWindow(String login) throws Exception {
        String fxmlFile = "/CreateClientOrderWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(Main.class.getClass().getResourceAsStream(fxmlFile));
        CreateClientOrderWindowController ccowc = loader.getController();
        ccowc.init(login);
        Scene scene = new Scene(root);
        myStage.setScene(scene);
    }

    public static void showCreateManagerOrderWindow(String login) throws Exception {
        String fxmlFile = "/CreateManagerOrderWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(Main.class.getClass().getResourceAsStream(fxmlFile));
        CreateManagerOrderWindowController cmowc = loader.getController();
        cmowc.init(login);
        Scene scene = new Scene(root);
        myStage.setScene(scene);
    }

}
