package Convertisseur;

import Bank.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverterTransaction {

    public String toJson(Transaction transaction) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(transaction);
    }

    public Transaction fromJson(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, Transaction.class);
    }
}
