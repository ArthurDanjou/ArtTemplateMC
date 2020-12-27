package fr.arthurdanjou.template.utils.team;

import fr.arthurdanjou.template.team.TeamUnit;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class Team {

    private final TeamUnit teamUnit;
    private final Set<UUID> players;

    public Team(TeamUnit teamUnit) {
        this.teamUnit = teamUnit;
        this.players = new HashSet<>();
    }

    public String getPrefix() {
        return this.teamUnit.getPrefix();
    }

    public String getName() {
        return this.teamUnit.getName();
    }

    public String getColoredName() {
        return this.teamUnit.getColoredName();
    }

    public String getColor() {
        return this.teamUnit.getColor();
    }

    public int getSize() {
        return this.players.size();
    }

    public boolean isEmpty() {
        return this.players.isEmpty();
    }

}
