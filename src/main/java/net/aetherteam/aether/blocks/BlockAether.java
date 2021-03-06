package net.aetherteam.aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAether extends Block implements IAetherBlock
{
    private boolean isDungeonBlock = false;

    protected BlockAether(int id, Material material)
    {
        super(id, material);
    }

    public Block setIconName(String name)
    {
        this.field_111026_f = "aether:" + name;
        return this.setUnlocalizedName("aether:" + name);
    }

    public boolean isDungeonBlock()
    {
        return this.isDungeonBlock;
    }

    public BlockAether setDungeonBlock(boolean isDungeonBlock)
    {
        this.isDungeonBlock = isDungeonBlock;
        return this;
    }
}
