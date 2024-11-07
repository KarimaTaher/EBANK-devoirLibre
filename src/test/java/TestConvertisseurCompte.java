import Bank.Client;
import Bank.Compte;
import Bank.Transaction;
import Convertisseur.JsonConverterBanque;
import Convertisseur.JsonConverterCompte;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class TestConvertisseurCompte {


    @Test
    public void testConvertisseurCompte() {
        Date now = new Date();
        Client client= new Client(2,"ZOUHRI","ALI","TATA","06338478347","zouhriali@gmail.com");
        Compte compte = new Compte("C27434892645", now, now, "MAD", 2000, client, null,null);
        ArrayList<Compte> comptes= new ArrayList<>();
        comptes.add(compte);
        Transaction trans= new Transaction("13/10/24",500,comptes);
        ArrayList<Transaction> transactions= new ArrayList<>();
        transactions.add(trans);

        compte = new Compte("C27434892645", now, now, "MAD", 2000, client, transactions,null);

        JsonConverterCompte converter= new JsonConverterCompte();
        String json=converter.toJson(compte);
        System.out.println(json);

    }


    @Test
    public void testConvertisseur_fromJson(){
        Date now = new Date();
        String json= String.format(
                "{\n" +
                        "  \"numCompte\": \"C27434892645\",\n" +
                        "  \"dateCreation\": \"%1$tY-%1$tm-%1$td\",\n" +
                        "  \"dateUpdate\": \"%1$tY-%1$tm-%1$td\",\n" +
                        "  \"solde\": 2000.0,\n" +
                        "  \"client_proprietaire\": {\n" +
                        "    \"NumClient\": \"2\",\n" +
                        "    \"Nom\": \"ZOUHRI\",\n" +
                        "    \"Prenom\": \"ALI\",\n" +
                        "    \"Address\": \"TATA\",\n" +
                        "    \"phone\": \"06338478347\",\n" +
                        "    \"email\": \"zouhriali@gmail.com\"\n" +
                        "  },\n" +
                        "  \"transactions\": [\n" +
                        "    {\n" +
                        "      \"timeStump\": \"13/10/24\",\n" +
                        "      \"reference\": \"lJVwengz\",\n" +
                        "      \"montant\": 500,\n" +
                        "      \"comptes\": [\n" +
                        "        {\n" +
                        "          \"numCompte\": \"C27434892645\",\n" +
                        "          \"dateCreation\": \"%1$tY-%1$tm-%1$td\",\n" +
                        "          \"dateUpdate\": \"%1$tY-%1$tm-%1$td\",\n" +
                        "          \"solde\": 2000.0,\n" +
                        "          \"client_proprietaire\": {\n" +
                        "            \"NumClient\": \"2\",\n" +
                        "            \"Nom\": \"ZOUHRI\",\n" +
                        "            \"Prenom\": \"ALI\",\n" +
                        "            \"Address\": \"TATA\",\n" +
                        "            \"phone\": \"06338478347\",\n" +
                        "            \"email\": \"zouhriali@gmail.com\"\n" +
                        "          }\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}"
                ,now);
        JsonConverterCompte converter= new JsonConverterCompte();
        Compte compte = converter.fromJson(json);
        Assert.assertEquals("numCompte","C27434892645", compte.getNumCompte());
    }
}
