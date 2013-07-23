package net.aetherteam.aether.client.gui.social;

import cpw.mods.fml.client.FMLClientHandler;
import java.util.ArrayList;
import net.aetherteam.aether.party.Party;
import net.aetherteam.aether.party.PartyController;
import net.aetherteam.aether.party.members.PartyMember;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StringUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiMemberList extends GuiScreen
{
    protected static final String ONLINE_TEXT = "ONLINE";
    protected static final String OFFLINE_TEXT = "OFFLINE";
    private static final int ONLINE_TEXT_COLOR = 6750054;
    private static final int OFFLINE_TEXT_COLOR = 16711680;
    private GuiYSlider sbar;
    private float sbarVal = 0.0F;
    private int backgroundTexture;
    private int dialogueTexture;
    private int xParty;
    private int yParty;
    private int wParty;
    private int hParty;

    /** Reference to the Minecraft object. */
    Minecraft mc;
    private GuiScreen parent;
    private EntityPlayer player;

    public GuiMemberList(EntityPlayer var1, GuiScreen var2)
    {
        this.player = var1;
        this.parent = var2;
        this.mc = FMLClientHandler.instance().getClient();
        this.backgroundTexture = this.mc.renderEngine.getTexture("/net/aetherteam/aether/client/sprites/gui/party.png");
        this.dialogueTexture = this.mc.renderEngine.getTexture("/net/aetherteam/aether/client/sprites/gui/dialogue.png");
        this.wParty = 256;
        this.hParty = 256;
        this.updateScreen();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.updateScreen();
        this.buttonList.clear();

        if (this.sbar != null)
        {
            this.sbarVal = this.sbar.sliderValue;
        }

        this.sbar = new GuiYSlider(-1, this.xParty + 46, this.yParty - 54, 10, 103);
        this.sbar.sliderValue = this.sbarVal;
        this.buttonList.add(new GuiButton(0, this.xParty - 58, this.yParty + 85 - 28, 120, 20, "Back"));
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char var1, int var2)
    {
        super.keyTyped(var1, var2);

        if (var2 == Minecraft.getMinecraft().gameSettings.keyBindInventory.keyCode)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int var1, int var2, int var3)
    {
        this.sbar.mousePressed(this.mc, var1, var2);
        super.mouseClicked(var1, var2, var3);
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    protected void mouseMovedOrUp(int var1, int var2, int var3)
    {
        if (var3 == 0)
        {
            this.sbar.mouseReleased(var1, var2);
        }

        super.mouseMovedOrUp(var1, var2, var3);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton var1)
    {
        switch (var1.id)
        {
            case 0:
                this.mc.displayGuiScreen(this.parent);

            default:
        }
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int var1, int var2, float var3)
    {
        if (PartyController.instance().getParty(this.player) == null)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
        }

        ArrayList var4 = new ArrayList();

        if (PartyController.instance().getParty(this.player) != null)
        {
            var4 = PartyController.instance().getParty(this.player).getMembers();
        }

        this.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.backgroundTexture);
        int var5 = Mouse.getDWheel();
        this.sbar.sliderValue -= (float)var5 / 1000.0F;

        if (this.sbar.sliderValue > 1.0F)
        {
            this.sbar.sliderValue = 1.0F;
        }

        if (this.sbar.sliderValue < 0.0F)
        {
            this.sbar.sliderValue = 0.0F;
        }

        int var6 = this.xParty - 70;
        int var7 = this.yParty - 84;
        ScaledResolution var8 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        this.drawTexturedModalRect(var6, var7, 0, 0, 141, this.hParty);
        boolean var9 = false;
        byte var10 = 100;
        byte var11 = 20;
        byte var12 = 2;
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((var6 + 14) * var8.getScaleFactor(), (var7 + 35) * var8.getScaleFactor(), var10 * var8.getScaleFactor(), 103 * var8.getScaleFactor());
        GL11.glPushMatrix();
        int var16 = var4.size() * (var11 + var12);
        float var13 = -this.sbar.sliderValue * (float)(var16 - 105);

        if (var16 > 103)
        {
            GL11.glTranslatef(0.0F, var13, 0.0F);
        }

        var16 = 0;

        for (int var14 = 0; var14 < var4.size(); ++var14)
        {
            String var15 = ((PartyMember)var4.get(var14)).username;
            this.drawPlayerSlot(var15, var6 + 15, var7 + var16 + 30, var10, var11, true);
            var16 += var11 + var12;
        }

        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_SCISSOR_TEST);

        if (var16 > 103)
        {
            this.sbar.drawButton(this.mc, var1, var2);
        }

        this.drawString(this.fontRenderer, "Player List", var6 + 40, var7 + 10, 16777215);
        super.drawScreen(var1, var2, var3);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
        ScaledResolution var1 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int var2 = var1.getScaledWidth();
        int var3 = var1.getScaledHeight();
        this.xParty = var2 / 2;
        this.yParty = var3 / 2;
    }

    public void drawPlayerSlot(String var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        ArrayList var7 = PartyController.instance().getParties();
        ArrayList var8 = new ArrayList();
        int var9;

        for (var9 = 0; var9 < var7.size(); ++var9)
        {
            PartyMember var10 = PartyController.instance().getMember(var1);

            if (((Party)var7.get(var9)).hasMember(var10))
            {
                var8.add(var1);
            }
        }

        this.drawGradientRect(var2, var3, var2 + var4, var3 + var5, -11184811, -10066330);
        var9 = this.mc.renderEngine.getTextureForDownloadableImage("http://skins.minecraft.net/MinecraftSkins/" + StringUtils.stripControlCodes(var1) + ".png", "/mob/char.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, var9);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        float var14 = 0.125F;
        float var11 = 0.25F;
        float var12 = 0.25F;
        float var13 = 0.5F;
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(var14, var11);
        GL11.glVertex2f((float)(var2 + 2), (float)(var3 + 2));
        GL11.glTexCoord2f(var14, var13);
        GL11.glVertex2f((float)(var2 + 2), (float)(var3 + 18));
        GL11.glTexCoord2f(var12, var13);
        GL11.glVertex2f((float)(var2 + 18), (float)(var3 + 18));
        GL11.glTexCoord2f(var12, var11);
        GL11.glVertex2f((float)(var2 + 18), (float)(var3 + 2));
        GL11.glEnd();
        this.mc.renderEngine.resetBoundTexture();
        this.fontRenderer.drawStringWithShadow(var1, var2 + var5, var3 + 2, 15066597);
        GL11.glPushMatrix();
        GL11.glScalef(0.75F, 0.75F, 1.0F);

        if (var8.contains(var1))
        {
            this.fontRenderer.drawString("ONLINE", (int)(((float)var2 + (float)var5) / 0.75F), (int)(((float)var3 + 12.0F) / 0.75F), 6750054);
        }
        else
        {
            this.fontRenderer.drawString("OFFLINE", (int)(((float)var2 + (float)var5) / 0.75F), (int)(((float)var3 + 12.0F) / 0.75F), 16711680);
        }

        GL11.glPopMatrix();
    }
}
