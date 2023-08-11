package com.example.myapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private ArrayList<Person> persons;

    public PersonAdapter(ArrayList<Person> persons) {
        this.persons = persons;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new PersonViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = persons.get(position);
        holder.textName.setText("Name: " + person.getName());
        holder.textAge.setText("Age: " + person.getAge());

        Address address = person.getAddress();
        if (address != null) {
            holder.textLine.setText("Line: " + address.getLine());
            holder.textCity.setText("City: " + address.getCity());
            holder.textState.setText("State: " + address.getState());
            holder.textZip.setText("Zip: " + address.getZip());
        } else {
            holder.textLine.setText("Line: N/A");
            holder.textCity.setText("City: N/A");
            holder.textState.setText("State: N/A");
            holder.textZip.setText("Zip: N/A");
        }
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textAge, textLine, textCity, textState, textZip;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textAge = itemView.findViewById(R.id.textAge);
            textLine = itemView.findViewById(R.id.textLine); // Make sure IDs match item_person.xml
            textCity = itemView.findViewById(R.id.textCity); // Make sure IDs match item_person.xml
            textState = itemView.findViewById(R.id.textState); // Make sure IDs match item_person.xml
            textZip = itemView.findViewById(R.id.textZip); // Make sure IDs match item_person.xml
        }
    }
}
