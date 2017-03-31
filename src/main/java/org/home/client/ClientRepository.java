package org.home.client;

import java.util.List;

class ClientRepository {

    Client save(Client client) {
        return client;
    }

    List<Client> save(List<Client> clients) {
        return clients;
    }

    void delete(Long id) {}
}
