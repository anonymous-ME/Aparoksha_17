package app.fahmi.affanafahmi.aparoksha17.model;

import java.util.Date;

/**
 * Created by affan on 13/3/17.
 */

public class Ticket {
    private String ID;
    private String EventName;
    private Double amt;
    private Date date;

    public Ticket(String ID, String eventName, Double amt, Date date) {
        this.ID = ID;
        EventName = eventName;
        this.amt = amt;
        this.date = date;
    }

    public Ticket(){}

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
