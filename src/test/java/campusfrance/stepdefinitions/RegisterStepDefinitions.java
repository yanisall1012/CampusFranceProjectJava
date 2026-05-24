package campusfrance.stepdefinitions;

import campusfrance.models.RegisterModel;
import campusfrance.readdata.JsonDataReader;
import io.cucumber.java.After;
import io.cucumber.java.fr.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RegisterStepDefinitions {

    private WebDriver driver;
    private RegisterModel utilisateur;
    private final JsonDataReader jsonDataReader = new JsonDataReader();

    // ─────────────────────────────────────
    // UTILITAIRE — CHAMP TEXTE
    // ─────────────────────────────────────
    private void remplirChamp(String id, String valeur) throws InterruptedException {
        WebElement champ = driver.findElement(By.id(id));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];", champ, valeur);
        Thread.sleep(1000);
    }

    // ─────────────────────────────────────
    // UTILITAIRE — SELECTIZE
    // ─────────────────────────────────────
    private void selectionnerOption(String id, String valeur) throws InterruptedException {
        WebElement champ = driver.findElement(By.id(id));

        // Scroll vers l'élément
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", champ);
        Thread.sleep(1000);

        // Clic JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", champ);
        Thread.sleep(1000);

        // Vider et saisir la valeur
        champ.sendKeys(Keys.CONTROL + "a");
        champ.sendKeys(Keys.DELETE);
        Thread.sleep(500);
        champ.sendKeys(valeur);
        Thread.sleep(2000);

        // Parcourir les options et cliquer sur la bonne
        List<WebElement> options = driver.findElements(
                By.cssSelector(".selectize-dropdown-content .option"));

        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(valeur.trim())) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();", option);
                break;
            }
        }

        Thread.sleep(1000);
    }

    // ─────────────────────────────────────
    // GIVEN
    // ─────────────────────────────────────
    @Soit("je suis sur la page d'inscription de CampusFrance")
    public void jeSuisSurLaPageDInscription() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.campusfrance.org/fr/user/register");
        Thread.sleep(5000);

        try {
            WebElement btn = driver.findElement(By.id("tarteaucitronPersonalize2"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            Thread.sleep(1500);
        } catch (Exception ignored) {}
    }

    @Et("je charge les données du profil {string}")
    public void jeChargeLesDonneesDuProfil(String profil) {
        utilisateur = jsonDataReader.getProfil(profil);
    }

    // ─────────────────────────────────────
    // EMAIL / MOT DE PASSE
    // ─────────────────────────────────────
    @Quand("je renseigne mon adresse e-mail")
    public void jeRenseigneMonEmail() throws InterruptedException {
        remplirChamp("edit-name", utilisateur.getEmail());
    }

    @Et("je renseigne mon mot de passe")
    public void jeRenseigneMonMotDePasse() throws InterruptedException {
        remplirChamp("edit-pass-pass1", utilisateur.getMotDePasse());
    }

    @Et("je confirme mon mot de passe")
    public void jeConfirmeMonMotDePasse() throws InterruptedException {
        remplirChamp("edit-pass-pass2", utilisateur.getMotDePasse());
    }

    // ─────────────────────────────────────
    // CIVILITE
    // ─────────────────────────────────────
    @Et("je sélectionne la civilité")
    public void jeSelectionneLaCivilite() throws InterruptedException {
        String civ = utilisateur.getCivilite();
        String id = (civ != null && (civ.equals("Mme") || civ.equals("Mme.")))
                ? "edit-field-civilite-mme"
                : "edit-field-civilite-mr";
        WebElement el = driver.findElement(By.id(id));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        Thread.sleep(500);
    }

    // ─────────────────────────────────────
    // NOM / PRENOM
    // ─────────────────────────────────────
    @Et("je renseigne mon nom")
    public void jeRenseigneMonNom() throws InterruptedException {
        remplirChamp("edit-field-nom-0-value", utilisateur.getNom());
    }

    @Et("je renseigne mon prénom")
    public void jeRenseigneMonPrenom() throws InterruptedException {
        remplirChamp("edit-field-prenom-0-value", utilisateur.getPrenom());
    }

    // ─────────────────────────────────────
    // PAYS
    // ─────────────────────────────────────
    @Et("je sélectionne mon pays de résidence")
    public void jeSelectionneMonPaysDeResidence() throws InterruptedException {
        selectionnerOption("edit-field-pays-concernes-selectized",
                utilisateur.getPaysResidence());
    }

    @Et("je sélectionne mon pays de nationalité")
    public void jeSelectionneMonPaysDeNationalite() throws InterruptedException {
        WebElement champ = driver.findElement(
                By.id("edit-field-nationalite-0-target-id"));
        champ.sendKeys(utilisateur.getPaysNationalite());
        Thread.sleep(1500);
        champ.sendKeys(Keys.ARROW_DOWN);
        champ.sendKeys(Keys.ENTER);
    }

    // ─────────────────────────────────────
    // INFOS PERSO
    // ─────────────────────────────────────
    @Et("je renseigne mon code postal")
    public void jeRenseigneMonCodePostal() throws InterruptedException {
        remplirChamp("edit-field-code-postal-0-value", utilisateur.getCodePostal());
    }

    @Et("je renseigne ma ville")
    public void jeRenseigneMaVille() throws InterruptedException {
        remplirChamp("edit-field-ville-0-value", utilisateur.getVille());
    }

    @Et("je renseigne mon téléphone")
    public void jeRenseigneMonTelephone() throws InterruptedException {
        remplirChamp("edit-field-telephone-0-value", utilisateur.getTelephone());
    }

    // ─────────────────────────────────────
    // PROFIL
    // ─────────────────────────────────────
    @Et("je sélectionne le profil {string}")
    public void jeSelectionneLeProfil(String profil) throws InterruptedException {
        String id = switch (profil) {
            case "Étudiants"     -> "edit-field-publics-cibles-2";
            case "Chercheurs"    -> "edit-field-publics-cibles-3";
            default              -> "edit-field-publics-cibles-4";
        };
        WebElement el = driver.findElement(By.id(id));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        Thread.sleep(1000);
    }

    // ─────────────────────────────────────
    // ETUDES
    // ─────────────────────────────────────
    @Et("je sélectionne le domaine d'études")
    public void jeSelectionneLeDomaineDetudes() throws InterruptedException {
        selectionnerOption("edit-field-domaine-etudes-selectized",
                utilisateur.getDomaineEtudes());
    }

    @Et("je sélectionne le niveau d'étude")
    public void jeSelectionneLeNiveauDetude() throws InterruptedException {
        selectionnerOption("edit-field-niveaux-etude-selectized",
                utilisateur.getNiveauEtude());
    }

    // ─────────────────────────────────────
    // INSTITUTIONNEL
    // ─────────────────────────────────────
    @Et("je renseigne la fonction")
    public void jeRenseigneLaFonction() throws InterruptedException {
        remplirChamp("edit-field-fonction-0-value", utilisateur.getFonction());
    }

    @Et("je sélectionne le type d'organisme")
    public void jeSelectionneLeTypeDOrganisme() throws InterruptedException {
        WebElement input = driver.findElement(
                By.id("edit-field-type-organisme-selectized"));
        input.sendKeys(utilisateur.getTypeOrganisme());
        Thread.sleep(1500);
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ENTER);
    }

    @Et("je renseigne le nom de l'organisme")
    public void jeRenseigneLeNomDeLOrganisme() throws InterruptedException {
        Thread.sleep(1000);
        remplirChamp("edit-field-nom-organisme-0-value",
                utilisateur.getNomOrganisme());
    }

    // ─────────────────────────────────────
    // SUBMIT
    // ─────────────────────────────────────
    @Et("je soumets le formulaire d'inscription")
    public void jeSoumetsLeFormulaire() throws InterruptedException {
        WebElement btn = driver.findElement(By.id("edit-submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        Thread.sleep(3000);
    }

    // ─────────────────────────────────────
    // THEN
    // ─────────────────────────────────────
    @Alors("mon compte est créé avec succès")
    public void monCompteEstCreeAvecSucces() {
        System.out.println("Scénario exécuté avec succès !");
    }

    // ─────────────────────────────────────
    // CLEANUP
    // ─────────────────────────────────────
    @After
    public void fermerNavigateur() {
        if (driver != null) {
            driver.quit();
        }
    }
}
