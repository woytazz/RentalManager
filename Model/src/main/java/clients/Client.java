package clients;

public abstract class Client {

    private final int clientID;
    private final String name;
    private final String lastName;

    public Client(int ID, String name, String lastName) {
        this.clientID = ID;
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getClientID() {
        return clientID;
    }

    public abstract int getNIP();

    public abstract int getIndexNumber();

    public abstract String getClientInfo();

}
