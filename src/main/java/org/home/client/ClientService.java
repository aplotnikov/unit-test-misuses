package org.home.client;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ClientService {

    ClientRepository repository;

    public void createOne(String name, String surname) {
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);

        repository.save(client);
    }

    public void createAll(Client... clients) {
        repository.save(asList(clients));
    }

    public void deleteOne(Long id) {
        repository.delete(id);
    }
}
