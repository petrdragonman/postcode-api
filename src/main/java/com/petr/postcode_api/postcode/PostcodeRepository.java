package com.petr.postcode_api.postcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostcodeRepository extends JpaRepository<Postcode, Long>{
    
}
