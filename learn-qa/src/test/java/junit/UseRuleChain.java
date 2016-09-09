package junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static org.junit.Assert.assertTrue;

/**
 * Created by a.nigam on 21/02/16.
 */
public class UseRuleChain {




    @Rule
    public TestRule chain= RuleChain
            .outerRule(new LoggingRule("outer rule"))
            .around(new LoggingRule("middle rule"))
            .around(new LoggingRule("inner rule"));

    @Test
    public void example() {
        assertTrue(true);
    }


    private class LoggingRule implements TestRule {
        private final String s;

        public LoggingRule(String s) {
            this.s =  s;
        }

        @Override
        public Statement apply(Statement statement, Description description) {
            System.out.println(s);
            return statement;
        }
    }
}
