package com.example.demo.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@RequiredArgsConstructor
@Builder
@JsonDeserialize(builder = CountryCreateRequestDto.CountryCreateRequestDtoBuilder.class)
public class CountryCreateRequestDto {

    @NonNull
    @NotNull
    private final String name;

    @NonNull
    @NotNull
    private final Boolean isActive;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CountryCreateRequestDtoBuilder {

    }

}
