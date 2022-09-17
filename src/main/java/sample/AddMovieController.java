package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.Movie;
import util.LoginDTO;

import java.io.IOException;

public class AddMovieController {
    private Stage stage;
    private LoginDTO loginDTO;
    private Main main;
    @FXML
    TextField title;
    @FXML
    TextField year;
    @FXML
    TextField genre1;
    @FXML
    TextField genre2;
    @FXML
    TextField genre3;
    @FXML
    TextField time;
    @FXML
    TextField budget;
    @FXML
    TextField revenue;


    public void onHomeBackButton(ActionEvent actionEvent) throws Exception {
        main.setStage(this.stage);
        main.showHomePage(loginDTO.getUserName(),this.loginDTO);

    }
    public void showAddMovieAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("SuccessFully Added!");
        alert.setContentText("The Movie You Have Provided Is Successfully Added.");
        alert.showAndWait();
    }
    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Can Not Add This Movie!");
        alert.setContentText("The Movie You Have Provided Is Already In List.");
        alert.showAndWait();

    }
    public void onAddButton(ActionEvent actionEvent) throws IOException {
        Movie movie=new Movie();
        movie.setName(title.getText());
        movie.setRelease_year(Integer.parseInt(year.getText()));
        movie.setGenre1(genre1.getText());
        movie.setGenre2(genre2.getText());
        movie.setGenre3(genre3.getText());
        movie.setRunning_time(Integer.parseInt(time.getText()));
        movie.setCompany(loginDTO.getUserName());
        long longBudget=Long.parseLong(budget.getText());
        movie.setBudget(longBudget);
        long longRevenue=Long.parseLong(revenue.getText());
        movie.setRevenue(longRevenue);
        movie.setProfit(longRevenue-longBudget);
        boolean shouldAdd=true;
        for(Movie m: loginDTO.getCompanyMovieList())
        {
            if(m.getName().equalsIgnoreCase(movie.getName()))
            {
                shouldAdd=false;
                break;
            }
        }
        if(shouldAdd)
        {
            main.getNetworkUtil().write(movie);
            showAddMovieAlert();
        }
        else
        {
            showErrorAlert();

        }

    }



    public void setController(Main main,LoginDTO loginDTO, Stage stage)
    {
        this.main=main;
        this.stage=stage;
        this.loginDTO=loginDTO;
    }

}
