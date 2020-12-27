package fr.arthurdanjou.template.config.lang;

import fr.arthurdanjou.template.Main;
import fr.arthurdanjou.template.config.ConfigFiles;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public enum Lang {

    PREFIX,

    MOTD_LOBBY,
    MOTD_GAME,

    GAME_START_COUNTDOWN,
    GAME_STOP_COUNTDOWN,
    ;

    @Getter
    private static final Map<Lang, String> VALUES = new HashMap<>();

    static {
        for (Lang lang : values()) {
            VALUES.put(lang, lang.getFromFile());
        }
        Main.getInstance().getLogger().info("Lecture du fichier de langues !");
    }

    public String get() {
        return VALUES.get(this);
    }

    public static String getPrefix() {
        return PREFIX.get() + ChatColor.RESET + " ";
    }

    private String getFromFile() {
        FileConfiguration configuration = ConfigFiles.LANG.getConfiguration();
        String key = name().toLowerCase().replace('_', '-');
        String value = configuration.getString(key);

        if (value == null) {
            value = "";
        }

        return ChatColor.translateAlternateColorCodes('&', value);
    }

}
