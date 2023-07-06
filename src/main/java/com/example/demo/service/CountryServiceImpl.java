package com.example.demo.service;

import com.example.demo.api.dto.CountryResponseDto;
import com.example.demo.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entity.Country;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CountryResponseDto> getCountries() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(c -> new CountryResponseDto(c.getId(), c.getName(), c.getIsActive()))
                .collect(Collectors.toList());
    }

}
