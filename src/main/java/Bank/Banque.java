package Bank;

import java.util.ArrayList;

public class Banque {
    private int id;
    private String pays;
    private ArrayList<Compte> comptes;

    public Banque(int id, String pays) {
        this.id = id;
        this.pays = pays;
    }
    public Banque(int id, String pays,ArrayList<Compte> comptes) {
        this.id = id;
        this.pays = pays;
        this.comptes = comptes;
    }
    public Banque(){}

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPays() {
        return this.pays;
    }
    public void setPays(String pays) {
        this.pays = pays;
    }
    public ArrayList<Compte> getComptes() {
        return this.comptes;
    }
}


