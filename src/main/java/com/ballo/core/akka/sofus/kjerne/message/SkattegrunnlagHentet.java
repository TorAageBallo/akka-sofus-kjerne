package com.ballo.core.akka.sofus.kjerne.message;

import com.ballo.core.akka.sofus.kjerne.domain.Skattegrunnlag;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

public class SkattegrunnlagHentet implements Serializable{
    public static final long serialVersionUID = 1;
    private final Map<String, Skattegrunnlag> skattegrunnlagForId;
    private final UUID meldingId;

    public SkattegrunnlagHentet(Map<String, Skattegrunnlag> skattegrunnlagForId, UUID meldingId) {
        this.skattegrunnlagForId = skattegrunnlagForId;
        this.meldingId = meldingId;
    }

    public UUID getMeldingId() {
        return meldingId;
    }

    public Map<String, Skattegrunnlag> getSkattegrunnlagForId() {
        return skattegrunnlagForId;
    }
}
