package com.petr.postcode_api.postcode;
import com.petr.postcode_api.postcode.Postcode.StateCode;
import jakarta.validation.constraints.Pattern;

public class UpdatePostcodeDTO {
    @Pattern(regexp = "^\\d{4}$", message = "Invalid code: Must be exactly 4 digits (0-9)")
    private String postcode;
    
    @Pattern(regexp = "\\S+.*", message = "Field must not be empty or blank")
    private String suburb;
    
    private StateCode stateCode;

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
        return "UpdatePostcodeDTO [postcode=" + postcode + ", suburb=" + suburb + ", stateCode=" + stateCode + "]";
    }
    
}
