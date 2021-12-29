package clients;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NormalCustomer extends Client {

    public NormalCustomer(int ID, String name, String lastName) {
        super(ID, name, lastName);
    }

    @Override
    public int getNIP() {
        return 0;
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
                .toString();
    }

}
