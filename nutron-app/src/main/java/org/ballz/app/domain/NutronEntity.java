package org.ballz.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.UUID;

@AllArgsConstructor
@Getter
public abstract class NutronEntity {
  protected final @Id UUID id;
  private final @Version int version;
  protected final String name;
  protected final String note;
}
