package com.petr.postcode_api.postcode;
import com.petr.postcode_api.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "postcodes")
public class Postcode extends BaseEntity {
    public enum StateCode {
        NSW,
        VIC,
        QLD,
        SA,
        WA,
        TAS,
        NT,
        ACT,
    }

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private String suburb;

    @Enumerated(EnumType.STRING)
    private StateCode stateCode;


    public Postcode() {

    }


    public String getPostcode() {
        return postcode;
    }


    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


    public String getSuburb() {
        return suburb;
    }


    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    @Override
    public String toString() {
        return "Postcode [postcode=" + postcode + ", suburb=" + suburb + ", stateCode=" + stateCode + "]";
    }
}
