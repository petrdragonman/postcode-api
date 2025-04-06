

package com.petr.postcode_api.postcode;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.petr.postcode_api.common.StatusCode;
import com.petr.postcode_api.common.exceptions.PostcodeNotFoundException;
import com.petr.postcode_api.postcode.Postcode.StateCode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostcodeControllerTest {

    @LocalServerPort
    private int port;

    @MockitoBean
    private PostcodeService postcodeService;

    @MockitoBean
    private ModelMapper mapper;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        //RestAssured.basePath = "/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void getPostcodeById_shouldReturnPostcode() {
        // Given
        Long id = 1L;
        Postcode postcode = TestDataFactory.createPostcode(id, "2037", "Glebe", Postcode.StateCode.NSW);
        given(postcodeService.getById(id)).willReturn(postcode);

        // When + Then
        given()
            .pathParam("id", id)
        .when()
            .get("/postcodes/{id}")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("flag", equalTo(true))
        .body("code", equalTo(200))
        .body("message", equalTo("Find One Success"))
        .body("data.id", equalTo(id.intValue()))
        .body("data.postcode", equalTo("2037"))
        .body("data.suburb", equalTo("Glebe"))
        .body("data.stateCode", equalTo("NSW"))
        .body("data.createdAt", notNullValue());
    }

    @Test
    public void getPostcodeById_shouldReturn404WhenNotFound() {
        // Given
        Long invalidId = 999L;
        given(postcodeService.getById(invalidId))
            .willThrow(new PostcodeNotFoundException(invalidId));

        // When + Then
        given()
            .pathParam("id", invalidId)
        .when()
            .get("/postcodes/{id}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body("flag", equalTo(false))
            .body("code", equalTo(HttpStatus.NOT_FOUND.value()))
            .body("message", containsString("Could not found postcode with id: " + invalidId))
            .body("data", nullValue());
    }

    @Test
    public void getAllPostcodes_shouldReturnList() {
        // Given
        List<Postcode> postcodes = List.of(
            TestDataFactory.createPostcode(1L, "2000", "Sydney", Postcode.StateCode.NSW),
            TestDataFactory.createPostcode(2L, "3000", "Melbourne", Postcode.StateCode.VIC)
        );
        given(postcodeService.getAll()).willReturn(postcodes);

        // When + Then
        given()
        .when()
            .get("/postcodes")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("flag", equalTo(true))
            .body("code", equalTo(200))
            .body("message", equalTo("Find All Success"))
            .body("data.size()", equalTo(2))
            .body("data[0].postcode", equalTo("2000"))
            .body("data[1].postcode", equalTo("3000"));
    }

    // @Test
    // public void getPostcodesByState_shouldFilterByState() {
    //     // Given
    //     List<Postcode> nswPostcodes = List.of(
    //         TestDataFactory.createPostcode(1L, "2000", "Sydney", Postcode.StateCode.NSW)
    //     );
    //     given(postcodeService.getPostcodesByState(Postcode.StateCode.NSW))
    //         .willReturn(nswPostcodes);

    //     // When + Then
    //     given()
    //         .queryParam("state", "NSW")
    //     .when()
    //         .get("/postcodes/search")
    //     .then()
    //         .statusCode(HttpStatus.OK.value())
    //         .body("size()", equalTo(1))
    //         .body("[0].stateCode", equalTo("NSW"));
    // }

    @Test
    public void createPostcode_shouldReturn201() {
        // Given
        Postcode expectedPostcode = new Postcode();
        expectedPostcode.setPostcode("2067");
        expectedPostcode.setSuburb("Chadswood");
        expectedPostcode.setStateCode(StateCode.NSW);

        when(mapper.map(any(CreatePostcodeDTO.class), eq(Postcode.class)))
            .thenReturn(expectedPostcode);
        
        given(postcodeService.createPostcode(any(CreatePostcodeDTO.class)))
            .willReturn(expectedPostcode);
        given()
            .contentType(ContentType.JSON)
            .body(expectedPostcode)
        .when()
            .post("/postcodes")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("flag", equalTo(true))
            .body("code", equalTo(200))
            .body("message", equalTo("Add Success"))
            .body("data.postcode", equalTo("2067"))
            .body("data.suburb", equalTo("Chadswood"))
            .body("data.stateCode", equalTo("NSW"));
    }

    @Test
    public void updatePostcodeById_success() {
        // Given
        Postcode expectedPostcode = new Postcode();
        expectedPostcode.setPostcode("2067");
        expectedPostcode.setSuburb("Chatswood");
        expectedPostcode.setStateCode(StateCode.NSW);

        when(mapper.map(any(UpdatePostcodeDTO.class), eq(Postcode.class)))
            .thenReturn(expectedPostcode);
        
        given(postcodeService.updatePostcode(eq(expectedPostcode.getId()), any(UpdatePostcodeDTO.class)))
            .willReturn(expectedPostcode);
        given()
            .contentType(ContentType.JSON)
            .body(expectedPostcode)
        .when()
            .patch("/postcodes/"+1L)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("flag", equalTo(true))
            .body("code", equalTo(200))
            .body("message", equalTo("Update Success"));
    }

    @Test
    public void updatePostcodeById_postcodeNotFound() {
        // given
        Long invalidId = 999L;
        given(postcodeService.getById(invalidId))
            .willThrow(new PostcodeNotFoundException(invalidId));

        given()
            .pathParam("id", invalidId)
        .when()
            .get("/postcodes/{id}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body("flag", equalTo(false))
            .body("code", equalTo(HttpStatus.NOT_FOUND.value()))
            .body("message", containsString("Could not found postcode with id: " + invalidId))
            .body("data", nullValue());
    }

    @Test
    public void deletePostcodeById_shoudReturnBoolean_Success() {
        // Given
        Long id = 1L;
        boolean result = true;
        given(postcodeService.deleteById(id)).willReturn(result);

        // When + Then
        given()
            .pathParam("id", id)
        .when()
            .get("/postcodes/{id}")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("flag", equalTo(true))
        .body("code", equalTo(200))
        .body("message", equalTo("Find One Success"));
    }

    @Test
    public void getPostcodeById_shouldReturn400ForInvalidIdFormat() {
        given()
            .pathParam("id", "invalid")
        .when()
            .get("/postcodes/{id}")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    private static class TestDataFactory {
        public static Postcode createPostcode(Long id, String code, String suburb, Postcode.StateCode state) {
            Postcode postcode = new Postcode();
            postcode.setId(id);
            postcode.setPostcode(code);
            postcode.setSuburb(suburb);
            postcode.setStateCode(state);
            postcode.onCreate();
            return postcode;
        }
    }
}