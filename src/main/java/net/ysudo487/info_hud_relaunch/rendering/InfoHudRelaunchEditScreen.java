package net.ysudo487.info_hud_relaunch.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ScrollableLayout;
import net.minecraft.client.gui.components.debugchart.FpsDebugChart;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.ysudo487.info_hud_relaunch.InfoHudRelaunchSaveConfig;

public class InfoHudRelaunchEditScreen extends Screen {
  public static boolean EditScreenVisible = false;
  private EditBox FPSColorEditBox;
  private EditBox PosColorEditBox;
  private EditBox BiomeColorEditBox;
  private EditBox DayColorEditBox;
  private EditBox FPSYEditBox;
  private EditBox CoordinatsYEditBox;
  private EditBox BiomeYEditBox;
  private EditBox DayYEditBox;
  public static boolean FpsDropShadowEnabled = false;
  public static boolean PosDropShadowEnabled = false;
  public static boolean BiomeDropShadowEnabled = false;
  public static boolean DayDropShadowEnabled = false;
  public static int UniversalColor = 0xFFFFFFFF;

  public InfoHudRelaunchEditScreen(Component title) {
    super(title);

  }

  @Override
  protected void init() {

    Minecraft client = Minecraft.getInstance();
    if(client.debugEntries.isOverlayVisible()) return;

    FPSColorEditBox = EditBoxMaker(8, this.width / 2, (this.height/1000)+5,50,20);
    FPSColorEditBox.setValue(LoadColor(InfoHudRelaunchHud.FPSColor));

    PosColorEditBox = EditBoxMaker(8, this.width / 2, 35,50,20);
    PosColorEditBox.setValue(LoadColor(InfoHudRelaunchHud.POSColor));

    BiomeColorEditBox = EditBoxMaker(8, this.width / 2, 65,50,20);
    BiomeColorEditBox.setValue(LoadColor(InfoHudRelaunchHud.BiomeColor));

    DayColorEditBox = EditBoxMaker(8, this.width / 2, 95,50,20);
    DayColorEditBox.setValue(LoadColor(InfoHudRelaunchHud.DayColor));

    FPSYEditBox = EditBoxMaker(2, 130, 5,20,20);
    FPSYEditBox.setValue(LoadY(InfoHudRelaunchHud.FPSY));

    CoordinatsYEditBox = EditBoxMaker(2, 130, 35,20,20);
    CoordinatsYEditBox.setValue(LoadY(InfoHudRelaunchHud.CoordinatesY));

    BiomeYEditBox = EditBoxMaker(2, 130, 65,20,20);
    BiomeYEditBox.setValue(LoadY(InfoHudRelaunchHud.BiomeY));

    DayYEditBox = EditBoxMaker(2, 130, 95,20,20);
    DayYEditBox.setValue(LoadY(InfoHudRelaunchHud.DayY));



    Button CloseBtn = ButtonMaker(this.width - 24, 5, 20, 20, "X",
        btn -> this.onClose());

    Button ResetColors = ButtonMaker((this.width / 2) + 280, 35, 100, 20, "Reset Colors",
        btn -> {
          FPSColorEditBox.setValue("FFFFFF");
          PosColorEditBox.setValue("FFFFFF");
          BiomeColorEditBox.setValue("FFFFFF");
          DayColorEditBox.setValue("FFFFFF");
        });
    Button ResetYPosition = ButtonMaker((this.width / 2) + 280, 65, 100, 20, "Reset Y Position",
        btn -> {
          FPSYEditBox.setValue("5");
          CoordinatsYEditBox.setValue("22");
          BiomeYEditBox.setValue("39");
          DayYEditBox.setValue("56");
        });

    //DropShadow

    Button FpsDropShadowBtn = ButtonMaker((this.width / 2) + 140, 5, 120, 20,
        "Fps Shadow: " + (FpsDropShadowEnabled ? "ON" : "OFF"),
        btn -> {
          FpsDropShadowEnabled = !FpsDropShadowEnabled;
          btn.setMessage(Component.literal("Fps Shadow: " + (FpsDropShadowEnabled ? "ON" : "OFF")));
        });

    Button PosDropShadowBtn = ButtonMaker((this.width / 2) + 140, 35, 120, 20,
        "Position Shadow: " + (PosDropShadowEnabled ? "ON" : "OFF"),
        btn -> {
          PosDropShadowEnabled = !PosDropShadowEnabled;
          btn.setMessage(Component.literal("Position Shadow: " + (PosDropShadowEnabled ? "ON" : "OFF")));
        });

    Button BiomeDropShadowBtn = ButtonMaker((this.width / 2) + 140, 65, 120, 20,
        "Biome Shadow: " + (BiomeDropShadowEnabled ? "ON" : "OFF"),
        btn -> {
          BiomeDropShadowEnabled = !BiomeDropShadowEnabled;
          btn.setMessage(Component.literal("Biome Shadow: " + (BiomeDropShadowEnabled ? "ON" : "OFF")));
        });

    Button DayDropShadowBtn = ButtonMaker((this.width / 2) + 140, 95, 120, 20,
        "Day Shadow: " + (DayDropShadowEnabled ? "ON" : "OFF"),
        btn -> {
          DayDropShadowEnabled = !DayDropShadowEnabled;
          btn.setMessage(Component.literal("Day Shadow: " + (DayDropShadowEnabled ? "ON" : "OFF")));
        });

    Button ResetDropShadow = ButtonMaker((this.width / 2) + 280, 5, 110, 20, "Reset Drop Shadow", btn -> {
      FpsDropShadowEnabled = false;
      PosDropShadowEnabled = false;
      BiomeDropShadowEnabled = false;
      DayDropShadowEnabled = false;
      FpsDropShadowBtn.setMessage(Component.literal("Fps Shadow: OFF"));
      PosDropShadowBtn.setMessage(Component.literal("Position Shadow: OFF"));
      BiomeDropShadowBtn.setMessage(Component.literal("Biome Shadow: OFF"));
      DayDropShadowBtn.setMessage(Component.literal("Day Shadow: OFF"));
    });

    this.addRenderableWidget(FPSColorEditBox);
    this.addRenderableWidget(PosColorEditBox);
    this.addRenderableWidget(BiomeColorEditBox);
    this.addRenderableWidget(DayColorEditBox);
    this.addRenderableWidget(CloseBtn);
    this.addRenderableWidget(ResetColors);
    this.addRenderableWidget(ResetYPosition);
    this.addRenderableWidget(ResetDropShadow);
    this.addRenderableWidget(FPSYEditBox);
    this.addRenderableWidget(CoordinatsYEditBox);
    this.addRenderableWidget(BiomeYEditBox);
    this.addRenderableWidget(DayYEditBox);


    this.addRenderableWidget(FpsDropShadowBtn);
    this.addRenderableWidget(PosDropShadowBtn);
    this.addRenderableWidget(BiomeDropShadowBtn);
    this.addRenderableWidget(DayDropShadowBtn);
  }
  @Override
  public void onClose() {
    EditScreenVisible = false;
    super.onClose();
  }
  @Override
  public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
    super.extractRenderState(graphics, mouseX, mouseY, delta);

    Minecraft client = Minecraft.getInstance();
    if(client.debugEntries.isOverlayVisible()) return;

    int FpsColor = Int1(FPSColorEditBox.getValue());
    int PosColor = Int1(PosColorEditBox.getValue());
    int BiomeColor = Int1(BiomeColorEditBox.getValue());
    int DayColor = Int1(DayColorEditBox.getValue());

    int FpsY = Int2(FPSYEditBox.getValue());
    int CoordinatesY = Int2(CoordinatsYEditBox.getValue());
    int BiomeY = Int2(BiomeYEditBox.getValue());
    int DayY = Int2(DayYEditBox.getValue());

    InfoHudRelaunchHud.FPSColor = FpsColor;
    InfoHudRelaunchHud.POSColor = PosColor;
    InfoHudRelaunchHud.BiomeColor = BiomeColor;
    InfoHudRelaunchHud.DayColor = DayColor;
    InfoHudRelaunchHud.FPSY = FpsY;
    InfoHudRelaunchHud.CoordinatesY = CoordinatesY;
    InfoHudRelaunchHud.BiomeY = BiomeY;
    InfoHudRelaunchHud.DayY = DayY;

    try {
      InfoHudRelaunchSaveConfig.Info_Hud_Save(FpsDropShadowEnabled,PosDropShadowEnabled,BiomeDropShadowEnabled,DayDropShadowEnabled,FpsColor, PosColor, BiomeColor, DayColor,
          FpsY, CoordinatesY, BiomeY, DayY);
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }


    graphics.text(this.font, "FPS Color", (this.width / 2) - 65, 10, FpsColor, FpsDropShadowEnabled);
    graphics.text(this.font, "Coordinates Color", (this.width / 2) - 95, 40, PosColor, PosDropShadowEnabled);
    graphics.text(this.font, "Biome Color", (this.width / 2) - 65, 70, BiomeColor, BiomeDropShadowEnabled);
    graphics.text(this.font, "Day Color", (this.width / 2) - 65, 100, DayColor, DayDropShadowEnabled);

    graphics.text(this.font, "Fps Y Position :", 5, 10, UniversalColor,FpsDropShadowEnabled);
    graphics.text(this.font, "Coordinates Y Position :", 5, 40, UniversalColor, PosDropShadowEnabled);
    graphics.text(this.font, "Biome Y Position :", 5, 70, UniversalColor, BiomeDropShadowEnabled);
    graphics.text(this.font, "Day Y Position :", 5, 100, UniversalColor, DayDropShadowEnabled);
  }

  private int Int1(String input) {
    try {
      long parsed = Long.parseLong(input, 16);
      if (input.length() <= 6) {
        parsed |= 0xFF000000L;
      }
      return (int) parsed;
    } catch (NumberFormatException e) {
      return 0xFFFFFFFF;
    }
  }
  private int Int2(String input) {
    try {
      return Integer.parseInt(input);
    } catch (NumberFormatException e) {
      return 0;
    }
  }
  public EditBox EditBoxMaker(int MaxNum, int posX, int posY,int width,int height) {
    EditBox box = new EditBox(this.font, posX, posY, width, height, Component.literal("Color"));
    box.setMaxLength(MaxNum);
    return box;
  }

  public Button ButtonMaker(int posX, int posY, int width, int height, String text, Button.OnPress onPress) {
    return Button.builder(Component.literal(text), onPress)
        .bounds(posX, posY, width, height)
        .build();
  }


  private String LoadColor(int Color) {
    return String.format("%06X", Color & 0xFFFFFF);
  }
  private String LoadY(int Y) {
    return Integer.toString(Y);
  }
}