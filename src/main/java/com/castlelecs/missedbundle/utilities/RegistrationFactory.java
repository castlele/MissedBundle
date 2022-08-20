package com.castlelecs.missedbundle.utilities;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public abstract class RegistrationFactory<Type> {

    public void register(IEventBus bus, DeferredRegister<Type> register, RegistryType<Type>[] types) {
        for (var type : types)
            register.register(type.getRawValue(), type::getItem);

        register.register(bus);

        return;
    }
}
