package com.ballo.core.akka.sofus.kjerne.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.ballo.core.akka.sofus.kjerne.domain.Beregningsgrunnlag;
import com.ballo.core.akka.sofus.kjerne.domain.SERGgrunnlag;
import com.ballo.core.akka.sofus.kjerne.domain.Skattegrunnlag;
import com.ballo.core.akka.sofus.kjerne.message.GrunnlagHentet;
import com.ballo.core.akka.sofus.kjerne.message.HentGrunnlag;
import com.ballo.core.akka.sofus.kjerne.message.SerggrunnlagHentet;
import com.ballo.core.akka.sofus.kjerne.message.SkattegrunnlagHentet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public class GrunnlagActor extends AbstractActor {
    private ActorRef master;
    private ActorRef serggrunnlagActor;
    private ActorRef skattegrunnlagActor;

    private Map<UUID, Integer> mottattGrunnlag = Maps.newConcurrentMap();
    private Map<UUID, Map<String, Skattegrunnlag>> skattegrunnlagForId = Maps.newConcurrentMap();
    private Map<UUID, Map<String, SERGgrunnlag>> serggrunnlagForId = Maps.newConcurrentMap();
    private int FORVENTET_GRUNNLAG = 2;

    public GrunnlagActor() {
        skattegrunnlagActor = getContext().actorOf(Props.create(SkattegrunnlagActor.class));
        serggrunnlagActor = getContext().actorOf(Props.create(SERGgrunnlagActor.class));
        receive(ReceiveBuilder.match(HentGrunnlag.class, message -> {
                    master = sender();
                    mottattGrunnlag.put(message.getMeldingId(), 0);

                    skattegrunnlagActor.tell(message, self());
                    serggrunnlagActor.tell(message, self());
                }
        ).match(SkattegrunnlagHentet.class, message -> {
            skattegrunnlagForId.put(message.getMeldingId(), message.getSkattegrunnlagForId());
            Integer integer = mottattGrunnlag.get(message.getMeldingId()) + 1;
            mottattGrunnlag.put(message.getMeldingId(), integer);
            haandterMottattGrunnlag();
        }).match(SerggrunnlagHentet.class, message -> {
            serggrunnlagForId.put(message.getMeldingId(), message.getSerggrunnlagForId());
            Integer integer = mottattGrunnlag.get(message.getMeldingId()) + 1;
            mottattGrunnlag.put(message.getMeldingId(), integer);
            haandterMottattGrunnlag();
        }).build());
    }

    private void haandterMottattGrunnlag() {
        Predicate<Map.Entry<UUID, Integer>> alleGrunnlagmottattPredicate = motatteGrunnlagEntrySet -> motatteGrunnlagEntrySet.getValue() == FORVENTET_GRUNNLAG;
        mottattGrunnlag.entrySet().stream().filter(alleGrunnlagmottattPredicate).forEach(motatteGrunnlagEntrySet -> {
            UUID meldingsId = motatteGrunnlagEntrySet.getKey();
            List<Beregningsgrunnlag> beregningsgrunnlagListe = lagBeregningsgrunnlagForMeldingId(meldingsId);
            mottattGrunnlag.remove(meldingsId);
            master.tell(new GrunnlagHentet(beregningsgrunnlagListe), self());
        });
    }

    private List<Beregningsgrunnlag> lagBeregningsgrunnlagForMeldingId(UUID meldingsId) {
        List<Beregningsgrunnlag> beregningsgrunnlagListe = Lists.newArrayList();
        Map<String, Skattegrunnlag> stringSkattegrunnlagMap = skattegrunnlagForId.get(meldingsId);
        Map<String, SERGgrunnlag> stringSERGgrunnlagMap = serggrunnlagForId.get(meldingsId);
        for (Map.Entry<String, Skattegrunnlag> skattegrunnlagEntry : stringSkattegrunnlagMap.entrySet()) {
            String identifikator = skattegrunnlagEntry.getKey();
            Beregningsgrunnlag beregningsgrunnlag = lagBeregningsgrunnlag(stringSkattegrunnlagMap, stringSERGgrunnlagMap, identifikator);
            beregningsgrunnlagListe.add(beregningsgrunnlag);
        }
        return beregningsgrunnlagListe;
    }

    private Beregningsgrunnlag lagBeregningsgrunnlag(Map<String, Skattegrunnlag> stringSkattegrunnlagMap, Map<String, SERGgrunnlag> stringSERGgrunnlagMap, String identifikator) {
        Beregningsgrunnlag beregningsgrunnlag = new Beregningsgrunnlag(identifikator);
        beregningsgrunnlag.setSkattegrunnlag(stringSkattegrunnlagMap.get(identifikator));
        beregningsgrunnlag.setSERGgrunnlag(stringSERGgrunnlagMap.get(identifikator));
        return beregningsgrunnlag;
    }

}
