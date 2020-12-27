package fr.arthurdanjou.template.listeners;

import fr.arthurdanjou.template.Main;
import fr.arthurdanjou.template.game.GameState;
import fr.arthurdanjou.template.config.lang.Lang;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

@AllArgsConstructor
public class ServerListeners implements Listener {

    private final Main main;

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        event.setMaxPlayers(main.getSettings().getSlots());

        if (GameState.isState(GameState.GAME)) {
            event.setMotd(Lang.MOTD_GAME.get());
        } else {
            event.setMotd(Lang.MOTD_LOBBY.get());
        }
    }

}
