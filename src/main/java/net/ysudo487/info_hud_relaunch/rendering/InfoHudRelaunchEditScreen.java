package net.ysudo487.info_hud_relaunch.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
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

    Button ResetColors = ButtonMaker((this.width / 2) + 280, 5, 100, 20, "Reset Colors",
        btn -> {
          FPSColorEditBox.setValue("FFFFFF");
          PosColorEditBox.setValue("FFFFFF");
          BiomeColorEditBox.setValue("FFFFFF");
          DayColorEditBox.setValue("FFFFFF");
    });
    Button ResetYPosition = ButtonMaker((this.width / 2) + 280, 35, 100, 20, "Reset Y Position",
        btn -> {
          FPSYEditBox.setValue("5");
          CoordinatsYEditBox.setValue("22");
          BiomeYEditBox.setValue("39");
          DayYEditBox.setValue("56");
    });
    Button ResetDropShadow = ButtonMaker((this.width / 2) + 280, 65, 100, 20, "Reset Drop Shadow",
        btn -> {
        FpsDropShadowEnabled = false;
        PosDropShadowEnabled = false;
        BiomeDropShadowEnabled = false;
        DayDropShadowEnabled = false;
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
    //Show Hide Btns
    Button FPSVisibility = ButtonMaker((this.width / 2) + 170, 65, 100, 20, "FPSVisibility",
        btn -> {

        });

    Button PosVisibility = ButtonMaker((this.width / 2) + 170, 65, 100, 20, "PosVisibility",
        btn -> {

        });

    Button BiomeVisibility = ButtonMaker((this.width / 2) + 170, 65, 100, 20, "BiomeVisibility",
        btn -> {

        });

    Button DayVisibility = ButtonMaker((this.width / 2) + 170, 65, 100, 20, "DayVisibility",
        btn -> {

        });
    // Fps
    Button FpsVioletBtn = ButtonMaker(5, (this.height) - 24, 100, 20, "Violet", btn -> FPSColorEditBox.setValue("8B00FF"));
    Button FpsIndigoBtn = ButtonMaker(5, (this.height) - 54, 100, 20, "Indigo", btn -> FPSColorEditBox.setValue("4B0082"));
    Button FpsBlueBtn = ButtonMaker(5, (this.height) - 84, 100, 20, "Blue", btn -> FPSColorEditBox.setValue("0000FF"));
    Button FpsGreenBtn = ButtonMaker(5, (this.height) - 114, 100, 20, "Green", btn -> FPSColorEditBox.setValue("008000"));
    Button FpsYellowBtn = ButtonMaker(5, (this.height) - 144, 100, 20, "Yellow", btn -> FPSColorEditBox.setValue("FFFF00"));
    Button FpsOrangeBtn = ButtonMaker(5, (this.height) - 174, 100, 20, "Orange", btn -> FPSColorEditBox.setValue("FF7F00"));
    Button FpsRedBtn = ButtonMaker(5, (this.height) - 204, 100, 20, "Red", btn -> FPSColorEditBox.setValue("FF0000"));

    // Pos
    Button PosVioletBtn = ButtonMaker(125, (this.height) - 24, 100, 20, "Violet", btn -> PosColorEditBox.setValue("8B00FF"));
    Button PosIndigoBtn = ButtonMaker(125, (this.height) - 54, 100, 20, "Indigo", btn -> PosColorEditBox.setValue("4B0082"));
    Button PosBlueBtn = ButtonMaker(125, (this.height) - 84, 100, 20, "Blue", btn -> PosColorEditBox.setValue("0000FF"));
    Button PosGreenBtn = ButtonMaker(125, (this.height) - 114, 100, 20, "Green", btn -> PosColorEditBox.setValue("008000"));
    Button PosYellowBtn = ButtonMaker(125, (this.height) - 144, 100, 20, "Yellow", btn -> PosColorEditBox.setValue("FFFF00"));
    Button PosOrangeBtn = ButtonMaker(125, (this.height) - 174, 100, 20, "Orange", btn -> PosColorEditBox.setValue("FF7F00"));
    Button PosRedBtn = ButtonMaker(125, (this.height) - 204, 100, 20, "Red", btn -> PosColorEditBox.setValue("FF0000"));

    // Biome
    Button BiomeVioletBtn = ButtonMaker(245, (this.height) - 24, 100, 20, "Violet", btn -> BiomeColorEditBox.setValue("8B00FF"));
    Button BiomeIndigoBtn = ButtonMaker(245, (this.height) - 54, 100, 20, "Indigo", btn -> BiomeColorEditBox.setValue("4B0082"));
    Button BiomeBlueBtn = ButtonMaker(245, (this.height) - 84, 100, 20, "Blue", btn -> BiomeColorEditBox.setValue("0000FF"));
    Button BiomeGreenBtn = ButtonMaker(245, (this.height) - 114, 100, 20, "Green", btn -> BiomeColorEditBox.setValue("008000"));
    Button BiomeYellowBtn = ButtonMaker(245, (this.height) - 144, 100, 20, "Yellow", btn -> BiomeColorEditBox.setValue("FFFF00"));
    Button BiomeOrangeBtn = ButtonMaker(245, (this.height) - 174, 100, 20, "Orange", btn -> BiomeColorEditBox.setValue("FF7F00"));
    Button BiomeRedBtn = ButtonMaker(245, (this.height) - 204, 100, 20, "Red", btn -> BiomeColorEditBox.setValue("FF0000"));

    // Day
    Button DayVioletBtn = ButtonMaker(365, (this.height) - 24, 100, 20, "Violet", btn -> DayColorEditBox.setValue("8B00FF"));
    Button DayIndigoBtn = ButtonMaker(365, (this.height) - 54, 100, 20, "Indigo", btn -> DayColorEditBox.setValue("4B0082"));
    Button DayBlueBtn = ButtonMaker(365, (this.height) - 84, 100, 20, "Blue", btn -> DayColorEditBox.setValue("0000FF"));
    Button DayGreenBtn = ButtonMaker(365, (this.height) - 114, 100, 20, "Green", btn -> DayColorEditBox.setValue("008000"));
    Button DayYellowBtn = ButtonMaker(365, (this.height) - 144, 100, 20, "Yellow", btn -> DayColorEditBox.setValue("FFFF00"));
    Button DayOrangeBtn = ButtonMaker(365, (this.height) - 174, 100, 20, "Orange", btn -> DayColorEditBox.setValue("FF7F00"));
    Button DayRedBtn = ButtonMaker(365, (this.height) - 204, 100, 20, "Red", btn -> DayColorEditBox.setValue("FF0000"));

    // Universal
    Button UniversalVioletBtn = ButtonMaker(485, (this.height) - 24, 100, 20, "Violet", btn -> {
      FPSColorEditBox.setValue("8B00FF");
      PosColorEditBox.setValue("8B00FF");
      BiomeColorEditBox.setValue("8B00FF");
      DayColorEditBox.setValue("8B00FF");
    });
    Button UniversalIndigoBtn = ButtonMaker(485, (this.height) - 54, 100, 20, "Indigo", btn -> {
      FPSColorEditBox.setValue("4B0082");
      PosColorEditBox.setValue("4B0082");
      BiomeColorEditBox.setValue("4B0082");
      DayColorEditBox.setValue("4B0082");
    });
    Button UniversalBlueBtn = ButtonMaker(485, (this.height) - 84, 100, 20, "Blue", btn -> {
      FPSColorEditBox.setValue("0000FF");
      PosColorEditBox.setValue("0000FF");
      BiomeColorEditBox.setValue("0000FF");
      DayColorEditBox.setValue("0000FF");
    });
    Button UniversalGreenBtn = ButtonMaker(485, (this.height) - 114, 100, 20, "Green", btn -> {
      FPSColorEditBox.setValue("008000");
      PosColorEditBox.setValue("008000");
      BiomeColorEditBox.setValue("008000");
      DayColorEditBox.setValue("008000");
    });
    Button UniversalYellowBtn = ButtonMaker(485, (this.height) - 144, 100, 20, "Yellow", btn -> {
      FPSColorEditBox.setValue("FFFF00");
      PosColorEditBox.setValue("FFFF00");
      BiomeColorEditBox.setValue("FFFF00");
      DayColorEditBox.setValue("FFFF00");
    });
    Button UniversalOrangeBtn = ButtonMaker(485, (this.height) - 174, 100, 20, "Orange", btn -> {
      FPSColorEditBox.setValue("FF7F00");
      PosColorEditBox.setValue("FF7F00");
      BiomeColorEditBox.setValue("FF7F00");
      DayColorEditBox.setValue("FF7F00");
    });
    Button UniversalRedBtn = ButtonMaker(485, (this.height) - 204, 100, 20, "Red", btn -> {
      FPSColorEditBox.setValue("FF0000");
      PosColorEditBox.setValue("FF0000");
      BiomeColorEditBox.setValue("FF0000");
      DayColorEditBox.setValue("FF0000");
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

    //Fps

    this.addRenderableWidget(FpsVioletBtn);
    this.addRenderableWidget(FpsIndigoBtn);
    this.addRenderableWidget(FpsBlueBtn);
    this.addRenderableWidget(FpsGreenBtn);
    this.addRenderableWidget(FpsYellowBtn);
    this.addRenderableWidget(FpsOrangeBtn);
    this.addRenderableWidget(FpsRedBtn);

    //Pos

    this.addRenderableWidget(PosVioletBtn);
    this.addRenderableWidget(PosIndigoBtn);
    this.addRenderableWidget(PosBlueBtn);
    this.addRenderableWidget(PosGreenBtn);
    this.addRenderableWidget(PosYellowBtn);
    this.addRenderableWidget(PosOrangeBtn);
    this.addRenderableWidget(PosRedBtn);

    //Biome

    this.addRenderableWidget(BiomeVioletBtn);
    this.addRenderableWidget(BiomeIndigoBtn);
    this.addRenderableWidget(BiomeBlueBtn);
    this.addRenderableWidget(BiomeGreenBtn);
    this.addRenderableWidget(BiomeYellowBtn);
    this.addRenderableWidget(BiomeOrangeBtn);
    this.addRenderableWidget(BiomeRedBtn);

    //Day

    this.addRenderableWidget(DayVioletBtn);
    this.addRenderableWidget(DayIndigoBtn);
    this.addRenderableWidget(DayBlueBtn);
    this.addRenderableWidget(DayGreenBtn);
    this.addRenderableWidget(DayYellowBtn);
    this.addRenderableWidget(DayOrangeBtn);
    this.addRenderableWidget(DayRedBtn);

    //Universal

    this.addRenderableWidget(UniversalVioletBtn);
    this.addRenderableWidget(UniversalIndigoBtn);
    this.addRenderableWidget(UniversalBlueBtn);
    this.addRenderableWidget(UniversalGreenBtn);
    this.addRenderableWidget(UniversalYellowBtn);
    this.addRenderableWidget(UniversalOrangeBtn);
    this.addRenderableWidget(UniversalRedBtn);
    //DropShadow
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

    graphics.text(this.font, "Fps Presets", 5, (this.height) - 220, FpsColor, FpsDropShadowEnabled);
    graphics.text(this.font, "Coordinates Presets", 125, (this.height) - 220, PosColor, PosDropShadowEnabled);
    graphics.text(this.font, "Biome Presets", 245, (this.height) - 220, BiomeColor, BiomeDropShadowEnabled);
    graphics.text(this.font, "Day Presets", 365, (this.height) - 220, DayColor, DayDropShadowEnabled);
    graphics.text(this.font, "Universal Presets", 485, (this.height) - 220, UniversalColor, true);

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