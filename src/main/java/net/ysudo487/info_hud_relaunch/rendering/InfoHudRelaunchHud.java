package net.ysudo487.info_hud_relaunch.rendering;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class InfoHudRelaunchHud {
  public static boolean Visible = true;
  public static int FC = 0xFFFFFFFF;
  public static int PC = 0xFFFFFFFF;
  public static int BC = 0xFFFFFFFF;
  public static int DC = 0xFFFFFFFF;
  public static int FY = 5;
  public static int CY = 22;
  public static int BY = 39;
  public static int DY = 56;

  public static void render(GuiGraphicsExtractor G, DeltaTracker DT) {
    if (!Visible) return;

    Minecraft C = Minecraft.getInstance();
    if (C.debugEntries.isOverlayVisible()) return;

    var P = C.player;
    int F = C.getFps();
    double PX = 0;
    double PY = 0;
    double PZ = 0;
    String BN = "";
    int DCN = 0;

    if (P != null) {
      PX = P.getX();
      PY = P.getY();
      PZ = P.getZ();
      BN = P.level().getBiome(P.blockPosition())
          .unwrapKey()
          .map(info_hud_relaunch_key -> info_hud_relaunch_key.identifier().getPath())
          .orElse("Unknown");
      BN = BiomeNameFormat(BN);

      long info_hud_relaunch_dayTime = P.level().getOverworldClockTime();
      DCN = (int) (info_hud_relaunch_dayTime / 24000L);
    }

    boolean FDSE = InfoHudRelaunchEditScreen.FDSE;
    boolean PDSE = InfoHudRelaunchEditScreen.PDSE;
    boolean BDSE = InfoHudRelaunchEditScreen.BDSE;
    boolean DDSE = InfoHudRelaunchEditScreen.DDSE;

    G.text(C.font, "Fps : " + F, 5, FY, FC, FDSE);
    G.text(C.font, String.format("Position : %.3f / %.3f / %.3f", PX, PY, PZ), 5, CY, PC, PDSE);
    G.text(C.font, "Biome : " + BN, 5, BY, BC, BDSE);
    G.text(C.font, "Day : " + DCN, 5, DY, DC, DDSE);
  }

  private static String BiomeNameFormat(String R) {
    if (R.isEmpty()) return R;

    String[] info_hud_relaunch_words = R.split("_");
    StringBuilder info_hud_relaunch_result = new StringBuilder();

    for (String info_hud_relaunch_word : info_hud_relaunch_words) {
      if (info_hud_relaunch_word.isEmpty()) continue;
      info_hud_relaunch_result.append(Character.toUpperCase(info_hud_relaunch_word.charAt(0)))
          .append(info_hud_relaunch_word.substring(1))
          .append(" ");
    }

    return info_hud_relaunch_result.toString().trim();
  }
}