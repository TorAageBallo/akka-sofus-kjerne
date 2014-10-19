package com.ballo.core.akka.sofus.kjerne.domain;

public class Beregningsgrunnlag {
    private final String identifikator;
    private Skattegrunnlag skattegrunnlag;
    private SERGgrunnlag SERGgrunnlag;

    public Beregningsgrunnlag(String identifikator) {
        this.identifikator = identifikator;
    }

    public void setSkattegrunnlag(Skattegrunnlag skattegrunnlag) {
        this.skattegrunnlag = skattegrunnlag;
    }

    public Skattegrunnlag getSkattegrunnlag() {
        return skattegrunnlag;
    }

    public void setSERGgrunnlag(SERGgrunnlag SERGgrunnlag) {
        this.SERGgrunnlag = SERGgrunnlag;
    }

    public SERGgrunnlag getSERGgrunnlag() {
        return SERGgrunnlag;
    }

    public String getIdentifikator() {
        return identifikator;
    }
}
