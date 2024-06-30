package cn.ksmcbrigade.waif.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(InventoryScreen.class)
public class InventoryMixin {

    private static final LivingEntity player;
    private static final EntityType<?>[] entities;
    private static final Random RANDOM;
    private static LivingEntity thisEntity = null;

    @ModifyVariable(method = "renderEntityInInventory", at = @At(value = "HEAD"), ordinal = 0, argsOnly = true)
    private static LivingEntity injected(LivingEntity entity) {
        return thisEntity;
    }

    @Inject(at = {@At("TAIL")}, method = {"<init>"})
    private void setThisEntity(Player p_98839_, CallbackInfo ci) {
        thisEntity = null;
        while (thisEntity==null){
            try {
                thisEntity = (LivingEntity) EntityType.create(Registry.ENTITY_TYPE.getId(entities[RANDOM.nextInt(0,entities.length-1)]),player.level);
            }
            catch (Exception e){
                //no code
            }
        }
    }

    static {
        player = Minecraft.getInstance().player;
        entities = ForgeRegistries.ENTITIES.getValues().toArray(new EntityType[0]);
        RANDOM = new Random();
        /*thisEntity = null;
        while (thisEntity==null){
            try {
                thisEntity = (LivingEntity) EntityType.create(Registry.ENTITY_TYPE.getId(entities[RANDOM.nextInt(0,entities.length-1)]),player.level);
            }
            catch (Exception e){
                //no code
            }
        }*/
    }
}
