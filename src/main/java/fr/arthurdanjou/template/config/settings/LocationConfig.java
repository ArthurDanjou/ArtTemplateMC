package fr.arthurdanjou.template.config.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@AllArgsConstructor
@Getter @Setter
public class LocationConfig {

    public final String world;
    public final double x, y, z;
    public final float yaw, pitch;

    public Location toBukkitLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }
}
