package cy.jdkdigital.golemsarefriends.ai.behavior;

import com.google.common.collect.ImmutableMap;
import cy.jdkdigital.golemsarefriends.GolemsAreFriends;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.npc.Villager;

public class RepairGolem extends Behavior<Villager>
{
    public RepairGolem() {
        super(ImmutableMap.of(GolemsAreFriends.NEAREST_GOLEM.get(), MemoryStatus.VALUE_PRESENT));
    }

    protected boolean checkExtraStartConditions(ServerLevel level, Villager villager) {
        boolean start = BehaviorUtils.targetIsValid(villager.getBrain(), GolemsAreFriends.NEAREST_GOLEM.get(), (entity) -> entity instanceof AbstractGolem && entity.getHealth() != entity.getMaxHealth());
        return start;
    }

    protected boolean canStillUse(ServerLevel level, Villager villager, long p_24421_) {
        return this.checkExtraStartConditions(level, villager);
    }

    protected void start(ServerLevel level, Villager villager, long p_24439_) {
        LivingEntity golem = villager.getBrain().getMemory(GolemsAreFriends.NEAREST_GOLEM.get()).get();
        BehaviorUtils.setWalkAndLookTargetMemories(villager, golem, 1.0F, 0);
    }

    protected void tick(ServerLevel level, Villager villager, long p_24447_) {
        LivingEntity entity = villager.getBrain().getMemory(GolemsAreFriends.NEAREST_GOLEM.get()).get();
        if (!(villager.distanceToSqr(entity) > 3.0D) && entity instanceof AbstractGolem golem) {
            BehaviorUtils.lockGazeAndWalkToEachOther(villager, golem, 0.5F);
            float f = golem.getHealth();
            golem.heal(25.0F);

            if (golem.getHealth() != f) {
                float f1 = 1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F;
                golem.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, f1);
            }

            if (golem.getHealth() == golem.getMaxHealth()) {
                villager.getBrain().eraseMemory(GolemsAreFriends.NEAREST_GOLEM.get());
            }
        }
    }
}
