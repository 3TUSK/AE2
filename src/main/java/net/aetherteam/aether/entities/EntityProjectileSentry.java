package net.aetherteam.aether.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityProjectileSentry extends EntityThrowable
{
    public EntityLiving victim;
    public float renderYawOffset;
    public static int texfxindex = 94;

    public EntityProjectileSentry(World world)
    {
        super(world);
        this.setSize(2.0F, 2.0F);
    }

    public EntityProjectileSentry(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public EntityProjectileSentry(World world, double x, double y, double z, EntityLiving thrower)
    {
        super(world, x, y, z);
    }

    public EntityProjectileSentry(World world, EntityLiving ent)
    {
        super(world, ent);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition var1)
    {
        if (var1.entityHit == null || var1.entityHit != this.getThrower() || !(var1.entityHit instanceof EntitySentryGolem))
        {
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 0.5F, false);

            if (var1.entityHit instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer)var1.entityHit;

                if (player.isBlocking() && player.getItemInUseDuration() < 30)
                {
                    this.motionX = -this.motionX;
                    this.motionY = -this.motionY;
                    this.motionZ = -this.motionZ;
                    this.setDead();
                    return;
                }
            }

            if (var1.entityHit != null)
            {
                var1.entityHit.addVelocity(1.0D, 0.0D, 0.0D);
                var1.entityHit.addVelocity(0.0D, 1.0D, 0.0D);
                var1.entityHit.addVelocity(0.0D, 0.0D, 1.0D);
                var1.entityHit.velocityChanged = true;
                var1.entityHit.attackEntityFrom(DamageSource.generic.setExplosion(), (float)MathHelper.clamp_int(this.rand.nextInt(4), 1, 4));
            }

            this.setDead();
        }
    }
}
