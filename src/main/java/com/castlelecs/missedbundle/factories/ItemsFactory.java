package com.castlelecs.missedbundle.factories;

import com.castlelecs.missedbundle.MissedBundle;
import com.castlelecs.missedbundle.items.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ItemsFactory {

    private final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, MissedBundle.MODID);

    public final void registerItems(IEventBus bus, ItemsType[] itemsTypes) {
        for (ItemsType itemType : itemsTypes) {

            switch (itemType) {
                case BUNDLE:
                    items.register(itemType.rawValue, this::createBundleItem);
                default:
                    break;
            }
        }

        items.register(bus);

        return;
    }

    private final Item createBundleItem() {
        return new BundleItem();
    }
}
