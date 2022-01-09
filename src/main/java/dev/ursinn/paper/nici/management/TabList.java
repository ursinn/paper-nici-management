/*
 * MIT License
 *
 * Copyright (c) 2022 Ursin Filli
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.ursinn.paper.nici.management;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class TabList implements Listener {

    public TabList() {
        Bukkit.getLogger().info("Loaded TabList Module.");
    }

    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        Scoreboard scoreboard = player.getScoreboard();
        scoreboard.getTeams().forEach(team -> team.removePlayer(player));

        Objects.requireNonNull(scoreboard.getTeam("0009")).addPlayer(player);
        if (player.hasPermission("nici.management.admin")) {
            Objects.requireNonNull(scoreboard.getTeam("0001")).addPlayer(player);
        }

    }

    public static void registerScoreboardTeams(Scoreboard scoreboard) {
        scoreboard.getTeams().forEach(Team::unregister);

        Team admin = scoreboard.registerNewTeam("0001");
        admin.color(NamedTextColor.GOLD);
        admin.prefix(Component.text("[Admin] ").color(NamedTextColor.RED));

        Team adminRosa = scoreboard.registerNewTeam("0002");
        adminRosa.color(NamedTextColor.AQUA);
        adminRosa.prefix(Component.text("[Admin] ").color(TextColor.color(243, 140, 170)));

        Team asilant = scoreboard.registerNewTeam("0003");
        asilant.color(NamedTextColor.BLACK);
        asilant.prefix(Component.text("Asilant ﴾✶﴿ ").color(NamedTextColor.GOLD));

        Team player = scoreboard.registerNewTeam("0009");
        player.color(NamedTextColor.GREEN);
    }

}
