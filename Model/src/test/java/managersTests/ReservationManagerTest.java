package managersTests;

import managers.ReservationManager;
import org.junit.jupiter.api.Test;
import reservations.Reservation;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationManagerTest {

    @Test
    public void constructorTest() {
        ReservationManager reservationManager = new ReservationManager();
        assertNotEquals(null, reservationManager);
    }

    @Test
    public void getReservationTest() {
        ReservationManager reservationManager = new ReservationManager();
        Date date = new Date();

        Reservation reservation = new Reservation(1, 1, "S", 1, "BVM", date, date, false);

        reservationManager.addReservation(reservation);
        assertNotEquals(reservationManager.getReservation(1),null);
    }

    @Test
    public void addReservationTest() {
        ReservationManager reservationManager = new ReservationManager();
        ReservationManager reservationManager_1 = new ReservationManager();
        Date date = new Date();

        Reservation reservation = new Reservation(1, 1, "S", 1, "BVM", date, date, false);

        reservationManager.addReservation(reservation);
        reservationManager_1.addReservation(reservation);

        assertNotEquals(reservationManager, reservationManager_1);
        assertNotEquals(reservationManager.getReservation(1), null);
        assertNull(reservationManager.getReservation(2));
        assertEquals(reservationManager.getReservation(1), reservationManager_1.getReservation(1));
    }

    @Test
    public void deleteReservationTest() {
        ReservationManager reservationManager = new ReservationManager();
        ReservationManager reservationManager_1 = new ReservationManager();
        Date date = new Date();

        Reservation reservation = new Reservation(1, 1, "S", 1, "BVM", date, date, false);
        Reservation reservation_1 = new Reservation(2, 2, "S", 1, "BVM", date, date, false);

        reservationManager.addReservation(reservation);
        reservationManager_1.addReservation(reservation_1);
        reservationManager_1.addReservation(reservation);

        assertNotEquals(reservationManager.getReservation(1), reservationManager_1.getReservation(2));
    }

}
