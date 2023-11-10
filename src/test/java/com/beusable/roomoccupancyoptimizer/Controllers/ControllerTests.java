package com.beusable.roomoccupancyoptimizer.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ControllerTests {

    @Autowired
    @InjectMocks
    RoomDistributionController roomDistributionController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(roomDistributionController).build();
    }

    @Test
    public void testGetRoomDistributionRecommendation_ValidInput() throws Exception {
        mockMvc.perform(get("/api/room-distribution?availablePremiumRooms={availablePremium}&availableEconomyRooms={availableEconomy}", 3,2)
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRoomDistributionRecommendation_InvalidInput() throws Exception {
        mockMvc.perform(get("/api/room-distribution?availablePremiumRooms={availablePremium}&availableEconomyRooms={availableEconomy}", -3,2))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetRoomDistributionRecommendation_NullInput() throws Exception {
        mockMvc.perform(get("/api/room-distribution?availablePremiumRooms=&availableEconomyRooms="))
                .andExpect(status().isBadRequest());
    }

}
