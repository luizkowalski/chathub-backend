package com.inkdrop.application.helpers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InstantHelper {

  public boolean biggerThanSixHours(Date time) {
    Instant updated = time.toInstant();
    Instant sixHours = Instant.now().minus(6L, ChronoUnit.HOURS);

    return updated.isBefore(sixHours);
  }
}