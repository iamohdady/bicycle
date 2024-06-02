package com.graduation.PublicBicycle.controller;
import com.graduation.PublicBicycle.entity.Tram;
import com.graduation.PublicBicycle.repository.TramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class MapController {

    @Autowired
    private TramRepository tramRepository;

private final String MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoibmhhbXZhbnR1bmciLCJhIjoiY2x3bHVwNXNjMG5qZjJrcDhzNmd2ejVlOSJ9.dQQImuxddFDUi0DOoben1g";

    @GetMapping(value = "/fetchMarkerData", produces = MediaType.APPLICATION_JSON_VALUE)
    public String fetchMarkerData() {
        JSONArray markers = new JSONArray();
        List<Tram> trams = tramRepository.findAll();
        trams.forEach(tram -> {
            try {
                String diadiemtram = URLEncoder.encode(tram.getDiadiemtram(), "UTF-8");
                String url = String.format(
                        "https://api.mapbox.com/geocoding/v5/mapbox.places/%s.json?access_token=%s",
                        diadiemtram, MAPBOX_ACCESS_TOKEN);
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response = in.readLine();
                in.close();

                JSONObject json = new JSONObject(response);
                JSONArray features = json.getJSONArray("features");
                if (features.length() > 0) {
                    JSONObject feature = features.getJSONObject(0);
                    JSONArray center = feature.getJSONArray("center");
                    double longitude = center.getDouble(0);
                    double latitude = center.getDouble(1);

                    JSONObject marker = new JSONObject();
                    marker.put("matram", tram.getMatram());
                    marker.put("tentram", tram.getTentram());
                    marker.put("diadiemtram", tram.getDiadiemtram());
                    marker.put("soluong", tram.getSoluong());
                    marker.put("latitude", latitude);
                    marker.put("longitude", longitude);
                    markers.put(marker);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return markers.toString();
    }
}
