package Convertisseur;

import Bank.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverterClient {
    public String toJson(Client client) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        return gson.toJson(client);
    }
    public Client fromJson(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, Client.class);
    }
}
