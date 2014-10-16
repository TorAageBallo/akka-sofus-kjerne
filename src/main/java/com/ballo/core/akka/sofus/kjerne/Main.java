package com.ballo.core.akka.sofus.kjerne;

import com.google.common.collect.Lists;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        ArrayList<String> identifikatorer = Lists.newArrayList();
        identifikatorer.add("12177812329");
        service.startBeregning(identifikatorer);
    }
}
