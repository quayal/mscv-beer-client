package dev.adriangrzebyk.msvcbeerclient.web.client;

import dev.adriangrzebyk.msvcbeerclient.web.model.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class CustomerClient {

	private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
	private String apihost;
	private final RestTemplate restTemplate;

	public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public void setApihost(String apihost) {
		this.apihost = apihost;
	}

	CustomerDto getCustomerById(UUID uuid) {
		return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + uuid, CustomerDto.class);
	}

	URI saveCustomer(CustomerDto customerDto) {
		return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto);
	}

	void updateCustomer(UUID uuid, CustomerDto customerDto) {
		restTemplate.put(apihost + CUSTOMER_PATH_V1 + uuid, customerDto);

	}

	void deleteCustomer(UUID uuid) {
		restTemplate.delete(apihost + CUSTOMER_PATH_V1 + uuid);
	}

}
