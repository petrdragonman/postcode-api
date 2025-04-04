

package com.petr.postcode_api.postcode;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.times;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.petr.postcode_api.common.BaseEntity;
import com.petr.postcode_api.common.exceptions.PostcodeNotFoundException;

@ExtendWith(MockitoExtension.class)
public class PostcodeServiceTest {

    @Mock
    private PostcodeRepository postcodeRepository;

    @InjectMocks
    private PostcodeService postcodeService;

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










// package com.petr.postcode_api.postcode;
// import static org.mockito.Mockito.verify;
// import java.util.Optional;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.mockito.Spy;
// import com.petr.postcode_api.common.exceptions.PostcodeNotFoundException;
// import com.petr.postcode_api.postcode.Postcode.StateCode;
// import static org.mockito.BDDMockito.*;
// import static org.assertj.core.api.Assertions.*;

// public class PostcodeServiceTest {
//     @Mock
//     private PostcodeRepository postcodeRepository;

//     @Spy
//     @InjectMocks
//     private PostcodeService postcodeService;

//     @BeforeEach
//     public void setup() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     public void getById_callsFindById() {
//         // given
//         Postcode postcode = new Postcode();
//         postcode.setPostcode("2037");
//         postcode.setSuburb("Glebe");
//         postcode.setStateCode(StateCode.NSW);

//         given(postcodeRepository.findById(postcode.getId())).willReturn(Optional.of(postcode));

//         // when
//         Postcode returnedPostcode = postcodeService.getById(postcode.getId());

//         // then
//         assertThat(returnedPostcode.getId()).isEqualTo(postcode.getId());
//         assertThat(returnedPostcode.getPostcode()).isEqualTo(postcode.getPostcode());
//         assertThat(returnedPostcode.getSuburb()).isEqualTo(postcode.getSuburb());
//         assertThat(returnedPostcode.getStateCode()).isEqualTo(postcode.getStateCode());
//         verify(postcodeRepository, times(1)).findById(postcode.getId());
//     }

//     @Test
//     public void getById_idNotFound() {
//         // given
//         given(postcodeRepository.findById(Mockito.any(Long.class))).willReturn(Optional.empty());

//         // when
//         Throwable thrown = catchThrowable(() -> {
//             Postcode returnedPostcode = postcodeService.getById(1L);
//         });

//         // then
//         assertThat(thrown)
//             .isInstanceOf(PostcodeNotFoundException.class)
//             .hasMessage("Could not found postcode with id: " + 1L);
//         verify(postcodeRepository, times(1)).findById(1L);
//     }

// }
