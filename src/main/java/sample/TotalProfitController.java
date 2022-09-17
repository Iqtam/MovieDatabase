package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.Movie;
import util.LoginDTO;

public class TotalProfitController {

    private Stage stage;
    @FXML
    TableView<Movie> table;
    @FXML
    TableColumn<Movie,String> title;
    @FXML
    TableColumn<Movie,Integer> year;

    @FXML
    TableColumn<Movie,String> company;
    @FXML
    TableColumn<Movie,Long> budget;
    @FXML
    TableColumn<Movie,Long> revenue;
    @FXML
    TableColumn<Movie,Long> profit;
    @FXML
    TextField totalProfit;
    private LoginDTO loginDTO;
    private Main main;
    boolean init=true;

    @FXML
    public void initialize(){
        title.setCellValueFactory(new PropertyValueFactory<>("name"));
        year.setCellValueFactory(new PropertyValueFactory<>("release_year"));
        company.setCellValueFactory(new PropertyValueFactory<>("company"));
        budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
        revenue.setCellValueFactory(new PropertyValueFactory<>("revenue"));
        profit.setCellValueFactory(new PropertyValueFactory<>("profit"));

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

        table.getItems().clear();

        for(Movie m:loginDTO.getCompanyMovieList())
        {
            table.getItems().add(m);
        }
        long sumProfit=0;
        for(Movie m:loginDTO.getCompanyMovieList())
        {
            sumProfit+=m.getProfit();
        }

        totalProfit.setText(Long.toString(sumProfit));

    }

}
