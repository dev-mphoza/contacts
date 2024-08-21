package co.za.contacts.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import co.za.contacts.model.Contact;
import co.za.contacts.ContactCardActivity;
import co.za.contacts.R;
import co.za.contacts.model.Sender;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Contact> contacts;
    LayoutInflater inflater;
    public Adapter(Context ctx, List<Contact> contacts){
        this.contacts = contacts;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.phoneNo.setText(contacts.get(position).getPhoneNumber());
        holder.name.setText(contacts.get(position).getName());
        //holder.imageView.setImageResource(contacts.get(position).getContactImage());
        holder.imageView.setImageURI(contacts.get(position).getContactImage());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView phoneNo;
        TextView name;
        ImageView imageView;
        LinearLayout tapLayout;
        ImageView btnDial;
        ImageView btnMessage;
        ImageView btnEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            phoneNo = itemView.findViewById(R.id.tvTitle);
            imageView = itemView.findViewById(R.id.ivIcon);
            name = itemView.findViewById(R.id.contact_name);
            tapLayout = itemView.findViewById(R.id.tapLayout);
            btnDial = itemView.findViewById(R.id.btnDial);
            btnMessage = itemView.findViewById(R.id.btnMessage);
            btnEdit = itemView.findViewById(R.id.btnEdit);


            setItemViewListener();

            setDialClickListener();

            setMessageClickListener();

            setEditClickListener();

        }

        private void setItemViewListener(){
            itemView.setOnClickListener(v -> {
                if(tapLayout.getVisibility() == View.GONE){
                    tapLayout.setVisibility(View.VISIBLE);
                }else{
                    tapLayout.setVisibility(View.GONE);
                }
            });
        }


        private void setDialClickListener(){
            btnDial.setOnClickListener(v -> {
                String phoneNumber = "tel: " + phoneNo.getText().toString();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse(phoneNumber));
                itemView.getContext().startActivity(dialIntent);
            });
        }

        private void setMessageClickListener(){
            btnMessage.setOnClickListener(v -> {
                String phoneNumber = phoneNo.getText().toString();
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setData(Uri.parse("sms:" + phoneNumber));
                itemView.getContext().startActivity(smsIntent);
            });
        }

        private void setEditClickListener(){
            btnEdit.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), ContactCardActivity.class);
                intent.putExtra("sender", Sender.EDIT.getDescription());
                intent.putExtra("contactDetails", new String[]{name.getText().toString(), phoneNo.getText().toString()});

                Bitmap bitmap = null;
                Drawable drawable = imageView.getDrawable();
                if(drawable != null){
                    if(drawable instanceof BitmapDrawable){
                        bitmap = ((BitmapDrawable) drawable).getBitmap();
                    }else{
                        bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap);
                        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                        drawable.draw(canvas);
                    }
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if(bitmap != null){
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    intent.putExtra("imageData",byteArray);
                }
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
