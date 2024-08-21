package co.za.contacts.data;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import co.za.contacts.R;
import co.za.contacts.model.Contact;

public class Data {
    public static List<Contact> contacts = new ArrayList<>();

    static {
        Contact contact1 = new Contact("Mpho", "084 512 8298",Uri.parse("android.resource://co.za.contacts/"+ R.drawable.avatar_01));
        Contact contact2 = new Contact("Thando", "076 212 3777",Uri.parse("android.resource://co.za.contacts/"+R.drawable.avatar_02));
        Contact contact3 = new Contact("Ronaldo", "073 513 1194", Uri.parse("android.resource://co.za.contacts/"+R.drawable.avatar_03));
        Contact contact4 = new Contact("Lebo", "078 504 3399", Uri.parse("android.resource://co.za.contacts/"+R.drawable.avatar_04));
        Contact contact5 = new Contact("Messi", "066 354 4794", Uri.parse("android.resource://co.za.contacts/"+R.drawable.avatar_05));
        Contact contact6 = new Contact("Salah", "032 245 6987",Uri.parse("android.resource://co.za.contacts/"+R.drawable.avatar_06));
        Contact contact7 = new Contact("Fernandes", "021 239 9748",Uri.parse("android.resource://co.za.contacts/"+R.drawable.avatar_07));
        Contact contact8 = new Contact("De Bruyne", "011 674 9845",Uri.parse("android.resource://co.za.contacts/"+R.drawable.avatar_08));
        Contact contact9 = new Contact("Mbappe", "021 239 9748", Uri.parse("android.resource://co.za.contacts/"+R.drawable.avatar_09));
        Contact contact10 = new Contact("Vinicius", "011 674 9845",Uri.parse("android.resource://co.za.contacts/"+R.drawable.avatar_05));

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        contacts.add(contact4);
        contacts.add(contact5);
        contacts.add(contact6);
        contacts.add(contact7);
        contacts.add(contact8);
        contacts.add(contact9);
        contacts.add(contact10);
    }
}
