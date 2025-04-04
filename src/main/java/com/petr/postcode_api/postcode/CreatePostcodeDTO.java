package com.petr.postcode_api.postcode;
import com.petr.postcode_api.postcode.Postcode.StateCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreatePostcodeDTO {
    @NotBlank
    private String postcode;
    @NotBlank
    private String suburb;
    @NotNull
    private StateCode stateCode;

    public CreatePostcodeDTO() {}

    public String getPostcode() {
        return postcode;
    }
    public String getSuburb() {
        return suburb;
    }
    public StateCode getStateCode() {
        return stateCode;
    }
    @Override
    public String toString() {
        return "CreatePostcodeDTO [postcode=" + postcode + ", suburb=" + suburb + ", stateCode=" + stateCode + "]";
    }
}
