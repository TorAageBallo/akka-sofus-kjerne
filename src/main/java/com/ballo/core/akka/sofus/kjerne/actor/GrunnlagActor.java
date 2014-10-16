package com.ballo.core.akka.sofus.kjerne.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.pf.ReceiveBuilder;
import com.ballo.core.akka.sofus.kjerne.domain.Beregningsgrunnlag;
import com.ballo.core.akka.sofus.kjerne.message.Beregn;
import com.ballo.core.akka.sofus.kjerne.message.GrunnlagHentet;
import com.ballo.core.akka.sofus.kjerne.message.HentGrunnlag;
import com.google.common.collect.Lists;

import java.util.List;

public class GrunnlagActor extends AbstractActor {

    public GrunnlagActor() {
        receive(ReceiveBuilder.match(HentGrunnlag.class, message -> {
                    List<Beregningsgrunnlag> beregningsgrunnlagListe = Lists.newArrayList();
                    for (String identifikator : message.getIdentifikatorer()) {
                        System.out.println("Henter grunnlag for identifikator " + identifikator);
                        beregningsgrunnlagListe.add(new Beregningsgrunnlag(identifikator));
                    }

                    sender().tell(new GrunnlagHentet(beregningsgrunnlagListe), self());
                }
        ).build());
    }

}
