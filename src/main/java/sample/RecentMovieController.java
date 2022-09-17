package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.Movie;
import util.LoginDTO;

public class RecentMovieController {
    private Stage stage;
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
    private Main main;
    boolean init=true;

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

    public void onHomeBackButton(ActionEvent actionEvent) throws Exception {
        main.setStage(this.stage);
        main.showHomePage(loginDTO.getUserName(),this.loginDTO);
    }
    public void setController(Main main,LoginDTO loginDTO, Stage stage)
    {
        this.main=main;
        this.stage=stage;
        this.loginDTO=loginDTO;
    }

    public TableView<Movie> getTable() {
        return table;
    }

    public void load() {

        if (init) {
            initialize();
            init = false;
        }
        int latest_year=0;

        for(Movie m:loginDTO.getCompanyMovieList())
        {
            latest_year= Math.max(latest_year, m.getRelease_year());
        }

        for(Movie m:loginDTO.getCompanyMovieList())
        {
            if(m.getRelease_year()==latest_year)
            {
                table.getItems().add(m);
            }
        }

    }

}
