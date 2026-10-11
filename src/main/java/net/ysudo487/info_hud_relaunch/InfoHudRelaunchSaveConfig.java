package net.ysudo487.info_hud_relaunch;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;

public class InfoHudRelaunchSaveConfig {
  private static final Path Config_Path =
      FabricLoader.getInstance().getConfigDir().resolve("ihrdp.txt");
  //  Short for Info Hud Relaunch Default Properties
  private static final String Separator = "S";

  //  ASS Var Naming Sorry not Sorry
  public static void Info_Hud_Save(
//    Drop Shadow -------------------------------------- Color -------------------------- Y Pos
      boolean FDS, boolean PDS, boolean BDS, boolean DDS, int FC, int PC, int BC, int DC, int FY, int PY, int BY, int DY)
      throws IOException {

    Object[] Values = {FDS, PDS, BDS, DDS, FC, PC, BC, DC, FY, PY, BY, DY};
    String[] Fields = new String[Values.length];

    for (int i = 0; i < Values.length; i++) {

      System.out.println(i);

      Object Value = Values[i];

      if (i < 4) {
        if ((boolean) Value) {
          Fields[i] = "1";
        } else {
          Fields[i] = "0";
        }
      } else if (i < 8) {
        Fields[i] = Integer.toHexString((int) Value);
      } else {
        Fields[i] = String.valueOf(Value);
      }
    }

    Files.writeString(Config_Path, String.join(Separator, Fields), UTF_8);
  }

  public static Object[] Info_Hud_Load() throws IOException {
    Object[] Defaults = {false, false, false, false, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 5, 22, 39, 56};

    if (!Files.exists(Config_Path)) return Defaults;

    String[] Fields = Files.readString(Config_Path, UTF_8).trim().split(Separator);
    if (Fields.length != Defaults.length) return Defaults;

    Object[] Values = new Object[Fields.length];

    for (int i = 0; i < Fields.length; i++) {
      System.out.println(i);

      if (i < 4) {
        Values[i] = Fields[i].equals("1");
      } else if (i < 8) {
        Values[i] = (int) Long.parseLong(Fields[i], 16);
      } else {
        Values[i] = Integer.parseInt(Fields[i]);
      }
    }
    return Values;
  }
}
// this was copied