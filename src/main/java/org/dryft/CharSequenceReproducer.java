package org.dryft;

import java.util.Map;

import org.graalvm.polyglot.Context;

public class CharSequenceReproducer {
  public static void wasWorkingTest(Map<? extends CharSequence, String> map) {
    for (Map.Entry<? extends CharSequence, String> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
  }
  public static void alwaysFailedTest(Map<CharSequence, String> map) {
    for (Map.Entry<CharSequence, String> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
  }
  public static void main(String[] args) {
    var context = Context.newBuilder()
      .allowAllAccess(true)
      .build();
    context.eval("js", """
      const r = Java.type("org.dryft.CharSequenceReproducer");
      r.wasWorkingTest({ should: "work" });
      r.alwaysFailedTest({ should: "not work" });""");
  }
}
