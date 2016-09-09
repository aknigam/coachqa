package junit;


import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by a.nigam on 21/02/16.
 */
@Ignore
public class MyLoggerTest {

    @Rule
    public TestLogger logger = new TestLogger();

    @Test
    public void checkOutMyLogger() {
        final TestLogger.Logger log = logger.getLogger();
        log.warn("Your test is showing!");
        assertEquals(3, 3);
    }

}
