package net.aetherteam.aether.entities;

import net.aetherteam.aether.blocks.AetherBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityZephyr extends EntityFlying implements IMob
{
    public String dir = "/net/aetherteam/aether/client/sprites";
    public float sinage = 0.0F;
    private long checkTime = 0L;
    private final long checkTimeInterval = 3000L;
    private double checkX = 0.0D;
    private double checkY = 0.0D;
    private double checkZ = 0.0D;
    private final double minTraversalDist = 3.0D;
    private boolean isStuckWarning = false;
    public int courseChangeCooldown = 0;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity = null;
    private int aggroCooldown = 0;
    public int prevAttackCounter = 0;
    public int attackCounter = 0;

    public EntityZephyr(World var1)
    {
        super(var1);
        this.texture = this.dir + "/mobs/zephyr/zephyr.png";
        this.setSize(4.0F, 4.0F);
    }

    public int getMaxHealth()
    {
        return 5;
    }

    protected void updateEntityActionState()
    {
        if (this.posY < -2.0D || this.posY > 130.0D)
        {
            this.despawnEntity();
        }

        this.prevAttackCounter = this.attackCounter;
        double var1 = this.waypointX - this.posX;
        double var3 = this.waypointY - this.posY;
        double var5 = this.waypointZ - this.posZ;
        double var7 = (double)MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5);

        if (var7 < 4.0D || var7 > 30.0D)
        {
            this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointY = this.posY + (double)(this.rand.nextFloat() * 2.0F * 16.0F);
            this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var7))
        {
            this.motionX += var1 / var7 * 0.1D;
            this.motionY += var3 / var7 * 0.1D;
            this.motionZ += var5 / var7 * 0.1D;
        }
        else
        {
            this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointY = this.posY + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.courseChangeCooldown-- <= 0)
        {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
        }
    }

    private boolean isCourseTraversable(double var1, double var3, double var5, double var7)
    {
        double var9 = (this.waypointX - this.posX) / var7;
        double var11 = (this.waypointY - this.posY) / var7;
        double var13 = (this.waypointZ - this.posZ) / var7;
        AxisAlignedBB var15 = this.boundingBox.copy();

        for (int var16 = 1; (double)var16 < var7; ++var16)
        {
            var15.offset(var9, var11, var13);

            if (this.worldObj.getCollidingBoundingBoxes(this, var15).size() > 0)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "aemob.zephyr.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "aemob.zephyr.say";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "aemob.zephyr.say";
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    public boolean canDespawn()
    {
        return true;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 3.0F;
    }

    private void checkForBeingStuck()
    {
        long var1 = System.currentTimeMillis();

        if (var1 > this.checkTime + 3000L)
        {
            double var3 = this.posX - this.checkX;
            double var5 = this.posY - this.checkY;
            double var7 = this.posZ - this.checkZ;
            double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);

            if (var9 < 3.0D)
            {
                if (!this.isStuckWarning)
                {
                    this.isStuckWarning = true;
                }
                else
                {
                    this.setDead();
                }
            }

            this.checkX = this.posX;
            this.checkY = this.posY;
            this.checkZ = this.posZ;
            this.checkTime = var1;
        }
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.boundingBox.minY);
        int var3 = MathHelper.floor_double(this.posZ);
        return this.rand.nextInt(65) == 0 && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.isAnyLiquid(this.boundingBox) && this.worldObj.getBlockId(var1, var2 - 1, var3) == AetherBlocks.AetherGrass.blockID && this.worldObj.getBlockId(var1, var2 - 1, var3) != AetherBlocks.Holystone.blockID && this.worldObj.difficultySetting > 0;
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
}
