import Bank.Banque;
import Bank.Client;
import Bank.Compte;
import Convertisseur.JsonConverterClient;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class TestConvertisseurClient {
    @Test
    public void test_toJson() {
        Date now=new Date();
        Client C1= new Client(2,"ZOUHRI","ALI","TATA","06338478347","zouhriali@gmail.com");
        Compte compte2 = new Compte("C27434892645", now, now, "MAD", 2000, C1, null);
        ArrayList<Compte> comptes = new ArrayList<>();
        comptes.add(compte2);
        Banque banque = new Banque(7363, "Maroc",comptes);
        //Affecter les attributs qui manque aux objects
        compte2 = new Compte("C27434892645", now, now, "MAD", 2000, C1, banque);
        C1= new Client(2,"ZOUHRI","ALI","TATA","06338478347","zouhriali@gmail.com",comptes);

        JsonConverterClient json= new JsonConverterClient();
        String jcf=json.toJson(C1);
        System.out.println(jcf);

        String expected = String.format(
                "{\n" +
                        "  \"NumClient\": 2,\n" +
                        "  \"Nom\": \"ZOUHRI\",\n" +
                        "  \"Prenom\": \"ALI\",\n" +
                        "  \"Address\": \"TATA\",\n" +
                        "  \"phone\": \"06338478347\",\n" +
                        "  \"email\": \"zouhriali@gmail.com\",\n" +
                        "  \"comptes\": [\n" +
                        "    {\n" +
                        "      \"numCompte\": \"C27434892645\",\n" +
                        "      \"dateCreation\": \"%1$tY-%1$tm-%1$td\",\n" +
                        "      \"dateUpdate\": \"%1$tY-%1$tm-%1$td\",\n" +
                        "      \"solde\": 2000.0,\n" +
                        "      \"client_proprietaire\": {\n" +
                        "        \"NumClient\": 2,\n" +
                        "        \"Nom\": \"ZOUHRI\",\n" +
                        "        \"Prenom\": \"ALI\",\n" +
                        "        \"Address\": \"TATA\",\n" +
                        "        \"phone\": \"06338478347\",\n" +
                        "        \"email\": \"zouhriali@gmail.com\",\n" +
                        "        \"comptes\": null\n" +
                        "      },\n" +
                        "      \"transactions\": null,\n" +
                        "      \"banque\": null\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}", now);


        Assert.assertEquals(expected,jcf);
    }


    @Test
    public void test_fromJson(){
        Date now=new Date();
        JsonConverterClient jcf= new JsonConverterClient();
        String json = String.format(
                "{\n" +
                        "  \"NumClient\": 2,\n" +
                        "  \"Nom\": \"ZOUHRI\",\n" +
                        "  \"Prenom\": \"ALI\",\n" +
                        "  \"Address\": \"TATA\",\n" +
                        "  \"phone\": \"06338478347\",\n" +
                        "  \"email\": \"zouhriali@gmail.com\",\n" +
                        "  \"comptes\": [\n" +
                        "    {\n" +
                        "      \"numCompte\": \"C27434892645\",\n" +
                        "      \"dateCreation\": \"%1$tY-%1$tm-%1$td\",\n" +
                        "      \"dateUpdate\": \"%1$tY-%1$tm-%1$td\",\n" +
                        "      \"solde\": 2000.0,\n" +
                        "      \"client_proprietaire\": {\n" +
                        "        \"NumClient\": 2,\n" +
                        "        \"Nom\": \"ZOUHRI\",\n" +
                        "        \"Prenom\": \"ALI\",\n" +
                        "        \"Address\": \"TATA\",\n" +
                        "        \"phone\": \"06338478347\",\n" +
                        "        \"email\": \"zouhriali@gmail.com\",\n" +
                        "        \"comptes\": null\n" +
                        "      },\n" +
                        "      \"transactions\": null,\n" +
                        "      \"banque\": null\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}", now);

        Client c =jcf.fromJson(json);
        Assert.assertEquals("NumClient",2,c.getNumClient());
        Assert.assertEquals("Nom","ZOUHRI",c.getNom());
        Assert.assertEquals("Prenom","ALI",c.getPrenom());
        Assert.assertEquals("Address","TATA",c.getAddress());
        Assert.assertEquals("phone","06338478347",c.getPhone());
        Assert.assertEquals("email","zouhriali@gmail.com",c.getEmail());
    }
}
