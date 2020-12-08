package de.mottekmalocher.invisibleitemframes.invisibleitemframes.timers

import de.mottekmalocher.invisibleitemframes.invisibleitemframes.InvisibleItemFrames.Companion.list
import de.mottekmalocher.invisibleitemframes.invisibleitemframes.recipes.InvisibleItemFrame
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

class ItemFrameDespawnTimer(plugin: JavaPlugin) {

    init {
        val key = NamespacedKey(plugin, "DropTime")
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, {
            ArrayList(list).forEach {
                if (it.isDead) list.remove(it)
                else if (it.item.type == Material.AIR) {
                    val dataContainer = it.persistentDataContainer
                    val currentTime = dataContainer.get(key, PersistentDataType.INTEGER)
                    if (currentTime != null) {
                        if (currentTime >= 300) {
                            it.remove()
                            it.world.dropItem(it.location, InvisibleItemFrame.getItem())
                        } else {
                            dataContainer.set(key, PersistentDataType.INTEGER, currentTime + 1)
                        }
                    } else dataContainer.set(key, PersistentDataType.INTEGER, 1)
                } else {
                    val dataContainer = it.persistentDataContainer
                    val currentTime = dataContainer.get(key, PersistentDataType.INTEGER)
                    if(currentTime != 0) dataContainer.set(key, PersistentDataType.INTEGER, 0)
                }
            }
        }, 1, 1)
    }
}