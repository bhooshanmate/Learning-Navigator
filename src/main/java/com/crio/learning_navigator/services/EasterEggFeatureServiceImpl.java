package com.crio.learning_navigator.services;

import com.crio.learning_navigator.DTOs.GenerateFactsByDateDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EasterEggFeatureServiceImpl implements EasterEggFeatureService{
    private static final Logger log = LoggerFactory
            .getLogger(EasterEggFeatureServiceImpl.class);
    private final RestTemplate restTemplate;

    @Autowired
    public EasterEggFeatureServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String generateRandomFactsByNumber(Long number) {
        try {
            String url = "http://numbersapi.com/"+number;
            String randomFact = restTemplate.getForObject(url,String.class);
            log.info(randomFact);
            return randomFact;
        } catch (RestClientException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String generateRamdonFactsByDate(Long day, Long month) {
        try {
            String url = "http://numbersapi.com/"
                    + month + "/"
                    + day + "/"
                    + "date"+"?json";
            GenerateFactsByDateDTO randomFactByDate = restTemplate.getForObject(url,
                    GenerateFactsByDateDTO.class);
            String randomFactByDateString = randomFactByDate.getText()
                    + "In fact this is fact for a number "
                    +randomFactByDate.getNumber();
            log.info(randomFactByDateString);
            return randomFactByDateString;
        } catch (RestClientException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
