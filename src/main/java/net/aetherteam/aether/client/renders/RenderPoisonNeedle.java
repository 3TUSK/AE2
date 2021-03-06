package net.aetherteam.aether.client.renders;

import net.aetherteam.aether.entities.EntityPoisonNeedle;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderPoisonNeedle extends Render
{
    public static final ResourceLocation TEXTURE = new ResourceLocation("aether", "textures/projectiles/dart/entitypoisonneedle.png");

    public void renderPoisonNeedle(EntityPoisonNeedle entityarrow, double d, double d1, double d2, float f, float f1)
    {
        if (entityarrow.victim == null)
        {
            this.func_110777_b(entityarrow);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)d, (float)d1, (float)d2);
            GL11.glRotatef(entityarrow.prevRotationYaw + (entityarrow.rotationYaw - entityarrow.prevRotationYaw) * f1 - 90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(entityarrow.prevRotationPitch + (entityarrow.rotationPitch - entityarrow.prevRotationPitch) * f1, 0.0F, 0.0F, 1.0F);
            Tessellator tessellator = Tessellator.instance;
            byte i = 1;
            float f2 = 0.0F;
            float f3 = 0.5F;
            float f4 = (float)(0 + i * 10) / 32.0F;
            float f5 = (float)(5 + i * 10) / 32.0F;
            float f6 = 0.0F;
            float f7 = 0.15625F;
            float f8 = (float)(5 + i * 10) / 32.0F;
            float f9 = (float)(10 + i * 10) / 32.0F;
            float f10 = 0.05625F;
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            float f11 = (float)entityarrow.arrowShake - f1;

            if (f11 > 0.0F)
            {
                float j = -MathHelper.sin(f11 * 3.0F) * f11;
                GL11.glRotatef(j, 0.0F, 0.0F, 1.0F);
            }

            GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(f10, f10, f10);
            GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
            GL11.glNormal3f(f10, 0.0F, 0.0F);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)f6, (double)f8);
            tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)f7, (double)f8);
            tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)f7, (double)f9);
            tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)f6, (double)f9);
            tessellator.draw();
            GL11.glNormal3f(-f10, 0.0F, 0.0F);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)f6, (double)f8);
            tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)f7, (double)f8);
            tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)f7, (double)f9);
            tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)f6, (double)f9);
            tessellator.draw();

            for (int var23 = 0; var23 < 5; ++var23)
            {
                GL11.glRotatef(72.0F, 1.0F, 0.0F, 0.0F);
                GL11.glNormal3f(0.0F, 0.0F, f10);
                tessellator.startDrawingQuads();
                tessellator.addVertexWithUV(-8.0D, -2.0D, 0.0D, (double)f2, (double)f4);
                tessellator.addVertexWithUV(8.0D, -2.0D, 0.0D, (double)f3, (double)f4);
                tessellator.addVertexWithUV(8.0D, 2.0D, 0.0D, (double)f3, (double)f5);
                tessellator.addVertexWithUV(-8.0D, 2.0D, 0.0D, (double)f2, (double)f5);
                tessellator.draw();
            }

            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
        }
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        this.renderPoisonNeedle((EntityPoisonNeedle)entity, d, d1, d2, f, f1);
    }

    protected ResourceLocation func_110775_a(Entity entity)
    {
        return TEXTURE;
    }
}
