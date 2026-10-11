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
  private EditBox FCB;
  private EditBox PCB;
  private EditBox BCB;
  private EditBox DCB;
  private EditBox FYB;
  private EditBox CYB;
  private EditBox BYB;
  private EditBox DYB;
  public static boolean FDSE = false;
  public static boolean PDSE = false;
  public static boolean BDSE = false;
  public static boolean DDSE = false;
  public static int UniversalColor = 0xFFFFFFFF;

  public InfoHudRelaunchEditScreen(Component T) {
    super(T);
  }

  @Override
  protected void init() {
    Minecraft C = Minecraft.getInstance();
    if (C.debugEntries.isOverlayVisible()) return;

    FCB = EditBoxMaker(8, this.width / 2, (this.height / 1000) + 5, 50, 20);
    FCB.setValue(LoadColor(InfoHudRelaunchHud.FC));

    PCB = EditBoxMaker(8, this.width / 2, 35, 50, 20);
    PCB.setValue(LoadColor(InfoHudRelaunchHud.PC));

    BCB = EditBoxMaker(8, this.width / 2, 65, 50, 20);
    BCB.setValue(LoadColor(InfoHudRelaunchHud.BC));

    DCB = EditBoxMaker(8, this.width / 2, 95, 50, 20);
    DCB.setValue(LoadColor(InfoHudRelaunchHud.DC));

    FYB = EditBoxMaker(2, 130, 5, 20, 20);
    FYB.setValue(LoadY(InfoHudRelaunchHud.FY));

    CYB = EditBoxMaker(2, 130, 35, 20, 20);
    CYB.setValue(LoadY(InfoHudRelaunchHud.CY));

    BYB = EditBoxMaker(2, 130, 65, 20, 20);
    BYB.setValue(LoadY(InfoHudRelaunchHud.BY));

    DYB = EditBoxMaker(2, 130, 95, 20, 20);
    DYB.setValue(LoadY(InfoHudRelaunchHud.DY));

    Button CB = ButtonMaker(this.width - 24, 5, 20, 20, "X",
        info_hud_relaunch_btn -> this.onClose());

    Button RCB = ButtonMaker((this.width / 2) + 280, 35, 100, 20, "Reset Colors",
        info_hud_relaunch_btn -> {
          FCB.setValue("FFFFFF");
          PCB.setValue("FFFFFF");
          BCB.setValue("FFFFFF");
          DCB.setValue("FFFFFF");
        });
    Button RYB = ButtonMaker((this.width / 2) + 280, 65, 100, 20, "Reset Y Position",
        info_hud_relaunch_btn -> {
          FYB.setValue("5");
          CYB.setValue("22");
          BYB.setValue("39");
          DYB.setValue("56");
        });

    //DropShadow

    Button FDSB = ButtonMaker((this.width / 2) + 140, 5, 120, 20,
        "Fps Shadow: " + (FDSE ? "ON" : "OFF"),
        info_hud_relaunch_btn -> {
          FDSE = !FDSE;
          info_hud_relaunch_btn.setMessage(Component.literal("Fps Shadow: " + (FDSE ? "ON" : "OFF")));
        });

    Button PDSB = ButtonMaker((this.width / 2) + 140, 35, 120, 20,
        "Position Shadow: " + (PDSE ? "ON" : "OFF"),
        info_hud_relaunch_btn -> {
          PDSE = !PDSE;
          info_hud_relaunch_btn.setMessage(Component.literal("Position Shadow: " + (PDSE ? "ON" : "OFF")));
        });

    Button BDSB = ButtonMaker((this.width / 2) + 140, 65, 120, 20,
        "Biome Shadow: " + (BDSE ? "ON" : "OFF"),
        info_hud_relaunch_btn -> {
          BDSE = !BDSE;
          info_hud_relaunch_btn.setMessage(Component.literal("Biome Shadow: " + (BDSE ? "ON" : "OFF")));
        });

    Button DDSB = ButtonMaker((this.width / 2) + 140, 95, 120, 20,
        "Day Shadow: " + (DDSE ? "ON" : "OFF"),
        info_hud_relaunch_btn -> {
          DDSE = !DDSE;
          info_hud_relaunch_btn.setMessage(Component.literal("Day Shadow: " + (DDSE ? "ON" : "OFF")));
        });

    Button RDSB = ButtonMaker((this.width / 2) + 280, 5, 110, 20, "Reset Drop Shadow",
        info_hud_relaunch_btn -> {
          FDSE = false;
          PDSE = false;
          BDSE = false;
          DDSE = false;
          FDSB.setMessage(Component.literal("Fps Shadow: OFF"));
          PDSB.setMessage(Component.literal("Position Shadow: OFF"));
          BDSB.setMessage(Component.literal("Biome Shadow: OFF"));
          DDSB.setMessage(Component.literal("Day Shadow: OFF"));
        });

    this.addRenderableWidget(FCB);
    this.addRenderableWidget(PCB);
    this.addRenderableWidget(BCB);
    this.addRenderableWidget(DCB);
    this.addRenderableWidget(CB);
    this.addRenderableWidget(RCB);
    this.addRenderableWidget(RYB);
    this.addRenderableWidget(RDSB);
    this.addRenderableWidget(FYB);
    this.addRenderableWidget(CYB);
    this.addRenderableWidget(BYB);
    this.addRenderableWidget(DYB);

    this.addRenderableWidget(FDSB);
    this.addRenderableWidget(PDSB);
    this.addRenderableWidget(BDSB);
    this.addRenderableWidget(DDSB);
  }

  @Override
  public void onClose() {
    EditScreenVisible = false;
    super.onClose();
  }

  @Override
  public void extractRenderState(GuiGraphicsExtractor G, int MX, int MY, float D) {
    super.extractRenderState(G, MX, MY, D);

    Minecraft C = Minecraft.getInstance();
    if (C.debugEntries.isOverlayVisible()) return;

    int FC = Int1(FCB.getValue());
    int PC = Int1(PCB.getValue());
    int BC = Int1(BCB.getValue());
    int DC = Int1(DCB.getValue());

    int FY = Int2(FYB.getValue());
    int CY = Int2(CYB.getValue());
    int BY = Int2(BYB.getValue());
    int DY = Int2(DYB.getValue());

    InfoHudRelaunchHud.FC = FC;
    InfoHudRelaunchHud.PC = PC;
    InfoHudRelaunchHud.BC = BC;
    InfoHudRelaunchHud.DC = DC;
    InfoHudRelaunchHud.FY = FY;
    InfoHudRelaunchHud.CY = CY;
    InfoHudRelaunchHud.BY = BY;
    InfoHudRelaunchHud.DY = DY;

    try {
      InfoHudRelaunchSaveConfig.Info_Hud_Save(FDSE, PDSE, BDSE, DDSE, FC, PC, BC, DC, FY, CY, BY, DY);
    } catch (java.io.IOException info_hud_relaunch_e) {
      info_hud_relaunch_e.printStackTrace();
    }

    G.text(this.font, "FPS Color", (this.width / 2) - 65, 10, FC, FDSE);
    G.text(this.font, "Coordinates Color", (this.width / 2) - 95, 40, PC, PDSE);
    G.text(this.font, "Biome Color", (this.width / 2) - 65, 70, BC, BDSE);
    G.text(this.font, "Day Color", (this.width / 2) - 65, 100, DC, DDSE);

    G.text(this.font, "Fps Y Position :", 5, 10, UniversalColor, FDSE);
    G.text(this.font, "Coordinates Y Position :", 5, 40, UniversalColor, PDSE);
    G.text(this.font, "Biome Y Position :", 5, 70, UniversalColor, BDSE);
    G.text(this.font, "Day Y Position :", 5, 100, UniversalColor, DDSE);
  }

  private int Int1(String I) {
    try {
      long info_hud_relaunch_parsed = Long.parseLong(I, 16);
      if (I.length() <= 6) {
        info_hud_relaunch_parsed |= 0xFF000000L;
      }
      return (int) info_hud_relaunch_parsed;
    } catch (NumberFormatException info_hud_relaunch_e) {
      return 0xFFFFFFFF;
    }
  }

  private int Int2(String I) {
    try {
      return Integer.parseInt(I);
    } catch (NumberFormatException info_hud_relaunch_e) {
      return 0;
    }
  }

  public EditBox EditBoxMaker(int MN, int PX, int PY, int W, int H) {
    EditBox info_hud_relaunch_box = new EditBox(this.font, PX, PY, W, H, Component.literal("Color"));
    info_hud_relaunch_box.setMaxLength(MN);
    return info_hud_relaunch_box;
  }

  public Button ButtonMaker(int PX, int PY, int W, int H, String T, Button.OnPress OP) {
    return Button.builder(Component.literal(T), OP)
        .bounds(PX, PY, W, H)
        .build();
  }

  private String LoadColor(int C) {
    return String.format("%06X", C & 0xFFFFFF);
  }

  private String LoadY(int Y) {
    return Integer.toString(Y);
  }
}