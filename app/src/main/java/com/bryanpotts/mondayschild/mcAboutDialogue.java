package com.bryanpotts.mondayschild;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by bryan on 02/10/2014.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class mcAboutDialogue extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        // Use the builder class for convenient dialog construction
        AlertDialog.Builder mcAboutDialog = new AlertDialog.Builder(getActivity());
        mcAboutDialog.setMessage("This app will take your birthday and tell you what day ...")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        mcAboutDialog.setTitle("About");
        mcAboutDialog.setIcon(R.drawable.ic_action_about);
        return mcAboutDialog.create();
    }

}
