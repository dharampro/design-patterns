package com.adumate.observer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Flow;

public class IntegerPublisher implements Flow.Publisher<Integer> {

  @Override
  public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
    int count = 0;
    Set<Integer> set = new HashSet<>();
    while (true) {
      Random rn = new Random();
      Integer num = rn.nextInt(1, 76);
      if(!set.contains(num)) {
        set.add(num);
        subscriber.onNext(num);
        if (set.size() == 75) {
          break;
        }
      }
    }
    subscriber.onComplete();
  }
}
