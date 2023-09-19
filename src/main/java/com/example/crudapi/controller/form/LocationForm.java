package com.example.crudapi.controller.form;

import com.example.crudapi.entity.Location;
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
    private String locationName;

    @Size(max = 20, message = "Please enter up to 20 characters")
    @NotBlank(message = "required item")
    private String place;

    @Size(max = 20, message = "Please enter up to 20 characters")
    @NotBlank(message = "required item")
    private String creator;

    public Location convertToLocation() {
        Location location = new Location(corner, locationName, place, creator);
        return location;
    }
}
