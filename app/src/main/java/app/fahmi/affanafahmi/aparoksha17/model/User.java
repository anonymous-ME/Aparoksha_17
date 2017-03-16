package app.fahmi.affanafahmi.aparoksha17.model;

/**
 * Created by affan on 20/2/17.
 */

public class User {

    public String name;
    public String email;
    public String phone;
    public double wallet_balance = 0.0;
    public Boolean isIIITA;

    public User(String name, String email, String phone, double wallet_balance, Boolean isIIITA) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.wallet_balance = wallet_balance;
        this.isIIITA = isIIITA;
    }
}
