
package com.petr.postcode_api.postcode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petr.postcode_api.common.Result;
import com.petr.postcode_api.common.StatusCode;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/postcodes")
public class PostcodeController {
    private PostcodeService postcodeService;

    public PostcodeController(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    @GetMapping("/{id}")
    public Result getPostcodeById(@PathVariable Long id) throws Exception {
        Postcode foundPostcode = this.postcodeService.getById(id);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", foundPostcode);
    }

    @GetMapping
    public Result getAllPostcodes() {
        List<Postcode>  foundPostcodes = this.postcodeService.getAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", foundPostcodes);
    }

    @PostMapping
    public Result createPostcode(@RequestBody @Valid CreatePostcodeDTO data) {
        Postcode newPostcode = this.postcodeService.createPostcode(data);
        return new Result(true, StatusCode.SUCCESS, "Add Success", newPostcode);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        boolean wasDeleted = this.postcodeService.deleteById(id);
        return null;
    }
    
}


/*
 * @GetMapping("/postcodes/{id}")
    public ResponseEntity<Postcode> getPostcodeById(@PathVariable Long id) throws Exception {
        Postcode foundPostcode = this.postcodeService.getById(id);
        //Postcode foundPostcode = result.orElseThrow(() -> new Exception("Could not find postcode with ID: " + id));
        return new ResponseEntity<Postcode>(foundPostcode, HttpStatus.OK);
    }
 */
