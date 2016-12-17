package junit;

/**
 * Created by a.nigam on 14/12/16.
 */

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Verifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.experimental.results.PrintableResult.testResult;
import static org.junit.experimental.results.ResultMatchers.isSuccessful;


public class UsesVerifier {
    private static String sequence;
    @Rule
    public Verifier collector = new Verifier() {
        @Override
        protected void verify() {
            sequence += "verify ";
        }
    };

    @Test
    public void example() {
        sequence += "test ";
    }

    @Test
    public void verifierRunsAfterTest() {
        sequence = "";
        assertThat(testResult(UsesVerifier.class), isSuccessful());
        assertEquals("test verify ", sequence);
    }



}