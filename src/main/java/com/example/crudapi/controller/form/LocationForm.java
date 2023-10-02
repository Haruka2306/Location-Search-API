package com.example.crudapi.controller.form;

import com.example.crudapi.dto.LocationDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationForm {
    @Size(max = 20, message = "Please enter up to 20 characters")
    @NotBlank(message = "required item")
    private String corner;

    @Pattern(regexp = "[A-Z]{1}", message = "Please enter in one capital letter of the alphabet")
    @JsonProperty("location_name")
    private String locationName;

    @Size(max = 20, message = "Please enter up to 20 characters")
    @NotBlank(message = "required item")
    private String place;

    @Size(max = 20, message = "Please enter up to 20 characters")
    @NotBlank(message = "required item")
    @JsonProperty("created_by")
    private String createdBy;

    @Pattern(regexp = "[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])", message = "Please enter in yyyy/mm/dd")
    @JsonProperty("created_date")
    private String createdDate;

    //formからdtoへ変換
    public LocationDto convertToLocationDto() {
        LocationDto locationDto = new LocationDto(corner, locationName, place, createdBy, createdDate);
        return locationDto;
    }
}
