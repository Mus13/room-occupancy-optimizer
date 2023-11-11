package com.beusable.roomoccupancyoptimizer.Seeder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seeder {

    public List<Double> generatePotentialGuests() {
        // Mock data for potential guests
        return Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0);
    }
}
