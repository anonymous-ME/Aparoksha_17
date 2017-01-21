package app.fahmi.affanafahmi.aparoksha17;

import java.util.Date;

/**
 * Created by Affan on 22/1/17.
 */

public class transaction {
    private String event_name;
    private int id;
    private Date dot;
    private double amt;

    public transaction(String event_name, int id, Date dot, double amt) {
        this.event_name = event_name;
        this.id = id;
        this.dot = dot;
        this.amt = amt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Date getDot() {
        return dot;
    }

    public void setDot(Date dot) {
        this.dot = dot;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }
}