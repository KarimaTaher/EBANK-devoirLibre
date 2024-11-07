import Bank.Banque;
import Bank.Client;
import Bank.Compte;
import Convertisseur.JsonConverterBanque;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestConvertisseurBanque {

    @Test
    public void test_toJson() {
        Date now=new Date();
        Client C2= new Client(2,"ZOUHRI","ALI","TATA","06338478347","zouhriali@gmail.com");
        Compte compte2 = new Compte("C27434892645", now, now, "MAD", 2000, C2, null);
        ArrayList<Compte> comptes = new ArrayList<>();
        comptes.add(compte2);
        Banque banque = new Banque(7363, "Maroc",comptes);
        //Affecter les attributs qui manque aux objects
        compte2 = new Compte("C27434892645", now, now, "MAD", 2000, C2, banque);
        C2= new Client(2,"ZOUHRI","ALI","TATA","06338478347","zouhriali@gmail.com",comptes);

        JsonConverterBanque json=new JsonConverterBanque();
        String result=json.toJson(banque);
        System.out.println(result);
        String expectedJson = String.format(
                "{\n" +
                        "  \"id\": 7363,\n" +
                        "  \"pays\": \"Maroc\",\n" +
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
        Assert.assertEquals(expectedJson,result);
    }
    @Test
    public void test_fromJson() {
        Date now=new Date();
        String json= String.format(
                "{\n" +
                        "  \"id\": 7363,\n" +
                        "  \"pays\": \"Maroc\",\n" +
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
        JsonConverterBanque jcf=new JsonConverterBanque();
        Banque result=jcf.fromJson(json);
        Assert.assertEquals("id",7363,result.getId());
        Assert.assertEquals("pays","Maroc",result.getPays());
        String comptes=String.format(
                " \"  \\\"comptes\\\": [\\n\" +\n" +
                        "                        \"    {\\n\" +\n" +
                        "                        \"      \\\"numCompte\\\": \\\"C27434892645\\\",\\n\" +\n" +
                        "                        \"      \\\"dateCreation\\\": \\\"%1$tY-%1$tm-%1$td\\\",\\n\" +\n" +
                        "                        \"      \\\"dateUpdate\\\": \\\"%1$tY-%1$tm-%1$td\\\",\\n\" +\n" +
                        "                        \"      \\\"solde\\\": 2000.0,\\n\" +\n" +
                        "                        \"      \\\"client_proprietaire\\\": {\\n\" +\n" +
                        "                        \"        \\\"NumClient\\\": 2,\\n\" +\n" +
                        "                        \"        \\\"Nom\\\": \\\"ZOUHRI\\\",\\n\" +\n" +
                        "                        \"        \\\"Prenom\\\": \\\"ALI\\\",\\n\" +\n" +
                        "                        \"        \\\"Address\\\": \\\"TATA\\\",\\n\" +\n" +
                        "                        \"        \\\"phone\\\": \\\"06338478347\\\",\\n\" +\n" +
                        "                        \"        \\\"email\\\": \\\"zouhriali@gmail.com\\\",\\n\" +\n" +
                        "                        \"        \\\"comptes\\\": null\\n\" +\n" +
                        "                        \"      },\\n\" +\n" +
                        "                        \"      \\\"transactions\\\": null,\\n\" +\n" +
                        "                        \"      \\\"banque\\\": null\\n\" +\n" +
                        "                        \"    }\\n\" +\n" +
                        "                        \"  ]\\n\""
        ,now);
        //Assert.assertEquals("comptes",comptes,result.getComptes());


    }
}
