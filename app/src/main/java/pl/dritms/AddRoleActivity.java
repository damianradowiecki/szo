package pl.dritms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
