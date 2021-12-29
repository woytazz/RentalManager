package reservationsTests;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;
import reservations.Reservation;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    @Test
    public void gettersTest() {
        Date date = new Date();
        Reservation reservation = new Reservation(1, 1337, "N", 1, "BVM", date, date, false);

        assertEquals(reservation.getReservationID(), 1);
        assertEquals(reservation.getClientID(), 1337);
        assertEquals(reservation.getVMID(), 1);

    }

    @Test
    public void getReservationInfoTest() {
        Date date = new Date();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Reservation reservation = new Reservation(1, 1337, "N", 1, "BVM", date, date, false);

        String test = new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("Reservation ID", 1)
                .append("Client ID", 1337)
                .append("Client type", "N")
                .append("VM ID", 1)
                .append("VM type", "BVM")
                .append("Start date", simpleDateFormat.format(date))
                .append("End date", simpleDateFormat.format(date))
                .toString();

        assertEquals(reservation.getReservationInfo(), test);
    }

}
