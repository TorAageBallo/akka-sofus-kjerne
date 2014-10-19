package com.ballo.core.akka.sofus.kjerne.actor;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import com.ballo.core.akka.sofus.kjerne.domain.BeregnetSkattDokument;
import com.ballo.core.akka.sofus.kjerne.domain.Beregningsgrunnlag;
import com.ballo.core.akka.sofus.kjerne.message.BeregnSkatt;
import com.ballo.core.akka.sofus.kjerne.repository.SkatteinfoRepository;
import com.google.common.collect.Lists;

import java.util.List;

public class SkatteberegningActor extends AbstractActor {

    public SkatteberegningActor() {
        receive(ReceiveBuilder.match(BeregnSkatt.class, message -> {
                    List<BeregnetSkattDokument> beregnetSkattDokumenter = beregnSkatt(message.getBeregningsgrunnlagListe());
                    SkatteinfoRepository.lagre(beregnetSkattDokumenter);
                }
        ).build());
    }

    private List<BeregnetSkattDokument> beregnSkatt(List<Beregningsgrunnlag> beregningsgrunnlagListe) {
        System.out.println("Beregner skatt for " + beregningsgrunnlagListe.size() + " beregningsgrunnlag.");

        List<BeregnetSkattDokument> beregnetSkattDokumenter = Lists.newArrayList();
        for (Beregningsgrunnlag beregningsgrunnlag : beregningsgrunnlagListe) {
            beregnetSkattDokumenter.add(new BeregnetSkattDokument(beregningsgrunnlag.getIdentifikator()));
        }
        return beregnetSkattDokumenter;
    }
}
