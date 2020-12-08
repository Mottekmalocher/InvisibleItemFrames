package de.mottekmalocher.invisibleitemframes.invisibleitemframes

import de.mottekmalocher.invisibleitemframes.invisibleitemframes.listeners.ChunkLoad
import de.mottekmalocher.invisibleitemframes.invisibleitemframes.listeners.EntitySpawn
import de.mottekmalocher.invisibleitemframes.invisibleitemframes.recipes.InvisibleItemFrame
import de.mottekmalocher.invisibleitemframes.invisibleitemframes.timers.ItemFrameDespawnTimer
import org.bukkit.entity.ItemFrame
import org.bukkit.plugin.java.JavaPlugin

class InvisibleItemFrames : JavaPlugin() {

    companion object {
        val list: ArrayList<ItemFrame> = ArrayList()
    }

    override fun onEnable() {
        // Plugin startup logic

        InvisibleItemFrame(this)
        ChunkLoad(this)
        EntitySpawn(this)
        ItemFrameDespawnTimer(this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

}