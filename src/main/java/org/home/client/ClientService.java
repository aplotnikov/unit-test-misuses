package org.home.client;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class ClientService {

    ClientRepository repository;

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
