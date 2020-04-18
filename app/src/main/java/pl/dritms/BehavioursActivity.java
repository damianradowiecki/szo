package pl.dritms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import pl.dritms.db.DBHelper;

public class BehavioursActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behaviours);

        ConstraintLayout constraintLayout = findViewById(R.id.behavoiursActivity);
        constraintLayout.setBackgroundColor(Color.BLACK);
        ((TextView)findViewById(R.id.behavoiurs)).setTextColor(Color.WHITE);
        ((TextView)findViewById(R.id.role)).setTextColor(Color.WHITE);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        getSupportActionBar().setTitle("");

        DBHelper db = new DBHelper(this);

    }
}
