package fr.arthurdanjou.template.game;

import fr.arthurdanjou.template.config.lang.Lang;
import fr.arthurdanjou.template.config.lang.PlaceHolders;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartRunnable extends BukkitRunnable {

    private final Game game;
    @Getter @Setter
    private boolean starting;
    @Getter @Setter
    private int countdown;

    public StartRunnable(Game game) {
        this.game = game;
        this.starting = false;
        this.countdown = 30;
    }

    @Override
    public void run() {
        if (this.countdown == 30 || this.countdown == 20 || (this.countdown <= 5 && this.countdown >= 1)) {
            Bukkit.broadcastMessage(
                    Lang.getPrefix() +
                    Lang.GAME_START_COUNTDOWN.get()
                    .replace(PlaceHolders.COUNTDOWN.getPlaceholder(), Integer.toString(this.countdown))
            );
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1f, 1f);
            }
        }

        if (this.countdown == 0) {
            cancel();
        }

        this.countdown--;
    }
}
