package com.crio.learning_navigator.controllers;

import com.crio.learning_navigator.services.EasterEggFeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hidden-feature")
public class EsterEggFeatureController {

    private final EasterEggFeatureService easterEggFeatureService;

    @Autowired
    public EsterEggFeatureController(
            EasterEggFeatureService easterEggFeatureService) {

        this.easterEggFeatureService = easterEggFeatureService;
    }

    @GetMapping("/{number}")
    public ResponseEntity<String> generateRandomFactByNumber(
            @PathVariable("number") Long number) {
        if(number == null) {
            return ResponseEntity.badRequest().body(
                    "Please provide the number as path variable ! "
            );
        }

        String randomFactsByNumber = easterEggFeatureService.
                generateRandomFactsByNumber(number);
        return ResponseEntity.status(HttpStatus.OK).body(randomFactsByNumber);
    }
    @GetMapping("/{month}/{date}")
    public ResponseEntity<String> generateRandomFactByDate(
            @PathVariable("month") Long month,
            @PathVariable("date") Long date) {
        if (month == null || date == null) {
            return ResponseEntity.badRequest().body(
                    "Please provide correct parameters ! "
            );
        }
        String randomFactsByDate = easterEggFeatureService
                .generateRamdonFactsByDate(date, month);
        return ResponseEntity.status(HttpStatus.OK).body(randomFactsByDate);
    }
}
