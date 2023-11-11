package com.beusable.roomoccupancyoptimizer.Controllers;

import com.beusable.roomoccupancyoptimizer.DTO.RoomOccupancy;
import com.beusable.roomoccupancyoptimizer.Service.RoomAllocatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class ControllerTests {

    @Autowired
    @InjectMocks
    RoomDistributionController roomDistributionController;
    @Mock
    private RoomAllocatorService roomAllocatorService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(roomDistributionController).build();
    }

    @Test
    public void testGetRoomDistributionRecommendation_ValidInput() throws Exception {

        RoomOccupancy expectedRoomOccupancy = new RoomOccupancy(3, 738, 3, 167.99);
        when(roomAllocatorService.roomDestribution(3, 3)).thenReturn(expectedRoomOccupancy);

        ResultActions resultActions = mockMvc.perform(get("/api/room-distribution?availablePremiumRooms={availablePremium}&availableEconomyRooms={availableEconomy}", 3, 3)
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(expectedRoomOccupancy);

        resultActions.andExpect(content().json(expectedJson))
                .andExpect(jsonPath("$.usagePremium", Matchers.is(3)))
                .andExpect(jsonPath("$.revenuePremium", Matchers.is(738.0)))
                .andExpect(jsonPath("$.usageEconomy", Matchers.is(3)))
                .andExpect(jsonPath("$.revenueEconomy", Matchers.is(167.99)));

        verify(roomAllocatorService, times(1)).roomDestribution(3, 3);
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
