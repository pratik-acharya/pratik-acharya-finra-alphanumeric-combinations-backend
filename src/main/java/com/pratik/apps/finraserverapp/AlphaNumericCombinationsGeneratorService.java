package com.pratik.apps.finraserverapp;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlphaNumericCombinationsGeneratorService {

	/**
	 * prepares alphaNumericCombination list for each page
	 * @param phoneNumber a string phone number
	 * @param page        current page of results being requested
	 * @param size        number of items to be returned in the result
	 * @return PageDto object that contains paging information along with
	 *         alphanumeric combinations for given page
	 */
	public PageDto<String> generateAlphaNumericCombinations(String phoneNumber, int page, int size) {
		List<String> alphaNumericCombinations = getAlphaNumericCombinations(phoneNumber);
		List<String> perPageAlphaNumericCombinations = new ArrayList<>();

		Collections.sort(alphaNumericCombinations);
		int alphaNumericCombinationSize = alphaNumericCombinations.size();

		int totalPages = (int) Math.ceil(alphaNumericCombinationSize / size) + 1;

		int tobeSkipped = page * size;
		if (tobeSkipped <= alphaNumericCombinationSize) {
			if (tobeSkipped + size < alphaNumericCombinationSize) {
				perPageAlphaNumericCombinations = alphaNumericCombinations.subList(tobeSkipped, tobeSkipped + size);
			} else {
				perPageAlphaNumericCombinations = alphaNumericCombinations.subList(tobeSkipped,
						alphaNumericCombinationSize);
			}
		}

		return new PageDto<>(totalPages, alphaNumericCombinationSize, page, perPageAlphaNumericCombinations);
	}

	public static Map<Character, String> getAlphaNumericMap() {
		Map<Character, String> alphaNumericMap = new HashMap<>();
		alphaNumericMap.put('2', "ABC");
		alphaNumericMap.put('3', "DEF");
		alphaNumericMap.put('4', "GHI");
		alphaNumericMap.put('5', "JKL");
		alphaNumericMap.put('6', "MNO");
		alphaNumericMap.put('7', "PQRS");
		alphaNumericMap.put('8', "TUV");
		alphaNumericMap.put('9', "WXYZ");
		alphaNumericMap.put('1', "1");
		alphaNumericMap.put('0', "0");
		return alphaNumericMap;

	}

	public static List<String> getAlphaNumericCombinations(String phoneNumber) {

		List<String> alphaNumericCombinations = new ArrayList<>();
		if (phoneNumber == null || phoneNumber.length() == 0) {
			return alphaNumericCombinations;
		}

		alphaNumericCombinations.add("");
		Map<Character, String> alphaNumericMap = getAlphaNumericMap();

		for (int i = 0; i < phoneNumber.length(); i++) {
			ArrayList<String> temp = new ArrayList<>();
			String option = alphaNumericMap.get(phoneNumber.charAt(i));

			for (int j = 0; j < alphaNumericCombinations.size(); j++) {
				for (int p = 0; p < option.length(); p++) {
					temp.add(new StringBuilder(alphaNumericCombinations.get(j)).append(option.charAt(p)).toString());
				}
			}

			alphaNumericCombinations.clear();
			alphaNumericCombinations.addAll(temp);
		}

		return alphaNumericCombinations;
	}
	

}
