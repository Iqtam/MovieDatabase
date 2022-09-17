package sample;

import javafx.application.Platform;
import server.Movie;
import server.TransferMovie;
import util.LoginDTO;

import java.io.IOException;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        //System.out.println(loginDTO.getUserName());
                        //System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(() -> {
                            if (loginDTO.isStatus()) {
                                try {
                                    main.showHomePage(loginDTO.getUserName(),loginDTO);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                main.showAlert();
                            }

                        });
                    }
                    else if(o instanceof TransferMovie)
                    {
                        TransferMovie obj=(TransferMovie) o;
                        main.getLoginDTO().getCompanyMovieList().add(obj.getMovie());
                        main.getLoginDTO().setReceivedStatus(true);
                        //System.out.println(obj.getMovie().getName());
                    }
                    else if(o instanceof Movie)
                    {
                        Movie obj=(Movie) o;
                        main.getLoginDTO().getCompanyMovieList().add(obj);

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



