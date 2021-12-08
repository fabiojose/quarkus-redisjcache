package io.quarkiverse.jcacheredis;

import java.io.Serializable;

public class MyResponse implements Serializable {

    private String pong;

    public MyResponse() {
    }

    public MyResponse(String pong) {
        this.pong = pong;
    }

    public String getPong() {
        return pong;
    }

    public void setPong(String pong) {
        this.pong = pong;
    }
}
