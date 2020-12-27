package fr.arthurdanjou.template.users;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter @Setter
public class User {

    private final UUID uuid;
    private final String name;
    private final boolean alive;
    private int kills;
    private int deaths;

    public User(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();

        this.alive = true;
        this.kills = 0;
        this.deaths = 0;
    }

    public void addKill() {
        this.kills++;
    }

    public void addDeath() {
        this.deaths++;
    }
}
