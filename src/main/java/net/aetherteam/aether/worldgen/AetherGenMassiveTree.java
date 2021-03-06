package net.aetherteam.aether.worldgen;

import java.util.Random;
import net.aetherteam.aether.blocks.AetherBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class AetherGenMassiveTree extends WorldGenerator
{
    int leaves;
    int randHeight;
    boolean branches;

    public AetherGenMassiveTree(int leafID, int heightWeight, boolean branchFlag)
    {
        this.leaves = leafID;
        this.randHeight = heightWeight;
        this.branches = branchFlag;
    }

    public boolean generate(World world, Random random, int x, int y, int z)
    {
        boolean cangen = true;
        int height = random.nextInt(this.randHeight) + (this.branches ? 8 : 4);
        int y1;
        int side;
        int y2;

        for (y1 = x - 3; y1 < x + 3; ++y1)
        {
            for (side = y + 1; side < y + height + 2; ++side)
            {
                for (y2 = z - 3; y2 < z + 3; ++y2)
                {
                    if (world.getBlockId(y1, side, y2) != 0)
                    {
                        cangen = false;
                    }
                }
            }
        }

        if (y + height + 2 <= world.getHeight() && cangen)
        {
            y1 = world.getBlockId(x, y - 1, z);

            if (y1 != AetherBlocks.AetherGrass.blockID && y1 != AetherBlocks.AetherDirt.blockID)
            {
                return false;
            }
            else
            {
                world.setBlock(x, y - 1, z, AetherBlocks.AetherDirt.blockID);

                for (side = y; side <= y + height; ++side)
                {
                    world.setBlock(x, side, z, AetherBlocks.AetherLog.blockID);
                }

                if (this.branches)
                {
                    world.setBlock(x + 1, y, z, AetherBlocks.SkyrootLogWall.blockID);
                    world.setBlock(x + 1, y + 1, z, AetherBlocks.SkyrootLogWall.blockID);
                    world.setBlock(x + 2, y, z, AetherBlocks.SkyrootLogWall.blockID);
                    world.setBlock(x - 1, y, z, AetherBlocks.SkyrootLogWall.blockID);
                    world.setBlock(x - 1, y + 1, z, AetherBlocks.SkyrootLogWall.blockID);
                    world.setBlock(x - 2, y, z, AetherBlocks.SkyrootLogWall.blockID);
                    world.setBlock(x, y, z + 1, AetherBlocks.SkyrootLogWall.blockID);
                    world.setBlock(x, y + 1, z + 1, AetherBlocks.SkyrootLogWall.blockID);
                    world.setBlock(x, y, z + 2, AetherBlocks.SkyrootLogWall.blockID);
                    world.setBlock(x, y, z - 1, AetherBlocks.SkyrootLogWall.blockID);
                    world.setBlock(x, y + 1, z - 1, AetherBlocks.SkyrootLogWall.blockID);
                    world.setBlock(x, y, z - 2, AetherBlocks.SkyrootLogWall.blockID);
                    world.setBlock(x + 1, y - 1, z, AetherBlocks.AetherGrass.blockID);
                    world.setBlock(x + 2, y - 1, z, AetherBlocks.AetherGrass.blockID);
                    world.setBlock(x - 1, y - 1, z, AetherBlocks.AetherGrass.blockID);
                    world.setBlock(x - 2, y - 1, z, AetherBlocks.AetherGrass.blockID);
                    world.setBlock(x, y - 1, z + 1, AetherBlocks.AetherGrass.blockID);
                    world.setBlock(x, y - 1, z + 2, AetherBlocks.AetherGrass.blockID);
                    world.setBlock(x, y - 1, z - 1, AetherBlocks.AetherGrass.blockID);
                    world.setBlock(x, y - 1, z - 2, AetherBlocks.AetherGrass.blockID);
                    world.setBlock(x + 1, y - 2, z, AetherBlocks.AetherDirt.blockID);
                    world.setBlock(x + 2, y - 2, z, AetherBlocks.AetherDirt.blockID);
                    world.setBlock(x - 1, y - 2, z, AetherBlocks.AetherDirt.blockID);
                    world.setBlock(x - 2, y - 2, z, AetherBlocks.AetherDirt.blockID);
                    world.setBlock(x, y - 2, z + 1, AetherBlocks.AetherDirt.blockID);
                    world.setBlock(x, y - 2, z + 2, AetherBlocks.AetherDirt.blockID);
                    world.setBlock(x, y - 2, z - 1, AetherBlocks.AetherDirt.blockID);
                    world.setBlock(x, y - 2, z - 2, AetherBlocks.AetherDirt.blockID);
                }

                this.setBlockAirCheck(world, x, y + height + 1, z, this.leaves);
                this.setBlockAirCheck(world, x, y + height + 2, z, this.leaves);

                for (side = y + 2; side <= y + height + 1; ++side)
                {
                    this.setBlockAirCheck(world, x + 1, side, z, this.leaves);
                    this.setBlockAirCheck(world, x - 1, side, z, this.leaves);
                    this.setBlockAirCheck(world, x, side, z + 1, this.leaves);
                    this.setBlockAirCheck(world, x, side, z - 1, this.leaves);
                }

                for (side = y + 3; side <= y + height; side += 2)
                {
                    this.setBlockAirCheck(world, x + 1, side, z + 1, this.leaves);
                    this.setBlockAirCheck(world, x - 1, side, z - 1, this.leaves);
                    this.setBlockAirCheck(world, x + 1, side, z - 1, this.leaves);
                    this.setBlockAirCheck(world, x - 1, side, z + 1, this.leaves);
                    this.setBlockAirCheck(world, x + 2, side, z, this.leaves);
                    this.setBlockAirCheck(world, x - 2, side, z, this.leaves);
                    this.setBlockAirCheck(world, x, side, z + 2, this.leaves);
                    this.setBlockAirCheck(world, x, side, z - 2, this.leaves);
                }

                if (this.branches)
                {
                    side = random.nextInt(3);

                    for (y2 = y + random.nextInt(2) + 3; y2 <= y + height - 2; y2 += 1 + random.nextInt(3))
                    {
                        int branchLength = random.nextInt(4) + 1;
                        int z3;

                        switch (side)
                        {
                            case 0:
                                for (z3 = x; z3 <= x + branchLength; ++z3)
                                {
                                    world.setBlock(z3, y2, z, AetherBlocks.AetherLog.blockID);
                                    this.setBlockAirCheck(world, z3, y2 + 1, z, this.leaves);
                                    this.setBlockAirCheck(world, z3, y2 - 1, z, this.leaves);
                                    this.setBlockAirCheck(world, z3 + 1, y2, z + 1, this.leaves);
                                    this.setBlockAirCheck(world, z3 - 1, y2, z - 1, this.leaves);
                                }

                                world.setBlock(x + branchLength + 1, y2 + 1, z, AetherBlocks.AetherLog.blockID);
                                world.setBlock(x + branchLength + 2, y2 + 2, z, AetherBlocks.AetherLog.blockID);
                                world.setBlock(x + branchLength + 2, y2 + 3, z, AetherBlocks.AetherLog.blockID);
                                this.setBlockAirCheck(world, x + branchLength + 1, y2 + 2, z, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 1, y2 + 3, z, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 2, y2 + 4, z, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 3, y2 + 2, z, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 3, y2 + 3, z, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 2, y2 + 2, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 2, y2 + 3, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 2, y2 + 2, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 2, y2 + 3, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 1, y2 + 1, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 1, y2 + 2, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 1, y2 + 1, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 1, y2 + 2, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength, y2, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength, y2 + 1, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength, y2, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength, y2 + 1, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x + (branchLength - 1), y2, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x + (branchLength - 1), y2, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x + (branchLength - 2), y2 - 1, z, this.leaves);
                                this.setBlockAirCheck(world, x + (branchLength - 1), y2 - 1, z, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength, y2 - 1, z, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 1, y2, z, this.leaves);
                                this.setBlockAirCheck(world, x + branchLength + 2, y2 + 1, z, this.leaves);
                                break;

                            case 1:
                                for (z3 = x; z3 >= x - branchLength; --z3)
                                {
                                    world.setBlock(z3, y2, z, AetherBlocks.AetherLog.blockID);
                                    this.setBlockAirCheck(world, z3, y2 + 1, z, this.leaves);
                                    this.setBlockAirCheck(world, z3, y2 - 1, z, this.leaves);
                                    this.setBlockAirCheck(world, z3 + 1, y2, z + 1, this.leaves);
                                    this.setBlockAirCheck(world, z3 - 1, y2, z - 1, this.leaves);
                                }

                                world.setBlock(x - (branchLength + 1), y2 + 1, z, AetherBlocks.AetherLog.blockID);
                                world.setBlock(x - (branchLength + 2), y2 + 2, z, AetherBlocks.AetherLog.blockID);
                                world.setBlock(x - (branchLength + 2), y2 + 3, z, AetherBlocks.AetherLog.blockID);
                                this.setBlockAirCheck(world, x - (branchLength + 1), y2 + 2, z, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 1), y2 + 3, z, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 2), y2 + 4, z, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 3), y2 + 2, z, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 3), y2 + 3, z, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 2), y2 + 2, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 2), y2 + 3, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 2), y2 + 2, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 2), y2 + 3, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 1), y2 + 1, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 1), y2 + 2, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 1), y2 + 1, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 1), y2 + 2, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x - branchLength, y2, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x - branchLength, y2 + 1, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x - branchLength, y2, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x - branchLength, y2 + 1, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength - 1), y2, z - 1, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength - 1), y2, z + 1, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength - 2), y2 - 1, z, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength - 1), y2 - 1, z, this.leaves);
                                this.setBlockAirCheck(world, x - branchLength, y2 - 1, z, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 1), y2, z, this.leaves);
                                this.setBlockAirCheck(world, x - (branchLength + 2), y2 + 1, z, this.leaves);
                                break;

                            case 2:
                                for (z3 = z; z3 <= z + branchLength; ++z3)
                                {
                                    world.setBlock(x, y2, z3, AetherBlocks.AetherLog.blockID);
                                    this.setBlockAirCheck(world, x, y2 + 1, z3, this.leaves);
                                    this.setBlockAirCheck(world, x, y2 - 1, z3, this.leaves);
                                    this.setBlockAirCheck(world, x + 1, y2, z3, this.leaves);
                                    this.setBlockAirCheck(world, x - 1, y2, z3, this.leaves);
                                }

                                world.setBlock(x, y2 + 1, z + branchLength + 1, AetherBlocks.AetherLog.blockID);
                                world.setBlock(x, y2 + 2, z + branchLength + 2, AetherBlocks.AetherLog.blockID);
                                world.setBlock(x, y2 + 3, z + branchLength + 2, AetherBlocks.AetherLog.blockID);
                                this.setBlockAirCheck(world, x, y2 + 2, z + branchLength + 1, this.leaves);
                                this.setBlockAirCheck(world, x, y2 + 3, z + branchLength + 1, this.leaves);
                                this.setBlockAirCheck(world, x, y2 + 4, z + branchLength + 2, this.leaves);
                                this.setBlockAirCheck(world, x, y2 + 2, z + branchLength + 3, this.leaves);
                                this.setBlockAirCheck(world, x, y2 + 3, z + branchLength + 3, this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2 + 2, z + branchLength + 2, this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2 + 3, z + branchLength + 2, this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2 + 2, z + branchLength + 2, this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2 + 3, z + branchLength + 2, this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2 + 1, z + branchLength + 1, this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2 + 2, z + branchLength + 1, this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2 + 1, z + branchLength + 1, this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2 + 2, z + branchLength + 1, this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2, z + branchLength, this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2 + 1, z + branchLength, this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2, z + branchLength, this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2 + 1, z + branchLength, this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2, z + (branchLength - 1), this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2, z + (branchLength - 1), this.leaves);
                                this.setBlockAirCheck(world, x, y2 - 1, z + (branchLength - 2), this.leaves);
                                this.setBlockAirCheck(world, x, y2 - 1, z + (branchLength - 1), this.leaves);
                                this.setBlockAirCheck(world, x, y2 - 1, z + branchLength, this.leaves);
                                this.setBlockAirCheck(world, x, y2, z + branchLength + 1, this.leaves);
                                this.setBlockAirCheck(world, x, y2 + 1, z + branchLength + 2, this.leaves);
                                break;

                            case 3:
                                for (z3 = z; z3 >= z - branchLength; --z3)
                                {
                                    world.setBlock(x, y2, z3, AetherBlocks.AetherLog.blockID);
                                    this.setBlockAirCheck(world, x, y2 + 1, z3, this.leaves);
                                    this.setBlockAirCheck(world, x, y2 - 1, z3, this.leaves);
                                    this.setBlockAirCheck(world, x + 1, y2, z3, this.leaves);
                                    this.setBlockAirCheck(world, x - 1, y2, z3, this.leaves);
                                }

                                world.setBlock(x, y2 + 1, z - (branchLength + 1), AetherBlocks.AetherLog.blockID);
                                world.setBlock(x, y2 + 2, z - (branchLength + 2), AetherBlocks.AetherLog.blockID);
                                world.setBlock(x, y2 + 3, z - (branchLength + 2), AetherBlocks.AetherLog.blockID);
                                this.setBlockAirCheck(world, x, y2 + 2, z - (branchLength + 1), this.leaves);
                                this.setBlockAirCheck(world, x, y2 + 3, z - (branchLength + 1), this.leaves);
                                this.setBlockAirCheck(world, x, y2 + 4, z - (branchLength + 2), this.leaves);
                                this.setBlockAirCheck(world, x, y2 + 2, z - (branchLength + 3), this.leaves);
                                this.setBlockAirCheck(world, x, y2 + 3, z - (branchLength + 3), this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2 + 2, z - (branchLength + 2), this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2 + 3, z - (branchLength + 2), this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2 + 2, z - (branchLength + 2), this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2 + 3, z - (branchLength + 2), this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2 + 1, z - (branchLength + 1), this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2 + 2, z - (branchLength + 1), this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2 + 1, z - (branchLength + 1), this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2 + 2, z - (branchLength + 1), this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2, z - branchLength, this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2 + 1, z - branchLength, this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2, z - branchLength, this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2 + 1, z - branchLength, this.leaves);
                                this.setBlockAirCheck(world, x - 1, y2, z - (branchLength - 1), this.leaves);
                                this.setBlockAirCheck(world, x + 1, y2, z - (branchLength - 1), this.leaves);
                                this.setBlockAirCheck(world, x, y2 - 1, z - (branchLength - 2), this.leaves);
                                this.setBlockAirCheck(world, x, y2 - 1, z - (branchLength - 1), this.leaves);
                                this.setBlockAirCheck(world, x, y2 - 1, z - branchLength, this.leaves);
                                this.setBlockAirCheck(world, x, y2, z - (branchLength + 1), this.leaves);
                                this.setBlockAirCheck(world, x, y2 + 1, z - (branchLength + 2), this.leaves);
                        }

                        ++side;

                        if (side > 3)
                        {
                            side = 0;
                        }
                    }
                }

                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public void setBlockAirCheck(World world, int x, int y, int z, int blockID)
    {
        if (world.getBlockId(x, y, z) == 0)
        {
            world.setBlock(x, y, z, blockID);
        }
    }
}
