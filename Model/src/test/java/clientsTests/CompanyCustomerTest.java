package clientsTests;

import clients.Client;
import clients.CompanyCustomer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyCustomerTest {

    @Test
    public void gettersTest() {
        Client company = new CompanyCustomer(2,"Adam", "Lenarczyk", 997);
        assertEquals(company.getClientID(),2);
        assertEquals(company.getName(),"Adam");
        assertEquals(company.getLastName(),"Lenarczyk");
        assertEquals(company.getNIP(),997);
    }

    @Test
    public void getClientInfoTest() {
        Client company = new CompanyCustomer(2,"Adam", "Lenarczyk", 997);
        String test = new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("Client ID", 2)
                .append("Name", "Adam")
                .append("Last name", "Lenarczyk")
                .append("NIP", 997)
                .toString();
        assertEquals(company.getClientInfo(), test);
    }

}
