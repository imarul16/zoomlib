package com.example.zoomlibtry.ui.meeting.feedback.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zoomlibtry.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class BaseFeedbackDialog extends BottomSheetDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                setDialog((BottomSheetDialog) dialog);
            }
        });
        return dialog;
    }


    protected void setDialog(BottomSheetDialog bottomSheetDialog) {
        FrameLayout bottomSheet = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheet.setBackgroundColor(getResources().getColor(R.color.transparent));
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        setHeight(bottomSheetDialog, behavior);
    }

    protected void setHeight(BottomSheetDialog bottomSheetDialog, BottomSheetBehavior behavior) {

    }
}
