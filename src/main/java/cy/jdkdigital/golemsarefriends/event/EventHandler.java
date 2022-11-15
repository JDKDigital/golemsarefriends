package cy.jdkdigital.golemsarefriends.event;

import cy.jdkdigital.golemsarefriends.GolemsAreFriends;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = GolemsAreFriends.MODID)
public class EventHandler
{
    @SubscribeEvent
    public static void onJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof Villager villager) {
            Brain<Villager> brain = villager.getBrain();
            if (!brain.memories.isEmpty()) {
                brain.sensors.putIfAbsent(GolemsAreFriends.DAMAGED_GOLEM.get(), GolemsAreFriends.DAMAGED_GOLEM.get().create());
                brain.memories.putIfAbsent(GolemsAreFriends.NEAREST_GOLEM.get(), Optional.empty());
            }
        }
    }
}
