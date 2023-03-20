package cy.jdkdigital.ididntmeanit;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(IDidntMeanIt.MODID)
public class IDidntMeanIt
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String MODID = "ididntmeanit";

    public IDidntMeanIt()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG);
    }
}
