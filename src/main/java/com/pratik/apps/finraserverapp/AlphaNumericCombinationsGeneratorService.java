package com.pratik.apps.finraserverapp;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlphaNumericCombinationsGeneratorService {

    /**
     *
     * @param phoneNumber a string phone number
     * @param page current page of results being requested
     * @param size number of items to be returned in the result
     * @return  PageDto object that contains paging information along with alphanumeric combinations for given page
     */
    public PageDto<String> generateAlphaNumericCombinations(String phoneNumber, int page, int size) {
        List<String> alphaNumericCombinations = new ArrayList<>();
        List<String> perPageAlphaNumericCombinations = new ArrayList<>();

        String[] phoneNumberArray = phoneNumber.split("(?!^)");
        Map<String, List<String>> alphaNumericMap = getAlphaNumericMap();

        for (int i = 0; i < phoneNumber.length(); i++) {
            List<String> alpha = alphaNumericMap.get(phoneNumberArray[i]);
            for (int j = 0; j < alpha.size(); j++) {
                String combination = new StringBuilder().append(phoneNumber.substring(0, i)).append(alpha.get(j)).append(phoneNumber.substring(i + 1)).toString();
                if (!phoneNumber.equals(combination)) {
                    alphaNumericCombinations.add(combination);
                }
            }
        }

        if(alphaNumericCombinations.isEmpty()){
            alphaNumericCombinations.add(phoneNumber);
        }

        Collections.sort(alphaNumericCombinations);
        int alphaNumericCombinationSize = alphaNumericCombinations.size();

        int totalPages = (int)Math.ceil(alphaNumericCombinationSize/size )+ 1;

            int tobeSkipped = page*size;
            if(tobeSkipped<=alphaNumericCombinationSize) {
                if (tobeSkipped + size < alphaNumericCombinationSize) {
                    perPageAlphaNumericCombinations = alphaNumericCombinations.subList(tobeSkipped, tobeSkipped + size);
                } else {
                    perPageAlphaNumericCombinations = alphaNumericCombinations.subList(tobeSkipped, alphaNumericCombinationSize);
                }
            }

        return new PageDto<>(totalPages, alphaNumericCombinationSize, page, perPageAlphaNumericCombinations);
    }

    public static Map<String, List<String>> getAlphaNumericMap(){
        Map<String, List<String>>  alphaNumericMap = new HashMap<>();
        alphaNumericMap.put("0", Arrays.asList("0"));
        alphaNumericMap.put("1", Arrays.asList("1"));
        alphaNumericMap.put("2", Arrays.asList("A", "B", "C"));
        alphaNumericMap.put("3", Arrays.asList("D", "E", "F"));
        alphaNumericMap.put("4", Arrays.asList("G", "H", "I"));
        alphaNumericMap.put("5", Arrays.asList("J", "K", "L"));
        alphaNumericMap.put("6", Arrays.asList("M", "N", "O"));
        alphaNumericMap.put("7", Arrays.asList("P", "Q", "R", "S"));
        alphaNumericMap.put("8", Arrays.asList("T", "U", "V"));
        alphaNumericMap.put("9", Arrays.asList("W", "X", "Y", "Z"));
        return alphaNumericMap;

    }


}
