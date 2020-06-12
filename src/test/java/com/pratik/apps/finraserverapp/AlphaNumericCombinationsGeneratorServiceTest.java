package com.pratik.apps.finraserverapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;


@SpringBootTest
public class AlphaNumericCombinationsGeneratorServiceTest {

    @Autowired
    private AlphaNumericCombinationsGeneratorService alphaNumericCombinationsGeneratorService;


    @Test
    void ShouldAddAlphasForNumbersOtherThan1sAnd0sInProvidedPhoneNumber() {
        PageDto<String> alphaNumericCombinationList = alphaNumericCombinationsGeneratorService.generateAlphaNumericCombinations("1234567891",0,2);
        Assertions.assertEquals(2, alphaNumericCombinationList.getPageItems().size(),2);
        Assertions.assertEquals(5833, alphaNumericCombinationList.getTotalPages());
        Assertions.assertEquals(11664, alphaNumericCombinationList.getTotalItems());
        Assertions.assertIterableEquals(Arrays.asList("1ADGJMPTW1", "1ADGJMPTX1"),alphaNumericCombinationList.getPageItems());
    }


    @Test
    void ShouldReturnNoPageItemsWhenProvidedPageIsGreaterThanTotalPages() {
        PageDto<String> alphaNumericCombinationList = alphaNumericCombinationsGeneratorService.generateAlphaNumericCombinations("1234567891",100,500);
        Assertions.assertEquals(0, alphaNumericCombinationList.getPageItems().size());
        Assertions.assertEquals(24, alphaNumericCombinationList.getTotalPages());
        Assertions.assertEquals(11664, alphaNumericCombinationList.getTotalItems());
        Assertions.assertIterableEquals(Arrays.asList(),alphaNumericCombinationList.getPageItems());
    }

    @Test
    void ShouldNotAddAlphasFor1sInProvidedPhoneNumber() {
        PageDto<String> alphaNumericCombinationList = alphaNumericCombinationsGeneratorService.generateAlphaNumericCombinations("111111111",0,2);
        Assertions.assertEquals(1, alphaNumericCombinationList.getPageItems().size());
        Assertions.assertEquals("111111111", alphaNumericCombinationList.getPageItems().get(0));
        Assertions.assertEquals(0,alphaNumericCombinationList.getCurrentPage());

    }

    @Test
    void ShouldNotAddAlphasFor0sInProvidedPhoneNumber() {
        PageDto<String> alphaNumericCombinationList = alphaNumericCombinationsGeneratorService.generateAlphaNumericCombinations("0000000000",0,2);
        Assertions.assertEquals(1, alphaNumericCombinationList.getPageItems().size());
        Assertions.assertEquals("0000000000", alphaNumericCombinationList.getPageItems().get(0));
        Assertions.assertEquals(0,alphaNumericCombinationList.getCurrentPage());

    }



}
