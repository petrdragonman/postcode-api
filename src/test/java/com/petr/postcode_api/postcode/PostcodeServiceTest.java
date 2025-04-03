package com.petr.postcode_api.postcode;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class PostcodeServiceTest {
    @Mock
    private PostcodeRepository postcodeRepository;

    @Spy
    @InjectMocks
    private PostcodeService postcodeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getById_callsFindById() {
        postcodeService.getById(1L);
        verify(postcodeRepository).findById(1L);
    }
}
