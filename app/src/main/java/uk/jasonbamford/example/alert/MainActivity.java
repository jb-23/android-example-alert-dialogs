/* ***********  copyright Jason Bamford 2019 ** please visit http://jasonbamford.uk/  *********** */
package uk.jasonbamford.example.alert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

/* imports for alert dialogs */
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* user interface code */

    public void startMoreAlert(View view) {
        Intent intent = new Intent(this,MoreAlert.class);
        startActivity(intent);
    }

    private void setBackground(int id) {
        View layout = findViewById(R.id.layout);
        layout.setBackgroundResource(id);
    }

    /* example code ***************************************************************************** */

    public void alert(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Touch outside to dismiss");
        /* make sure will cancel on touch outside so we don't block app */
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }


    public void alertButton(View view) {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // do stuff
                setBackground(R.color.colorBackgound);
            }
        };

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert with Button");
        alertDialog.setMessage("Reset the colour");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", listener);
        alertDialog.show();
    }


    public void alertButtons(View view) {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                switch (which) {
                    case AlertDialog.BUTTON_NEUTRAL:
                        // do neutral stuff
                        setBackground(R.color.colorNeutral);
                        break;
                    case AlertDialog.BUTTON_NEGATIVE:
                        // do negative stuff
                        setBackground(R.color.colorNegative);
                        break;
                    case AlertDialog.BUTTON_POSITIVE:
                        // do positive stuff
                        setBackground(R.color.colorPositive);
                        break;
                }
            }
        };

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert with Buttons");
        alertDialog.setMessage("Change the background colour");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Neutral Grey", listener);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Negative Red", listener);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Positive Green", listener);
        alertDialog.show();
    }


    public void alertWithCancelListener(View view) {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // do stuff
                setBackground(R.color.colorPositive);
            }
        };
        DialogInterface.OnCancelListener cancelListener = new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // do cancel stuff
                setBackground(R.color.colorCancelled);
            }
        };

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert with Cancel Detection");
        alertDialog.setMessage("Set colour to green");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", listener);

        /* make sure user can cancel by touching outside, and set listener for this event */
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.setOnCancelListener(cancelListener);

        alertDialog.show();
    }
}
