package fr.arthurdanjou.template.team;

import fr.arthurdanjou.template.utils.team.Team;
import fr.arthurdanjou.template.utils.team.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TeamManager {

    private final TeamHandler teamHandler;
    //todo set teams here

    public TeamManager() {
        this.teamHandler = new TeamHandler();

        for  (TeamUnit team : TeamUnit.values()) {
            if (this.teamHandler.getTeam(team) != null) {
                continue;
            }
            TeamHandler.VTeam vTeam = this.teamHandler.createNewTeam(team.getName(), team.getColoredName());
            vTeam.setRealName(team.getName());
            vTeam.setPrefix(team.getColoredName());

            this.teamHandler.addTeam(vTeam);
        }
    }

    public void onLogout(Player player) {
        this.teamHandler.removeReceiver(player);
    }

    public void joinTeam(Player player, TeamUnit teamUnit) {
        this.leaveTeam(player);

        Team team = getTeam(teamUnit);

        if (team != null) {
            team.getPlayers().add(player.getUniqueId());
        }

        this.teamHandler.addReceiver(player);
        this.teamHandler.addPlayerToTeam(player, this.teamHandler.getTeam(teamUnit));
    }

    public void leaveTeam(Player player) {
        this.leaveTeam(player.getUniqueId());
    }

    public void leaveTeam(UUID uuid) {

    }

    public Team getTeam(TeamUnit teamUnit) {
        //todo if teamUnit == TEAM alors on retourne
        return null;
    }

    public Team getPlayerTeam(Player player) {
        return this.getPlayerTeam(player.getUniqueId());
    }

    public Team getPlayerTeam(UUID uuid) {
        //todo if teams.getPlayers.contains(uuid) alors on retourne
        return null;
    }

    public boolean areAllInTeam() {
        int count = 0;
        for (TeamHandler.VTeam team : this.teamHandler.getTeams()) {
            count += team.getPlayers().size();
        }
        return count == Bukkit.getOnlinePlayers().size();
    }

}
