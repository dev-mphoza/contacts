package co.za.contacts.model;

public enum Sender {
    ADD("Add"), EDIT("Edit");
    private String description;
    Sender(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
