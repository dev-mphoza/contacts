package co.za.contacts.model;

import android.net.Uri;

public class Contact {
    private String name;
    private String phoneNumber;
    private Uri contactImage;
    public Contact(String name, String phoneNumber, Uri contactImage){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.contactImage = contactImage;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Uri getContactImage() {
        return contactImage;
    }
}
