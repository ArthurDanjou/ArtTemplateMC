package fr.arthurdanjou.template.game;

import fr.arthurdanjou.template.Main;
import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public class Game {

    private final Main main;

    private StartRunnable startRunnable;
    private GameRunnable gameRunnable;

    public Game(Main main) {
        this.main = main;
        this.startRunnable = new StartRunnable(this);
        this.gameRunnable = new GameRunnable();
    }

    public void startCountDown() {
        this.startRunnable.runTaskTimer(main, 0, 20);
        this.startRunnable.setStarting(true);
    }

    public void stopCountDown() {
        if (this.startRunnable.getCountdown() > 0) {
            this.startRunnable.cancel();
            this.startRunnable = new StartRunnable(this);
        }
    }

    public void startGame() {

    }

    public void checkWin() {

    }

    public void end() {

    }

    public void checkStart() {
        if (main.getTeamManager().areAllInTeam() && main.getSettings().getMinimumPlayers() <= Bukkit.getOnlinePlayers().size()) {
            this.startCountDown();
        }
    }

}
