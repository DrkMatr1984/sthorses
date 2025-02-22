package com.shepherdjerred.sthorses.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;

public class StoreUtils {
  public static List<String> createAbstractHorseLore(AbstractHorse abstractHorse) {
    List<String> lore = new ArrayList<>();
    String name = "Name: ";
    String owner = "Owner: ";
    String ownerUuid = "Owner UUID: ";
    if (abstractHorse.getCustomName() != null) {
      name = name.concat(abstractHorse.getCustomName());
    } else {
      name = name.concat("None");
    } 
    if (abstractHorse.getOwner() != null) {
      owner = owner.concat(String.valueOf(abstractHorse.getOwner().getName()));
      ownerUuid = ownerUuid.concat(String.valueOf(abstractHorse.getOwner().getUniqueId()));
    } else {
      owner = owner.concat("None");
      ownerUuid = ownerUuid.concat("None");
    } 
    double jumpValue = abstractHorse.getAttribute(Attribute.HORSE_JUMP_STRENGTH).getValue();
    double jumpValueInBlocks = -0.1817584952D * Math.pow(jumpValue, 3.0D) + 3.689713992D * Math.pow(jumpValue, 2.0D) + 2.128599134D * jumpValue - 0.343930367D;
    jumpValueInBlocks = Math.round(jumpValueInBlocks * 1.0D) / 1.0D;
    double speedValue = abstractHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue();
    double speedValueInBlocks = 43.178D * speedValue - 0.0214D;
    speedValueInBlocks = Math.round(speedValueInBlocks * 1.0D) / 1.0D;
    String variant = "Variant: " + abstractHorse.getClass().getSimpleName().replace("Craft", "");
    String jump = "Jump: ~" + String.valueOf(jumpValueInBlocks) + " blocks";
    String speed = "Speed: ~" + String.valueOf(speedValueInBlocks) + " blocks";
    String health = "Health: " + String.valueOf(abstractHorse.getHealth()) + "/" + String.valueOf(abstractHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    String domestication = "Domestication: " + String.valueOf(abstractHorse.getDomestication() + "/" + String.valueOf(abstractHorse.getMaxDomestication()));
    String age = "Age: " + String.valueOf(abstractHorse.getAge());
    String realJump = "Real Jump: " + String.valueOf(jumpValue);
    String realSpeed = "Real Speed: " + String.valueOf(speedValue);
    lore.addAll(Arrays.asList(new String[] { 
            name, owner, variant, jump, speed, health, domestication, age, ownerUuid, realJump, 
            realSpeed }));
    return lore;
  }
  
  public static List<String> createHorseLore(Horse horse) {
    List<String> lore = new ArrayList<>();
    lore.add("Color: " + horse.getColor().toString());
    lore.add("Style: " + horse.getStyle().toString());
    return lore;
  }
  
  public static List<String> createLlamaLore(Llama llama) {
    List<String> lore = new ArrayList<>();
    lore.add("Color: " + llama.getColor().toString());
    return lore;
  }
}
