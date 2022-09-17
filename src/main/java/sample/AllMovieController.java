package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.Movie;
import server.TransferMovie;
import util.LoginDTO;

import java.io.IOException;

public class AllMovieController {
    private Stage stage;
    @FXML
    TextField receiver;
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
    TableColumn<Movie,Integer> budget;
    @FXML
    TableColumn<Movie,Integer> revenue;
    private LoginDTO loginDTO;
    private Main main;
    boolean init=true;
    boolean running=true;
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
    public void onTransferButton(ActionEvent actionEvent) throws IOException {
        String receiverCompany=receiver.getText();
        String senderCompany=loginDTO.getUserName();
        int selectedID=table.getSelectionModel().getSelectedIndex();
        Movie movie=table.getItems().get(selectedID);
        movie.setCompany(receiverCompany);
        loginDTO.getCompanyMovieList().remove(movie);
        table.getItems().remove(selectedID);
        loginDTO.setTransferMovie(new TransferMovie(movie,senderCompany,receiverCompany));
        //new WriteThread(main.getNetworkUtil(),loginDTO.getTransferMovie());
        main.getNetworkUtil().write(loginDTO.getTransferMovie());
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

//    public void onLoadButton(ActionEvent actionEvent) {
//            table.getItems().clear();
//            for(Movie m: loginDTO.getCompanyMovieList())
//            {
//                table.getItems().add(m);
//            }
//            table.refresh();
//    }
    public void load() {

        if (init) {
            initialize();
            init = false;
        }
        for(Movie m: loginDTO.getCompanyMovieList())
        {
            table.getItems().add(m);
        }
       new Thread(() -> {
           while (true) {
                try {
                    if(this.loginDTO.isReceivedStatus() )
                    {
                        //table.getItems().clear();
                        table.refresh();
                        this.loginDTO.setReceivedStatus(false);
                        Platform.runLater(() -> {
                            //table.getItems().clear();
                            //table.refresh();
//                        for(Movie m: loginDTO.getCompanyMovieList())
//                            {
//                                //table.getItems().add(m);
//                            }
                            ObservableList<Movie>obj=FXCollections.observableArrayList();
                            for(Movie m:loginDTO.getCompanyMovieList())
                            {
                                   obj.add(m);
                            }
                            //table.setEditable(true);
                            table.setItems(obj);

                        table.refresh();

                    });



                    }

//                    Platform.runLater(() -> {
//                        table.refresh();
//                    });
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();

    }
}
