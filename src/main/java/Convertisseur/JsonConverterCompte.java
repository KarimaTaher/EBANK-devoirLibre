package Convertisseur;

import Bank.Compte;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverterCompte {
    public String toJson(Compte compte) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(compte);
    }
    public Compte fromJson(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, Compte.class);
    }
}
