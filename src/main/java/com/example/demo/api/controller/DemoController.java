package com.example.demo.api.controller;

import com.example.demo.api.dto.CountryResponseDto;
import com.example.demo.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final CountryService countryService;

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

}
