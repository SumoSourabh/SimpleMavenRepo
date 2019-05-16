import org.junit.Test;

import static sun.misc.PostVMInitHook.run;

public class FirstTest {

    @Test
    public void mytest() {
        run();
    }
}
