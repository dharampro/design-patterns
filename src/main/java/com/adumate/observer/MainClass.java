package com.adumate.observer;

public class MainClass {
  public static void main(String[] args) {
    IntegerPublisher publisher = new IntegerPublisher();
    IntegerSubscriber subscriber = new IntegerSubscriber();
    publisher.subscribe(subscriber);
  }
}
