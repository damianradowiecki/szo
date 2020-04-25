package pl.dritms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import pl.dritms.db.DBHelper;

public class RolesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles);
        ConstraintLayout constraintLayout = findViewById(R.id.rolesActivity);
        constraintLayout.setBackgroundColor(Color.BLACK);
        ((TextView)findViewById(R.id.roles)).setTextColor(Color.WHITE);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        getSupportActionBar().setTitle("");

        FloatingActionButton fab = findViewById(R.id.floatingAddRoleButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddRole(view);
            }
        });

        DBHelper db = new DBHelper(this);
    }

    public void goToBehaviours(View view) {
        Intent intent = new Intent(RolesActivity.this, BehavioursActivity.class);
        startActivity(intent);
    }

    public void goToAddRole(View view){
        Intent intent = new Intent(RolesActivity.this, AddRoleActivity.class);
        startActivity(intent);
    }
}
