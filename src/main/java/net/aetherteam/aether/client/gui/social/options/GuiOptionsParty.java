package net.aetherteam.aether.client.gui.social.options;

import cpw.mods.fml.client.FMLClientHandler;

import java.util.List;

import net.aetherteam.aether.data.AetherOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiOptionsParty extends GuiScreen
{
    private static final ResourceLocation TEXTURE_PARTYMAIN = new ResourceLocation("aether", "textures/gui/partyMain.png");
    private int xParty;
    private int yParty;
    private int wParty;
    private int hParty;
    
    Minecraft mc;
    private EntityPlayer player;
    private GuiScreen parent;

    public GuiOptionsParty(EntityPlayer player, GuiScreen parent)
    {
        this.parent = parent;
        this.player = player;
        this.mc = FMLClientHandler.instance().getClient();
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

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char charTyped, int keyTyped)
    {
        super.keyTyped(charTyped, keyTyped);

        if (keyTyped == Minecraft.getMinecraft().gameSettings.keyBindInventory.keyCode)
        {
            this.mc.displayGuiScreen((GuiScreen) null);
            this.mc.setIngameFocus();
        }
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton button)
    {
        List playerList = this.mc.thePlayer.sendQueue.playerInfoList;
        boolean online = playerList.size() > 1;

        switch (button.id)
        {
            case 0:
                this.mc.displayGuiScreen(this.parent);
                break;
            case 1:
                AetherOptions.setShowPartyHUD(!AetherOptions.getShowPartyHUD());
                break;
            case 2:
                AetherOptions.setMinimalPartyHUD(!AetherOptions.getMinimalPartyHUD());
                break;
            case 3:
                AetherOptions.setRenderHead(!AetherOptions.getRenderHead());
                break;
            case 4:
                AetherOptions.setShowPartyName(!AetherOptions.getShowPartyName());
        }
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int x, int y, float partialTick)
    {
        this.buttonList.clear();

        drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.func_110577_a(TEXTURE_PARTYMAIN);
        int centerX = this.xParty - 70;
        int centerY = this.yParty - 84;
        new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        this.drawTexturedModalRect(centerX, centerY, 0, 0, 141, this.hParty);
        List playerList = this.mc.thePlayer.sendQueue.playerInfoList;
        boolean online = playerList.size() > 1;
        String renderHeadString = AetherOptions.getRenderHead() ? "是" : "否";
        String showHUDString = AetherOptions.getShowPartyHUD() ? "是" : "否";
        String showNameString = AetherOptions.getShowPartyName() ? "开" : "关";
        GuiButton minimalHUD = new GuiButton(2, this.xParty - 60, this.yParty - 14 - 28, 120, 20, "HUD风格: " + (AetherOptions.getMinimalPartyHUD() ? "简约" : "华丽"));
        GuiButton renderHead = new GuiButton(3, this.xParty - 60, this.yParty + 8 - 28, 120, 20, "头像渲染: " + renderHeadString);
        GuiButton showName = new GuiButton(4, this.xParty - 60, this.yParty + 30 - 28, 120, 20, "公会名称: " + showNameString);
        this.buttonList.add(new GuiButton(1, this.xParty - 60, this.yParty - 36 - 28, 120, 20, "显示HUD: " + showHUDString));
        this.buttonList.add(minimalHUD);
        this.buttonList.add(renderHead);
        this.buttonList.add(showName);

        if (AetherOptions.getShowPartyHUD())
        {
            minimalHUD.enabled = true;
            renderHead.enabled = true;
            showName.enabled = true;
        }
        else
        {
            minimalHUD.enabled = false;
            renderHead.enabled = false;
            showName.enabled = false;
        }

        String title = "公会HUD";
        this.drawString(this.fontRenderer, title, centerX + 70 - this.fontRenderer.getStringWidth(title) / 2, centerY + 5, 16777215);
        this.buttonList.add(new GuiButton(0, this.xParty - 60, this.yParty + 81 - 28, 120, 20, "返回"));
        super.drawScreen(x, y, partialTick);
    }

    public void updateScreen()
    {
        super.updateScreen();
        ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
        this.xParty = width / 2;
        this.yParty = height / 2;
    }
}
