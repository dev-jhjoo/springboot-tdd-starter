package dev.jh.joo;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConditionAppTest {


    // 조건 테스트
    @Test
    @EnabledOnOs({OS.MAC, OS.WINDOWS})
    @DisabledOnOs(OS.WINDOWS)
//    @DisabledOnJre(JRE.JAVA_8)
    void createTest(){
        String envStr = System.getenv("TEST_ENV");
        System.out.println("envStr=" + envStr);

//        Assumptions.assumeTrue("LOCAL".equalsIgnoreCase(envStr));
//        App app = new App(10);
//        assertNotNull(app);

        Assumptions.assumingThat("LOCAL".equalsIgnoreCase(envStr),
                () -> {
                    App app = new App(10);
                    assertNotNull(app);
                });
    }

}