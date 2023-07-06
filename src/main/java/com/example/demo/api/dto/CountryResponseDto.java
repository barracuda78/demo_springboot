package com.example.demo.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class CountryResponseDto {

    private final Long id;

    private final String name;

    private final Boolean isActive;

}
