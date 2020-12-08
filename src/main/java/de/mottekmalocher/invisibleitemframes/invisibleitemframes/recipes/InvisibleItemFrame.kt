package de.mottekmalocher.invisibleitemframes.invisibleitemframes.recipes

import net.minecraft.server.v1_16_R3.NBTTagCompound
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.plugin.java.JavaPlugin

class InvisibleItemFrame(var plugin: JavaPlugin) {

    companion object {
        fun getItem(): ItemStack {
            var item = ItemStack(Material.ITEM_FRAME)
            val meta: ItemMeta? = item.itemMeta
            meta?.addEnchant(Enchantment.DURABILITY, 1, false)
            meta?.addItemFlags(ItemFlag.HIDE_ENCHANTS)
            meta?.setDisplayName("§dInvisible Item Frame")
            item.itemMeta = meta

            val nmsItem = CraftItemStack.asNMSCopy(item)
            val entityTag = NBTTagCompound()
            entityTag.setByte("Invisible", 1)
            nmsItem.tag?.set("EntityTag", entityTag)

            item = CraftItemStack.asBukkitCopy(nmsItem)
            return item
        }
    }

    init {
        val key = NamespacedKey(plugin, "invisible_item_frame")
        val item: ItemStack = getItem()
        val recipe = ShapedRecipe(key, item)
        recipe.shape("GGG", "GIG", "GGG")

        recipe.setIngredient('G', Material.GLASS_PANE)
        recipe.setIngredient('I', RecipeChoice.ExactChoice(ItemStack(Material.ITEM_FRAME)))

        Bukkit.addRecipe(recipe)
    }
}