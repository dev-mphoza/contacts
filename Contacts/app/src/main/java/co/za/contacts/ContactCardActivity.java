package co.za.contacts;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import co.za.contacts.data.Data;
import co.za.contacts.model.Contact;
import co.za.contacts.model.Sender;

public class ContactCardActivity extends AppCompatActivity {
    private EditText etContactName;
    private EditText etContactNo;
    private ImageView imgView;
    private ImageButton btnImageUpload;
    private ActivityResultLauncher<Intent> resultLauncher;
    private ImageButton btnSave;
    private ImageButton btnHome;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_card);

        etContactName = findViewById(R.id.etContactName);
        etContactNo = findViewById(R.id.etContactNo);
        imgView = findViewById(R.id.imageEdit);
        btnImageUpload = findViewById(R.id.btnImageUpload);
        btnSave = findViewById(R.id.btnSave);
        btnHome = findViewById(R.id.btnHome);

        Intent intent = getIntent();

        if (intent.getStringExtra("sender").equals(Sender.EDIT.getDescription())) {
            String[] data = intent.getStringArrayExtra("contactDetails");

            String name = data[0];
            String contactNo = data[1];

            etContactName.setText(name);
            etContactNo.setText(contactNo);

            byte[] byteArray = getIntent().getByteArrayExtra("imageData");
            if (byteArray != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                imgView.setImageBitmap(bitmap);
                Toast.makeText(this, String.format("Id: %d",bitmap.getGenerationId()), Toast.LENGTH_LONG).show();
            }
        } else if (intent.getStringExtra("sender").equals(Sender.ADD.getDescription())) {
            etContactNo.setText("");
            etContactName.setText("");

            imgView.setImageResource(R.drawable.avatar_pokemon);
        }

        registerResult();
        btnImageUpload.setOnClickListener(v -> {
            pickImage();
        });

        btnSave.setOnClickListener(v -> {
            String name = etContactName.getText().toString();
            String contactNo = etContactNo.getText().toString();
            if(!name.equals("") && !contactNo.equals("")){
                Contact contact = new Contact(name, contactNo, Uri.parse(imageUri.getPath()));
                Data.contacts.add(contact);
            }else{
                Toast.makeText(getApplicationContext(), "Ensure name and contact are not empty", Toast.LENGTH_LONG).show();
            }
        });

        btnHome.setOnClickListener( v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
    }

    private void pickImage(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }
    private void registerResult(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    try {
                        imageUri = result.getData().getData();
                        imgView.setImageURI(imageUri);
                    }catch (Exception exc){
                        Toast.makeText(ContactCardActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}