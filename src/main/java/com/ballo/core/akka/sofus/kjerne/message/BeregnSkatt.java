package com.ballo.core.akka.sofus.kjerne.message;

import com.ballo.core.akka.sofus.kjerne.domain.Beregningsgrunnlag;

import java.io.Serializable;
import java.util.List;

public class BeregnSkatt implements Serializable {
    public static final long serialVersionUID = 1;
    private final List<Beregningsgrunnlag> beregningsgrunnlagListe;

    public BeregnSkatt(List<Beregningsgrunnlag> beregningsgrunnlagListe) {
        this.beregningsgrunnlagListe = beregningsgrunnlagListe;
    }

    public List<Beregningsgrunnlag> getBeregningsgrunnlagListe() {
        return beregningsgrunnlagListe;
    }
}
