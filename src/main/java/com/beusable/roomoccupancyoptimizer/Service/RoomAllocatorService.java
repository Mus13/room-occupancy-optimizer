package com.beusable.roomoccupancyoptimizer.Service;

import com.beusable.roomoccupancyoptimizer.DTO.RoomOccupancy;
import com.beusable.roomoccupancyoptimizer.Seeder.Seeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomAllocatorService {

    @Autowired
    Seeder seeder;

    public RoomOccupancy roomDestribution(Integer freePremium, Integer freeEco){
        List<Double> guests = seeder.generatePotentialGuests();
        RoomOccupancy roomOccupancy = new RoomOccupancy();
        int countPassedGuests=0;
        guests.sort((a, b) -> Double.compare(b, a));

        for (double guest : guests) {
            countPassedGuests++;
            if (freePremium > 0 && (guest >= 100 || (guest<100 && (guests.size()-countPassedGuests>=freeEco) ))) {
                roomOccupancy.incrementUsagePremium();
                roomOccupancy.addToRevenuePremium(guest);
                freePremium--;
            } else if (freeEco > 0 && guest>=0 && guest<100 ){
                roomOccupancy.incrementUsageEconomy();
                roomOccupancy.addToRevenueEconomy(guest);
                freeEco--;
            }
        }

        return roomOccupancy;
    }

}

