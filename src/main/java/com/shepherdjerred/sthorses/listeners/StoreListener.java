package com.shepherdjerred.sthorses.listeners;

import com.shepherdjerred.sthorses.Main;
import com.shepherdjerred.sthorses.util.ItemUtils;
import com.shepherdjerred.sthorses.util.PlaceUtils;
import com.shepherdjerred.sthorses.util.StoreUtils;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StoreListener implements Listener {
  private final List<InventoryAction> ALLOWED_ACTIONS = Arrays.asList(new InventoryAction[] { InventoryAction.PICKUP_ALL });
  
  @EventHandler
  public void onClickEvent(InventoryClickEvent event) {
    Player player = (Player)event.getWhoClicked();
    if (!player.hasPermission("stHorses.store"))
      return; 
    if (event.getClickedInventory() == null)
      return; 
    if (!(event.getClickedInventory().getHolder() instanceof AbstractHorse))
      return; 
    if (event.getSlot() != 0 && event.getSlot() != 1)
      return; 
    if (event.getCurrentItem().getType() != Material.SADDLE && !PlaceUtils.isCarpet(event.getCurrentItem()))
      return; 
    if (Main.getInstance().getConfig().getBoolean("store.shiftClickIgnored") && 
      !this.ALLOWED_ACTIONS.contains(event.getAction()))
      return; 
    if (event.getCurrentItem().getItemMeta().hasLore())
      return; 
    AbstractHorse abstractHorse = (AbstractHorse)event.getClickedInventory().getHolder();
    ItemStack saddle = new ItemStack(event.getCurrentItem().getType(), 1);
    ItemMeta saddleMeta = saddle.getItemMeta();
    List<String> lore = StoreUtils.createAbstractHorseLore(abstractHorse);
    if (abstractHorse instanceof Horse) {
      lore.addAll(StoreUtils.createHorseLore((Horse)abstractHorse));
    } else if (abstractHorse instanceof Llama) {
      lore.addAll(StoreUtils.createLlamaLore((Llama)abstractHorse));
      short colorCode = event.getCurrentItem().getDurability();
      String carpetColor = ItemUtils.getCarpetColorAsString(colorCode);
      saddle.setDurability(colorCode);
      lore.add("Carpet: " + carpetColor);
    } 
    saddleMeta.setDisplayName("stHorses Saddle");
    saddleMeta.setLore(lore);
    saddle.setItemMeta(saddleMeta);
    ItemUtils.addGlow(saddle);
    event.setCurrentItem(new ItemStack(Material.AIR));
    if (abstractHorse instanceof ChestedHorse) {
      ChestedHorse chestedHorse = (ChestedHorse)abstractHorse;
      if (chestedHorse.isCarryingChest()) {
        ItemStack chestToDrop = new ItemStack(Material.CHEST, 1);
        chestedHorse.getWorld().dropItem(chestedHorse.getLocation(), chestToDrop);
      } 
    } 
    abstractHorse.getInventory().forEach(item -> {
          if (item != null) {
            abstractHorse.getWorld().dropItem(abstractHorse.getLocation(), item);
            item.setType(Material.AIR);
          } 
        });
    abstractHorse.remove();
    if (player.getInventory().firstEmpty() == -1) {
      player.getWorld().dropItem(player.getLocation(), saddle);
    } else {
      player.getInventory().addItem(new ItemStack[] { saddle });
    } 
    event.setCancelled(true);
  }
}
