package fr.arthurdanjou.template;

import fr.arthurdanjou.template.game.Game;
import fr.arthurdanjou.template.team.TeamManager;
import fr.arthurdanjou.template.config.settings.Settings;
import fr.arthurdanjou.template.listeners.PlayerListeners;
import fr.arthurdanjou.template.listeners.ServerListeners;
import fr.arthurdanjou.template.listeners.WorldListeners;
import fr.arthurdanjou.template.config.FileManager;
import fr.arthurdanjou.template.config.ConfigFiles;
import fr.arthurdanjou.template.game.GameState;
import fr.arthurdanjou.template.users.UserManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    private Game game;
    private FileManager fileManager;
    private TeamManager teamManager;
    private UserManager userManager;
    private Settings settings;

    @Override
    public void onEnable() {
        instance = this;

        this.getLogger().info("+------[ PROJET ]------+");
        this.getLogger().info("Initialisation en cours...");

        this.fileManager = new FileManager(this, getClassLoader());
        this.teamManager = new TeamManager();
        this.userManager = new UserManager();
        this.fileManager.saveResourceAs(ConfigFiles.CONFIG.getFileName(), ConfigFiles.CONFIG.getFileName());

        this.settings = this.fileManager.getSettings();

        this.game = new Game(this);

        this.registerCommands();
        this.registerListeners();

        GameState.setState(GameState.LOBBY);

        Bukkit.getWorlds().forEach(world -> {
            world.setDifficulty(Difficulty.PEACEFUL);
            world.setPVP(false);
            world.setTime(6000L);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.MOB_GRIEFING, false);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        });

        this.getLogger().info("+----------------------+");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerListeners(this), this);
        pluginManager.registerEvents(new WorldListeners(this), this);
        pluginManager.registerEvents(new ServerListeners(this), this);
    }

    private void registerCommands() {

    }
}
