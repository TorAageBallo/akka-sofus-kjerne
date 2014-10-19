package com.ballo.core.akka.sofus.kjerne.message;

import com.ballo.core.akka.sofus.kjerne.domain.Skattegrunnlag;

import java.io.Serializable;
import java.util.Map;

public class SkattegrunnlagHentet implements Serializable{
public static final long serialVersionUID = 1;
    private final Map<String, Skattegrunnlag> skattegrunnlagForId;

    public SkattegrunnlagHentet(Map<String, Skattegrunnlag> skattegrunnlagForId) {
        this.skattegrunnlagForId = skattegrunnlagForId;
    }

    public Map<String, Skattegrunnlag> getSkattegrunnlagForId() {
        return skattegrunnlagForId;
    }
}
