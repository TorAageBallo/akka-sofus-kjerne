package com.ballo.core.akka.sofus.kjerne.domain;

public class Beregningsgrunnlag {
    private final String identifikator;
    private Skattegrunnlag skattegrunnlag;
    private SERGgrunnlag serggrunnlag;

    public Beregningsgrunnlag(String identifikator) {
        this.identifikator = identifikator;
    }

    public void setSkattegrunnlag(Skattegrunnlag skattegrunnlag) {
        this.skattegrunnlag = skattegrunnlag;
    }

    public Skattegrunnlag getSkattegrunnlag() {
        return skattegrunnlag;
    }

    public void setSerggrunnlag(SERGgrunnlag serggrunnlag) {
        this.serggrunnlag = serggrunnlag;
    }

    public SERGgrunnlag getSerggrunnlag() {
        return serggrunnlag;
    }

    public String getIdentifikator() {
        return identifikator;
    }
}
