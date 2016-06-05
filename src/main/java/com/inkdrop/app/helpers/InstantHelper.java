package com.inkdrop.app.helpers;

import java.time.Instant;
import java.util.Date;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InstantHelper {

	public boolean biggerThanSixHours(Date time){
		Instant updated = time.toInstant();
		Instant sixHours = Instant.now().minusSeconds(6L*60L*60L);

		return updated.isBefore(sixHours);
	}
}
