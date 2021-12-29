package clients;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CompanyCustomer extends Client {

    private final int NIP;

    public CompanyCustomer(int ID, String name, String lastName, int NIP) {
        super(ID, name, lastName);
        this.NIP = NIP;
    }

    @Override
    public int getNIP() {
        return NIP;
    }

    @Override
    public int getIndexNumber() {
        return 0;
    }

    @Override
    public String getClientInfo() {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("Client ID", getClientID())
                .append("Name", getName())
                .append("Last name", getLastName())
                .append("NIP", getNIP())
                .toString();
    }

}
