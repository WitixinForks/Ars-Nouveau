package com.hollingsworth.arsnouveau.common.spell.method;

import com.hollingsworth.arsnouveau.GlyphLib;
import com.hollingsworth.arsnouveau.api.spell.AbstractAugment;
import com.hollingsworth.arsnouveau.api.spell.AbstractCastMethod;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.hollingsworth.arsnouveau.api.spell.SpellResolver;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Items;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class MethodUnderfoot extends AbstractCastMethod {
    public static MethodUnderfoot INSTANCE = new MethodUnderfoot();

    private MethodUnderfoot() {
        super(GlyphLib.MethodUnderfootID, "Underfoot");
    }

    @Override
    public void onCast(@Nullable ItemStack stack, LivingEntity caster, Level world, List<AbstractAugment> augments, SpellContext context, SpellResolver resolver) {
        resolver.onResolveEffect(caster.getCommandSenderWorld(), caster, new BlockHitResult(caster.position, Direction.DOWN, caster.blockPosition().below(), true));
        resolver.expendMana(caster);
    }

    @Override
    public void onCastOnBlock(UseOnContext context, List<AbstractAugment> augments, SpellContext spellContext, SpellResolver resolver) {
        LivingEntity caster = context.getPlayer();
        resolver.onResolveEffect(caster.getCommandSenderWorld(), caster, new BlockHitResult(caster.position, Direction.DOWN, caster.blockPosition().below(), true));
        resolver.expendMana(caster);
    }

    @Override
    public void onCastOnBlock(BlockHitResult blockRayTraceResult, LivingEntity caster, List<AbstractAugment> augments, SpellContext spellContext, SpellResolver resolver) {
        resolver.onResolveEffect(caster.getCommandSenderWorld(), caster, new BlockHitResult(caster.position, Direction.DOWN, caster.blockPosition().below(), true));
        resolver.expendMana(caster);
    }

    @Override
    public void onCastOnEntity(@Nullable ItemStack stack, LivingEntity caster, Entity target, InteractionHand hand, List<AbstractAugment> augments, SpellContext spellContext, SpellResolver resolver) {
        resolver.onResolveEffect(caster.getCommandSenderWorld(), caster, new BlockHitResult(caster.position, Direction.DOWN, caster.blockPosition().below(), true));
        resolver.expendMana(caster);
    }

    @Override
    public boolean wouldCastSuccessfully(@Nullable ItemStack stack, LivingEntity playerEntity, Level world, List<AbstractAugment> augments, SpellResolver resolver) {
        return false;
    }

    @Override
    public boolean wouldCastOnBlockSuccessfully(UseOnContext context, List<AbstractAugment> augments, SpellResolver resolver) {
        return false;
    }

    @Override
    public boolean wouldCastOnBlockSuccessfully(BlockHitResult blockRayTraceResult, LivingEntity caster, List<AbstractAugment> augments, SpellResolver resolver) {
        return false;
    }

    @Override
    public boolean wouldCastOnEntitySuccessfully(@Nullable ItemStack stack, LivingEntity caster, Entity target, InteractionHand hand, List<AbstractAugment> augments, SpellResolver resolver) {
        return false;
    }

    @Override
    public int getManaCost() {
        return 5;
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf();
    }

    @Override
    public String getBookDescription() {
        return "Targets the spell on the block beneath the player.";
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return Items.STONE_PRESSURE_PLATE;
    }
}
