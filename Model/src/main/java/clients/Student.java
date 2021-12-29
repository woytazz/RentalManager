package clients;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Student extends Client {

    private final int indexNumber;

    public Student(int ID, String name, String lastName, int indexNumber) {
        super(ID, name, lastName);
        this.indexNumber = indexNumber;
    }

    @Override
    public int getNIP() {
        return 0;
    }

    @Override
    public int getIndexNumber() {
        return indexNumber;
    }

    @Override
    public String getClientInfo() {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("Client ID", getClientID())
                .append("Name", getName())
                .append("Last name", getLastName())
                .append("Index number", getIndexNumber())
                .toString();
    }

}
