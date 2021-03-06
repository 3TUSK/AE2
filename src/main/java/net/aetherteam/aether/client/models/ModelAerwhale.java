package net.aetherteam.aether.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAerwhale extends ModelBase
{
    ModelRenderer FrontBody;
    ModelRenderer RightFin;
    ModelRenderer BottomPartHead;
    ModelRenderer LeftFin;
    ModelRenderer BottomPartMiddlebody;
    ModelRenderer Head;
    ModelRenderer MiddleFin;
    ModelRenderer BackfinRight;
    ModelRenderer BackBody;
    ModelRenderer BackfinLeft;
    ModelRenderer Middlebody;

    public ModelAerwhale()
    {
        this.textureWidth = 512;
        this.textureHeight = 64;
        this.FrontBody = new ModelRenderer(this, 0, 0);
        this.FrontBody.addBox(-11.5F, -1.0F, -0.5F, 19, 5, 21);
        this.FrontBody.setRotationPoint(2.0F, 6.0F, 38.0F);
        this.FrontBody.setTextureSize(512, 64);
        this.FrontBody.mirror = true;
        this.setRotation(this.FrontBody, -0.1047198F, 0.0F, 0.0F);
        this.RightFin = new ModelRenderer(this, 446, 1);
        this.RightFin.addBox(-20.0F, -2.0F, -6.0F, 19, 3, 14);
        this.RightFin.setRotationPoint(-10.0F, 4.0F, 10.0F);
        this.RightFin.setTextureSize(512, 64);
        this.RightFin.mirror = true;
        this.setRotation(this.RightFin, -0.148353F, 0.2094395F, 0.0F);
        this.RightFin.mirror = false;
        this.BottomPartHead = new ModelRenderer(this, 116, 28);
        this.BottomPartHead.addBox(-13.0F, 4.0F, -15.0F, 26, 6, 30);
        this.BottomPartHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BottomPartHead.setTextureSize(512, 64);
        this.BottomPartHead.mirror = true;
        this.setRotation(this.BottomPartHead, 0.0F, 0.0F, 0.0F);
        this.LeftFin = new ModelRenderer(this, 446, 1);
        this.LeftFin.addBox(1.0F, -2.0F, -6.0F, 19, 3, 14);
        this.LeftFin.setRotationPoint(10.0F, 4.0F, 10.0F);
        this.LeftFin.setTextureSize(512, 64);
        this.LeftFin.mirror = true;
        this.setRotation(this.LeftFin, -0.148353F, -0.2094395F, 0.0F);
        this.BottomPartMiddlebody = new ModelRenderer(this, 16, 32);
        this.BottomPartMiddlebody.addBox(-12.0F, 5.0F, -1.0F, 24, 6, 26);
        this.BottomPartMiddlebody.setRotationPoint(0.0F, -1.0F, 14.0F);
        this.BottomPartMiddlebody.setTextureSize(512, 64);
        this.BottomPartMiddlebody.mirror = true;
        this.setRotation(this.BottomPartMiddlebody, 0.0F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 408, 18);
        this.Head.addBox(-12.0F, -9.0F, -14.0F, 24, 18, 28);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.setTextureSize(512, 64);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
        this.MiddleFin = new ModelRenderer(this, 318, 35);
        this.MiddleFin.addBox(-1.0F, -11.0F, 7.0F, 2, 7, 8);
        this.MiddleFin.setRotationPoint(0.0F, -1.0F, 14.0F);
        this.MiddleFin.setTextureSize(512, 64);
        this.MiddleFin.mirror = true;
        this.setRotation(this.MiddleFin, -0.1441704F, 0.0F, 0.0F);
        this.BackfinRight = new ModelRenderer(this, 261, 5);
        this.BackfinRight.addBox(-11.0F, 0.0F, -6.0F, 15, 3, 24);
        this.BackfinRight.setRotationPoint(-4.0F, 5.0F, 59.0F);
        this.BackfinRight.setTextureSize(512, 64);
        this.BackfinRight.mirror = true;
        this.setRotation(this.BackfinRight, -0.1047198F, -0.7330383F, 0.0F);
        this.BackfinRight.mirror = false;
        this.BackBody = new ModelRenderer(this, 228, 32);
        this.BackBody.addBox(-10.5F, -9.0F, -2.0F, 17, 10, 22);
        this.BackBody.setRotationPoint(2.0F, 5.0F, 38.0F);
        this.BackBody.setTextureSize(512, 64);
        this.BackBody.mirror = true;
        this.setRotation(this.BackBody, -0.1047198F, 0.0F, 0.0F);
        this.BackfinLeft = new ModelRenderer(this, 261, 5);
        this.BackfinLeft.addBox(-4.0F, 0.0F, -6.0F, 13, 3, 24);
        this.BackfinLeft.setRotationPoint(5.0F, 5.0F, 59.0F);
        this.BackfinLeft.setTextureSize(512, 64);
        this.BackfinLeft.mirror = true;
        this.setRotation(this.BackfinLeft, -0.1047198F, 0.7330383F, 0.0F);
        this.Middlebody = new ModelRenderer(this, 314, 25);
        this.Middlebody.addBox(-11.0F, -5.0F, -1.0F, 22, 14, 25);
        this.Middlebody.setRotationPoint(0.0F, -1.0F, 14.0F);
        this.Middlebody.setTextureSize(512, 64);
        this.Middlebody.mirror = true;
        this.setRotation(this.Middlebody, -0.0698132F, 0.0F, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.FrontBody.render(f5);
        this.RightFin.render(f5);
        this.BottomPartHead.render(f5);
        this.LeftFin.render(f5);
        this.BottomPartMiddlebody.render(f5);
        this.Head.render(f5);
        this.MiddleFin.render(f5);
        this.BackfinRight.render(f5);
        this.BackBody.render(f5);
        this.BackfinLeft.render(f5);
        this.Middlebody.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
