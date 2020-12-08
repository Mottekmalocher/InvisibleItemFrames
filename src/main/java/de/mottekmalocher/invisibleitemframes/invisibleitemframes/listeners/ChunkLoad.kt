package de.mottekmalocher.invisibleitemframes.invisibleitemframes.listeners

import de.mottekmalocher.invisibleitemframes.invisibleitemframes.InvisibleItemFrames.Companion.list
import org.bukkit.Bukkit
import org.bukkit.entity.ItemFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.ChunkLoadEvent
import org.bukkit.event.world.ChunkUnloadEvent
import org.bukkit.plugin.java.JavaPlugin

class ChunkLoad(plugin: JavaPlugin) : Listener {

    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onChunkLoad(event: ChunkLoadEvent) {
        list.addAll(event.chunk.entities.filter { it is ItemFrame && !it.isVisible } as List<ItemFrame>)
    }

    @EventHandler
    fun onChunkUnload(event: ChunkUnloadEvent) {
        list.removeAll(event.chunk.entities.filter { it is ItemFrame && !it.isVisible })
    }
}