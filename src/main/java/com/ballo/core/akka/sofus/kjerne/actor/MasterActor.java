package com.ballo.core.akka.sofus.kjerne.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;
import com.ballo.core.akka.sofus.kjerne.message.Beregn;
import com.ballo.core.akka.sofus.kjerne.message.GrunnlagHentet;
import com.ballo.core.akka.sofus.kjerne.message.HentGrunnlag;

public class MasterActor extends AbstractActor {
    private final ActorRef grunnlagActor;

    public MasterActor() {
        grunnlagActor = getContext().actorOf(new RoundRobinPool(5).props(Props.create(GrunnlagActor.class)));
        receive(ReceiveBuilder.match(Beregn.class, message -> {
                    grunnlagActor.tell(new HentGrunnlag(message.getIdentifikatorer()), self());
                }
        ).match(GrunnlagHentet.class, message -> {
            System.out.println("Grunnlag er hentet, sender beregningsgrunnlag til skatteberegning!");
        })
          .build());
    }


}
