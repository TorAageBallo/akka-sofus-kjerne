package com.ballo.core.akka.sofus.kjerne.message;

import com.ballo.core.akka.sofus.kjerne.domain.Beregningsgrunnlag;

import java.io.Serializable;
import java.util.List;

public class GrunnlagHentet implements Serializable{
    public static final long serialVersionUID = 1;
    private final List<Beregningsgrunnlag> beregningsgrunnlagListe;

    public GrunnlagHentet(List<Beregningsgrunnlag> beregningsgrunnlagListe) {
        this.beregningsgrunnlagListe = beregningsgrunnlagListe;
    }
}
