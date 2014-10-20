package com.ballo.core.akka.sofus.kjerne.message;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class HentGrunnlag implements Serializable {
    public static final long serialVersionUID = 1;
    private final List<String> identifikatorer;
    private final UUID meldingId;

    public HentGrunnlag(List<String> identifikatorer) {
        this.identifikatorer = identifikatorer;
        this.meldingId = UUID.randomUUID();
    }

    public List<String> getIdentifikatorer() {
        return identifikatorer;
    }

    public UUID getMeldingId() {
        return meldingId;
    }
}
