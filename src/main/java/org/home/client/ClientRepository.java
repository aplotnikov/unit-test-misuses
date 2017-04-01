package org.home.client;

import java.util.List;

interface ClientRepository {

    void save(Client client);

    void save(List<Client> clients);

    void delete(Long id);
}
