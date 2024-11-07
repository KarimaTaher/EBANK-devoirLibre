package Bank;
import java.util.ArrayList;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numCompte")
public class Compte {
    private String numCompte;
    private Date dateCreation;
    private Date dateUpdate;
    private static String devise;
    private double solde;
    final private Client client_proprietaire;
    private ArrayList<Transaction> transactions;
    private Banque banque;
    //constructeur
    public Compte(String numCompte,Date datecreation,Date dateupdate,String devise,double solde,Client client_proprietaire,ArrayList<Transaction> transactions,Banque banque) {
        this.numCompte = numCompte;
        this.dateCreation = datecreation;
        this.dateUpdate = dateupdate;
        this.devise = devise;
        this.solde = solde;
        this.client_proprietaire = client_proprietaire;
        this.transactions = transactions;
        this.banque = banque;
    }
    public Compte(String numCompte,Date datecreation,Date dateupdate,String devise,double solde,Client client_proprietaire,Banque banque) {
        this.numCompte = numCompte;
        this.dateCreation = datecreation;
        this.dateUpdate = dateupdate;
        this.devise = devise;
        this.solde = solde;
        this.client_proprietaire = client_proprietaire;
        this.banque = banque;
    }
    //les getters et les setters
    public String getNumCompte() {
        return numCompte;
    }
    public void setNumCompte(String numCompte) {
        this.numCompte=numCompte;
    }
    public Date getDatecreation() {
        return this.dateCreation;
    }
    public void setDatecreation(Date datecreation) {
        this.dateCreation = datecreation;
    }
    public Date getDateupdate() {
        return this.dateUpdate;
    }
    public void setDateupdate(Date dateupdate) {
        this.dateUpdate = dateupdate;
    }
    public String getDevise() {
        return this.devise;
    }
    public void setDevise(String devise) {
        this.devise = devise;
    }
    public double getSolde() {
        return this.solde;
    }
    public void setSolde(double solde) {
        this.solde = solde;
    }
    public Client getClient_proprietaire() {
        return this.client_proprietaire;
    }

    public Banque getBanque() {
        return this.banque;
    }
    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    //methode d'ajout et retirer argent de compte
    double crediter(double montant){
        return this.solde + montant;
    }
    double debiter(double montant){
        return this.solde - montant;
    }

}
