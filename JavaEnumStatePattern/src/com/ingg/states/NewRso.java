package com.ingg.states;

import com.ingg.state.pattern.ReservationStatusOperations;

public class NewRso implements ReservationStatusOperations {

	@Override
	public ReservationStatus accept(Reservation reservation) {
		return ReservationStatus.ACCEPTED;
	}

	@Override
	public ReservationStatus charge(Reservation reservation) {
		return ReservationStatus.CANCELLED;
	}

	@Override
	public ReservationStatus cancel(Reservation reservation) {
		throw new UnsupportedOperationException();
	}
}
