package com.example.term6project;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking
{
    private SimpleIntegerProperty bookingId;
    private Date bookingDate;
    private SimpleStringProperty bookingNo;
    private SimpleDoubleProperty travelerCount;
    private SimpleIntegerProperty customerId;
    private SimpleStringProperty tripTypeId;
    private SimpleIntegerProperty packageId;

    public Booking(int bookingId, Date bookingDate, String bookingNo,
                   double travelerCount, int customerId,
                   String tripTypeId, int packageId)
    {
        this.bookingId = new SimpleIntegerProperty(bookingId);
        this.bookingDate = bookingDate;
        this.bookingNo = new SimpleStringProperty(bookingNo);
        this.travelerCount = new SimpleDoubleProperty(travelerCount);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.tripTypeId = new SimpleStringProperty(tripTypeId);
        this.packageId = new SimpleIntegerProperty(packageId);
    }

    public int getBookingId() {
        return bookingId.get();
    }

    public SimpleIntegerProperty bookingIdProperty() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId.set(bookingId);
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingNo() {
        return bookingNo.get();
    }

    public SimpleStringProperty bookingNoProperty() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo.set(bookingNo);
    }

    public double getTravelerCount() {
        return travelerCount.get();
    }

    public SimpleDoubleProperty travelerCountProperty() {
        return travelerCount;
    }

    public void setTravelerCount(double travelerCount) {
        this.travelerCount.set(travelerCount);
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public SimpleIntegerProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public String getTripTypeId() {
        return tripTypeId.get();
    }

    public SimpleStringProperty tripTypeIdProperty() {
        return tripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.tripTypeId.set(tripTypeId);
    }

    public int getPackageId() {
        return packageId.get();
    }

    public SimpleIntegerProperty packageIdProperty() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId.set(packageId);
    }
}
