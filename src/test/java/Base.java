import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.AssumptionViolatedException;

import static org.junit.Assert.fail;

public class Base {

    @Test
    @Ignore
    public void run() {
        double r = Math.random();
        if (r < 0.1) {
            fail("oops");
        } else if (r < 0.2) {
            throw new AssumptionViolatedException("skipping");
        }
    }
}
