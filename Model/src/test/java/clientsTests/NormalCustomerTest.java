package clientsTests;

import clients.Client;
import clients.NormalCustomer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NormalCustomerTest {

    @Test
    public void gettersTest() {
        Client normal = new NormalCustomer(1337,"Bartosz", "Pietrzyba");
        assertEquals(normal.getClientID(),1337);
        assertEquals(normal.getName(),"Bartosz");
        assertEquals(normal.getLastName(),"Pietrzyba");
    }

    @Test
    public void getClientInfoTest() {
        Client normal = new NormalCustomer(1337,"Bartosz", "Pietrzyba");
        String test = new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("Client ID", 1337)
                .append("Name", "Bartosz")
                .append("Last name", "Pietrzyba")
                .toString();
        assertEquals(normal.getClientInfo(), test);
    }

}
