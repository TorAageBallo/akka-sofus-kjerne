package com.ballo.core.akka.sofus.kjerne.message;

import com.ballo.core.akka.sofus.kjerne.domain.SERGgrunnlag;

import java.util.Map;

public class SerggrunnlagHentet {
    private final Map<String, SERGgrunnlag> serggrunnlagForId;

    public SerggrunnlagHentet(Map<String, SERGgrunnlag> serggrunnlagForId) {
        this.serggrunnlagForId = serggrunnlagForId;
    }

    public Map<String, SERGgrunnlag> getSerggrunnlagForId() {
        return serggrunnlagForId;
    }
}
