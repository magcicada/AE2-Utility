package com.lhy.ae2utility.mixin.jei;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.lhy.ae2utility.jei.JeiRecipeOptionButton;

import mezz.jei.gui.elements.IconButton;
import mezz.jei.gui.recipes.RecipeOptionButtons;

@Mixin(value = RecipeOptionButtons.class, remap = false)
public abstract class RecipeOptionButtonsMixin {
    @Shadow
    @Final
    @Mutable
    private List<IconButton> buttons;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void ae2utility$prependButtons(Runnable onValueChanged, CallbackInfo callbackInfo) {
        List<IconButton> extendedButtons = new ArrayList<>(buttons.size() + 4);
        extendedButtons.add(new IconButton(JeiRecipeOptionButton.create(JeiRecipeOptionButton.Kind.BATCH_PAGE)));
        extendedButtons.add(new IconButton(JeiRecipeOptionButton.create(JeiRecipeOptionButton.Kind.BATCH_CATEGORY)));
        extendedButtons.add(new IconButton(JeiRecipeOptionButton.create(JeiRecipeOptionButton.Kind.ITEM_SUBSTITUTION)));
        extendedButtons.add(new IconButton(JeiRecipeOptionButton.create(JeiRecipeOptionButton.Kind.FLUID_SUBSTITUTION)));
        extendedButtons.addAll(buttons);
        buttons = extendedButtons;
    }
}
