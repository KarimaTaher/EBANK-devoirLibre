import Bank.Client;
import Bank.Compte;
import Bank.Transaction;
import Convertisseur.JsonConverterTransaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class TestConvertisseurTransaction {
    @Test
    public void testConvertisseur_toJson() {
        Date now = new Date();
        Client client= new Client(2,"ZOUHRI","ALI","TATA","06338478347","zouhriali@gmail.com");
        Compte compte = new Compte("C27434892645", now, now, "MAD", 2000, client, null,null);
        ArrayList<Compte> comptes= new ArrayList<>();
        comptes.add(compte);
        Transaction trans= new Transaction("13/10/24",500,comptes);
        ArrayList<Transaction> transactions= new ArrayList<>();
        transactions.add(trans);
        compte = new Compte("C27434892645", now, now, "MAD", 2000, client, transactions,null);
        JsonConverterTransaction jcf = new JsonConverterTransaction();
        String json=jcf.toJson(trans);
        System.out.println(json);
    }

    @Test
    public void testConvertisseur_fromJson() {
        Date now = new Date();
        JsonConverterTransaction jcf = new JsonConverterTransaction();
        String json=String.format(
                "{\n" +
                        "  \"timeStump\": \"13/10/24\",\n" +
                        "  \"reference\": \"DUXOWLMZ\",\n" +
                        "  \"montant\": 500,\n" +
                        "  \"comptes\": [\n" +
                        "    {\n" +
                        "      \"numCompte\": \"C27434892645\",\n" +
                        "      \"dateCreation\": \"%1$tY-%1$tm-%1$td\",\n" +
                        "      \"dateUpdate\": \"%1$tY-%1$tm-%1$td\",\n" +
                        "      \"solde\": 2000.0,\n" +
                        "      \"client_proprietaire\": {\n" +
                        "        \"NumClient\": \"2\",\n" +
                        "        \"Nom\": \"ZOUHRI\",\n" +
                        "        \"Prenom\": \"ALI\",\n" +
                        "        \"Address\": \"TATA\",\n" +
                        "        \"phone\": \"06338478347\",\n" +
                        "        \"email\": \"zouhriali@gmail.com\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}"
                ,now);
        Transaction transaction=jcf.fromJson(json);
        Assert.assertEquals("reference","DUXOWLMZ",transaction.getReference());

    }
}
