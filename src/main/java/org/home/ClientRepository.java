package org.home;

import java.util.List;

public class ClientRepository {
    public Client save(Client client) {
        return client;
    }

    public List<Client> save(List<Client> clients) {
        return clients;
    }

    public void delete(Long id) {}
}
