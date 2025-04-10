package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import org.springframework.web.client.RestTemplate;

public record AddressDto(
        double latitude,
        double longitude,
        String firstLine,
        String secondLine,
        String postalCode,
        String city
) {

    public AddressDto(double latitude, double longitude) {
        //TODO: faire une requête à openStreetMaps pour avoir ces infos
        //https://nominatim.openstreetmap.org/reverse?format=json&lat=52.5487429714954&lon=-1.81602098644987&zoom=18&addressdetails=1

        this(latitude,longitude, "", "", "", "");
    }
}
