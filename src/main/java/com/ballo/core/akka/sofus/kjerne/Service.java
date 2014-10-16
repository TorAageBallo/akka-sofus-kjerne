package com.ballo.core.akka.sofus.kjerne;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.ballo.core.akka.sofus.kjerne.actor.MasterActor;
import com.ballo.core.akka.sofus.kjerne.message.Beregn;
import com.ballo.core.akka.sofus.kjerne.message.HentGrunnlag;
import com.google.common.collect.Lists;

import java.util.List;

public class Service {
    public void startBeregning(List<String> identifikatorer) {
        final ActorSystem system = ActorSystem.create("akka-sofus-kjerne");
        final ActorRef masterActor = system.actorOf(Props.create(MasterActor.class), "masterActor");

        for (List<String> partition : Lists.partition(identifikatorer, 1000)) {
            masterActor.tell(new Beregn(partition), ActorRef.noSender());
        }
    }
}
