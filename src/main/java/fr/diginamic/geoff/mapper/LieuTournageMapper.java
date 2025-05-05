package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.LieuTournageDTO;
import fr.diginamic.geoff.entity.Lieu;

public class LieuTournageMapper implements EntityMapper<LieuTournageDTO, Lieu> {


    @Override
    public Lieu mapToEntity(LieuTournageDTO dto) {
        Lieu lieu = new Lieu();

        lieu.setRegion(dto.getEtatDept().trim());
        lieu.setVille(dto.getVille().trim());

        return lieu;
    }
}