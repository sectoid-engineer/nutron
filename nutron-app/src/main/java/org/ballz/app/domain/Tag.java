package org.ballz.app.domain;

import java.util.HashSet;
import java.util.Set;

public class Tag {
  private final static Set<String> CREATED_TAGS = new HashSet<>();
  {{
    CREATED_TAGS.add("dinner");
    CREATED_TAGS.add("cheap");
    CREATED_TAGS.add("quick");
    CREATED_TAGS.add("delicious");
    CREATED_TAGS.add("lowcarb");
  }}

  private final String name;

  private Tag(String name){
    this.name = name;
  }

  public static Tag of(String name) {
    CREATED_TAGS.add(name);
    return new Tag(name);
  }

  @Override
  public String toString() {
    return name;
  }
}
