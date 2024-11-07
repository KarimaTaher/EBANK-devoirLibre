package org.example;

import Bank.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Date now=new Date();

        Banque banque1=new Banque(645,"MAROC");
        Banque banque2=new Banque(646,"FRANCE");
        Banque banque3=new Banque(64567,"MAROC");

        Client C1 = new Client(1,"TAHER","Karima","OUed Kbir Marrakech","0689400338","taherkarima@gmail.com");
        Client C2= new Client(2,"ZOUHRI","ALI","TATA","06338478347","zouhriali@gmail.com");
        Client C3= new Client(3,"Toujanie","Ossama","Marrakech","0692892443","toujanie@gmail.com");

        Compte compte1= new Compte("N73467463764567", now, now,"MAD",5000,C1,banque1);
        Compte compte2= new Compte("C27434892645",now,now,"MAD",2000,C2,banque2);
        Compte compte3=new Compte("B83434737645",now,now,"MAD",3000,C3,banque3);


         ArrayList<Compte> comptes= new ArrayList<Compte>();
         comptes.add(compte1);
         comptes.add(compte2);
         comptes.add(compte3);

         //TESTER LE TYPE DE TRANSACTION
        System.out.println("------------VIRMULTA------------");
        Transaction trans= new Transaction("13/10/24",500,comptes);
        TypeTrans matype= trans.getType();
        String ref = trans.getReference();
        System.out.println( "Le type de transaction est : " + matype + " Sa reference est " + ref); //VIRMULTA

        System.out.println("------------------VIRCHAR------------------");

        comptes.remove(compte3);
        trans= new Transaction("13/10/24",500,comptes);
        matype= trans.getType();
         ref= trans.getReference();
        System.out.println("Le type de transaction est : " + matype + " Sa reference est " + ref); //VIRCHAR

        System.out.println("---------------------VIREST------------------");
        comptes.remove(compte2);
        comptes.add(compte3);
        trans= new Transaction("13/10/24",500,comptes);
        matype= trans.getType();
        ref= trans.getReference();
        System.out.println("Le type de transaction est : " + matype + " Sa reference est " + ref); //VIREST

        System.out.println("-------------------VIRIN-----------------------");
        comptes.remove(compte3);
        compte3=new Compte("B83434737645",now,now,"MAD",3000,C3,banque1);
        comptes.add(compte3);
        trans= new Transaction("13/10/24",500,comptes);
        matype= trans.getType();
        ref= trans.getReference();
        System.out.println("Le type de transaction est : " + matype + " Sa reference est " + ref); //VIRIN



        System.out.println("------------SOLDES------------------");
        trans.effectuerTransaction();
        System.out.println( "LE SOLDE COMPTE EMETEUR "+compte1.getSolde());
        System.out.println("LE SOLDE COMPTE RECEPTEUR "+compte2.getSolde());
        System.out.println("LE SOLDE COMPTE RECEPTEUR "+compte3.getSolde());


        System.out.println("-----------DATE DE UPDATE----------------");
        System.out.println( "COMPTE EMETEUR "+compte1.getDateupdate());
        System.out.println("COMPTE RECEPTEUR1 "+compte2.getDateupdate());
        System.out.println("COMPTE RECEPTEUR2 "+compte3.getDateUpdate());




    }
}