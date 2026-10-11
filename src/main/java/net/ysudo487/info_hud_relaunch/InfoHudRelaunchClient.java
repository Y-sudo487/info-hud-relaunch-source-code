package net.ysudo487.info_hud_relaunch;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.ysudo487.info_hud_relaunch.rendering.InfoHudRelaunchEditScreen;
import net.ysudo487.info_hud_relaunch.rendering.InfoHudRelaunchHud;

import java.io.IOException;

public class InfoHudRelaunchClient implements ClientModInitializer {
  private static final KeyMapping.Category IHCC = KeyMapping.Category.register(
      Identifier.fromNamespaceAndPath(InfoHudRelaunch.MOD_ID, "info_hud_custom_category")
  );
  private static KeyMapping TESK;
  private static KeyMapping THK;

  @Override
  public void onInitializeClient() {
    try {
//      L Short For Load
      Object[] L = InfoHudRelaunchSaveConfig.Info_Hud_Load();
      InfoHudRelaunchEditScreen.FDSE = (boolean) L[0];
      InfoHudRelaunchEditScreen.PDSE = (boolean) L[1];
      InfoHudRelaunchEditScreen.BDSE = (boolean) L[2];
      InfoHudRelaunchEditScreen.DDSE = (boolean) L[3];
      InfoHudRelaunchHud.FC = (int) L[4];
      InfoHudRelaunchHud.PC = (int) L[5];
      InfoHudRelaunchHud.BC = (int) L[6];
      InfoHudRelaunchHud.DC = (int) L[7];
      InfoHudRelaunchHud.FY = (int) L[8];
      InfoHudRelaunchHud.CY = (int) L[9];
      InfoHudRelaunchHud.BY = (int) L[10];
      InfoHudRelaunchHud.DY = (int) L[11];
    } catch (IOException info_hud_relaunch_e) {
      throw new RuntimeException(info_hud_relaunch_e);
    }

    THK = KeyMappingHelper.registerKeyMapping(new KeyMapping(
        "key.info-hud-relaunch.toggle_hud",
        InputConstants.Type.KEYSYM,
        InputConstants.KEY_H,
        IHCC
    ));

    HudElementRegistry.addLast(
        Identifier.fromNamespaceAndPath(InfoHudRelaunch.MOD_ID, "info_hud_relaunch_hud"),
        InfoHudRelaunchHud::render
    );

    ClientTickEvents.END_CLIENT_TICK.register(info_hud_relaunch_client -> {
      while (THK.consumeClick()) {
        if (InfoHudRelaunchEditScreen.EditScreenVisible) {
          InfoHudRelaunchHud.Visible = false;
        } else {
          InfoHudRelaunchHud.Visible = !InfoHudRelaunchHud.Visible;
        }
      }
    });

    TESK = KeyMappingHelper.registerKeyMapping(new KeyMapping(
        "key.info-hud-relaunch.open_edit_screen",
        InputConstants.Type.KEYSYM,
        InputConstants.KEY_G,
        IHCC
    ));

    ClientTickEvents.END_CLIENT_TICK.register(info_hud_relaunch_client -> {
      while (TESK.consumeClick()) {
        info_hud_relaunch_client.gui.setScreen(new InfoHudRelaunchEditScreen(Component.empty()));
        InfoHudRelaunchEditScreen.EditScreenVisible = true;
      }
    });
  }
}