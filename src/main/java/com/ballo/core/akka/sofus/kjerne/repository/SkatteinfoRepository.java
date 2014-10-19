package com.ballo.core.akka.sofus.kjerne.repository;

import com.ballo.core.akka.sofus.kjerne.domain.SERGgrunnlag;
import com.ballo.core.akka.sofus.kjerne.domain.Skattegrunnlag;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class SkatteinfoRepository {

    public static Map<String, SERGgrunnlag> hentSerggrunnlagFraSkatteinfo(List<String> identifikatorer) {
        simulerVenting(3000L);

        Map<String, SERGgrunnlag> serggrunnlagForId = Maps.newHashMap();
        for (String identifikator : identifikatorer) {
            serggrunnlagForId.put(identifikator, new SERGgrunnlag());
        }
        return serggrunnlagForId;
    }

    public static Map<String, Skattegrunnlag> hentSkattegrunnlagFraSkatteinfo(List<String> identifikatorer) {
    simulerVenting(1000L);

        Map<String, Skattegrunnlag> skattegrunnlagForId = Maps.newHashMap();
        for (String identifikator : identifikatorer) {
            skattegrunnlagForId.put(identifikator, new Skattegrunnlag());
        }
        return skattegrunnlagForId;
    }

    private static void simulerVenting(long ventetid) {
        try {
            Thread.sleep(ventetid);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
