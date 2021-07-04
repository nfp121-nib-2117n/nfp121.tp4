package question3;

import question3.tp3.PileI;
import question3.tp3.PileVideException;
import question3.tp3.PilePleineException;

public class PileModele<T> extends  java.util.Observable implements PileI<T> {

    private PileI<T> pile;

    public PileModele(PileI<T> pile) {
        this.pile = pile;
    }

    public void empiler(T o) throws PilePleineException {
      pile.empiler(o);
      setChanged();
      notifyObservers(o);
    }

    public T depiler() throws PileVideException {
        T object = pile.depiler();
        setChanged();
        notifyObservers(object);
        return object;
    }

    public T sommet() throws PileVideException {
        return pile.sommet();
    }

    public int taille() {
        return pile.taille();
    }

    public int capacite() {
        return pile.capacite();
    }

    public boolean estVide() {
        return pile.estVide();
    }

    public boolean estPleine() {
        return pile.estPleine();
    }

    public String toString() {
        return pile.toString();
    }
}