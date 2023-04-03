package dev.adriangrzebyk.msvcbeerclient.web.client;

import dev.adriangrzebyk.msvcbeerclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CustomerClientTest {

	@Autowired
	CustomerClient customerClient;


	@Test
	public void shouldGetCustomerById() {
		CustomerDto customerDto = customerClient.getCustomerById(UUID.randomUUID());
		assertThat(customerDto).isNotNull();
	}

	@Test
	public void shouldSaveNewCustomer() {
		URI uri = customerClient.saveCustomer(CustomerDto.builder().name("New Customer").build());

		assertThat(uri).isNotNull();
	}

	@Test
	public void shouldUpdateCustomer() {
		customerClient.updateCustomer(UUID.randomUUID(), CustomerDto.builder().name("New Customer").build());
	}

	@Test
	public void shouldDeleteCustomer() {
		customerClient.deleteCustomer(UUID.randomUUID());
	}
}
