package de.mottekmalocher.invisibleitemframes.invisibleitemframes.listeners

import de.mottekmalocher.invisibleitemframes.invisibleitemframes.InvisibleItemFrames.Companion.list
import org.bukkit.Bukkit
import org.bukkit.entity.ItemFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.plugin.java.JavaPlugin

class EntitySpawn(plugin: JavaPlugin) : Listener {

    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onEntitySpawn(event: EntitySpawnEvent) {
        event.entity.apply {
            if(this is ItemFrame && !isVisible) list.add(this)
        }
    }
}