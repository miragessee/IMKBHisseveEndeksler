package com.hakan.imkbhisseveendeksler.activities.ui.tableview.holder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.hakan.imkbhisseveendeksler.R;
import com.hakan.imkbhisseveendeksler.activities.ui.tableview.TableViewModel;

public class MoodCellViewHolder extends AbstractViewHolder {

    @NonNull
    public final ImageView cell_image;

    public MoodCellViewHolder(@NonNull View itemView) {
        super(itemView);
        cell_image = itemView.findViewById(R.id.cell_image);
    }

    public void setData(Object data) {
        int mood = (int) data;
        int moodDrawable = mood == TableViewModel.HAPPY ? R.drawable.ic_happy : R.drawable.ic_sad;

        cell_image.setImageResource(moodDrawable);
    }
}
