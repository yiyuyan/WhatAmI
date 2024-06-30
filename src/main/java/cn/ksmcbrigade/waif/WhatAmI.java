package cn.ksmcbrigade.waif;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("waif")
public class WhatAmI {

    public WhatAmI() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
