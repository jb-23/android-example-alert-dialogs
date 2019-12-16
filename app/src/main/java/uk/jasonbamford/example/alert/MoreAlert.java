/* ***********  copyright Jason Bamford 2019 ** please visit http://jasonbamford.uk/  *********** */
package uk.jasonbamford.example.alert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import android.app.AlertDialog;

import android.os.Handler;
import android.widget.TextView;

public class MoreAlert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_alert);
    }

    /* user interface code */

    public void startLessAlert(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    /* example code ***************************************************************************** */

    /* instance variables for tracking countdown and holding reference to alert dialog */
    private int countdown;
    private AlertDialog myAlertDialog;

    /* update alert dialog during countdown and dismiss at end */
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (countdown > 0) {
                ((TextView) myAlertDialog.findViewById(android.R.id.message))
                        .setText(String.valueOf(countdown));
                countdown -= 1;
                handler.postDelayed(this,700);
            } else {
                /* countdown is over, dismiss alert dialog to release UI */
                myAlertDialog.dismiss();
            }
        }
    };

    public void alertWait(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(MoreAlert.this).create();
        alertDialog.setTitle("Please Wait");
        alertDialog.setMessage("Starting countdown...");

        /* disable cancel on touch outside so we can block UI during countdown */
        alertDialog.setCanceledOnTouchOutside(false);

        /* keep reference to alertDialog in instance variable so we can dismiss later */
        myAlertDialog = alertDialog;

        alertDialog.show();

        /* start countdown */
        countdown = 10;
        handler.postDelayed(runnable, 1200);
    }
}
