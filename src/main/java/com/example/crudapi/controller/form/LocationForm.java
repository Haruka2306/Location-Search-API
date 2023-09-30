package com.example.crudapi.controller.form;

import com.example.crudapi.dto.LocationDto;
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
    @NotBlank(message = "required item")
    private String location_name;

    @Size(max = 20, message = "Please enter up to 20 characters")
    @NotBlank(message = "required item")
    private String place;

    @Size(max = 20, message = "Please enter up to 20 characters")
    @NotBlank(message = "required item")
    private String creator;

    @Pattern(regexp = "[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])", message = "Please enter in yyyy/mm/dd")
    @NotBlank(message = "required item")
    private String date_created;

    //formからdtoへ変換
    public LocationDto convertToLocationDto() {
        LocationDto locationDto = new LocationDto(corner, location_name, place, creator, date_created);
        return locationDto;
    }
}
