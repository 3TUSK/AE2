package net.aetherteam.aether.client.gui.social;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import java.util.ArrayList;
import net.aetherteam.aether.packets.AetherPacketHandler;
import net.aetherteam.aether.party.Party;
import net.aetherteam.aether.party.PartyController;
import net.aetherteam.aether.party.PartyType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class GuiPartyEdit extends GuiScreen
{
    private final PartyData pm;
    private int backgroundTexture;
    private int easterTexture;
    private int xParty;
    private int yParty;
    private int wParty;
    private int hParty;

    /** Reference to the Minecraft object. */
    Minecraft mc;
    private ArrayList partyType = new ArrayList();
    private int typeIndex = 0;
    private EntityPlayer player;
    private GuiButton typeButton;
    private String newPartyName;
    private GuiScreen parent;

    public GuiPartyEdit(EntityPlayer var1, GuiScreen var2)
    {
        this(new PartyData(), var1, var2);
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

    public GuiPartyEdit(PartyData var1, EntityPlayer var2, GuiScreen var3)
    {
        this.partyType = new ArrayList();
        this.typeIndex = 0;
        this.parent = var3;
        String name = PartyController.instance().getParty(var2).getType().name();

        if (name == "OPEN")
        {
            this.typeIndex = 0;
        }
        if (name == "CLOSE")
        {
            this.typeIndex = 1;
        }
        if (name == "PRIVATE")
        {
            this.typeIndex = 2;
        }
        this.partyType.add("公开");
        this.partyType.add("关闭");
        this.partyType.add("私有");

        this.player = player;
        this.mc = FMLClientHandler.instance().getClient();
        this.pm = var1;
        this.backgroundTexture = this.mc.renderEngine.getTexture("/net/aetherteam/aether/client/sprites/gui/partyMain.png");
        this.easterTexture = this.mc.renderEngine.getTexture("/net/aetherteam/aether/client/sprites/gui/partyMain.png");
        this.wParty = 256;
        this.hParty = 256;
        ArrayList var5 = PartyController.instance().getParties();

        for (int var6 = 0; var6 < var5.size(); ++var6)
        {
            if (((Party)var5.get(var6)).getLeader().username.equals(var2.username))
            {
                this.newPartyName = ((Party)var5.get(var6)).getName();
            }
        }

        this.updateScreen();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.updateScreen();
        this.buttonList.clear();
        Party var1 = PartyController.instance().getParty(this.player);

        if (var1 != null)
        {
            this.typeButton = new GuiButton(4, this.xParty - 60, this.yParty + 30 - 28, 120, 20, "类型: " + PartyController.instance().getParty(this.player).getType().realname);
        }

        this.buttonList.add(this.typeButton);
        this.buttonList.add(new GuiButton(5, this.xParty - 60, this.yParty + 12 - 28, 120, 20, "保存"));
        this.buttonList.add(new GuiButton(3, this.xParty - 60, this.yParty - 36 - 28, 120, 20, "重命名"));
        this.buttonList.add(new GuiButton(2, this.xParty - 60, this.yParty - 14 - 28, 120, 20, "管理权限"));
        this.buttonList.add(new GuiButton(1, this.xParty - 60, this.yParty + 8 - 28, 120, 20, "管理会员"));
        this.buttonList.add(new GuiButton(0, this.xParty - 60, this.yParty + 81 - 28, 120, 20, "返回"));
        ArrayList var2 = new ArrayList();

        for (int i = 0; i < var2.size(); ++i)
        {
            if (((Party)var2.get(i)).getLeader().username == this.player.username)
            {
                ((GuiButton)this.buttonList.get(1)).enabled = false;
            }
        }
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
                break;

            case 1:
                this.mc.displayGuiScreen(new GuiManageMembers(this.player, this));

            case 2:
            default:
                break;

            case 3:
                this.mc.displayGuiScreen(new GuiEditPartyName(this.player, this));
                break;

            case 4:
                ++this.typeIndex;

                if (this.typeIndex > this.partyType.size() - 1)
                {
                    this.typeIndex = 0;
                }

                PartyController.instance().getParty(this.player).setType(PartyType.getTypeFromString((String)this.partyType.get(this.typeIndex)));
                PacketDispatcher.sendPacketToServer(AetherPacketHandler.sendPartyTypeChange(PartyController.instance().getParty(this.player).getName(), PartyType.getTypeFromString((String)this.partyType.get(this.typeIndex))));
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
    public void drawScreen(int x, int y, float partialTick)
    {
        this.drawDefaultBackground();
        this.buttonList.clear();

        this.typeButton = new GuiButton(4, this.xParty - 60, this.yParty + 30 - 28, 120, 20, "类型: " + PartyController.instance().getParty(this.player).getType().realname);

        this.buttonList.add(this.typeButton);

        this.buttonList.add(new GuiButton(3, this.xParty - 60, this.yParty - 36 - 28, 120, 20, "重命名"));
        this.buttonList.add(new GuiButton(1, this.xParty - 60, this.yParty - 14 - 28, 120, 20, "管理会员"));
        this.buttonList.add(new GuiButton(0, this.xParty - 60, this.yParty + 81 - 28, 120, 20, "返回"));
        ArrayList partyList = PartyController.instance().getParties();
        int centerX;

        for (centerX = 0; centerX < partyList.size(); ++centerX)
        {
            if (((Party)partyList.get(centerX)).getLeader().username.equals(this.player.username) && ((Party)partyList.get(centerX)).getType().name() != this.partyType.get(this.typeIndex) && ((Party)partyList.get(centerX)).getName() == this.newPartyName)
            {
                ;
            }
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.backgroundTexture);
        centerX = this.xParty - 70;
        int centerY = this.yParty - 84;
        new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        this.drawTexturedModalRect(centerX, centerY, 0, 0, 141, this.hParty);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.backgroundTexture);
        this.mc.renderEngine.resetBoundTexture();

        String name = "管理公会";

        drawString(this.fontRenderer, name, centerX + 69 - this.fontRenderer.getStringWidth(name) / 2, centerY + 5, 16777215);
        super.drawScreen(x, y, partialTick);
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
}