package com.techendear.singleton;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
    public static void main(String[] args) throws CloneNotSupportedException {
        SingletonGreeting greeting = SingletonGreeting.getInstance();
        System.out.println(greeting.greetings("Paul Jack"));
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i <= 1000; i++) {
            SingletonGreeting greeting1 = SingletonGreeting.getInstance();
            System.out.println("Object created::" + greeting1.hashCode());
            System.out.println(greeting1.greetings("Jack " + i));
        }

        System.out.println("=========== Serialization Test ============");

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
            // Verify that deserialized object is same as original singleton instance
            System.out.println("Deserialized object hashcode: " + singleton.hashCode());
            System.out.println("Singleton instance hashcode: " + SingletonGreeting.getInstance().hashCode());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=========== Clone Test ============");
        System.out.println(greeting.clone());
    }
}
