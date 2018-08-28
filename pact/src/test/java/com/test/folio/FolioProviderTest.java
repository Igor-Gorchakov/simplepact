package com.test.folio;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class)
@Provider("Provider")
@PactFolder("target/pacts")
public class FolioProviderTest {

    @TestTarget
    public final Target target = new HttpTarget("http", "localhost", 9130);

    @State("test GET")
    public void toGetState() {
    }
}
