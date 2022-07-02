package com.hollingsworth.arsnouveau.common.datagen;

import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.common.items.FamiliarScript;
import com.hollingsworth.arsnouveau.common.items.Glyph;
import com.hollingsworth.arsnouveau.common.items.RitualTablet;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class LangDatagen extends LanguageProvider {
    public LangDatagen(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {
        ArsNouveauAPI arsNouveauAPI = ArsNouveauAPI.getInstance();
        for (Supplier<Glyph> supplier : arsNouveauAPI.getGlyphItemMap().values()) {
            Glyph i = supplier.get();
            add("ars_nouveau.glyph_desc." + i.spellPart.getRegistryName().getPath(), i.spellPart.getBookDescription());
            add("ars_nouveau.glyph_name." + i.spellPart.getRegistryName().getPath(), i.spellPart.getName());
        }
        for (FamiliarScript i : arsNouveauAPI.getFamiliarScriptMap().values()) {
            add("ars_nouveau.familiar_desc." + i.familiar.getRegistryName().getPath(), i.familiar.getBookDescription());
            add("ars_nouveau.familiar_name." + i.familiar.getRegistryName().getPath(), i.familiar.getBookName());
            add("item.ars_nouveau." + i.familiar.getRegistryName().getPath(), ".script_name. " + i.familiar.getBookName());
        }
        for (RitualTablet i : arsNouveauAPI.getRitualItemMap().values()) {
            add("ars_nouveau.ritual_desc." + i.ritual.getRegistryName().getPath(), i.ritual.getLangDescription());
            add("item.ars_nouveau." + i.ritual.getRegistryName().getPath(), ".ritual_name." + i.ritual.getLangName());
        }
    }
}
