package com.pratik.apps.finraserverapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;



@RestController
@RequestMapping("/alphaNumericPhoneNumbers")
@Validated
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AlphaNumericPhoneNumbersController {

    private final AlphaNumericCombinationsGeneratorService alphaNumericCombinationsGeneratorService;

    @Autowired
    public AlphaNumericPhoneNumbersController(AlphaNumericCombinationsGeneratorService alphaNumericCombinationsGeneratorService) {
        this.alphaNumericCombinationsGeneratorService = alphaNumericCombinationsGeneratorService;
    }

    @GetMapping(value = "/{phoneNumber}")
    public ResponseEntity<PageDto<String>> getAlphaNumericCombinations(
            @PathVariable("phoneNumber")  @NotBlank @Pattern(regexp="^(\\d{7})(\\d{3})?$", message = "Please provide a valid 7 or 10 digit phone number") String phoneNumber,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "1") int size) {
        PageDto<String> alphaNumericCombinations = this.alphaNumericCombinationsGeneratorService.generateAlphaNumericCombinations(phoneNumber, page, size);
        return ResponseEntity.ok().body(alphaNumericCombinations);
    }


}
