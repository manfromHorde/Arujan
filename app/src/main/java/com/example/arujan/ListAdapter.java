package com.example.arujan;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ListData> dataArrayList;
    private ArrayList<ListData> originalData; // Добавлены оригинальные данные
    private OnItemClickListener onItemClickListener;

    public ListAdapter(Context context, ArrayList<ListData> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;
        this.originalData = new ArrayList<>(dataArrayList);
    }

    public void filter(String query) {
        dataArrayList.clear();

        if (query.isEmpty()) {
            dataArrayList.addAll(originalData);
        } else {
            query = query.toLowerCase();
            for (ListData data : originalData) {
                if (data.name.toLowerCase().contains(query)) {
                    dataArrayList.add(data);
                }
            }
        }

        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListData currentItem = dataArrayList.get(position);

        holder.listImage.setImageResource(currentItem.image);
        holder.listName.setText(currentItem.name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView listImage;
        TextView listName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listImage = itemView.findViewById(R.id.listImage);
            listName = itemView.findViewById(R.id.listName);
        }
    }
}
