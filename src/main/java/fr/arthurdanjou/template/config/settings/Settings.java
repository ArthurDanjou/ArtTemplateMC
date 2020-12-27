package fr.arthurdanjou.template.config.settings;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter @Setter
public class Settings {

    public int slots;
    public boolean spectator;
    public LocationConfig spawnLocation;
    public int minimumPlayers;

    public Location getSpawnLocation() {
        return spawnLocation.toBukkitLocation();
    }

}
