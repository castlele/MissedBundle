package com.castlelecs.missedbundle;

import com.castlelecs.missedbundle.factories.ItemsFactory;
import com.castlelecs.missedbundle.factories.ItemsType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MissedBundle.MODID)
public class MissedBundle {
    public static final String MODID = "missedbundle";

    public MissedBundle() {
        System.out.printf("hello Bliat");
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemsFactory itemsFactory = new ItemsFactory();

        itemsFactory.registerItems(bus, ItemsType.values());
    }
}
