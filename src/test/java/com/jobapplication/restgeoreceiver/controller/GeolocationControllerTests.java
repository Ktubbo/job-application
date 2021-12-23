package com.jobapplication.restgeoreceiver.controller;

import com.google.gson.Gson;
import com.jobapplication.restgeoreceiver.domain.Device;
import com.jobapplication.restgeoreceiver.domain.dto.GeolocationDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringJUnitWebConfig
@WebMvcTest(GeolocationController.class)
public class GeolocationControllerTests {

    @MockBean
    private GeolocationController controller;
    @Autowired
    private MockMvc mockMvc;

    private static Device nokia;
    private static Device samsung;
    private static GeolocationDto nokiaGeolocation;
    private static GeolocationDto secondNokiaGeolocation;
    private static GeolocationDto samsungGeolocation;
    private static List<GeolocationDto> geolocationList;
    private static List<GeolocationDto> nokiaGeolocationList;

    @BeforeAll
    static void prepareTests() {
        nokia = new Device(1L,"Nokia");
        samsung = new Device(2L,"Samsung");
        nokiaGeolocation = new GeolocationDto(1L,12.34567,-23.34567,nokia);
        secondNokiaGeolocation = new GeolocationDto(2L,-12.34567,-23.45678,nokia);
        samsungGeolocation = new GeolocationDto(3L,32.16335,-56.23542,samsung);
        geolocationList = List.of(nokiaGeolocation,samsungGeolocation,secondNokiaGeolocation);
        nokiaGeolocationList = List.of(nokiaGeolocation,secondNokiaGeolocation);
    }


    @Test
    void getGeolocations() throws Exception{
        //Given
        when(controller.getGeolocations()).thenReturn(geolocationList);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/geolocations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status().is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    void createGeolocation() throws Exception{
        //Given
        when(controller.createGeolocation(any(GeolocationDto.class))).thenReturn(nokiaGeolocation);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(nokiaGeolocation);
        //When & Then
        mockMvc
                .perform(post("/v1/geolocations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.device.deviceName",Matchers.is("Nokia")));
    }


    @Test
    void getGeolocation() throws Exception {
        //Given
        when(controller.getGeolocation(any(Long.class))).thenReturn(nokiaGeolocation);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/geolocations/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status().is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.device.deviceName", Matchers.is("Nokia")));
    }

    @Test
    void getDeviceGeolocation() throws Exception{
        //Given
        when(controller.getDeviceGeolocation(any(Long.class))).thenReturn(nokiaGeolocationList);
        Gson gson = new Gson();
        String content = gson.toJson(nokiaGeolocationList);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/deviceGeolocations/1"))
                .andExpect((MockMvcResultMatchers.status().is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['device']['deviceName']", Matchers.is("Nokia")));
    }
}
