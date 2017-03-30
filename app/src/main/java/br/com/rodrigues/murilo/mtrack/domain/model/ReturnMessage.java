package br.com.rodrigues.murilo.mtrack.domain.model;

public class ReturnMessage {
    private boolean ok;
    private String message;

    public ReturnMessage() {
    }

    public ReturnMessage(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return ok + " : " + message;
    }
}
