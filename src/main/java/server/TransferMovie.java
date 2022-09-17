package server;

import java.io.Serializable;

public class TransferMovie implements Serializable {
    private Movie movie;
    private String sender;
    private String receiver;

    public TransferMovie(Movie movie, String sender, String receiver) {
        this.movie=movie;
        this.sender=sender;
        this.receiver=receiver;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }
}
