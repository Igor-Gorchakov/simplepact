package test.folio.login;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.runner.RunWith;

/*
 * Test interacts with OKAPI gateway and calls mod-login using a mock client based on the contract.
 * @author Igor Gorchakov
 */
@RunWith(PactRunner.class)
@Provider("Provider")
@PactFolder("src/test/resources/pacts/folio/login")
public class FolioProviderTest {

    /**
     * Define the target to be used for verifying the interactions in the contract
     */
    @TestTarget
    public final Target target = new HttpTarget("http", "localhost", 9130);

    /**
     * Specify the state in the contract that we want to test
     */
    @State("User login scenario")
    public void testLogUserInTheSystem() {
    }
}
