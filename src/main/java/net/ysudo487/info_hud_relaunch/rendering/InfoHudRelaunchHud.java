package net.ysudo487.info_hud_relaunch.rendering;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class InfoHudRelaunchHud {
  public static boolean Visible = true;
  public static int FPSColor = 0xFFFFFFFF;
  public static int POSColor = 0xFFFFFFFF;
  public static int BiomeColor = 0xFFFFFFFF;
  public static int DayColor = 0xFFFFFFFF;
  public static int FPSY = 5;
  public static int CoordinatesY = 22;
  public static int BiomeY = 39;
  public static int DayY = 56;

  public static void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
    if (!Visible) return;

    Minecraft client = Minecraft.getInstance();
    if(client.debugEntries.isOverlayVisible()) return;

    var player = Minecraft.getInstance().player;
    int fps = Minecraft.getInstance().getFps();
    double posX = 0;
    double posY = 0;
    double posZ = 0;
    String biomeName = "";
    int dayCounter = 0;

    if (player != null) {
      posX = player.getX();
      posY = player.getY();
      posZ = player.getZ();
      biomeName = player.level().getBiome(player.blockPosition())
          .unwrapKey()
          .map(key -> key.identifier().getPath())
          .orElse("Unknown");
      biomeName = BiomeNameFormat(biomeName);

      long dayTime = player.level().getOverworldClockTime();
      dayCounter = (int) (dayTime / 24000L);
    }

    boolean FpsDropShadow = InfoHudRelaunchEditScreen.FpsDropShadowEnabled;
    boolean PosDropShadow = InfoHudRelaunchEditScreen.PosDropShadowEnabled;
    boolean BiomeDropShadow = InfoHudRelaunchEditScreen.BiomeDropShadowEnabled;
    boolean DayDropShadow = InfoHudRelaunchEditScreen.DayDropShadowEnabled;

    graphics.text(Minecraft.getInstance().font, "Fps : " + fps, 5, FPSY, FPSColor, FpsDropShadow);
    graphics.text(Minecraft.getInstance().font, String.format("Position : %.3f / %.3f / %.3f", posX, posY, posZ), 5, CoordinatesY, POSColor, PosDropShadow);
    graphics.text(Minecraft.getInstance().font, "Biome : " + biomeName, 5, BiomeY, BiomeColor, BiomeDropShadow);
    graphics.text(Minecraft.getInstance().font, "Day : " + dayCounter, 5, DayY, DayColor, DayDropShadow);
  }

  private static String BiomeNameFormat(String raw) {
    if (raw.isEmpty()) return raw;

    String[] words = raw.split("_");
    StringBuilder result = new StringBuilder();

    for (String word : words) {
      if (word.isEmpty()) continue;
      result.append(Character.toUpperCase(word.charAt(0)))
          .append(word.substring(1))
          .append(" ");
    }

    return result.toString().trim();
  }
}