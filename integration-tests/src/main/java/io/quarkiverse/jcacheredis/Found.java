package io.quarkiverse.jcacheredis;

import java.io.Serializable;

public class Found implements Serializable {

    private String term;

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }
}
