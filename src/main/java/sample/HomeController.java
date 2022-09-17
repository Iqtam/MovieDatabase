package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.LoginDTO;

import java.io.IOException;

public class HomeController {
    private LoginDTO loginDTO;
    @FXML
    public ImageView onMovie;
    private Main main;

    @FXML
    private Label message;
    private Stage stage;
    //private Parent root;
    private  Scene scene;

    @FXML
    private Button button;

    public void init(String msg) {
        message.setText(msg);
        Image img = new Image(Main.class.getResourceAsStream("HomeProd.png"));
        onMovie.setImage(img);
    }



    void setMain(Main main, LoginDTO loginDTO, Stage stage) {

        this.main = main;
        this.loginDTO=loginDTO;
        this.stage=stage;
    }


    public void onSearchMovieButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchMovie.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SearchMovieController controller = loader.getController();
        controller.init(loginDTO.getUserName());
        controller.setController(this.main,this,loginDTO,this.stage);
        stage.setTitle("Search Movie of "+loginDTO.getUserName());
        stage.setScene(new Scene(root));
        stage.show();

    }
    @FXML
    public void onAllMovieButton(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AllMovie.fxml"));
        Parent root = loader.load();

        // Loading the controller
        AllMovieController controller = loader.getController();
        controller.setController(this.main,loginDTO,this.stage);
        Platform.runLater(()->{
            controller.getTable().getItems().clear();
            controller.load();
        });

//        for(Movie m: loginDTO.getCompanyMovieList())
//        {
//            controller.getTable().getItems().add(m);
//        }

        // Set the primary stage
        //stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setTitle("All Movie of "+loginDTO.getUserName());
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void onAddMovieHomeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddMovie.fxml"));
        Parent root = loader.load();

        AddMovieController controller = loader.getController();
        controller.setController(this.main,loginDTO,this.stage);

        stage.setTitle("Add Movie of "+loginDTO.getUserName());
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void onTransferButton(ActionEvent actionEvent) throws IOException {

    }

    public void onProfitButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TotalProfit.fxml"));
        Parent root = loader.load();

        TotalProfitController controller = loader.getController();
        controller.setController(this.main,loginDTO,this.stage);
        Platform.runLater(()->{
            controller.getTable().getItems().clear();
            controller.load();
        });

        stage.setTitle("Total Profit of "+loginDTO.getUserName());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onMaxRevenueButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MaxRevenue.fxml"));
        Parent root = loader.load();

        MaxRevenueController controller = loader.getController();
        controller.setController(this.main,loginDTO,this.stage);
        Platform.runLater(()->{
            controller.getTable().getItems().clear();
            controller.load();
        });

        stage.setTitle("Movies with Maximum Revenue of "+loginDTO.getUserName());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onRecentMovieButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RecentMovie.fxml"));
        Parent root = loader.load();

        RecentMovieController controller = loader.getController();
        controller.setController(this.main,loginDTO,this.stage);
        Platform.runLater(()->{
            controller.getTable().getItems().clear();
            controller.load();
        });

        stage.setTitle("Recent Movie of "+loginDTO.getUserName());
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void onLogoutButton(ActionEvent actionEvent) {
        try {
            main.showLoginPage(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onHomeBackButton(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NewHome.fxml"));
        Parent root = loader.load();

        stage.setTitle("Home");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void showSearchMovie(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchMovie.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SearchMovieController controller = loader.getController();
        controller.init(loginDTO.getUserName());
        controller.setController(this.main,this,loginDTO,this.stage);
        stage.setTitle("Search Movie of "+loginDTO.getUserName());
        stage.setScene(new Scene(root));
        stage.show();
    }
}
