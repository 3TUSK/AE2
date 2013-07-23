package net.aetherteam.aether.client.gui.social.options;

import cpw.mods.fml.client.FMLClientHandler;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class GuiOptions extends GuiScreen
{
    private int backgroundTexture;
    private int easterTexture;
    private int xParty;
    private int yParty;
    private int wParty;
    private int hParty;
    Minecraft mc;
    private EntityPlayer player;
    private GuiScreen parent;

    public GuiOptions(EntityPlayer player, GuiScreen parent)
    {
        this.parent = parent;
        this.player = player;
        this.mc = FMLClientHandler.instance().getClient();
        this.backgroundTexture = this.mc.renderEngine.getTexture("/net/aetherteam/aether/client/sprites/gui/partyMain.png");
        this.easterTexture = this.mc.renderEngine.getTexture("/net/aetherteam/aether/client/sprites/gui/partyMain.png");
        this.wParty = 256;
        this.hParty = 256;
        updateScreen();
    }

    public void initGui()
    {
        updateScreen();
        this.buttonList.clear();

        this.buttonList.add(new GuiButton(0, this.xParty - 60, this.yParty + 81 - 28, 120, 20, "返回"));
    }

    protected void keyTyped(char charTyped, int keyTyped)
    {
        super.keyTyped(charTyped, keyTyped);

        if (keyTyped == Minecraft.getMinecraft().gameSettings.keyBindInventory.keyCode)
        {
            this.mc.displayGuiScreen((GuiScreen) null);
            this.mc.setIngameFocus();
        }
    }

    protected void actionPerformed(GuiButton button)
    {
        List playerList = this.mc.thePlayer.sendQueue.playerInfoList;

        boolean online = playerList.size() > 1;

        switch (button.id)
        {
            case 0:
                if (online)
                {
                    this.mc.displayGuiScreen(this.parent);
                } else this.mc.displayGuiScreen(null);

                break;
            case 1:
                this.mc.displayGuiScreen(new GuiOptionsParty(this.player, this));
                break;
            case 2:
                this.mc.displayGuiScreen(new GuiOptionsNotification(this.player, this));
                break;
            case 3:
                this.mc.displayGuiScreen(new GuiOptionsCoinbar(this.player, this));
        }
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }

    public void drawScreen(int x, int y, float partialTick)
    {
        this.buttonList.clear();

        drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBindTexture(3553, this.backgroundTexture);
        int centerX = this.xParty - 70;
        int centerY = this.yParty - 84;

        ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        drawTexturedModalRect(centerX, centerY, 0, 0, 141, this.hParty);

        List playerList = this.mc.thePlayer.sendQueue.playerInfoList;

        boolean online = playerList.size() > 1;

        if (online)
        {
            this.buttonList.add(new GuiButton(1, this.xParty - 60, this.yParty - 36 - 28, 120, 20, "公会HUD"));
            this.buttonList.add(new GuiButton(2, this.xParty - 60, this.yParty - 14 - 28, 120, 20, "信息"));
            this.buttonList.add(new GuiButton(3, this.xParty - 60, this.yParty + 8 - 28, 120, 20, "以太币界面"));

            this.mc.renderEngine.resetBoundTexture();

            String title = "选项";

            drawString(this.fontRenderer, title, centerX + 70 - this.fontRenderer.getStringWidth(title) / 2, centerY + 5, 16777215);
        } else
        {
            GL11.glBindTexture(3553, this.backgroundTexture);
            drawTexturedModalRect(centerX + 13, centerY + 40, 141, 131, 115, 125);

            this.mc.renderEngine.resetBoundTexture();
            drawString(this.fontRenderer, "注定孤独一生 :(", centerX + 26, centerY + 10, 15658734);
            drawString(this.fontRenderer, "(单人游戏)", centerX + 31, centerY + 22, 15658734);
        }

        this.buttonList.add(new GuiButton(0, this.xParty - 60, this.yParty + 81 - 28, 120, 20, online ? "后退" : "退出"));

        super.drawScreen(x, y, partialTick);
    }

    public void updateScreen()
    {
        super.updateScreen();
        ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
        this.xParty = (width / 2);
        this.yParty = (height / 2);
    }
}
