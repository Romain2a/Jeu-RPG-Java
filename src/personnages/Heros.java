package personnages;

import objets.Objets;

import javax.crypto.Mac;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Heros {
    int ptDeVie = 15;
    int ptEndurance = 100;
    int ptMagie = 100;
    int niveau = 1;
    int ptExperience = 1;
    int force = 20;
    int intelligence = 12;
    int agilite = 20;
    String arme = "couteau";
    public ArrayList<Objets> inventaire = new ArrayList<>();

    String nom;
    private boolean vivant = true;

    // Constructeur qui permet de créér un personnage qui possède tous les attributs de la classe Heros
    public Heros(String nom) {
        //this.inventaire.add("couteau");
        this.nom = nom;
        Objets potionDeVie = new Objets("Potion de vie", 50, 10);
    }

    public void attaquer(Ennemis mechant) {
        if (this.ptEndurance < (50 - this.agilite)) {
            System.out.println("Tu ne peux pas attaquer, manque Endurance, dégage !! ");
        } else {
            this.ptEndurance -= (50 - this.agilite);
            mechant.ptDeVie -= (int) (this.force * 0.5);
            System.out.println("Attaque réalisée !");
            System.out.println("Vous infligez " + (int) (this.force * 0.5) + " de dégats.");
            System.out.println();
            if (mechant.ptDeVie <= 0) {
                mechant.mourir(this);
            }
        }
    }

    public void lancerUnSort(Ennemis mechant) {
        if (this.ptMagie < (50 - this.intelligence)) {
            System.out.println("Tu ne peux pas attaquer, manque Magie, dégage !!");
        } else {
            mechant.ptDeVie -= (this.intelligence * 0.25);
            this.ptMagie -= (50 - this.intelligence);
            System.out.println("Sors lancé !");
            System.out.println("Vous infligez " + (this.intelligence * 0.25) + " de dégats.");
            if (mechant.ptDeVie <= 0) {
                mechant.mourir(this);
            }
        }
    }

    public void seReposer() {
        this.ptEndurance += 10;
        System.out.println("Un peu de repos... > +10 pts d'endurance");
        if (ptEndurance > 100) {
            ptEndurance = 100;
            System.out.println("Endurance au max");
        }
    }

    public void boirePotionSoin() {
        this.ptDeVie += 20;
        System.out.println("Potion de Soin... > +20 pts");
        if (ptDeVie > 100) {
            ptDeVie = 100;
            System.out.println("Vie au max");
        }
    }

    public void boirePotionMagie() {
        this.ptMagie += 30;
        System.out.println("Potion de Magie... > +30 pts");
        if (ptMagie > 100) {
            ptMagie = 100;
            System.out.println("Magie au max");
        }
    }

    private void monterNiveau() {
        System.out.println("Bravo ! le niveau supérieur est atteint !");
        this.niveau++;
        this.ptDeVie = 100;
        this.ptEndurance = 100;
        this.ptMagie = 100;
        int aleatoire = (int) (Math.random() * 3); // borne entre 0 et 3 pour avoir le 2...
        if (aleatoire == 0) {
            this.force += 1;
            System.out.println("+1 pt de force");
        } else if (aleatoire == 1) {
            this.intelligence += 1;
            System.out.println("+1 pt d'intelligence");
        } else if (aleatoire == 2) {
            this.agilite += 1;
            System.out.println("+1 pt d'agilité");
        }
    }

    public void inventaire() {
        System.out.println("voici mon inventaire : ");
        for (int i = 0; i < this.inventaire.size(); i++) {// i parcours la liste à partir de 0; sur la longueur de la liste et + 1 à chaque argument
            System.out.println("----------------------");
            System.out.println("Objet : " + this.inventaire.get(i).nom);
            System.out.println("Poids : " + this.inventaire.get(i).poids);
            System.out.println("Valeur en Or : " + this.inventaire.get(i).valeurOr);
        }
        System.out.println("");
        System.out.println(" --> L'inventaire contient " + inventaire.size() + " objets");
    }

    public void poidsTotal() {
        int poids = 0;
        for (Objets objets : this.inventaire) {
            poids += objets.poids;
        }
        System.out.println(" --> Le poids de l'inventaire est de " + poids + " kgs.");
    }

    public void gagngerObjet(Objets objet) {
        this.inventaire.add(objet);
    }

    public void tonEtat() {
        System.out.println("Il te reste : ");
        System.out.println(" > " + this.ptDeVie + " pts de Vie et " + this.ptEndurance + " pts d'Endurance.");
        System.out.println(" > " + this.ptMagie + " pts de Magie et " + this.ptExperience + " pts d'Experience.");
        System.out.println(" > Ton niveau est : " + this.niveau + " et ta force de : " + this.force);
        System.out.println(" > Ton intelligence est : " + this.intelligence + " et ton Agilité de " + this.agilite);
    }

    public void Niveau(int nbreExp) {
        if (nbreExp < 0) {
            System.out.println("pas de valeur négative ! ");
        } else {
            this.ptExperience += nbreExp;    // on additionne : nb de pt d'experience actuel + nb de pt gagné
            while (this.ptExperience >= this.niveau * 50) {   // tant que ce nouveau nb est >= au niveau actuel x 50 (niv max pour passer au niv sup)
                this.ptExperience -= this.niveau * 50;    // faire la différence des 2
                this.monterNiveau();    // utiliser la fonction monter de niveau(+1)
                System.out.println("tu es au niveau " + this.niveau + " et il te reste " + ptExperience + " pts d'expérience");
            }
        }
    }

    public void mourir() {
        System.out.println();
        System.out.println("Tu es mort ...");
        System.out.println("Ton ennemi à récupéré tes " + this.ptExperience + " pts d'expérience");
        this.vivant = false;
        reset();
    }

    public void reset() {
        this.ptDeVie = 100;
        this.ptEndurance = 100;
        this.ptMagie = 100;
        this.niveau = 1;
        this.ptExperience = 0;
        this.force = 20;
        this.intelligence = 20;
        this.agilite = 20;
        ArrayList<String> inventaire = new ArrayList<String>();
        this.vivant = true;
    }

    public void combat(Ennemis Macron) {
        System.out.println();
        System.out.println(Macron.nom + " t'attaque");
        while (true) {
            this.attaquer(Macron);
            Macron.attaquer(this);
            if (this.ptEndurance < (50 - this.agilite)) {
                seReposer();
            }
            if (!this.vivant) {
                System.out.println("Macron a gagné !");
                break;
            } else if (!Macron.vivant) {
                System.out.println("Romain a gagné !");
                break;
            }
        }
    }

    public void combatMagie(Ennemis Macron) {
        while (true) {
            this.lancerUnSort(Macron);
            Macron.attaquer(this);
            if (this.ptMagie < (50 - this.intelligence)) {
                boirePotionMagie();
            }
            if (!this.vivant) {
                System.out.println("Macron a gagné !");
                break;
            } else if (!Macron.vivant) {
                System.out.println("Romain a gagné !");
                break;
            }
            if (this.ptDeVie <= 10) {
                boirePotionMagie();
            }

        }
    }
}