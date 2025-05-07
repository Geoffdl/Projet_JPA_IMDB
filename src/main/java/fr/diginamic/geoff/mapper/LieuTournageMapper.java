package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.LieuTournageDTO;
import fr.diginamic.geoff.entity.Lieu;

public class LieuTournageMapper implements EntityMapper<LieuTournageDTO, Lieu> {


    @Override
    public Lieu mapToEntity(LieuTournageDTO dto) {
        Lieu lieu = new Lieu();

        lieu.setRegion(dto.getEtatDept().trim());
        lieu.setVille(dto.getVille().trim());
        setLibelle(lieu, dto);

        return lieu;
    }

    private void setLibelle(Lieu lieu, LieuTournageDTO dto) {

        StringBuilder sb = new StringBuilder();
        sb.append(dto.getVille()).append(dto.getEtatDept());

        lieu.setLibelle(sb.toString());
    }
}