package com.powsybl.dynawomain;

import com.powsybl.contingency.ContingenciesProvider;
import com.powsybl.iidm.network.*;
import com.powsybl.loadflow.LoadFlow;
import com.powsybl.loadflow.LoadFlowParameters;
import com.powsybl.loadflow.LoadFlowResult;
import com.powsybl.contingency.ContingenciesProvider;
import com.powsybl.contingency.Contingency;
import com.powsybl.contingency.dsl.GroovyDslContingenciesProvider;
import com.powsybl.security.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;

import static com.powsybl.security.Security.printLimitsViolations;

public final class TestMain {
    private TestMain() {
    }

    public static void main(String[] args) {
        Network network = Network.read("SmallBusBranch.xiidm");
        String limits = printLimitsViolations(network);
        System.out.println(limits);
        LoadFlowResult result = LoadFlow.run(network);
        String lims = printLimitsViolations(network);
        System.out.println(lims);

        /*System.out.println(result.getComponentResults().get(0).getStatus());

        ContingenciesProvider contingenciesProvider = new GroovyDslContingenciesProvider(Paths.get("contingencies.groovy"));
        SecurityAnalysisReport report = SecurityAnalysis.run(network, contingenciesProvider.getContingencies(network));
        SecurityAnalysisResult resultAS = report.getResult();

        System.out.println(resultAS.getPreContingencyResult().getLimitViolationsResult().getLimitViolations());
        System.out.println(resultAS.getPostContingencyResults().get(0).getStatus());*/
    }
}
