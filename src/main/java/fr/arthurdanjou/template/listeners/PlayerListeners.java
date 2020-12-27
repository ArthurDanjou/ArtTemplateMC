package fr.arthurdanjou.template.listeners;

import fr.arthurdanjou.template.Main;
import fr.arthurdanjou.template.game.GameState;
import fr.arthurdanjou.template.team.TeamUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@AllArgsConstructor @Getter
public class PlayerListeners implements Listener {

    private final Main main;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (GameState.isState(GameState.LOBBY)) {
            preparePlayer(player, false);
            main.getTeamManager().joinTeam(player, TeamUnit.NONE);
            main.getUserManager().onLogin(player);
            main.getGame().checkStart();
        } else {
            preparePlayer(player, true);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f);
            main.getTeamManager().joinTeam(player, TeamUnit.NONE);
        }
    }

    private void preparePlayer(Player player, boolean started) {
        player.setFoodLevel(20);
        player.setHealth(20);
        player.setGameMode(started ? GameMode.SPECTATOR : GameMode.ADVENTURE);
        player.getInventory().clear();
        player.setExp(0);
        player.setLevel(0);
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        player.teleport(main.getSettings().getSpawnLocation());
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        main.getTeamManager().onLogout(player);
        main.getUserManager().delete(player);
    }

    @EventHandler
    public void onPlayerPickupArrow(PlayerPickupArrowEvent event) {
        event.setCancelled(!GameState.isState(GameState.GAME));
    }

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        event.setCancelled(!GameState.isState(GameState.GAME));
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(!GameState.isState(GameState.GAME));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(GameState.isState(GameState.GAME));
    }
}
