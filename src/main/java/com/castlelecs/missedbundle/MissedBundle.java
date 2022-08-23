package com.castlelecs.missedbundle;

import com.castlelecs.missedbundle.factories.ItemsFactory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(MissedBundle.MODID)
public class MissedBundle {

    public static final String MODID = "missedbundle";

    // MARK: - Init

    public MissedBundle() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemsFactory.shared.registerItems(bus);;
    }
}
