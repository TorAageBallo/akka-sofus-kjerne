package com.ballo.core.akka.sofus.kjerne.actor;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import com.ballo.core.akka.sofus.kjerne.domain.Skattegrunnlag;
import com.ballo.core.akka.sofus.kjerne.message.HentGrunnlag;
import com.ballo.core.akka.sofus.kjerne.message.SkattegrunnlagHentet;
import com.ballo.core.akka.sofus.kjerne.repository.SkatteinfoRepository;

import java.util.Map;

public class SkattegrunnlagActor extends AbstractActor {

    public SkattegrunnlagActor() {
        receive(ReceiveBuilder.match(HentGrunnlag.class, message -> {
                    Map<String, Skattegrunnlag> skattegrunnlagForId = SkatteinfoRepository.hentSkattegrunnlagFraSkatteinfo(message.getIdentifikatorer());
                    sender().tell(new SkattegrunnlagHentet(skattegrunnlagForId), self());
                }
        ).build());
    }


}
