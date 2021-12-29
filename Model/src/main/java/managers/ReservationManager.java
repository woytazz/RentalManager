package managers;

import repositories.Repository;
import reservations.Reservation;
import repositories.ReservationRepository;

import java.util.List;

public class ReservationManager {
    private final Repository<Reservation> reservationRepository = new ReservationRepository();

    // Add Reservation to repository
    public void addReservation(Reservation reservation) {
        reservationRepository.add(reservation);
    }

    // Get Reservation from repository by ID
    public Reservation getReservation(int index) {
        for (Reservation a : reservationRepository.getElements()) {
            if (a.getReservationID() == index) {
                return a;
            }
        }
        return null;
    }

    // Return list of Reservations
    public List<Reservation> getReservationsList() {
        return reservationRepository.getElements();
    }

}
