package com.techendear.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FactoryPatternTest {
    private final static String GREETING = "Hello Mr ";

    @Test
    void testObjectCreation() {
        ObjectFactory factory = new ObjectFactory(this.getClass().getPackageName());
        TestGreeting greeting = (TestGreeting) factory.getInstance(TestGreeting.class);
        Assertions.assertEquals(GREETING + "name", greeting.getGreeting("name"));

        TestStatus status = (TestStatus) factory.getInstance(TestStatus.class);
        Assertions.assertEquals(status.getStatus(), "ACTIVE");
    }
}
