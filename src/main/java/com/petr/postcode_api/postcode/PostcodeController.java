package com.petr.postcode_api.postcode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/postcodes")
public class PostcodeController {
    private PostcodeService postcodeService;

    public PostcodeController(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    @GetMapping("/postcodes/{id}")
    public ResponseEntity<Postcode> getPostcodeById(@PathVariable Long id) throws Exception {
        Optional<Postcode> result = this.postcodeService.getById(id);
        Postcode foundPostcode = result.orElseThrow(() -> new Exception("Could not find postcode with ID: " + id));
        return new ResponseEntity<Postcode>(foundPostcode, HttpStatus.OK);
    }
    
}
