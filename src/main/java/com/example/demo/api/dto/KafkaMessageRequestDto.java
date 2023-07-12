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
@JsonDeserialize(builder = KafkaMessageRequestDto.KafkaMessageRequestDtoBuilder.class)
public class KafkaMessageRequestDto {

    @NonNull
    private final String topic;

    @NonNull
    private final String key;

    @NonNull
    private final String value;

    @JsonPOJOBuilder(withPrefix = "")
    public static class KafkaMessageRequestDtoBuilder {

    }

}
