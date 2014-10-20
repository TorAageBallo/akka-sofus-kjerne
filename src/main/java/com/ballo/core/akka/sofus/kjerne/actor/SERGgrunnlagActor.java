package com.ballo.core.akka.sofus.kjerne.actor;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import com.ballo.core.akka.sofus.kjerne.domain.SERGgrunnlag;
import com.ballo.core.akka.sofus.kjerne.message.HentGrunnlag;
import com.ballo.core.akka.sofus.kjerne.message.SerggrunnlagHentet;
import com.ballo.core.akka.sofus.kjerne.repository.SkatteinfoRepository;

import java.util.Map;

public class SERGgrunnlagActor extends AbstractActor {

    public SERGgrunnlagActor() {
        receive(ReceiveBuilder.match(HentGrunnlag.class, message -> {
                    Map<String, SERGgrunnlag> serggrunnlagForId =
                            SkatteinfoRepository.hentSerggrunnlagFraSkatteinfo(message.getIdentifikatorer());
                    sender().tell(new SerggrunnlagHentet(serggrunnlagForId, message.getMeldingId()), self());
                }
        ).build());
    }


}
