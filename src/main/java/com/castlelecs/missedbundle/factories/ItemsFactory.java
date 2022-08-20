package com.castlelecs.missedbundle.factories;

import com.castlelecs.missedbundle.MissedBundle;
import com.castlelecs.missedbundle.utilities.RegistrationFactory;
import com.castlelecs.missedbundle.utilities.Singleton;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsFactory extends RegistrationFactory<Item> implements Singleton {

    private final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, MissedBundle.MODID);

    public final static ItemsFactory shared = new ItemsFactory();

    // MARK: - Init

    private ItemsFactory() { }

    // MARK: - Public methods

    public void registerItems(IEventBus bus) {
        super.register(bus, items, ItemsType.values());
    }
}
