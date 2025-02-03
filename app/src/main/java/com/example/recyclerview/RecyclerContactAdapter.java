package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

    Context context;
    private int lastPosition = -1;
    ArrayList<ContactModel> arrContacts;
    RecyclerContactAdapter(Context context, ArrayList<ContactModel> arrContacts){
        this.context = context;
        this.arrContacts = arrContacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ContactModel model = arrContacts.get(position);

        holder.imgContact.setImageResource(model.img);
        holder.txtName.setText(model.name);
        holder.txtNumber.setText(model.number);

        setAnimation(holder.itemView, position);

        holder.llrow.setOnClickListener(new OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_update_lay);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);
                TextView txtTile = dialog.findViewById(R.id.txtTitle);

                txtTile.setText("Update Contact");
                btnAction.setText("Update");

                edtName.setText((arrContacts.get(position)).name);
                edtNumber.setText((arrContacts.get(position)).number);

                btnAction.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "" , number = "";
                        if(!edtName.getText().toString().isEmpty()) {
                            name = edtName.getText().toString();
                        } else {
                            Toast.makeText(context, "Please Enter Contact Name!", Toast.LENGTH_SHORT).show();
                        }
                        if(!edtNumber.getText().toString().isEmpty()){
                            number = edtNumber.getText().toString();
                        } else {
                            Toast.makeText(context, "Please Enter Number!", Toast.LENGTH_SHORT).show();
                        }
                        arrContacts.set(position, new ContactModel(arrContacts.get(position).img, name, number));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Delete Contact")
                        .setMessage("Are you sure want to delete?")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrContacts.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtNumber;
        ImageView imgContact;
        LinearLayout llrow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            imgContact = itemView.findViewById(R.id.imgContact);
            llrow = itemView.findViewById(R.id.llRow);

        }
    }
    private void setAnimation(View viewToAnimate, int position){

        if(position > lastPosition) {
//            Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            Animation slideIn = AnimationUtils.loadAnimation(context, R.anim.lay_anim);
            viewToAnimate.startAnimation(slideIn);
            lastPosition = position;
        }
    }
}
