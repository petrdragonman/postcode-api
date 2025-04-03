package com.petr.postcode_api.postcode;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PostcodeService {

    private PostcodeRepository repo;

    public PostcodeService(PostcodeRepository repo) {
        this.repo = repo;
    }



    public Optional<Postcode> getById(Long id) {
        return this.repo.findById(id);
    }
    
}
