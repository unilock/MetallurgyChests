package krelox.metallurgychests.blocks.animations;

import krelox.metallurgychests.Tags;
import krelox.metallurgychests.blocks.BlockRubraciumChest;
import krelox.metallurgychests.blocks.tileentities.TileEntityRubraciumChest;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
 
@SideOnly(Side.CLIENT)
public class RenderRubraciumChest extends TileEntitySpecialRenderer<TileEntityRubraciumChest>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Tags.MOD_ID, "textures/blocks/rubracium_chest.png");
    private final ModelRubraciumChest MODEL = new ModelRubraciumChest();
    
    @Override
    public void render(TileEntityRubraciumChest te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) 
    {
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
 
        int i;
        
        if (te.hasWorld())
        {
            Block block = te.getBlockType();
            i = te.getBlockMetadata();
 
            if (block instanceof BlockRubraciumChest && i == 0)
            {
                i = te.getBlockMetadata();
             }
            }
            else
            {
            i = 0;
            }
        
        ModelRubraciumChest model = MODEL;
        
        if (destroyStage >= 0)
        {
        	this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else this.bindTexture(TEXTURE);
        
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);
        GlStateManager.scale(1.0F, -1.0F, -1.0F);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
        int j = 0;
        
        if (i == 2)
        {
            j = 180;
        }
 
        if (i == 3)
        {
            j = 0;
        }
 
        if (i == 4)
        {
            j = 90;
        }
 
        if (i == 5)
        {
            j = -90;
        }
 
        GlStateManager.rotate((float)j, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
       
        float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
        f = 1.0F - f;
        f = 1.0F - f * f * f;
        model.chestLid.rotateAngleX = -(f * ((float)Math.PI / 2F));
        model.renderAll();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
 
        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }   
    }
}
