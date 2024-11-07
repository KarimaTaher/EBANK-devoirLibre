package Bank;

import java.util.ArrayList;

public class Client {
    private int NumClient;
    private String Nom;
    private String Prenom;
    private String Address;
    private String phone;
    private String email;
    private ArrayList<Compte> comptes;
    public Client(){

    }
    public Client(int numClient, String nom, String prenom, String address, String phone, String email) {
        NumClient = numClient;
        Nom = nom;
        Prenom = prenom;
        Address = address;
        this.phone = phone;
        this.email = email;
    }

    public Client(int numClient, String nom, String prenom, String address, String phone, String email, ArrayList<Compte> comptes) {
        NumClient = numClient;
        Nom = nom;
        Prenom = prenom;
        Address = address;
        this.phone = phone;
        this.email = email;
        this.comptes = comptes;
    }

    //LES GETTERS ET LES SETTERS
    public int getNumClient() {
        return this.NumClient;
    }
    public void setNumClient(int numClient) {
        NumClient = numClient;
    }
    public String getNom() {
        return Nom;
    }
    public void setNom(String nom) {
        this.Nom = nom;
    }
    public String getPrenom() {
        return this.Prenom;
    }
    public void setPrenom(String prenom) {this.Prenom = prenom;}
    public String getAddress() {
        return this.Address;
    }
    public void setAddress(String address) {
        this.Address = address;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public ArrayList<Compte> getComptes() {return comptes;}
    public void setComptes(Compte compte) {
        this.comptes.add(compte);
    }

}
