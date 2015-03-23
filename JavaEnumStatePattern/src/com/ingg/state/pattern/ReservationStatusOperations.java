package com.ingg.state.pattern;

import com.ingg.states.Reservation;
import com.ingg.states.ReservationStatus;

public interface ReservationStatusOperations {
	ReservationStatus accept(Reservation reservation);

	ReservationStatus charge(Reservation reservation);

	ReservationStatus cancel(Reservation reservation);
}
