package com.ballo.core.akka.sofus.kjerne;

import com.google.common.collect.Lists;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        List<String> identifikatorer = lagListeMedIdentifikatorer(10000);
        service.startBeregning(identifikatorer);
    }

    private static List<String> lagListeMedIdentifikatorer(int antall) {
        List<String> identifikatorer = Lists.newArrayList();
        for (int i = 0; i < antall; i++) {
            identifikatorer.add(String.valueOf(i +1));
        }
        return identifikatorer;
    }
}
