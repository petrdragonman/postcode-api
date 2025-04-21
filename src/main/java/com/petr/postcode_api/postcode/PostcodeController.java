
package com.petr.postcode_api.postcode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petr.postcode_api.common.Result;
import com.petr.postcode_api.common.StatusCode;
import com.petr.postcode_api.common.exceptions.PostcodeNotFoundException;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @GetMapping("/suburb/{suburb}")
    public Result getPostcodeBySuburb(@PathVariable String suburb) {
        List<Postcode> foundPostcode = this.postcodeService.getBySuburb(suburb);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", foundPostcode);
    }

    @GetMapping("/postcode/{postcode}")
    public Result getPostcodeByPostcode(@PathVariable String postcode) {
        List<Postcode> foundPostcode = this.postcodeService.getByPostcode(postcode);
        if(foundPostcode.isEmpty()) {
            new PostcodeNotFoundException(postcode);
        }
        return new Result(true, StatusCode.SUCCESS, "Find One Success", foundPostcode);
    }

    @GetMapping
    public Result getAllPostcodes() {
        List<Postcode> foundPostcodes = this.postcodeService.getAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", foundPostcodes);
    }

    @PostMapping
    public Result createPostcode(@RequestBody @Valid CreatePostcodeDTO data) {
        Postcode newPostcode = this.postcodeService.createPostcode(data);
        return new Result(true, StatusCode.SUCCESS, "Add Success", newPostcode);
    }

    @PatchMapping("/{id}")
    public Result updatePostcodeById(@PathVariable Long id, @Valid @RequestBody UpdatePostcodeDTO data) {
        Postcode updatedPostcode = this.postcodeService.updatePostcode(id, data);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedPostcode);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        boolean wasDeleted = this.postcodeService.deleteById(id);
        return new Result(wasDeleted, StatusCode.SUCCESS, "Delete Success");
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
