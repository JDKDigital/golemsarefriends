package cy.jdkdigital.ididntmeanit;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;
    public static final Common COMMON = new Common(BUILDER);

    static {
        CONFIG = BUILDER.build();
    }

    public static class Common
    {
        public final ForgeConfigSpec.BooleanValue onlyEmptyHands;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("Common");

            onlyEmptyHands = builder
                    .comment("Set to true if only empty hands should be ignored. If set to false, only tools and weapons can damage villagers.")
                    .define("onlyEmptyHands", true);

            builder.pop();
        }
    }
}
