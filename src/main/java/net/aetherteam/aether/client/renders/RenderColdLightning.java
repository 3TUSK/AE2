package net.aetherteam.aether.client.renders;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.aetherteam.aether.entities.EntityColdLightningBolt;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderColdLightning extends Render
{
    public void doRenderLightningBolt(EntityColdLightningBolt par1EntityLightningBolt, double par2, double par4, double par6, float par8, float par9)
    {
        Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glDepthMask(true);
        double[] adouble = new double[8];
        double[] adouble1 = new double[8];
        double d3 = 0.0D;
        double d4 = 0.0D;
        Random random = new Random(par1EntityLightningBolt.boltVertex);
        int j;

        for (j = 7; j >= 0; --j)
        {
            adouble[j] = d3;
            adouble1[j] = d4;
            d3 += (double)(random.nextInt(11) - 5);
            d4 += (double)(random.nextInt(11) - 5);
        }

        for (j = 0; j < 4; ++j)
        {
            Random random1 = new Random(par1EntityLightningBolt.boltVertex);

            for (int k = 0; k < 3; ++k)
            {
                int l = 7;
                int i1 = 0;

                if (k > 0)
                {
                    l = 7 - k;
                }

                if (k > 0)
                {
                    i1 = l - 2;
                }

                double d5 = adouble[l] - d3;
                double d6 = adouble1[l] - d4;

                for (int j1 = l; j1 >= i1; --j1)
                {
                    double d7 = d5;
                    double d8 = d6;

                    if (k == 0)
                    {
                        d5 += (double)(random1.nextInt(11) - 5);
                        d6 += (double)(random1.nextInt(11) - 5);
                    }
                    else
                    {
                        d5 += (double)(random1.nextInt(31) - 15);
                        d6 += (double)(random1.nextInt(31) - 15);
                    }

                    tessellator.startDrawing(5);
                    float f2 = 0.5F;
                    tessellator.setColorRGBA_F(0.9F * f2, 0.9F * f2, 1.0F * f2, 7.3F);
                    double d9 = 0.1D + (double)j * 0.2D;

                    if (k == 0)
                    {
                        d9 *= (double)j1 * 0.1D + 1.0D;
                    }

                    double d10 = 0.1D + (double)j * 0.2D;

                    if (k == 0)
                    {
                        d10 *= (double)(j1 - 1) * 0.1D + 1.0D;
                    }

                    for (int k1 = 0; k1 < 5; ++k1)
                    {
                        double d11 = par2 + 0.5D - d9;
                        double d12 = par6 + 0.5D - d9;

                        if (k1 == 1 || k1 == 2)
                        {
                            d11 += d9 * 2.0D;
                        }

                        if (k1 == 2 || k1 == 3)
                        {
                            d12 += d9 * 2.0D;
                        }

                        double d13 = par2 + 0.5D - d10;
                        double d14 = par6 + 0.5D - d10;

                        if (k1 == 1 || k1 == 2)
                        {
                            d13 += d10 * 2.0D;
                        }

                        if (k1 == 2 || k1 == 3)
                        {
                            d14 += d10 * 2.0D;
                        }

                        tessellator.addVertex(d13 + d5, par4 + (double)(j1 * 16), d14 + d6);
                        tessellator.addVertex(d11 + d7, par4 + (double)((j1 + 1) * 16), d12 + d8);
                    }

                    tessellator.draw();
                }
            }
        }

        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderLightningBolt((EntityColdLightningBolt)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity entity)
    {
        return null;
    }
}
