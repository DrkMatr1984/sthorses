package com.shepherdjerred.sthorses.util;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.inventory.ItemStack;

public class PlaceUtils {
  public static String translateOldVariants(String variant) {
    switch (variant) {
      case "DONKEY":
        return "Horse";
      case "HORSE":
        return "Horse";
      case "MULE":
        return "Mule";
      case "SKELETON_HORSE":
        return "SkeletonHorse";
    } 
    return variant;
  }
  
  public static void createHorse(Horse horse, List<String> lore) {
    String color = ((String)lore.stream().filter(str -> str.contains("Color: ")).findFirst().get()).replace("Color: ", "");
    String style = ((String)lore.stream().filter(str -> str.contains("Style: ")).findFirst().get()).replace("Style: ", "");
    horse.setColor(Horse.Color.valueOf(color));
    horse.setStyle(Horse.Style.valueOf(style));
  }
  
  public static void createLlama(Llama llama, List<String> lore) {
    String color = ((String)lore.stream().filter(str -> str.contains("Color: ")).findFirst().get()).replace("Color: ", "");
    llama.setColor(Llama.Color.valueOf(color));
  }
  
  public static void giveSaddle(AbstractHorse abstractHorse) {
    ((Horse)abstractHorse).getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
  }
  
  public static void giveCarpet(AbstractHorse abstractHorse, String carpetColor) {
    ItemStack carpet = ItemUtils.getCarpetByColor(carpetColor);
    abstractHorse.getInventory().setItem(1, carpet);
  }
  
  public static boolean isCarpet(ItemStack item) {
    if (item.getType() == Material.BLACK_CARPET || item.getType() == Material.BLUE_CARPET || item.getType() == Material.BROWN_CARPET || item
      .getType() == Material.CYAN_CARPET || item.getType() == Material.GRAY_CARPET || item
      .getType() == Material.GREEN_CARPET || item.getType() == Material.LEGACY_CARPET || item
      .getType() == Material.LIGHT_BLUE_CARPET || item.getType() == Material.LIGHT_GRAY_CARPET || item
      .getType() == Material.LIME_CARPET || item.getType() == Material.MAGENTA_CARPET || item
      .getType() == Material.MOSS_CARPET || item.getType() == Material.ORANGE_CARPET || item
      .getType() == Material.PINK_CARPET || item.getType() == Material.PURPLE_CARPET || item
      .getType() == Material.RED_CARPET || item.getType() == Material.WHITE_CARPET || item
      .getType() == Material.YELLOW_CARPET)
      return true; 
    return false;
  }
}
