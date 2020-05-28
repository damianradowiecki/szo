package pl.dritms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout constraintLayout = findViewById(R.id.mainActivity);
        constraintLayout.setBackgroundColor(Color.BLACK);
        ((TextView)findViewById(R.id.titleBig)).setTextColor(Color.WHITE);
        ((TextView)findViewById(R.id.titleSmall)).setTextColor(Color.WHITE);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        getSupportActionBar().setTitle("");

        Button exitButton = (Button) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    public void goToRoles(View view) {
        Intent intent = new Intent(MainActivity.this, RolesActivity.class);
        startActivity(intent);
    }

    public void goToSettings(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public  void putNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NotificationChannel.DEFAULT_CHANNEL_ID)
                .setContentTitle("Pamiętaj")
                .setContentText("cos tam...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}
