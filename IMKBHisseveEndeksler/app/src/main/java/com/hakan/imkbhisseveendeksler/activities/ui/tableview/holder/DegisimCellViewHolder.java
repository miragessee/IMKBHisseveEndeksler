package com.hakan.imkbhisseveendeksler.activities.ui.tableview.holder;

import android.view.View;

import androidx.annotation.NonNull;

import com.hakan.imkbhisseveendeksler.R;

public class DegisimCellViewHolder extends MoodCellViewHolder {

    public DegisimCellViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void setData(Object data) {

        boolean isUp = (boolean) data;

        if (isUp) {
            cell_image.setImageResource(R.drawable.ic_up_chevron);
        }
        else {
            cell_image.setImageResource(R.drawable.ic_down_arrow);
        }
    }
}
