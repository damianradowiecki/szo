package pl.dritms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddRoleActivity extends AppCompatActivity {

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
    }

    public void goToRoles(View view){
        Intent intent = new Intent(AddRoleActivity.this, RolesActivity.class);
        startActivity(intent);
    }
}
