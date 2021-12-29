package managersTests;

import clients.Client;
import clients.NormalCustomer;
import clients.Student;
import managers.ClientManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientManagerTest {

    @Test
    public void getClientTest() {
        ClientManager clientManager = new ClientManager();
        Client normal = new NormalCustomer(3,"Bartosz", "Pietrzyba");
        Client student = new Student(1,"Jan", "Kowalski",  2020);

        clientManager.addClient(normal);
        clientManager.addClient(student);

        assertNotEquals(clientManager.getClient(0), clientManager.getClient(1));
    }

    @Test
    public void addClientTest() {
        ClientManager clientManager = new ClientManager();
        ClientManager clientManager_1 = new ClientManager();

        Client normal = new NormalCustomer(2,"Bartosz", "Pietrzyba");

        clientManager.addClient(normal);
        clientManager_1.addClient(normal);

        assertNotEquals(clientManager, clientManager_1);
        assertNull(clientManager.getClient(1));
        assertEquals(clientManager.getClient(2), clientManager_1.getClient(2));
    }

}
