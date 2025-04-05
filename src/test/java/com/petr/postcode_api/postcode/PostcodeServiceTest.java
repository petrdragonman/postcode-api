

package com.petr.postcode_api.postcode;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.petr.postcode_api.common.BaseEntity;
import com.petr.postcode_api.common.exceptions.PostcodeNotFoundException;
import com.petr.postcode_api.postcode.Postcode.StateCode;

@ExtendWith(MockitoExtension.class)
public class PostcodeServiceTest {

    @Mock
    private PostcodeRepository postcodeRepository;

    @InjectMocks
    private PostcodeService postcodeService;

    List<Postcode> postcodes;

    @BeforeEach
    void setup() {
        this.postcodes = new ArrayList<>();
        Postcode p1 = new Postcode();
        p1.setPostcode("2037");
        p1.setSuburb("Glebe");
        p1.setStateCode(StateCode.NSW);
        this.postcodes.add(p1);
        Postcode p2 = new Postcode();
        p1.setPostcode("2037");
        p1.setSuburb("Forest Lodge");
        p1.setStateCode(StateCode.NSW);
        this.postcodes.add(p2);
        Postcode p3 = new Postcode();
        p1.setPostcode("2039");
        p1.setSuburb("Pyrmont");
        p1.setStateCode(StateCode.NSW);
        this.postcodes.add(p3);
        Postcode p4 = new Postcode();
        p1.setPostcode("2000");
        p1.setSuburb("Sydney");
        p1.setStateCode(StateCode.NSW);
        this.postcodes.add(p4);
    }

    @Test
    public void getById_shouldReturnPostcode_whenIdExists() {
        // Given
        Postcode postcode = createTestPostcode(1L, "2037", "Glebe", Postcode.StateCode.NSW);
        given(postcodeRepository.findById(1L)).willReturn(Optional.of(postcode));

        // When
        Postcode result = postcodeService.getById(1L);

        // Then
        assertThat(result)
            .extracting(
                BaseEntity::getId,
                Postcode::getPostcode,
                Postcode::getSuburb,
                Postcode::getStateCode,
                BaseEntity::getCreatedAt
            )
            .containsExactly(1L, "2037", "Glebe", Postcode.StateCode.NSW, postcode.getCreatedAt());

        verify(postcodeRepository, times(1)).findById(1L);
    }

    @Test
    public void getById_shouldThrowException_whenIdDoesNotExist() {
        // Given
        Long invalidId = 999L;
        given(postcodeRepository.findById(invalidId)).willReturn(Optional.empty());

        // When + Then
        assertThatThrownBy(() -> postcodeService.getById(invalidId))
            .isInstanceOf(PostcodeNotFoundException.class)
            .hasMessage("Could not found postcode with id: " + invalidId);

        verify(postcodeRepository, times(1)).findById(invalidId);
    }

    @Test
    void findAllSuccess() {
        // given
        given(postcodeRepository.findAll()).willReturn(postcodes);

        // when
        List<Postcode> result = postcodeService.getAll();

        // then
        assertThat(result.size()).isEqualTo(this.postcodes.size());
        verify(postcodeRepository, times(1)).findAll();
    }

    

    // Helper method to create a test Postcode with BaseEntity fields
    private Postcode createTestPostcode(Long id, String postcode, String suburb, Postcode.StateCode stateCode) {
        Postcode entity = new Postcode();
        entity.setId(id); // Inherited from BaseEntity
        entity.setPostcode(postcode);
        entity.setSuburb(suburb);
        entity.setStateCode(stateCode);
        entity.onCreate(); // Triggers @PrePersist to set createdAt/updatedAt
        return entity;
    }
}