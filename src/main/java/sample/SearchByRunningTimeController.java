package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.Movie;
import util.LoginDTO;

import java.io.IOException;

public class SearchByRunningTimeController {
    private Stage stage;
    @FXML
    TextField minTime;
    @FXML
    TextField maxTime;
    @FXML
    TableView<Movie> table;
    @FXML
    TableColumn<Movie,String> title;
    @FXML
    TableColumn<Movie,Integer> year;
    @FXML
    TableColumn<Movie,String> genre1;
    @FXML
    TableColumn<Movie,String> genre2;
    @FXML
    TableColumn<Movie,String> genre3;
    @FXML
    TableColumn<Movie,Integer> time;
    @FXML
    TableColumn<Movie,String> company;
    @FXML
    TableColumn<Movie,Long> budget;
    @FXML
    TableColumn<Movie,Long> revenue;
    private LoginDTO loginDTO;

    private HomeController home;

    @FXML
    public void initialize(){
        title.setCellValueFactory(new PropertyValueFactory<>("name"));
        year.setCellValueFactory(new PropertyValueFactory<>("release_year"));
        genre1.setCellValueFactory(new PropertyValueFactory<>("genre1"));
        genre2.setCellValueFactory(new PropertyValueFactory<>("genre2"));
        genre3.setCellValueFactory(new PropertyValueFactory<>("genre3"));
        time.setCellValueFactory(new PropertyValueFactory<>("running_time"));
        company.setCellValueFactory(new PropertyValueFactory<>("company"));
        budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
        revenue.setCellValueFactory(new PropertyValueFactory<>("revenue"));

    }
    public void setController(HomeController home, LoginDTO loginDTO, Stage stage)
    {

        this.home=home;
        this.stage=stage;
        this.loginDTO=loginDTO;
    }

    public void onSearchButton(ActionEvent actionEvent) {
        int r1=Integer.parseInt(minTime.getText());
        int r2=Integer.parseInt(maxTime.getText());
        boolean ok=false;
        table.getItems().clear();
        for(Movie m:loginDTO.getCompanyMovieList())
        {
            if(r1<=m.getRunning_time() && m.getRunning_time()<=r2)
            {
                table.getItems().add(m);
                ok=true;
            }
        }
        if(!ok)
        {
            showNotFoundAlert();
        }

    }
    public void showNotFoundAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Can Not Find This Movie!");
        alert.setContentText("The Movie With This Running Time Range Is Not In List.");
        alert.showAndWait();
        minTime.setText(null);
        maxTime.setText(null);
    }

    public void onSearchMovieBackButton(ActionEvent actionEvent) throws IOException {
        home.showSearchMovie(this.stage);
    }
}
