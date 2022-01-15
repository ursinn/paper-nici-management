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

package dev.ursinn.paper.nici.management.modules;

import dev.ursinn.paper.nici.management.Utils;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class MessageModule implements Listener {

    public MessageModule() {
        Bukkit.getLogger().info("Loaded Message Module.");
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        Component message = event.message();

        // Word Filter
        for (String word : Utils.getBadWordsList()) {
            if (message.toString().toLowerCase().contains(word.toLowerCase())) {
                TextReplacementConfig config = TextReplacementConfig.builder().match("(?i)" + word).replacement("*".repeat(word.length())).build();
                message = message.replaceText(config);
            }
        }

        Component component = Component.text()
                .content("")
                .append(Component.text("[").color(NamedTextColor.GRAY))
                .append(Utils.getWorldName(player).color(NamedTextColor.YELLOW))
                .append(Component.text("] ").color(NamedTextColor.GRAY))
                .append(Utils.getPrefix(player))
                .append(Component.text(":"))
                .append(Component.space())
                .append(message)
                .build();

        Bukkit.broadcast(component);
        event.setCancelled(true);
    }

}
