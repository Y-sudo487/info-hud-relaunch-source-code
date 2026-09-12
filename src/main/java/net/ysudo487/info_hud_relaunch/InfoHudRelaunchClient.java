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
  private static final KeyMapping.Category Info_Hud_Custom_Category = KeyMapping.Category.register(
      Identifier.fromNamespaceAndPath(InfoHudRelaunch.MOD_ID, "info_hud_custom_category")
  );
  private static KeyMapping ToggleEditScreenKey;
  private static KeyMapping ToggleHudKey;

  @Override
  public void onInitializeClient() {
    try {
      Object[] Loaded = InfoHudRelaunchSaveConfig.Info_Hud_Load();
      InfoHudRelaunchEditScreen.FpsDropShadowEnabled = (boolean) Loaded[0];
      InfoHudRelaunchEditScreen.PosDropShadowEnabled = (boolean) Loaded[1];
      InfoHudRelaunchEditScreen.BiomeDropShadowEnabled = (boolean) Loaded[2];
      InfoHudRelaunchEditScreen.DayDropShadowEnabled = (boolean) Loaded[3];
      InfoHudRelaunchHud.FPSColor = (int) Loaded[4];
      InfoHudRelaunchHud.POSColor = (int) Loaded[5];
      InfoHudRelaunchHud.BiomeColor = (int) Loaded[6];
      InfoHudRelaunchHud.DayColor = (int) Loaded[7];
      InfoHudRelaunchHud.FPSY = (int) Loaded[8];
      InfoHudRelaunchHud.CoordinatesY = (int) Loaded[9];
      InfoHudRelaunchHud.BiomeY = (int) Loaded[10];
      InfoHudRelaunchHud.DayY = (int) Loaded[11];
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    ToggleHudKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
        "key.info-hud-relaunch.toggle_hud",
        InputConstants.Type.KEYSYM,
        InputConstants.KEY_H,
        Info_Hud_Custom_Category
    ));

    HudElementRegistry.addLast(
        Identifier.fromNamespaceAndPath(InfoHudRelaunch.MOD_ID, "info_hud_relaunch_hud"),
        InfoHudRelaunchHud::render
    );

    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      while (ToggleHudKey.consumeClick()) {
        if (InfoHudRelaunchEditScreen.EditScreenVisible) {
          InfoHudRelaunchHud.Visible = false;
        } else {
          InfoHudRelaunchHud.Visible = !InfoHudRelaunchHud.Visible;
        }
      }
    });

    ToggleEditScreenKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
        "key.info-hud-relaunch.open_edit_screen",
        InputConstants.Type.KEYSYM,
        InputConstants.KEY_G,
        Info_Hud_Custom_Category
    ));

    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      while (ToggleEditScreenKey.consumeClick()) {
        client.gui.setScreen(new InfoHudRelaunchEditScreen(Component.empty()));
        InfoHudRelaunchEditScreen.EditScreenVisible = true;
      }
    });
  }
}