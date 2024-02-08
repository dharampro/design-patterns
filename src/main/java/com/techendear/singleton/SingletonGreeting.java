package com.techendear.singleton;

import java.io.Serial;
import java.io.Serializable;

public class SingletonGreeting implements Serializable, Cloneable {
    private static volatile SingletonGreeting instance;

    /**
     * Made private to prevent instance creation using new
     */
    private SingletonGreeting() {
    }

    /**
     * one public static method to get the instance
     *
     * @return instance
     */
    public static SingletonGreeting getInstance() {
        // Double-checked locking for thread safety
        if (instance == null) {
            synchronized (SingletonGreeting.class) {
                if (instance == null) {
                    instance = new SingletonGreeting();
                }
            }
        }
        return instance;
    }

    /**
     * Method to handle serialization and deserialization and keep singleton behavior
     * @return instance
     */
    @Serial
    protected Object readResolve() {
        return getInstance();
    }

    public String greetings(String name) {
        return "Hello Mr " + name;
    }

    @Override
    public SingletonGreeting clone() throws CloneNotSupportedException {
     throw new CloneNotSupportedException("Clone not supported");
    }
}
