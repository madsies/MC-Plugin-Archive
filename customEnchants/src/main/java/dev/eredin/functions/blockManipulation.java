package dev.eredin.functions;

import org.bukkit.Location;

import java.util.ArrayList;

public class blockManipulation {

    public static ArrayList<Location> getLocationsBetweenLocations(Location begin, Location end)
    {
        int tbX = Math.max(begin.getBlockX(), end.getBlockX());
        int bbX = Math.min(begin.getBlockX(), end.getBlockX());

        int tbY = Math.max(begin.getBlockY(), end.getBlockY());
        int bbY = Math.min(begin.getBlockY(), end.getBlockY());

        int tbZ = Math.max(begin.getBlockZ(), end.getBlockZ());
        int bbZ = Math.min(begin.getBlockZ(), end.getBlockZ());

        ArrayList<Location> locations = new ArrayList<>();

        for (int x = bbX; x <= tbX; x++)
            for (int z = bbZ; z <= tbZ; z++)
                for (int y = bbY; y <= tbY; y++)
                    locations.add(new Location(begin.getWorld(), x, y, z));

        return locations;
    }
}
