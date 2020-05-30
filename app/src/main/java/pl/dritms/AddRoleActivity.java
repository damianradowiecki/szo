package pl.dritms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.dritms.db.DBHelper;

public class AddRoleActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_role);

        ConstraintLayout constraintLayout = findViewById(R.id.add_role_activity);
        constraintLayout.setBackgroundColor(Color.BLACK);
        ((TextView)findViewById(R.id.roleNameBaner)).setTextColor(Color.WHITE);
        ((TextView)findViewById(R.id.roleNameText)).setTextColor(Color.WHITE);
        ((TextView)findViewById(R.id.roleName)).setTextColor(Color.WHITE);
        ((TextView)findViewById(R.id.roleDescriptionText)).setTextColor(Color.WHITE);
        ((TextView)findViewById(R.id.roleDescription)).setTextColor(Color.WHITE);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        getSupportActionBar().setTitle("");


        Button cancelButton = findViewById(R.id.cancelAddRoleButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRoles(view);
            }
        });

        Button saveRoleButton = findViewById(R.id.saveRoleButton);
        saveRoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndGoToRoles(view);
            }
        });

        dbHelper = new DBHelper(this);
    }

    private void saveAndGoToRoles(View view) {
        TextView roleNameTextView = findViewById(R.id.roleName);
        TextView roleDescriptionTextView = findViewById(R.id.roleDescription);
        dbHelper.addRole(roleNameTextView.getText().toString(), roleDescriptionTextView.getText().toString());
        goToRoles(view);
    }

    public void goToRoles(View view){
        Intent intent = new Intent(AddRoleActivity.this, RolesActivity.class);
        startActivity(intent);
    }
}
