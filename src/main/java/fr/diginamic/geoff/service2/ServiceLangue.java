package fr.diginamic.geoff.service2;

import fr.diginamic.geoff.dao.LangueDao;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Langue;
import fr.diginamic.geoff.mapper.LangueMapper;

import java.util.Optional;

public class ServiceLangue {
    private final LangueMapper langueMapper = new LangueMapper();
    private final LangueDao langueDao;

    public ServiceLangue(LangueDao langueDao) {
        this.langueDao = langueDao;
    }

    public Langue getOrCreateFromFilmDTO(FilmDTO filmDTO) {
        if (filmDTO == null && filmDTO.getLangue() == null) {
            return null;
        }

        Optional<Langue> existingLangue = langueDao.findByNom(filmDTO.getLangue());
        if (existingLangue.isPresent()) {
            return existingLangue.get();
        }

        Langue newLangue = langueMapper.mapToEntity(filmDTO.getLangue());

        return langueDao.create(newLangue);

    }

}