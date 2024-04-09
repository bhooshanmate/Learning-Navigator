package com.crio.learning_navigator.services;

import org.springframework.stereotype.Service;

@Service
public interface EasterEggFeatureService {
    public String generateRandomFactsByNumber(Long number);
    public String generateRamdonFactsByDate(Long day,Long month);
}
