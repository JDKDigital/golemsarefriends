package cy.jdkdigital.ididntmeanit.event;

import cy.jdkdigital.ididntmeanit.Config;
import cy.jdkdigital.ididntmeanit.IDidntMeanIt;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IDidntMeanIt.MODID)
public class EventHandler
{
    @SubscribeEvent
    public static void onHurt(LivingAttackEvent event) {
        if (event.getEntity() instanceof Villager) {
            if (event.getSource().getEntity() instanceof Player player) {
                ItemStack heldItem = player.getMainHandItem();
                if (heldItem.equals(ItemStack.EMPTY) || (!Config.COMMON.onlyEmptyHands.get() && !(heldItem.getItem() instanceof TieredItem))) {
                    event.setCanceled(true);
                }
            }
        }
    }
}
