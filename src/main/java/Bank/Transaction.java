package Bank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reference")
public class Transaction {
   private TypeTrans type;
   final private String timeStump;
   final  private String reference;
   final  private int montant;

    final private ArrayList<Compte> comptes;
    public Transaction(String timeStump,int montant, ArrayList<Compte> comptes) {
        this.timeStump = timeStump;
        this.reference = generateReference();
        this.montant = montant;
        this.comptes = comptes;
        this.type = CalculateTransaction();
    }

    public TypeTrans getType() {
        return type;
    }
    public String getReference() {
        return reference;
    }
    //generation d'une reference a la transaction
    public String generateReference() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom RANDOM = new SecureRandom();
        StringBuilder reference = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            reference.append(CHARACTERS.charAt(index));
        }
        return reference.toString();
    }

    //Le calcul de type de transaction
    public TypeTrans CalculateTransaction() {
       if(this.comptes.size() == 2){
           Compte c1 = this.comptes.get(0);
           Compte c2 = this.comptes.get(1);
           if(c1.getBanque().getId() == c2.getBanque().getId()){
               this.type =TypeTrans.VIRIN;
           }
           else {
               if (c1.getBanque().getPays().equals(c2.getBanque().getPays())) {
                   this.type = TypeTrans.VIREST;
               }
               else {
                   this.type = TypeTrans.VIRCHAR;
               }

           }
       }
       if(this.comptes.size() > 2){
           this.type =TypeTrans.VIRMULTA;
       }
       return this.type;
    }
   //Verifier le solde
    public boolean verifySold(){
        Compte c1 = this.comptes.getFirst();
        if(c1.getSolde()<this.montant){
            return false;
        }
        return true;
    }
    //effectuer la transaction
    public boolean effectuerTransaction(){
        if(!verifySold()) {
            System.out.println("Fonds insuffisants Pour effectuer le virement!");
            return false;
        }
        switch(this.type){
            case VIRIN:
               return effectuerVirin();

            case VIRCHAR:
              return effectuerVircha();

            case VIREST:
               return  effectuerVirest();

            case VIRMULTA:
               return effectuerVirmulta();

            default:
                System.out.println("Transaction inconnue");
                return false;
        }
    }

    public boolean effectuerVirin() {
          //On PEUT DEJA FAIRE UN TEST EST CE QUE LE COMPTE DESTINATION EXISTE OU NON
              Compte c1 = this.comptes.get(0);
              Compte c2 = this.comptes.get(1);
              c1.setSolde(c1.debiter(this.montant));
              c2.setSolde(c2.crediter(this.montant));
              //modifier les dates de UPDATES DES COMPTES
              Date now=new Date();
              c1.setDateUpdate(now);
              c2.setDateUpdate(now);
        System.out.println("Le Virement a ete bien effectue de " +c1.getClient_proprietaire().getNom() + " avec le numero de compte "
                + c1.getNumCompte()+ " à " + c2.getClient_proprietaire().getNom() + " avec le numero de compte " +
                c2.getNumCompte() + "exactement à "+ this.timeStump +" d'un montant de " +this.montant + c1.getDevise());
          return true;
    }
    public boolean effectuerVircha() {


        return true;
    }
    public boolean effectuerVirest() {
        Compte c1 = this.comptes.get(0);
        Compte c2 = this.comptes.get(1);
        //j'ai fixé les frais d'envoie a 5%
        double fraisEnvoie=10.0;
        double montantFinale= this.montant-fraisEnvoie;
        c1.setSolde(c1.debiter(this.montant));
        c2.setSolde(c2.crediter(montantFinale));
        Date now=new Date();
        c1.setDateUpdate(now);
        c2.setDateUpdate(now);
        System.out.println("Le Virement a ete effectue de " +c1.getClient_proprietaire().getNom() + " avec le numero de compte "
                + c1.getNumCompte()+ " à " + c2.getClient_proprietaire().getNom() + " avec le numero de compte " +
                c2.getNumCompte() + "exactement à "+ this.timeStump +" d'un montant de " +montantFinale + c1.getDevise());

        return true;
    }
    public boolean effectuerVirmulta() {
        //Verification que tous les comptes on la meme banque que emetteur
        for (int i = 1; i <comptes.size()-1 ; i++) {
            if(comptes.get(i).getBanque().getId()!=comptes.get(i+1).getBanque().getId()){
                System.out.println("Tous les bénéficiaires doivent avoir meme banque");
                return false;
            }
        }
        Compte compteEmetteur=comptes.get(0);
        //Emetteur et tous les bénéficiaires ont meme compte
        if(comptes.get(0).getBanque().getId()==comptes.get(1).getBanque().getId()){
            System.out.println("pas de frais d'envoie");
            for (int i = 1; i < comptes.size(); i++) {
                Compte compte=comptes.get(i);
                Date now=new Date();
                compteEmetteur.setSolde(compteEmetteur.debiter(this.montant));
                compte.setSolde(compte.crediter(this.montant));
                compte.setDateUpdate(now);
                compteEmetteur.setDateUpdate(now);

            }
        }
        //le Compte d'Emetteur est different des comptes de bénéficiaires
        else{
            System.out.println(" Comptabiliser les frais d'envoie");
            double fraisEnvoie=10.0;
            for (int i = 1; i < comptes.size(); i++) {
                Compte compte=comptes.get(i);
                Date now=new Date();
                compteEmetteur.setSolde(compteEmetteur.debiter(this.montant));
                compte.setSolde(compte.crediter(this.montant-fraisEnvoie));
                compte.setDateUpdate(now);compteEmetteur.setDateUpdate(now);
            }
        }
        System.out.println("Sucess!!");
        return true;
    }



}

