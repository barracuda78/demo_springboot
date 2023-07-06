package com.example.demo.service;

import com.example.demo.api.dto.CountryResponseDto;

import java.util.List;

public interface CountryService {

    List<CountryResponseDto> getCountries();

}
