package com.petr.postcode_api.postcode;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostcodeRepository extends JpaRepository<Postcode, Long>{
    Optional<List<Postcode>> findBySuburb(String suburb);
    Optional<List<Postcode>> findByPostcode(String postcode);
}
