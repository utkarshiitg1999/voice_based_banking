package com.example.cmbuser.bank;

/**
 * Created by ravi on 20/02/18.
 */

public class Note {
    public static final String TABLE_NAME = "ransaction";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MODE = "creditdebit";
    //public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_BALANCE= "balance";
    public static final String TO= "receiver";


    private int id;
    private String note;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+
            " ("+COLUMN_ID+" INTEGER , "+COLUMN_MODE+" VARCHAR(255) ,"+COLUMN_AMOUNT+" REAL , "+TO+" VARCHAR(255) ," +COLUMN_BALANCE+" REAL);";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
    static String balance="CREATE TABLE IF NOT EXISTS balancesheet (id "+"INTEGER PRIMARY KEY ,"+"balance INTEGER);";

    public Note() {
    }

    public Note(int id, String note, String timestamp) {
        this.id = id;
        this.note = note;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
