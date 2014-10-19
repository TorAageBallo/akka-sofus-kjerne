package com.ballo.core.akka.sofus.kjerne.message;

import java.io.Serializable;
import java.util.List;

    public class Beregn implements Serializable {
        public static final long serialVersionUID = 1;
    private final List<String> identifikatorer;

    public Beregn(List<String> identifikatorer) {
        this.identifikatorer = identifikatorer;
    }

    public List<String> getIdentifikatorer() {
        return identifikatorer;
    }
}
