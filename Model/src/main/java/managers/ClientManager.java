package managers;

import clients.Client;
import repositories.ClientRepository;
import repositories.Repository;

import java.util.List;

public class ClientManager {
    private final Repository<Client> clientRepository = new ClientRepository();

    // Add Client to repository
    public void addClient(Client client) {
        clientRepository.add(client);
    }

    // Check if Student or CompanyCustomer exist with these indexNumber or NIP
    public boolean checkNewClient(int number) {
        for (Client a : clientRepository.getElements()) {
            if (a.getIndexNumber() == number || a.getNIP() == number) {
                return false;
            }
        }
        return true;
    }

    // Get Client from repository by ID
    public Client getClient(int index) {
        for (Client a : clientRepository.getElements()) {
            if (a.getClientID() == index) {
                return a;
            }
        }
        return null;
    }

    // Return list of Clients
    public List<Client> getClientList() {
        return clientRepository.getElements();
    }

}
