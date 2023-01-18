package fr.ot.tools;

import fr.ot.entities.AdresseEntity;
import fr.ot.entities.EcoleEntity;
import fr.ot.entities.VilleEntity;
import fr.ot.repository.AdresseRepository;
import fr.ot.repository.VilleRepository;

import java.util.List;

public class EcoleTools {

    private EcoleTools(){}

    public static VilleEntity getVilleOfEcole(EcoleEntity ecole) {
        AdresseRepository adresseRepository = new AdresseRepository();
        VilleRepository villeRepository = new VilleRepository();
        AdresseEntity adresse = adresseRepository.findById(ecole.getIdAdresse());
        return villeRepository.findById(adresse.getIdVille());
    }

    public static EcoleEntity getClosestFromCoordinates(List<EcoleEntity> ecoleEntities, double latitudeA, double longitudeA) {
        System.out.println("getClosestFromCoordinates 1");
        double closestDistance = Double.MAX_VALUE;
        EcoleEntity closestEcole = new EcoleEntity();
        System.out.println("getClosestFromCoordinates 2");
        for (EcoleEntity ecole : ecoleEntities) {
            VilleEntity ville = getVilleOfEcole(ecole);
            double distance = Maths.getDistanceBetweentwoCoordinates(latitudeA, longitudeA, ville.getLatitude(), ville.getLongitude());
            if (distance < closestDistance) {
                closestDistance = distance;
                closestEcole = ecole;
            }
        }
        System.out.println("getClosestFromCoordinates 3");
        return closestEcole;
    }
}
