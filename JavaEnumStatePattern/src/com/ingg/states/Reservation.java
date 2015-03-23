package com.ingg.states;

public class Reservation {

	private ReservationStatus status;

	public void accept() {
		setStatus(status.accept(this));
	}

	public void charge() {
		setStatus(status.charge(this));
	}

	public void cancel() {
		setStatus(status.cancel(this));
	}

	public void setStatus(ReservationStatus status) {
		if (status != null && status != this.status) {
			this.status = status;
		}
	}
	
	
	public static void main(String args[]) {
		Reservation reservation = new Reservation();
		reservation.setStatus(ReservationStatus.NEW);
		reservation.accept();
		reservation.charge();
	}
}
