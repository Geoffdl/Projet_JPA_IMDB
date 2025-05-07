package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.entity.Langue;

public class LangueMapper implements EntityMapper<String, Langue> {
    @Override
    public Langue mapToEntity(String langueName) {
        Langue langue = new Langue();
        langue.setNom(langueName);
        return langue;
    }
}