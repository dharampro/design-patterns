package com.techendear.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SingletonGreetingTest {
    private final static String GREETING = "Hello Mr ";

    @Test
    void testMultipleObject() {
        SingletonGreeting greetingSingleton = SingletonGreeting.getInstance();
        List<SingletonGreeting> list = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            SingletonGreeting greeting = SingletonGreeting.getInstance();
            list.add(greeting);
        }
        AtomicInteger i = new AtomicInteger();
        list.forEach(value -> {
            Assertions.assertEquals(value.greetings(i + ""), GREETING + i.getAndIncrement());
            Assertions.assertEquals(greetingSingleton.hashCode(), value.hashCode());
        });
    }

    @Test
    void tesSerialization() {
        SingletonGreeting greetingSingleton = SingletonGreeting.getInstance();

        // Serialize singleton object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("singleton.ser"))) {
            SingletonGreeting singleton = SingletonGreeting.getInstance();
            out.writeObject(singleton);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Deserialize singleton object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("singleton.ser"))) {
            SingletonGreeting singleton = (SingletonGreeting) in.readObject();
            Assertions.assertEquals(singleton.hashCode(), greetingSingleton.hashCode());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testClone() throws CloneNotSupportedException {
        SingletonGreeting greetingSingleton = SingletonGreeting.getInstance();
        Assertions.assertThrows(CloneNotSupportedException.class, greetingSingleton::clone);
    }
}
