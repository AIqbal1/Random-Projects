package com.ingg.states;

import com.ingg.state.pattern.ReservationStatusOperations;

public class CancelledRso implements ReservationStatusOperations {

	@Override
	public ReservationStatus accept(Reservation reservation) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ReservationStatus charge(Reservation reservation) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ReservationStatus cancel(Reservation reservation) {
		throw new UnsupportedOperationException();
	}

}
