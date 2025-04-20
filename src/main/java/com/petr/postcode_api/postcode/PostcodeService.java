package com.petr.postcode_api.postcode;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.petr.postcode_api.common.exceptions.PostcodeNotFoundException;
import com.petr.postcode_api.common.exceptions.SuburbNotFoundException;

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
        Postcode newPostcode = mapper.map(data, Postcode.class);
        return this.repo.save(newPostcode);
    }

    public boolean deleteById(Long id) {
        Postcode result = this.getById(id);
        this.repo.delete(result);
        return true;
    }

    public Postcode updatePostcode(Long id, UpdatePostcodeDTO data) {
        Postcode foundPostcode = this.getById(id);
        foundPostcode.setPostcode(data.getPostcode());
        foundPostcode.setSuburb(data.getSuburb());
        foundPostcode.setStateCode(data.getStateCode());
        //mapper.map(data, foundPostcode);
        this.repo.save(foundPostcode);
        return foundPostcode;
    }

    public List<Postcode> getBySuburb(String suburb) {
        List<Postcode> result = this.repo.findBySuburb(suburb).orElseThrow(() -> new SuburbNotFoundException(suburb));
        if(result.isEmpty()) {
            throw new SuburbNotFoundException(suburb);
        }
        return result;
    }

    public List<Postcode> getByPostcode(String postcode) {
        List<Postcode> result = this.repo.findByPostcode(postcode).orElseThrow(() -> new PostcodeNotFoundException(postcode));
        if(result.isEmpty()) {
            throw new PostcodeNotFoundException(postcode);
        }
        return result;
    }
    
}
