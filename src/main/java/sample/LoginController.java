package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.LoginDTO;

import java.io.IOException;


public class LoginController {

    private Main main;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;
    @FXML
    private ImageView image;
    @FXML
    ImageView userimg;
    @FXML
    ImageView keyimg;
    @FXML
    ImageView companyimg;
    @FXML
    private Button loginButton;

    @FXML
    void loginAction(ActionEvent event) {
        String userName = userText.getText();
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        try {
            main.getNetworkUtil().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    void setMain(Main main) {

        this.main = main;
        Image img = new Image(Main.class.getResourceAsStream("LoginMovie.jpg"));
        image.setImage(img);
        img=new Image(Main.class.getResourceAsStream("new-password.gif"));
        keyimg.setImage(img);
        img=new Image(Main.class.getResourceAsStream("user.png"));
        userimg.setImage(img);
        img=new Image(Main.class.getResourceAsStream("Company.jpg"));
        companyimg.setImage(img);

    }

}
