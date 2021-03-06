package net.aetherteam.aether.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemCrystalBottle extends ItemAccessory
{
    protected ItemCrystalBottle(int par1, int par2, int par3, int par4)
    {
        super(par1, par2, par3, par4);
        this.setMaxStackSize(1);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        NBTTagCompound nbt = new NBTTagCompound();

        if (par1ItemStack.hasTagCompound())
        {
            float exp = par1ItemStack.getTagCompound().getFloat("Experience");

            if (par2EntityPlayer.isSneaking())
            {
                if (par2EntityPlayer.experience > 0.0F)
                {
                    par1ItemStack.getTagCompound().setFloat("Experience", exp + 0.1F);
                    par2EntityPlayer.experience -= 0.1F;
                }
                else if (par2EntityPlayer.experienceLevel > 0)
                {
                    par1ItemStack.getTagCompound().setFloat("Experience", exp + 1.0F);
                    --par2EntityPlayer.experienceLevel;
                    par2EntityPlayer.experience = 1.0F;
                }
            }
            else if (exp > 0.0F)
            {
                if (par2EntityPlayer.experience < 1.0F)
                {
                    par1ItemStack.getTagCompound().setFloat("Experience", exp - 0.1F);
                    par2EntityPlayer.experience += 0.1F;
                }
                else
                {
                    par1ItemStack.getTagCompound().setFloat("Experience", exp - 1.0F);
                    par2EntityPlayer.experienceLevel = (int)((float)par2EntityPlayer.experienceLevel + 1.0F);
                    par2EntityPlayer.experience = 0.0F;
                }
            }

            return true;
        }
        else
        {
            par1ItemStack.setTagCompound(nbt);
            return false;
        }
    }
}
