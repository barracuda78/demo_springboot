package com.example.demo.api.controller;

import com.example.demo.api.dto.CountryCreateRequestDto;
import com.example.demo.api.dto.CountryResponseDto;
import com.example.demo.api.dto.KafkaMessageRequestDto;
import com.example.demo.service.AwsService;
import com.example.demo.service.CountryService;
import com.example.demo.service.KafkaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final CountryService countryService;

    private final KafkaClient kafkaClient;

    private final AwsService awsService;

    @GetMapping("api/v1/names")
    ResponseEntity<List<String>> getAllNames() {
        final List<String> names = List.of("Andrei", "Oksana", "Alisa", "Olya");
        return ResponseEntity.ok(names);
    }

    @GetMapping("api/v1/countries")
    ResponseEntity<List<CountryResponseDto>> getAllCountries() {
        List<CountryResponseDto> countries = countryService.getCountries();
        return ResponseEntity.ok(countries);
    }

    @PostMapping(path = "api/v1/countries", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Long> createCountry(@RequestBody CountryCreateRequestDto dto) {
        final Long newCountryId = countryService.createCountry(dto);
        return ResponseEntity.ok(newCountryId);
    }

    @PostMapping(path = "api/v1/kafka-message", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Long> sendToKafka(@RequestBody KafkaMessageRequestDto dto) {
        final Long newCountryId = kafkaClient.sendMessage(dto);
        return ResponseEntity.ok(newCountryId);
    }

    @GetMapping("api/v1/sqs/{message}")
    ResponseEntity<String> sendToSqs(@PathVariable("message") String message) {
        awsService.sendToSqs(message);
        return ResponseEntity.ok(message + " sent.");
    }

}
