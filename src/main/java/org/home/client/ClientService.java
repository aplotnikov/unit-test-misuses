package org.home.client;

import static java.util.Arrays.asList;

class ClientService {

    private final ClientRepository repository;

    ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    void createOne(String name, String surname) {
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);

        repository.save(client);
    }

    void createAll(Client... clients) {
        repository.save(asList(clients));
    }

    void deleteOne(Long id) {
        repository.delete(id);
    }
}
