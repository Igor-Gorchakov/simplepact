package com.test;


import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.ProviderServiceApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/*  Test for the provider using a mock client based on the contract */
@RunWith(PactRunner.class)
@Provider("FOLIO provider")
@PactFolder("target/pacts")
public class PactProviderTest {

    private static ConfigurableApplicationContext application;

    /* Define the target to be used for verifying the interactions in the contract */
    @TestTarget
    public final Target target = new HttpTarget(8080);

    @BeforeClass
    public static void start() {
        application = SpringApplication.run(ProviderServiceApplication.class);
    }

    /* Specify the state in the contract that we want to test */
    @State("test GET")
    public void toGetState() {
    }

    @AfterClass
    public static void kill() {
        application.stop();
    }
}
