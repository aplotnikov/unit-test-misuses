package org.home.client;

import org.home.client.Client;
import org.home.client.ClientRepository;
import org.home.client.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GoodTest {

    @Mock private ClientRepository repository;

    @InjectMocks private ClientService service;

    @Captor private ArgumentCaptor<Client> clientArgumentCaptor;

    @Captor private ArgumentCaptor<List<Client>> clientsArgumentCaptor;

    @Test
    public void shouldCreateNewClient() {
        String name = "Andrii";
        String surname = "Plotnikov";

        service.createOne(name, surname);

        verify(repository).save(clientArgumentCaptor.capture());

        Client newClient = clientArgumentCaptor.getValue();

        assertThat(newClient.getName(), is(equalTo(name)));
        assertThat(newClient.getSurname(), is(equalTo(surname)));
    }

    @Test
    public void shouldDeleteClientById() {
        Long id = 1L;

        service.deleteOne(id);

        verify(repository).delete(id);
    }

    @Test
    public void shouldCreateAllClients() {
        Client firstClient = client("Andrii", "Plotnikov");
        Client secondClient = client("Ivan", "Ivanov");

        service.createAll(firstClient, secondClient);

        verify(repository).save(clientsArgumentCaptor.capture());

        List<Client> clients = clientsArgumentCaptor.getValue();
        assertThat(clients, hasItems(firstClient, secondClient));
    }

    private static Client client(String name, String surname) {
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);

        return client;
    }
}
