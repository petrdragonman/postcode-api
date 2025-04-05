package com.petr.postcode_api.postcode;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.petr.postcode_api.common.exceptions.PostcodeNotFoundException;

@Service
public class PostcodeService {

    private PostcodeRepository repo;
    private ModelMapper mapper;


    public PostcodeService(PostcodeRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Postcode getById(Long id) {
        return this.repo.findById(id).orElseThrow(() -> new PostcodeNotFoundException(id));
    }

    public List<Postcode> getAll() {
        return this.repo.findAll();
    }

    public Postcode createPostcode(CreatePostcodeDTO data) {
        //Postcode newPostcode = new Postcode();
        //newPostcode.setPostcode(data.getPostcode());
        //newPostcode.setSuburb(data.getSuburb());
        //newPostcode.setStateCode(data.getStateCode());
        Postcode newPostcode = mapper.map(data, Postcode.class);
        return this.repo.save(newPostcode);
    }
    
}
