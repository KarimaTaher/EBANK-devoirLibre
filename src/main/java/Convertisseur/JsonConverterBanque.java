package Convertisseur;

import Bank.Banque;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
public class JsonConverterBanque {

    public String toJson(Banque banque ) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.serializeNulls();
        Gson gson =gsonBuilder.create();
        return gson.toJson(banque);
    }
    public Banque fromJson(String json) {
        Gson gson =new GsonBuilder().create();
        return gson.fromJson(json, Banque.class);
    }
}
