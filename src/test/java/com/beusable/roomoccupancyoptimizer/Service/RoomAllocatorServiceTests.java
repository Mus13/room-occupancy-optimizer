package com.beusable.roomoccupancyoptimizer.Service;

import com.beusable.roomoccupancyoptimizer.DTO.RoomOccupancy;
import com.beusable.roomoccupancyoptimizer.Seeder.Seeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RoomAllocatorServiceTest {

    @Mock
    private Seeder seeder;

    @InjectMocks
    private RoomAllocatorService roomAllocatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test1() {
        when(seeder.generatePotentialGuests()).thenReturn(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        RoomOccupancy result = roomAllocatorService.roomDestribution(3, 3);
        assertEquals(3, result.getUsagePremium());
        assertEquals(738, result.getRevenuePremium(), 0.01);
        assertEquals(3, result.getUsageEconomy());
        assertEquals(167.99, result.getRevenueEconomy(), 0.01);
    }

    @Test
    void test2() {
        when(seeder.generatePotentialGuests()).thenReturn(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        RoomOccupancy result = roomAllocatorService.roomDestribution(7, 5);
        assertEquals(6, result.getUsagePremium());
        assertEquals(1054, result.getRevenuePremium(), 0.01);
        assertEquals(4, result.getUsageEconomy());
        assertEquals(189.99, result.getRevenueEconomy(), 0.01);
    }

    @Test
    void test3() {
        when(seeder.generatePotentialGuests()).thenReturn(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        RoomOccupancy result = roomAllocatorService.roomDestribution(2, 7);
        assertEquals(2, result.getUsagePremium());
        assertEquals(583, result.getRevenuePremium(), 0.01);
        assertEquals(4, result.getUsageEconomy());
        assertEquals(189.99, result.getRevenueEconomy(), 0.01);
    }

    @Test
    void test4() {
        when(seeder.generatePotentialGuests()).thenReturn(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        RoomOccupancy result = roomAllocatorService.roomDestribution(7, 1);
        assertEquals(7, result.getUsagePremium());
        assertEquals(1153.99, result.getRevenuePremium(), 0.01);
        assertEquals(1, result.getUsageEconomy());
        assertEquals(45, result.getRevenueEconomy(), 0.01);
    }
}

