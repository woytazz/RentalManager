package clientsTests;

import clients.Client;
import clients.Student;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void gettersTest() {
        Client student = new Student(1,"Jan", "Kowalski",  2020);
        assertEquals(student.getClientID(),1);
        assertEquals(student.getName(),"Jan");
        assertEquals(student.getLastName(),"Kowalski");
        assertEquals(student.getIndexNumber(),2020);
    }

    @Test
    public void getClientInfoTest() {
        Client student = new Student(2,"Jan", "Pawel",  137);
        String test = new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("Client ID", "2")
                .append("Name", "Jan")
                .append("Last name", "Pawel")
                .append("Index number", "137")
                .toString();
        assertEquals(student.getClientInfo(), test);
    }

}
