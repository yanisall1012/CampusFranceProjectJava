# language: fr
Fonctionnalité: Création de compte sur le portail CampusFrance

  Scénario: Création de compte en tant qu'Étudiant
    Soit je suis sur la page d'inscription de CampusFrance
    Et je charge les données du profil "etudiant"

    Quand je renseigne mon adresse e-mail
    Et je renseigne mon mot de passe
    Et je confirme mon mot de passe

    Et je sélectionne la civilité
    Et je renseigne mon nom
    Et je renseigne mon prénom

    Et je sélectionne mon pays de résidence
    Et je sélectionne mon pays de nationalité
    Et je renseigne mon code postal
    Et je renseigne ma ville
    Et je renseigne mon téléphone

    Et je sélectionne le profil "Étudiants"

    Et je sélectionne le domaine d'études
    Et je sélectionne le niveau d'étude

    Et je soumets le formulaire d'inscription

    Alors mon compte est créé avec succès

  Scénario: Création de compte en tant que Chercheur
    Soit je suis sur la page d'inscription de CampusFrance
    Et je charge les données du profil "chercheur"

    Quand je renseigne mon adresse e-mail
    Et je renseigne mon mot de passe
    Et je confirme mon mot de passe

    Et je sélectionne la civilité
    Et je renseigne mon nom
    Et je renseigne mon prénom

    Et je sélectionne mon pays de résidence
    Et je sélectionne mon pays de nationalité
    Et je renseigne mon code postal
    Et je renseigne ma ville
    Et je renseigne mon téléphone

    Et je sélectionne le profil "Chercheurs"

    Et je sélectionne le domaine d'études
    Et je sélectionne le niveau d'étude

    Et je soumets le formulaire d'inscription

    Alors mon compte est créé avec succès

  Scénario: Création de compte en tant qu'Institutionnel
    Soit je suis sur la page d'inscription de CampusFrance
    Et je charge les données du profil "institutionnel"

    Quand je renseigne mon adresse e-mail
    Et je renseigne mon mot de passe
    Et je confirme mon mot de passe

    Et je sélectionne la civilité
    Et je renseigne mon nom
    Et je renseigne mon prénom

    Et je sélectionne mon pays de résidence
    Et je sélectionne mon pays de nationalité
    Et je renseigne mon code postal
    Et je renseigne ma ville
    Et je renseigne mon téléphone

    Et je sélectionne le profil "Institutionnel"

    Et je renseigne la fonction
    Et je sélectionne le type d'organisme
    Et je renseigne le nom de l'organisme

    Et je soumets le formulaire d'inscription

    Alors mon compte est créé avec succès
