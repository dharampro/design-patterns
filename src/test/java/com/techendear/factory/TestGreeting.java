package com.techendear.factory;

@Component
public class TestGreeting {
    public String getGreeting(String name) {
        return "Hello Mr " + name;
    }
}
