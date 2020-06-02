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
        constraintLayout.setBackgroundColor(ColorGlobalSettings.BACKGROUND_COLOR);
        ((TextView)findViewById(R.id.roleNameBaner)).setTextColor(ColorGlobalSettings.TEXT_COLOR);
        ((TextView)findViewById(R.id.roleNameText)).setTextColor(ColorGlobalSettings.TEXT_COLOR);
        ((TextView)findViewById(R.id.roleName)).setTextColor(ColorGlobalSettings.TEXT_COLOR);
        ((TextView)findViewById(R.id.roleDescriptionText)).setTextColor(ColorGlobalSettings.TEXT_COLOR);
        ((TextView)findViewById(R.id.roleDescription)).setTextColor(ColorGlobalSettings.TEXT_COLOR);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ColorGlobalSettings.BACKGROUND_COLOR));
        getSupportActionBar().setTitle("");


        Button cancelButton = findViewById(R.id.cancelAddRoleButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRoles();
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

    @Override
    public void onBackPressed() {
        goToRoles();
    }

    private void saveAndGoToRoles(View view) {
        TextView roleNameTextView = findViewById(R.id.roleName);
        TextView roleDescriptionTextView = findViewById(R.id.roleDescription);
        dbHelper.addRole(roleNameTextView.getText().toString(), roleDescriptionTextView.getText().toString());
        goToRoles();
    }

    public void goToRoles(){
        Intent intent = new Intent(AddRoleActivity.this, RolesActivity.class);
        startActivity(intent);
    }
}
