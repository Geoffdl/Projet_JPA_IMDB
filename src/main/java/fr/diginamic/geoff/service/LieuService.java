package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.LieuTournageDTO;
import fr.diginamic.geoff.entity.Lieu;

import java.util.List;

public class LieuService implements EntityService<Lieu, LieuTournageDTO> {

    //TODO IMPLEMENTATION
    @Override
    public List<Lieu> createEntityList(List<FilmDTO> filmDTOList) {
        return List.of();
    }

    //TODO IMPLEMENTATION
    @Override
    public List<LieuTournageDTO> getList(List<FilmDTO> filmDTOList) {
        return List.of();
    }


    public List<String> getListLieuNaissance(List<FilmDTO> filmDTOList) {
        return List.of();
    }
}