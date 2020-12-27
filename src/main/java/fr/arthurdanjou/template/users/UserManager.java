package fr.arthurdanjou.template.users;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class UserManager {

    private final Set<User> users = new HashSet<>();

    public void onLogin(Player player) {
        users.add(new User(player));
    }

    public Optional<User> getUser(Player player) {
        return getUser(player.getUniqueId());
    }

    public Optional<User> getUser(UUID uuid) {
        return this.users.stream().filter(user -> user.getUuid().equals(uuid)).findFirst();
    }

    public Set<User> getAliveUsers() {
      return this.users.stream().filter(User::isAlive).collect(Collectors.toSet());
    }

    public Set<User> getSpectators() {
        return this.users.stream().filter(user -> !user.isAlive()).collect(Collectors.toSet());
    }

    public void delete(Player player) {
        this.delete(player.getUniqueId());
    }

    public void delete(UUID uuid) {
        this.getUser(uuid).ifPresent(users::remove);
    }

}
