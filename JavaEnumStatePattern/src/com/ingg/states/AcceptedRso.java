package com.ingg.states;

import com.ingg.state.pattern.ReservationStatusOperations;

public class AcceptedRso implements ReservationStatusOperations {
	@Override
	public ReservationStatus accept(Reservation reservation) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ReservationStatus charge(Reservation reservation) {
		// charge client's credit card
		// send e-mail
		// print ticket
		return ReservationStatus.PAID;
	}

	@Override
	public ReservationStatus cancel(Reservation reservation) {
		// send cancellation e-mail
		return ReservationStatus.CANCELLED;
	}
}
