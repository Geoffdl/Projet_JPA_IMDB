package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dao.LangueDAO;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Langue;
import fr.diginamic.geoff.mapper.LangueMapper;

import java.util.Optional;

public class ServiceLangue {
    private final LangueMapper langueMapper = new LangueMapper();
    private final LangueDAO langueDao;

    public ServiceLangue(LangueDAO langueDao) {
        this.langueDao = langueDao;
    }

    /**
     *
     * @param filmDTO
     * @return
     */
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