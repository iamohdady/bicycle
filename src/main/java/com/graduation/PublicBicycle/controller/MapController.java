package com.graduation.PublicBicycle.controller;
import com.graduation.PublicBicycle.entity.Tram;
import com.graduation.PublicBicycle.repository.TramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@RestController
public class MapController {

    @Autowired
    private TramRepository tramRepository;

    @GetMapping(value = "/fetchMarkerData")
    public String fetchMarkerData() {
        JSONArray markers = new JSONArray();
        List<Tram> trams = tramRepository.findAll();
        trams.forEach(tram -> {
            try {
                JSONObject marker = new JSONObject();
                marker.put("matram", tram.getMatram());
                marker.put("tentram", tram.getTentram());
                marker.put("diadiemtram", tram.getDiadiemtram());
                marker.put("soluong", tram.getSoluong());
                marker.put("latitude", tram.getLatitude());  // Assuming 'getLatitude()' returns the latitude
                marker.put("longitude", tram.getLongitude());  // Assuming 'getLongitude()' returns the longitude
                markers.put(marker);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return markers.toString();
    }
}

