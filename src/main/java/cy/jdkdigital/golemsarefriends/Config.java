package cy.jdkdigital.golemsarefriends;

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
        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("Common");

            builder.pop();
        }
    }
}
