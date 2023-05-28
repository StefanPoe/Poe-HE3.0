package com.example.myemployee;
import android.content.Context;
import android.content.Intent;
import android.text.AutoText;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent , false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recName.setText(dataList.get(position).getDataName());
        holder.recAge.setText(dataList.get(position).getDataAge());
        holder.recCity.setText(dataList.get(position).getDataCity());
        holder.recSkill.setText(dataList.get(position).getDataSkill());
        holder.recType.setText(dataList.get(position).getDataType());
        holder.recSalary.setText(dataList.get(position).getDataSalary());
        holder.recEducation.setText(dataList.get(position).getDataEducation());
        holder.recEmail.setText(dataList.get(position).getDataEmail());
        holder.recCV.setText(dataList.get(position).getDataCV());
        holder.recPhone.setText(dataList.get(position).getDataPhone());


        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , DetailActivity.class);
                intent.putExtra("Image" , dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Name" , dataList.get(holder.getAdapterPosition()).getDataName());
                intent.putExtra("Age" , dataList.get(holder.getAdapterPosition()).getDataAge());
                intent.putExtra("City" , dataList.get(holder.getAdapterPosition()).getDataCity());
                intent.putExtra("Salary" , dataList.get(holder.getAdapterPosition()).getDataSalary());
                intent.putExtra("Education" , dataList.get(holder.getAdapterPosition()).getDataEducation());
                intent.putExtra("Type" , dataList.get(holder.getAdapterPosition()).getDataType());
                intent.putExtra("Skill" , dataList.get(holder.getAdapterPosition()).getDataSkill());
                intent.putExtra("Email" , dataList.get(holder.getAdapterPosition()).getDataEmail());
                intent.putExtra("CV" , dataList.get(holder.getAdapterPosition()).getDataCV());
                intent.putExtra("Phone" , dataList.get(holder.getAdapterPosition()).getDataPhone());





                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();

    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recName , recAge , recCity , recSkill , recType , recSalary , recEducation ,recEmail , recCV , recPhone;

    CardView recCard;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recName = itemView.findViewById(R.id.recName);
        recAge = itemView.findViewById(R.id.recAge);
        recCity = itemView.findViewById(R.id.recCity);
        recSkill = itemView.findViewById(R.id.recSkill);
        recSalary = itemView.findViewById(R.id.recSalary);
        recType = itemView.findViewById(R.id.recType);
        recEducation = itemView.findViewById(R.id.recEducation);
        recCard = itemView.findViewById(R.id.recCard);
        recEmail = itemView.findViewById(R.id.recEmail);
        recCV = itemView.findViewById(R.id.recCV);
        recPhone = itemView.findViewById(R.id.recPhone);

    }

}
