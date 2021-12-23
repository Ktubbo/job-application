package com.jobapplication.restgeoreceiver.controller;

import com.google.gson.Gson;
import com.jobapplication.restgeoreceiver.domain.dto.DeviceDto;
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

@SpringJUnitWebConfig
@WebMvcTest(DeviceController.class)
class DeviceControllerTests {

    @MockBean
    private DeviceController controller;
    @Autowired
    private MockMvc mockMvc;

    private static DeviceDto nokia;
    private static DeviceDto samsung;
    private static List<DeviceDto> deviceList;

    @BeforeAll
    static void prepareTests() {
        nokia = new DeviceDto(1L,"Nokia");
        samsung = new DeviceDto(2L,"Samsung");
        deviceList = List.of(nokia,samsung);
    }


    @Test
    void getDevices() throws Exception{
        //Given
        when(controller.getDevices()).thenReturn(deviceList);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/devices")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status().is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    void createDevice() throws Exception{
        //Given
        when(controller.createDevice(any(DeviceDto.class))).thenReturn(nokia);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(nokia);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deviceName",Matchers.is("Nokia")));
    }

    @Test
    void deleteDevice() throws Exception{
        //Given
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/devices/11")
                        .characterEncoding("UTF-8"))
                .andExpect((MockMvcResultMatchers.status().is(200)));
    }

    @Test
    void updateDevice() throws Exception{
        //Given
        when(controller.updateDevice(any(DeviceDto.class))).thenReturn(samsung);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(nokia);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deviceName", Matchers.is("Samsung")));
    }

    @Test
    void getDevice() throws Exception {
        //Given
        when(controller.getDevice(any(Long.class))).thenReturn(nokia);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/devices/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status().is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deviceName", Matchers.is("Nokia")));
    }
}
