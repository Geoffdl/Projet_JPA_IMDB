package fr.diginamic.geoff;

import fr.diginamic.geoff.service.BaseEntityCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static final String JSONURL = "data/films.json";

    public static void main(String[] args) {


        BaseEntityCreationService creationService = new BaseEntityCreationService();
        creationService.generateBaseEntityObjects();



    /*
        try {
            JsonParser parser = new JsonParser();
            List<FilmDTO> films = parser.tryReading(FilmDTO.class, JSONURL);

            EntityMapper<FilmDTO, Film> filmMapper = new FilmMapper();

            List<FilmDTO> filmsWoDuplicates = DTOUtils.removeDuplicatesByNaturalId(films);


            AtomicInteger line = new AtomicInteger();
            filmsWoDuplicates.forEach(dto -> {
                EntityManager em = emf.createEntityManager();
                EntityTransaction transaction = em.getTransaction();
                try {
                    transaction.begin();
                    em.persist(filmMapper.mapToEntity(dto));
                    transaction.commit();
                } catch (Exception e) {
                    LOGGER.warn("Error at line {}", line.get());
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                } finally {
                    em.close();
                }
                line.getAndIncrement();
            });
            emf.close();


        } catch (IOException e) {

        }

*/
    }
}