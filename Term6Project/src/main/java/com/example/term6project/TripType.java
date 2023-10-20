package com.example.term6project;

public class TripType
{
    private String TripTypeId;
    private String TTName;

    public TripType(String tripTypeId, String string) {
        TripTypeId = tripTypeId;
        this.TTName = TTName;
    }

    public String getTripTypeId() {
        return TripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        TripTypeId = tripTypeId;
    }

    public String getTTName() {
        return TTName;
    }

    public void setTTName(String TTName) {
        this.TTName = TTName;
    }
}