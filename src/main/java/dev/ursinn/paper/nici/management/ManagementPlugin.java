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

import dev.ursinn.paper.nici.management.modules.AnvilModule;
import dev.ursinn.paper.nici.management.modules.MessageModule;
import dev.ursinn.paper.nici.management.modules.TabListModule;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ManagementPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MessageModule(), this);
        getServer().getPluginManager().registerEvents(new TabListModule(), this);
        getServer().getPluginManager().registerEvents(new AnvilModule(), this);

        TabListModule.registerScoreboardTeams(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
