package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.NaissanceDTO;
import fr.diginamic.geoff.entity.Lieu;
import fr.diginamic.geoff.utils.StringUtils;

public class LieuNaissanceMapper implements EntityMapper<NaissanceDTO, Lieu> {


    @Override
    public Lieu mapToEntity(NaissanceDTO dto) {
        Lieu lieuNaissance = new Lieu();

        // TODO implement proper handling depending on the data received typical : "city", "region", "country" // can also be "city", "country"
        splitLieuIntoArrayOfString result = getSplitLieuIntoArrayOfString(dto);

        lieuNaissance.setRegion(result.region());
        lieuNaissance.setVille(result.ville());

        return lieuNaissance;
    }

    private static splitLieuIntoArrayOfString getSplitLieuIntoArrayOfString(NaissanceDTO dto) {
        String[] adresseArray = StringUtils.stringToArrayOfStrings(dto.getLieuNaissance(), ",");
        String region = "";
        String ville = "";

        if(adresseArray.length == 2) {
             region = adresseArray[1];
             ville = adresseArray[0];
        }
        if(adresseArray.length >2){
            region = "";
            ville = adresseArray[0];
        }
        splitLieuIntoArrayOfString result = new splitLieuIntoArrayOfString(region, ville);
        return result;
    }

    private record splitLieuIntoArrayOfString(String region, String ville) {
    }
}