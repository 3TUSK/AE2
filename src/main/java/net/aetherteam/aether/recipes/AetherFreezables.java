package net.aetherteam.aether.recipes;

import net.aetherteam.aether.blocks.AetherBlocks;
import net.aetherteam.aether.items.AetherItems;
import net.aetherteam.aether.tile_entities.TileEntityFreezer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AetherFreezables
{
    public static void init()
    {
        addFreezable(new ItemStack(Item.bucketWater, 1), new ItemStack(Block.ice, 5), 500);
        addFreezable(new ItemStack(AetherItems.SkyrootBucket, 1, 8), new ItemStack(Block.ice, 5), 500);
        addFreezable(new ItemStack(Item.bucketLava, 1), new ItemStack(Block.obsidian, 2), 500);
        addFreezable(new ItemStack(AetherBlocks.Aercloud, 1, 0), new ItemStack(AetherBlocks.Aercloud, 1, 1), 50);
        addFreezable(new ItemStack(AetherItems.GoldenPendant, 1), new ItemStack(AetherItems.IcePendant, 1), 2500);
        addFreezable(new ItemStack(AetherItems.GoldenRing, 1), new ItemStack(AetherItems.IceRing, 1), 1500);
        addFreezable(new ItemStack(AetherItems.IronRing, 1), new ItemStack(AetherItems.IceRing, 1), 1500);
        addFreezable(new ItemStack(AetherItems.IronPendant, 1), new ItemStack(AetherItems.IcePendant, 1), 2500);
    }

    public static void addFreezable(ItemStack var0, ItemStack var1, int var2)
    {
        TileEntityFreezer.addFreezable(var0, var1, var2);
    }
}
