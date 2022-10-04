package cy.jdkdigital.golemsarefriends.mixin;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import cy.jdkdigital.golemsarefriends.ai.behavior.RepairGolem;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.VillagerGoalPackages;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Debug(export = true)
@Mixin(value = VillagerGoalPackages.class)
public class MixinVillagerGoalPackages
{
    @Inject(method = ("getWorkPackage"), at = @At("RETURN"))
    private static ImmutableList<Pair<Integer, ? extends Behavior<? super Villager>>> getWorkPackage(VillagerProfession profession, float p_24591_, CallbackInfoReturnable<ImmutableList<Pair<Integer, ? extends Behavior<? super Villager>>>> ci) {
        List<Pair<Integer, ? extends Behavior<? super Villager>>> copy = new ArrayList<>(ci.getReturnValue());
        if (profession.equals(VillagerProfession.ARMORER) || profession.equals(VillagerProfession.TOOLSMITH) || profession.equals(VillagerProfession.WEAPONSMITH)) {
            copy.add(Pair.of(6, new RepairGolem()));
        }
        return ImmutableList.copyOf(copy);
    }
}
