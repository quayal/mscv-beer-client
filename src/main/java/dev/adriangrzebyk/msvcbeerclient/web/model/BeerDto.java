package dev.adriangrzebyk.msvcbeerclient.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
public record BeerDto(UUID id, String beerName, BeerStyle beerStyle, long upc) {}
