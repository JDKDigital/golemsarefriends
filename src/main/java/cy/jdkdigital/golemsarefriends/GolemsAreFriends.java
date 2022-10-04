package cy.jdkdigital.golemsarefriends;

import com.mojang.logging.LogUtils;
import cy.jdkdigital.golemsarefriends.ai.sensing.DamagedGolemSensor;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.Optional;

@Mod(GolemsAreFriends.MODID)
public class GolemsAreFriends
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String MODID = "golemsarefriends";

    public static final DeferredRegister<MemoryModuleType<?>> MEMORY_MODULES = DeferredRegister.create(ForgeRegistries.MEMORY_MODULE_TYPES, MODID);
    public static final DeferredRegister<SensorType<?>> SENSOR_TYPES = DeferredRegister.create(ForgeRegistries.SENSOR_TYPES, MODID);

    public static final RegistryObject<MemoryModuleType<LivingEntity>> NEAREST_GOLEM = MEMORY_MODULES.register("nearest_golem", () -> new MemoryModuleType<>(Optional.empty()));
    public static final RegistryObject<SensorType<DamagedGolemSensor>> DAMAGED_GOLEM = SENSOR_TYPES.register("damaged_golem", () -> new SensorType<>(DamagedGolemSensor::new));

    public GolemsAreFriends()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MEMORY_MODULES.register(modEventBus);
        SENSOR_TYPES.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG);
    }
}
