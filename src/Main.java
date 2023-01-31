import objets.Objets;
import personnages.Ennemis;
import personnages.Heros;

import javax.crypto.Mac;

public class Main {
    public static void main(String[] args) {
        Heros Romain = new Heros("Romain");
        Ennemis Macron = new Ennemis("Macron");
        Ennemis Darmanin = new Ennemis("Darmanin");
        Ennemis Veyran = new Ennemis("Veyran");
        Objets hache = new Objets("hache", 10, 5);
        Objets batte = new Objets("batte", 4, 2);
        Objets massue = new Objets("massue", 6, 7);
        Objets couteau = new Objets("couteau", 8, 1);

        Romain.tonEtat();
        Darmanin.etatEnnemi();
        Romain.combat(Veyran);
        Romain.seReposer();
        Romain.tonEtat();
        Veyran.etatEnnemi();
        Romain.combatMagie(Darmanin);
        Romain.seReposer();

        Romain.inventaire.add(couteau);
        Romain.inventaire.add(massue);
        Romain.inventaire.add(batte);
        Romain.inventaire.add(hache);
        Romain.inventaire();
        Romain.poidsTotal();

    }

}