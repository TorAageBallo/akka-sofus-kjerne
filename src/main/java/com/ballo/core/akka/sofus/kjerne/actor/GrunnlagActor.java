package com.ballo.core.akka.sofus.kjerne.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.ballo.core.akka.sofus.kjerne.domain.Beregningsgrunnlag;
import com.ballo.core.akka.sofus.kjerne.domain.SERGgrunnlag;
import com.ballo.core.akka.sofus.kjerne.domain.Skattegrunnlag;
import com.ballo.core.akka.sofus.kjerne.message.*;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

public class GrunnlagActor extends AbstractActor {
    private ActorRef master;
    private ActorRef serggrunnlagActor;
    private ActorRef skattegrunnlagActor;

    private List<String> identifikatorer;
    private Map<String, Skattegrunnlag> skattegrunnlagForId;
    private Map<String, SERGgrunnlag> serggrunnlagForId;
    private int grunnlagMottatt = 0;
    private int FORVENTET_GRUNNLAG = 2;

    public GrunnlagActor() {
        skattegrunnlagActor = getContext().actorOf(Props.create(SkattegrunnlagActor.class));
        serggrunnlagActor = getContext().actorOf(Props.create(SERGgrunnlagActor.class));
        receive(ReceiveBuilder.match(HentGrunnlag.class, message -> {
                    master = sender();
                    identifikatorer = message.getIdentifikatorer();
                    skattegrunnlagActor.tell(message, self());
                    serggrunnlagActor.tell(message, self());
                }
        ).match(SkattegrunnlagHentet.class, message -> {
            skattegrunnlagForId = message.getSkattegrunnlagForId();
            haandterMottattGrunnlag();
        }).match(SerggrunnlagHentet.class, message -> {
            serggrunnlagForId = message.getSerggrunnlagForId();
            haandterMottattGrunnlag();
        }).build());
    }

    private void haandterMottattGrunnlag() {
        grunnlagMottatt++;
        if (FORVENTET_GRUNNLAG == grunnlagMottatt) {
            List<Beregningsgrunnlag> beregningsgrunnlagListe = Lists.newArrayList();
            for (String identifikator : identifikatorer) {
                Beregningsgrunnlag beregningsgrunnlag = new Beregningsgrunnlag(identifikator);
                beregningsgrunnlag.setSkattegrunnlag(skattegrunnlagForId.get(identifikator));
                beregningsgrunnlag.setSerggrunnlag(serggrunnlagForId.get(identifikator));

                beregningsgrunnlagListe.add(beregningsgrunnlag);
                System.out.println("Hentet grunnlag for identifikator " + identifikator);
            }
            master.tell(new GrunnlagHentet(beregningsgrunnlagListe), self());
        }
    }

}
