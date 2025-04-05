

package com.petr.postcode_api.postcode;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import com.petr.postcode_api.common.exceptions.PostcodeNotFoundException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostcodeControllerTest {

    @LocalServerPort
    private int port;

    @MockitoBean
    private PostcodeService postcodeService;

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

    // @Test
    // public void createPostcode_shouldReturn201() {
    //     // Given
    //     Postcode newPostcode = TestDataFactory.createPostcode(null, "2000", "Sydney", Postcode.StateCode.NSW);
    //     Postcode savedPostcode = TestDataFactory.createPostcode(1L, "2000", "Sydney", Postcode.StateCode.NSW);
        
    //     given(postcodeService.savePostcode(any(Postcode.class)))
    //         .willReturn(savedPostcode);

    //     // When + Then
    //     given()
    //         .contentType(ContentType.JSON)
    //         .body(newPostcode)
    //     .when()
    //         .post("/postcodes")
    //     .then()
    //         .statusCode(HttpStatus.CREATED.value())
    //         .header("Location", containsString("/api/postcodes/1"))
    //         .body("postcode", equalTo("2000"));
    // }

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



// package com.petr.postcode_api.postcode;
// import static org.mockito.BDDMockito.given;
// import java.util.ArrayList;
// import java.util.List;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.bean.override.mockito.MockitoBean;
// import org.springframework.test.web.servlet.MockMvc;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // .*

// import com.petr.postcode_api.common.StatusCode;
// import com.petr.postcode_api.common.exceptions.PostcodeNotFoundException;
// import com.petr.postcode_api.postcode.Postcode.StateCode;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class PostcodeControllerTest {
    
//     @Autowired
//     MockMvc mockMvc;

//     @MockitoBean
//     PostcodeService postcodeService;

//     List<Postcode> postcodes = new ArrayList<>();

//     @BeforeEach
//     void setup() {
//         this.postcodes = new ArrayList<>();
//         Postcode p1 = new Postcode();
//         p1.setPostcode("2037");
//         p1.setSuburb("Glebe");
//         p1.setStateCode(StateCode.NSW);
//         this.postcodes.add(p1);
//         Postcode p2 = new Postcode();
//         p1.setPostcode("2037");
//         p1.setSuburb("Forest Lodge");
//         p1.setStateCode(StateCode.NSW);
//         this.postcodes.add(p2);
//         Postcode p3 = new Postcode();
//         p1.setPostcode("2039");
//         p1.setSuburb("Pyrmont");
//         p1.setStateCode(StateCode.NSW);
//         this.postcodes.add(p3);
//         Postcode p4 = new Postcode();
//         p1.setPostcode("2000");
//         p1.setSuburb("Sydney");
//         p1.setStateCode(StateCode.NSW);
//         this.postcodes.add(p4);
//     }

//     // @Test
//     // void findPostcodeById_Success() throws Exception {
//     //     // given
//     //     given(this.postcodeService.getById(postcodes.get(0).getId())).willReturn(this.postcodes.get(0));

//     //     // when and then
//     //     this.mockMvc.perform(get("/postcodes/" + postcodes.get(0).getId()).accept(MediaType.APPLICATION_JSON))
//     //         .andExpect(jsonPath("$.flag").value(true))
//     //         .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
//     //         //.andExpect(jsonPath("$.code").value(HttpStatus.OK))
//     //         .andExpect(jsonPath("$.message").value("Find One Success"))
//     //         .andExpect(jsonPath("$.data.id").value(postcodes.get(0).getId()))
//     //         .andExpect(jsonPath("$.data.postcode").value(postcodes.get(0).getPostcode()));    
//     // }

//     @Test
//     void findPostcodeById_NotFound() throws Exception {
//         // given
//         given(this.postcodeService.getById(postcodes.get(0).getId())).willThrow(new PostcodeNotFoundException(postcodes.get(0).getId()));

//         // when and then
//         this.mockMvc.perform(get("/postcodes/" + postcodes.get(0).getId()).accept(MediaType.APPLICATION_JSON))
//             .andExpect(jsonPath("$.flag").value(false))
//             .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
//             //.andExpect(jsonPath("$.code").value(HttpStatus.OK))
//             .andExpect(jsonPath("$.message").value("Could not find postcode with id "))
//             .andExpect(jsonPath("$.data").isEmpty());
            
//     }
// }
