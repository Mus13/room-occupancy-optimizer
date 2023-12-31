package com.beusable.roomoccupancyoptimizer.Controllers;

import com.beusable.roomoccupancyoptimizer.DTO.RoomOccupancy;
import com.beusable.roomoccupancyoptimizer.Service.RoomAllocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomDistributionController {

    @Autowired
    RoomAllocatorService roomAllocatorService;


    @GetMapping(value="/room-distribution", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomOccupancy> getRoomDistributionRecommendation(
            @RequestParam(name = "availablePremiumRooms") Integer availablePremiumRooms,
            @RequestParam(name = "availableEconomyRooms") Integer availableEconomyRooms) {

        if (availablePremiumRooms == null || availableEconomyRooms == null
                || availablePremiumRooms < 0 || availableEconomyRooms < 0) {
            return ResponseEntity.badRequest().build();
        }

        RoomOccupancy result = roomAllocatorService.roomDestribution(availablePremiumRooms,availableEconomyRooms);

        return ResponseEntity.ok(result);
    }
}
