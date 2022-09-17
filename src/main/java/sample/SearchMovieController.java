package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.LoginDTO;

import java.io.IOException;

public class SearchMovieController {
    private HomeController home;
    private LoginDTO loginDTO;
    private Stage stage;
    @FXML
    private Label message;
    @FXML
    public ImageView onMovie;
    private Main main;

    public void init(String msg) {
        message.setText(msg);
        Image img = new Image(Main.class.getResourceAsStream("HomeProd.png"));
        onMovie.setImage(img);
    }
    public void onTitleButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchByTitle.fxml"));
        Parent root = loader.load();

        SearchByTitleController controller = loader.getController();
        controller.setController(this.home,loginDTO,this.stage);

        stage.setTitle("Search By Title of "+loginDTO.getUserName());
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void onReleaseYearButton(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchByReleaseYear.fxml"));
        Parent root = loader.load();

        SearchByReleaseYearController controller = loader.getController();
        controller.setController(this.home,loginDTO,this.stage);

        stage.setTitle("Search By Release Year of "+loginDTO.getUserName());
        stage.setScene(new Scene(root));
        stage.show();

    }



    public void onGenreButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchByGenre.fxml"));
        Parent root = loader.load();

        SearchByGenreController controller = loader.getController();
        controller.setController(this.home,loginDTO,this.stage);

        stage.setTitle("Search By Genre of "+loginDTO.getUserName());
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void onRunningTimeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchByRunningTime.fxml"));
        Parent root = loader.load();

        SearchByRunningTimeController controller = loader.getController();
        controller.setController(this.home,loginDTO,this.stage);

        stage.setTitle("Search By Running Time of "+loginDTO.getUserName());
        stage.setScene(new Scene(root));
        stage.show();

    }
    public void onHomeBackButton(ActionEvent actionEvent) throws Exception {
        main.setStage(this.stage);
        main.showHomePage(loginDTO.getUserName(),this.loginDTO);

    }

    public void setController(Main main,HomeController home, LoginDTO loginDTO, Stage stage) {

        this.main=main;
        this.home=home;
        this.loginDTO=loginDTO;
        this.stage=stage;
    }
}
