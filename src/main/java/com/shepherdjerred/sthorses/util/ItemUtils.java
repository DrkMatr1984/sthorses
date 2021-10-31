package com.shepherdjerred.sthorses.util;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
  public static void addGlow(ItemStack item) {
    ItemMeta meta = item.getItemMeta();
    meta.addEnchant(Enchantment.DURABILITY, 1, true);
    meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
    item.setItemMeta(meta);
  }
  
  public static String getCarpetColorAsString(short color) {
    List<String> colorNames = new ArrayList<>();
    colorNames.add(0, "WHITE");
    colorNames.add(1, "ORANGE");
    colorNames.add(2, "MAGENTA");
    colorNames.add(3, "LIGHT_BLUE");
    colorNames.add(4, "YELLOW");
    colorNames.add(5, "LIME");
    colorNames.add(6, "PINK");
    colorNames.add(7, "GRAY");
    colorNames.add(8, "SILVER");
    colorNames.add(9, "CYAN");
    colorNames.add(10, "PURPLE");
    colorNames.add(11, "BLUE");
    colorNames.add(12, "BROWN");
    colorNames.add(13, "GREEN");
    colorNames.add(14, "RED");
    colorNames.add(15, "BLACK");
    return colorNames.get(color);
  }
  
  public static short getCarpetColorAsShort(String color) {
    for (short i = 0; i < 16; i = (short)(i + 1)) {
      if (getCarpetColorAsString(i).equalsIgnoreCase(color))
        return i; 
    } 
    return 0;
  }
  
  public static ItemStack getCarpetByColor(String color) {
    switch (color) {
      case "WHITE":
        return new ItemStack(Material.WHITE_CARPET, 1);
      case "ORANGE":
        return new ItemStack(Material.ORANGE_CARPET, 1);
      case "MAGENTA":
        return new ItemStack(Material.MAGENTA_CARPET, 1);
      case "LIGHT_BLUE":
        return new ItemStack(Material.LIGHT_BLUE_CARPET, 1);
      case "YELLOW":
        return new ItemStack(Material.YELLOW_CARPET, 1);
      case "LIME":
        return new ItemStack(Material.LIME_CARPET, 1);
      case "PINK":
        return new ItemStack(Material.PINK_CARPET, 1);
      case "GRAY":
        return new ItemStack(Material.GRAY_CARPET, 1);
      case "SILVER":
        return new ItemStack(Material.LIGHT_GRAY_CARPET, 1);
      case "LIGHT_GRAY":
        return new ItemStack(Material.LIGHT_GRAY_CARPET, 1);
      case "CYAN":
        return new ItemStack(Material.CYAN_CARPET, 1);
      case "PURPLE":
        return new ItemStack(Material.PURPLE_CARPET, 1);
      case "BLUE":
        return new ItemStack(Material.BLUE_CARPET, 1);
      case "BROWN":
        return new ItemStack(Material.BROWN_CARPET, 1);
      case "GREEN":
        return new ItemStack(Material.GREEN_CARPET, 1);
      case "RED":
        return new ItemStack(Material.RED_CARPET, 1);
      case "BLACK":
        return new ItemStack(Material.BLACK_CARPET, 1);
      case "MOSS":
        return new ItemStack(Material.MOSS_CARPET, 1);
    } 
    return null;
  }
}
