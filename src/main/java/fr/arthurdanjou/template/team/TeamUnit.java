package fr.arthurdanjou.template.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;

@AllArgsConstructor
@Getter
public enum TeamUnit {

    //todo set teams info here

    NONE("none", "&f", "&f"),
    ;

    private final String name, prefix, color;

    public String getColor() {
        return ChatColor.translateAlternateColorCodes('&', color);
    }

    public String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&', prefix);
    }

    public String getColoredName() {
        return getColor() + getName();
    }
}
