package dev.adriangrzebyk.msvcbeerclient.web.client;

import dev.adriangrzebyk.msvcbeerclient.web.model.BeerDto;
import dev.adriangrzebyk.msvcbeerclient.web.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BeerClientTest {

	@Autowired
	BeerClient client;

	@Test
	public void shouldGetBeerById() {
		BeerDto dto = client.getBeerById(UUID.randomUUID());
		assertThat(dto.beerName()).isEqualTo("Galaxy Cat");
		assertThat(dto.beerStyle()).isEqualTo(BeerStyle.PALE_ALE);
	}

	@Test
	public void shouldCreateNewBeer() {
		BeerDto dto = new BeerDto(UUID.randomUUID(), "New Beer", BeerStyle.PALE_ALE, 123L);
		URI uri = client.saveNewBeer(dto);
		assertThat(uri).isNotNull();
		System.out.println(uri);
	}

	@Test
	public void shouldUpdateBeer() {
		BeerDto dto = new BeerDto(UUID.randomUUID(), "Beer Name", BeerStyle.PALE_ALE, 123L);
		client.updateBeer(UUID.randomUUID(), dto);
	}

	@Test
	public void shouldDeleteBeer() {
		client.deleteBeer(UUID.randomUUID());
	}
}