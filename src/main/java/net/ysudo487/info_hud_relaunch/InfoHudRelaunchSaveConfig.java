package net.ysudo487.info_hud_relaunch;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class InfoHudRelaunchSaveConfig {
  private static final Path Config_Paht =
      FabricLoader.getInstance().getConfigDir().resolve("info_hud_relaunch_colors.txt");

  public static void Info_Hud_Save(
      boolean FpsDropShadow, boolean PosDropShadow, boolean BiomeDropShadow, boolean DayDropShadow,
      int FpsColor, int PosColor, int BiomeColor, int DayColor,
      int FpsY, int CoordinatesY, int BiomeY, int DayY) throws IOException {
    String Data =
        (FpsDropShadow ? "1" : "0") + "Info_Hud_Save_Config_K" +
            (PosDropShadow ? "1" : "0") + "Info_Hud_Save_Config_K" +
            (BiomeDropShadow ? "1" : "0") + "Info_Hud_Save_Config_K" +
            (DayDropShadow ? "1" : "0") + "Info_Hud_Save_Config_K" +
            Integer.toHexString(FpsColor) + "Info_Hud_Save_Config_K" +
            Integer.toHexString(PosColor) + "Info_Hud_Save_Config_K" +
            Integer.toHexString(BiomeColor) + "Info_Hud_Save_Config_K" +
            Integer.toHexString(DayColor) + "Info_Hud_Save_Config_K" +
            FpsY + "Info_Hud_Save_Config_K" +
            CoordinatesY + "Info_Hud_Save_Config_K" +
            BiomeY + "Info_Hud_Save_Config_K" +
            DayY;

    Files.writeString(Config_Paht, Data, StandardCharsets.UTF_8);
  }

  public static Object[] Info_Hud_Load() throws IOException {
    Object[] Info_Hud_Default_Configs = {false, false, false, false, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 5, 22, 39, 56};
    if (!Files.exists(Config_Paht)) return Info_Hud_Default_Configs;

    String[] Info_Hud_String = Files.readString(Config_Paht, StandardCharsets.UTF_8).trim().split("Info_Hud_Save_Config_K");
    if (Info_Hud_String.length != 12) return Info_Hud_Default_Configs;

    Object[] Info_Hud_Obj = new Object[12];

    Info_Hud_Obj[0] = Info_Hud_String[0].equals("1");
    Info_Hud_Obj[1] = Info_Hud_String[1].equals("1");
    Info_Hud_Obj[2] = Info_Hud_String[2].equals("1");
    Info_Hud_Obj[3] = Info_Hud_String[3].equals("1");

    for (int i = 4; i < 8; i++) {
      Info_Hud_Obj[i] = (int) Long.parseLong(Info_Hud_String[i], 16);
    }

    for (int i = 8; i < 12; i++) {
      Info_Hud_Obj[i] = Integer.parseInt(Info_Hud_String[i]);
    }

    return Info_Hud_Obj;
  }
}