package junit;


import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


/**
 * Created by a.nigam on 21/02/16.
 */
public class TestLogger implements TestRule {
    private Logger logger;

    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                logger = Logger.getLogger(description.getTestClass().getName() + '.' + description.getDisplayName());
                try {
                    base.evaluate();
                } finally {
                    logger = null;
                }
            }
        };
    }

    static class Logger{

        private final String name;

        public Logger(String s) {
            this.name  = s;
        }

        public static Logger getLogger(String s) {
            return new Logger(s);
        }

        public void warn(String s) {
            System.out.println(name+" WARNING: "+s);
        }
    }
}
