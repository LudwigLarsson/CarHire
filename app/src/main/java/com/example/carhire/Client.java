package com.example.carhire;

public class Client {
    int id;
    private String clientName;
    private String clientSurname;
    private String clientPhone;
    private int clientTransactionNum;

    public Client(int id, String clientName, String clientSurname, String clientPhone, int clientTransactionNum) {
        this.id = id;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientPhone = clientPhone;
        this.clientTransactionNum = clientTransactionNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientTransactionNum() {
        return clientTransactionNum;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public void setClientTransactionNum(int clientTransactionNum) {
        this.clientTransactionNum = clientTransactionNum;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }
}
