package campusfrance.models;

public class RegisterDataRoot {
    private RegisterModel etudiant;
    private RegisterModel chercheur;
    private RegisterModel institutionnel;

    public RegisterModel getEtudiant() { return etudiant; }
    public void setEtudiant(RegisterModel etudiant) { this.etudiant = etudiant; }

    public RegisterModel getChercheur() { return chercheur; }
    public void setChercheur(RegisterModel chercheur) { this.chercheur = chercheur; }

    public RegisterModel getInstitutionnel() { return institutionnel; }
    public void setInstitutionnel(RegisterModel institutionnel) { this.institutionnel = institutionnel; }
}
