package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.LoginDTO;
import util.NetworkUtil;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;
    private LoginDTO loginDTO;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public LoginDTO getLoginDTO() {
        return loginDTO;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage(stage);
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
        //new WriteThread(networkUtil,null);
    }

    public void showLoginPage(Stage stage) throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);
        //stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showHomePage(String userName, LoginDTO loginDTO) throws Exception {
        this.loginDTO=loginDTO;
        //new WriteThread(networkUtil,loginDTO.getTransferMovie());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NewHome.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        controller.init(userName);
        controller.setMain(this,this.loginDTO,this.stage);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root));
        stage.show();
        //new WriteThread(networkUtil,loginDTO.getTransferMovie());

    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}
