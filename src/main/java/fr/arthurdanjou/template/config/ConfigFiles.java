package fr.arthurdanjou.template.config;

import fr.arthurdanjou.template.Main;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor @Getter
public enum ConfigFiles {

    CONFIG("config.yml"),
    LANG("lang.yml"),

    ;

    private final String fileName;

    public File getDataFolder() {
        return Main.getInstance().getDataFolder();
    }

    public File getFile() {
        return new File(getDataFolder(), getFileName());
    }

    public FileConfiguration getConfiguration() {
        return YamlConfiguration.loadConfiguration(getFile());
    }

    public void save(FileConfiguration configuration) {
        try {
            configuration.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
