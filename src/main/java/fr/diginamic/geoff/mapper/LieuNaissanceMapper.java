package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.NaissanceDTO;
import fr.diginamic.geoff.entity.Lieu;
import fr.diginamic.geoff.utils.LieuUtils;

public class LieuNaissanceMapper implements EntityMapper<NaissanceDTO, Lieu> {


    @Override
    public Lieu mapToEntity(NaissanceDTO dto) {
        Lieu lieu = new Lieu();

        lieu.setVille(extractLieuVille(dto));
        lieu.setRegion(extractLieuRegion(dto));
        lieu.setLibelle(extractLibelle(dto));

        return lieu;
    }


    //TODO Appropriate util methods to extract the corresponding data from the LieuxNaissance String in the raw data
    private String extractLieuVille(NaissanceDTO dto){
        return LieuUtils.extractLieuVille(dto);

    }
    private String extractLieuRegion(NaissanceDTO dto){
        return LieuUtils.extractLieuRegion(dto);
    }
    private String extractLibelle(NaissanceDTO dto){
        return LieuUtils.extractLibelle(dto);
    }
}