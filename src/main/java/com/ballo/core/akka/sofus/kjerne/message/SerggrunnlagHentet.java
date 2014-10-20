package com.ballo.core.akka.sofus.kjerne.message;

import com.ballo.core.akka.sofus.kjerne.domain.SERGgrunnlag;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

public class SerggrunnlagHentet implements Serializable{
    public static final long serialVersionUID = 1;
    private final Map<String, SERGgrunnlag> serggrunnlagForId;
    private final UUID meldingId;

    public SerggrunnlagHentet(Map<String, SERGgrunnlag> serggrunnlagForId, UUID meldingId) {
        this.serggrunnlagForId = serggrunnlagForId;
        this.meldingId = meldingId;
    }

    public UUID getMeldingId() {
        return meldingId;
    }

    public Map<String, SERGgrunnlag> getSerggrunnlagForId() {
        return serggrunnlagForId;
    }
}
