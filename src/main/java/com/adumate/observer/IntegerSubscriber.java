package com.adumate.observer;

import java.util.concurrent.Flow;

public class IntegerSubscriber implements Flow.Subscriber<Integer> {
  private Flow.Subscription subscription;

  @Override
  public void onSubscribe(Flow.Subscription subscription) {
    this.subscription = subscription;
    subscription.request(1);
  }

  @Override
  public void onNext(Integer item) {
    System.out.println(item);
  }

  @Override
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  @Override
  public void onComplete() {
    System.out.println("Done");
  }
}
