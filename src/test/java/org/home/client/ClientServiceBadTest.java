package org.home.client;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientServiceBadTest {

    private ClientRepository repository;

    private ClientService service;

    @Before
    public void setUp() throws Exception {
        repository = mock(ClientRepository.class);
        service = new ClientService(repository);
    }

    @Test
    public void shouldCreateNewClient() throws Exception {
        String name = "Andrii";
        String surname = "Plotnikov";

        ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);

        service.createOne(name, surname);

        verify(repository).save(captor.capture());

        Client newClient = captor.getValue();
        assertEquals(name, newClient.getName());
        assertEquals(surname, newClient.getSurname());
    }

    public void shouldDeleteClientById() throws Exception {
        Long id = 1L;

        service.deleteOne(id);

        verify(repository).delete(id);
    }

    @Test
    public void shouldCreateAllClients() throws Exception {
        Client firstClient = client("Andrii", "Plotnikov");
        Client secondClient = client("Ivan", "Ivanov");

        ArgumentCaptor<List<Client>> captor = ArgumentCaptor.forClass(List.class);

        service.createAll(firstClient, secondClient);

        verify(repository).save(captor.capture());

        List<Client> clients = captor.getValue();
        assertTrue(clients.containsAll(asList(firstClient, secondClient)));
    }

    private static Client client(String name, String surname) {
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);

        return client;
    }
}
