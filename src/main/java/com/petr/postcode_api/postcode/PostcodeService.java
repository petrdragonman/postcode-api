package com.petr.postcode_api.postcode;
import java.util.List;

import org.springframework.stereotype.Service;

import com.petr.postcode_api.common.Result;
import com.petr.postcode_api.common.exceptions.PostcodeNotFoundException;

@Service
public class PostcodeService {

    private PostcodeRepository repo;

    public PostcodeService(PostcodeRepository repo) {
        this.repo = repo;
    }

    public Postcode getById(Long id) {
        return this.repo.findById(id).orElseThrow(() -> new PostcodeNotFoundException(id));
    }

    public List<Postcode> getAll() {
        //return this.repo.findAll();
        return List.of();
    }
    
}
