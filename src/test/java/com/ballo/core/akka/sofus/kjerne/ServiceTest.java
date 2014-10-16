package com.ballo.core.akka.sofus.kjerne;


import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

public class ServiceTest {

    @Test
    public void hentBeregningsgrunnlagForIdentifikator() {
        Service service = new Service();
        ArrayList<String> identifikatorer = Lists.newArrayList();
        identifikatorer.add("12177812329");
        service.startBeregning(identifikatorer);
    }
}
