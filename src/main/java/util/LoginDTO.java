package util;

import server.Movie;
import server.TransferMovie;

import java.io.Serializable;
import java.util.List;

public class LoginDTO implements Serializable {

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private boolean receivedStatus=false;
    private String userName;
    private String password;
    private boolean status;
    private List<Movie> companyMovieList;
    private TransferMovie transferMovie;

    public void setTransferMovie(TransferMovie transferMovie) {
        this.transferMovie = transferMovie;
    }

    public TransferMovie getTransferMovie() {
        return transferMovie;
    }

    public void setCompanyMovieList(List<Movie> companyMovieList) {
        this.companyMovieList = companyMovieList;
    }

    public List<Movie> getCompanyMovieList() {
        return companyMovieList;
    }

    public void setReceivedStatus(boolean b) {
        receivedStatus=b;
    }

    public boolean isReceivedStatus() {
        return receivedStatus;
    }
}

