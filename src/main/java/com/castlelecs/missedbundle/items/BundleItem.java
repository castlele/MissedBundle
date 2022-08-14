package com.castlelecs.missedbundle.items;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class BundleItem extends Item {

    // MARK: - Init

    public BundleItem() {
        super(new BundleProperties());
    }

    // MARK: - Overrided methods

    @Override
    public boolean overrideStackedOnOther(ItemStack otherStack, Slot slot, ClickAction actionType, Player player) {
        return super.overrideStackedOnOther(otherStack, slot, actionType, player);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack bundleStack,
                                            ItemStack otherStack,
                                            Slot slot,
                                            ClickAction actionType,
                                            Player player,
                                            SlotAccess slotAccess) {

        if (actionType == ClickAction.SECONDARY && otherStack.getItem() != Items.AIR) {
            NetworkHooks.openScreen((ServerPlayer) player, new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return new Component() {
                        @Override
                        public Style getStyle() {
                            return null;
                        }

                        @Override
                        public ComponentContents getContents() {
                            return null;
                        }

                        @Override
                        public List<Component> getSiblings() {
                            return null;
                        }

                        @Override
                        public FormattedCharSequence getVisualOrderText() {
                            return null;
                        }
                    };
                }

                @Nullable
                @Override
                public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
                    return null;
                }
            });
            return true;
        }

        System.out.printf("%s\n", bundleStack.toString());
        System.out.printf("%s\n", otherStack.toString());
        System.out.printf("%s\n", slot.toString());
        System.out.printf("%s\n", slotAccess.toString());
        System.out.print("----------------\n");

        return false;
    }
}
