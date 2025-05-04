package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Acteur;
import fr.diginamic.geoff.parser.JsonParser;

import java.io.IOException;
import java.util.List;

import static fr.diginamic.geoff.App.JSONURL;

public class BaseEntityCreationService {
    JsonParser parser = new JsonParser();


    public void generateBaseEntityObjects(){

        List<FilmDTO> baseDtoList = null;
        try {
             baseDtoList = parser.tryReading(FilmDTO.class, JSONURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ActeurService acteurService = new ActeurService();
        List<Acteur> acteurList = acteurService.createEntities(baseDtoList);
    }

}