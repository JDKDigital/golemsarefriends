package cy.jdkdigital.golemsarefriends.ai.sensing;

import com.google.common.collect.ImmutableSet;
import cy.jdkdigital.golemsarefriends.GolemsAreFriends;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.animal.AbstractGolem;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DamagedGolemSensor extends Sensor<LivingEntity>
{
    public DamagedGolemSensor() {
        this(241);
    }

    public DamagedGolemSensor(int scanRate) {
        super(scanRate);
    }

    protected void doTick(ServerLevel level, LivingEntity villager) {
        checkForNearbyGolem(villager);
    }

    public Set<MemoryModuleType<?>> requires() {
        return ImmutableSet.of(MemoryModuleType.NEAREST_LIVING_ENTITIES);
    }

    public static void checkForNearbyGolem(LivingEntity villager) {
        Optional<List<LivingEntity>> optional = villager.getBrain().getMemory(MemoryModuleType.NEAREST_LIVING_ENTITIES);
        if (optional.isPresent()) {
            Optional<LivingEntity> flag = optional.get().stream().filter((e) -> e instanceof AbstractGolem).findFirst();
            if (flag.isPresent() && flag.get().getHealth() < flag.get().getMaxHealth()) {
                golemDetected(villager, flag.get());
            }
        }
    }

    public static void golemDetected(LivingEntity villager, LivingEntity golem) {
        villager.getBrain().setMemoryWithExpiry(GolemsAreFriends.NEAREST_GOLEM.get(), golem, 600L);
    }
}
