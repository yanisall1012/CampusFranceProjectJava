package campusfrance.readdata;

import campusfrance.models.RegisterDataRoot;
import campusfrance.models.RegisterModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonDataReader {

    private final RegisterDataRoot data;

    public JsonDataReader() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Le fichier est dans src/test/resources/testdata/RegisterData.json
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("testdata/RegisterData.json");

            if (is == null) {
                throw new RuntimeException("Fichier RegisterData.json introuvable dans les ressources !");
            }

            data = mapper.readValue(is, RegisterDataRoot.class);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la lecture de RegisterData.json", e);
        }
    }

    public RegisterModel getProfil(String profil) {
        return switch (profil.toLowerCase()) {
            case "etudiant"       -> data.getEtudiant();
            case "chercheur"      -> data.getChercheur();
            case "institutionnel" -> data.getInstitutionnel();
            default -> throw new IllegalArgumentException("Profil inconnu : " + profil);
        };
    }
}
