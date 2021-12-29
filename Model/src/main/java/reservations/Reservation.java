package reservations;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Reservation {
    private final int reservationID;
    private final int clientID;
    private final String clientType;
    private final int VMID;
    private final String vmType;
    private final Date startDate;
    private final Date endDate;
    private final boolean isEnded;

    // Set date format to display
    String pattern = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public Reservation(int ID, int clientID, String clientType, int VMID, String vmType, Date startDate, Date endDate, boolean isEnded) {
        this.reservationID = ID;
        this.clientID = clientID;
        this.clientType = clientType;
        this.VMID = VMID;
        this.vmType = vmType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isEnded = isEnded;
    }

    public int getReservationID() {
        return reservationID;
    }

    public int getClientID() {
        return clientID;
    }

    public String getClientType() {
        return clientType;
    }

    public int getVMID() {
        return VMID;
    }

    public String getVmType() {
        return vmType;
    }

    public String getStartDate() {
        return simpleDateFormat.format(startDate);
    }

    public String getEndDate() {
        try {
            return simpleDateFormat.format(endDate);
        } catch (NullPointerException e) {
            return "";
        }

    }

    // Calculate duration of rent in hours
    public long getTimeDuration() {
        if (endDate != null) {
            long time = endDate.getTime() - startDate.getTime();
            time = time / 3600000;
            if (time < 1) {
                return 1;
            } else {
                return time + 1;
            }
        } else {
            return 0;
        }
    }

    public boolean isEnded() {
        return isEnded;
    }

    public String getReservationInfo() {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("Reservation ID", getReservationID())
                .append("Client ID", getClientID())
                .append("Client type", getClientType())
                .append("VM ID", getVMID())
                .append("VM type", getVmType())
                .append("Start date", getStartDate())
                .append("End date", getEndDate())
                .toString();
    }
}
