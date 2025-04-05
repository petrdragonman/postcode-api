package com.petr.postcode_api.postcode;
import com.petr.postcode_api.postcode.Postcode.StateCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreatePostcodeDTO {
    @NotBlank(message = "postcode is required")
    @Pattern(regexp = "^\\d{4}$", message = "Invalid code: Must be exactly 4 digits (0-9)")
    private String postcode;
    @NotBlank(message = "suburb name is required")
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
