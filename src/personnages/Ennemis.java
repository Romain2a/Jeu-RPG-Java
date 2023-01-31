package personnages;

import objets.Objets;

public class Ennemis {
    int ptDeVie = 20;
    int valAttaqueMax = 10;
    int ptExperience = 55;
    public boolean vivant = true;
    String nom;

    public Objets loot = new Objets("épée", 25, 8);

    // Constructeur Ennemis
    public Ennemis(String nom) {
        this.nom = nom;
    }

    public void mourir(Heros Romain) {
        System.out.println();
        System.out.println("l'ennemi is dead...");
        System.out.println("tu récupères ces " + this.ptExperience + " pts d'expérience");
        this.vivant = false;
        Romain.Niveau(this.ptExperience);
        Romain.gagngerObjet(this.loot);
        this.ptExperience = 0;
        this.ptDeVie = 0;
    }

    public void etatEnnemi() {
        System.out.println("Etat de l'ennemi : ");
        if (this.ptDeVie == 0) {
            System.out.println("Massacré, en mort cérébrale...");
        }
        System.out.println(" > " + this.ptDeVie + " pts de Vie.");
        System.out.println(" > " + this.ptExperience + " pts d'Experience.");
    }

    public void attaquer(Heros Romain) {
        // if (this.vivant) {
        System.out.println("Attention, attaque ennemi ! ");
        int aleatoire = (int) (Math.random() * 11); // 11 exclu ! si valAttaqueMax est 10
        Romain.ptDeVie -= aleatoire;
        if (Romain.ptDeVie <= 0) {
            Romain.mourir();
        }
        //} else {
        //    System.out.println("Romain est mort");
        //}
    }
}

