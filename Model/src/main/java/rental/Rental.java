package rental;

import jdbc.*;
import reservations.Reservation;

public class Rental {
    private final JdbcNormalCustomers normalCustomers = new JdbcNormalCustomers();
    private final JdbcStudents students = new JdbcStudents();
    private final JdbcCompanyCustomers companyCustomers = new JdbcCompanyCustomers();
    private final JdbcBasicVMs basicVMs = new JdbcBasicVMs();
    private final JdbcCustomVMs customVMs = new JdbcCustomVMs();
    private final JdbcReservations reservations = new JdbcReservations();

    public JdbcNormalCustomers getNormalCustomers() {
        return normalCustomers;
    }

    public JdbcStudents getStudents() {
        return students;
    }

    public JdbcCompanyCustomers getCompanyCustomers() {
        return companyCustomers;
    }

    public JdbcBasicVMs getBasicVMs() {
        return basicVMs;
    }

    public JdbcCustomVMs getCustomVMs() {
        return customVMs;
    }

    public JdbcReservations getReservations() {
        return reservations;
    }

    // Check if this Client exists
    public boolean checkClient(int clientID, String clientType) {
        switch (clientType) {
            case "N":
                return normalCustomers.getNormalCustomersManager().getClient(clientID) != null;
            case "S":
                return students.getStudentsManager().getClient(clientID) != null;
            case "C":
                return companyCustomers.getCompanyCustomersManager().getClient(clientID) != null;
            default:
                System.out.println("Bad client type!");
                return false;
        }
    }

    // Check if this VM exists
    public boolean checkVM(int VMID, String vmType) {
        switch (vmType) {
            case "BVM":
                return basicVMs.getBasicVMsManager().getVM(VMID) != null;
            case "CVM":
                return customVMs.getCustomVMsManager().getVM(VMID) != null;
            default:
                System.out.println("Bad VM type!");
                return false;
        }
    }

    // Check if this VM can be rented
    // If it can, it does it
    public boolean rentVM(int VMID, String vmType) {
        if (vmType.equals("BVM")) {
            if (basicVMs.getBasicVMsManager().getVM(VMID).isRented()) {
                return false;
            } else {
                basicVMs.setRented(VMID);
                return true;
            }
        } else if (vmType.equals("CVM")) {
            if (customVMs.getCustomVMsManager().getVM(VMID).isRented()) {
                return false;
            } else {
                customVMs.setRented(VMID);
                return true;
            }
        } else {
            System.out.println("Bad VM type!");
            return false;
        }
    }

    // Check if this VM can be set as ready to rent
    // If it can, it does it
    public boolean unrentVM(int reservationID) {
        Reservation res = reservations.getReservationsManager().getReservation(reservationID);

        if (res.getVmType().equals("BVM")) {
            if (!basicVMs.getBasicVMsManager().getVM(res.getVMID()).isRented()) {
                return false;
            } else {
                basicVMs.setUnrented(res.getVMID());
                return true;
            }
        } else if (res.getVmType().equals("CVM")) {
            if (!customVMs.getCustomVMsManager().getVM(res.getVMID()).isRented()) {
                return false;
            } else {
                customVMs.setUnrented(res.getVMID());
                return true;
            }
        } else {
            System.out.println("Bad VM type");
            return false;
        }
    }

    // After ending reservation this function calculates how many we must to pay
    public int getCalculatedPrice(int reservationID) {
        int calculatedPrice = 0;

        if (reservations.getReservationsManager().getReservation(reservationID).getVmType().equals("BVM")) {
            calculatedPrice = basicVMs.getBasicVMsManager().getVM(reservations.getReservationsManager().getReservation(reservationID).getVMID()).Price();
        } else if (reservations.getReservationsManager().getReservation(reservationID).getVmType().equals("CVM")) {
            calculatedPrice = customVMs.getCustomVMsManager().getVM(reservations.getReservationsManager().getReservation(reservationID).getVMID()).Price();
        }

        calculatedPrice *= reservations.getReservationsManager().getReservation(reservationID).getTimeDuration();

        return calculatedPrice;
    }

    // Check if this Reservation exists
    public boolean checkReservation(int reservationID) {
        return reservations.getReservationsManager().getReservation(reservationID) != null;
    }

    // Check if this Reservation is ended
    public boolean checkIfReservationEnded(int reservationID) {
        for (Reservation a : reservations.getReservationsManager().getReservationsList()) {
            if (a.getReservationID() == reservationID) {
                return a.isEnded();
            }
        }
        return false;
    }

}
