package pl.dritms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.dritms.db.DBHelper;
import pl.dritms.model.Role;

public class AddBehaviourActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private long roleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_behaviour);

        Button cancelButton = findViewById(R.id.cancelAddBehaviourButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBehaviours(view, roleId);
            }
        });

        Button saveBehaviourButton = findViewById(R.id.saveBehaviourButton);
        saveBehaviourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndGoToBehaviours(view, roleId);
            }
        });

        dbHelper = new DBHelper(this);

        roleId = (long)getIntent().getExtras().get("roleId");

        Role role = null;

        for(Role r : dbHelper.getRoles()){
            if(r.getId() == roleId){
                role = r;
            }
        }

        if(role == null){
            throw new IllegalStateException();
        }

        TextView title = findViewById(R.id.newBehaviourNameForRoleText);
        title.setText("Nowe zachowanie dla roli - " + role.getName());


    }

    private void saveAndGoToBehaviours(View view, long roleId) {
        TextView behaviourNameTextView = findViewById(R.id.behaviourName);
        TextView behaviourDescriptionTextView = findViewById(R.id.behaviourDescription);
        dbHelper.addBehaviour(behaviourNameTextView.getText().toString(), behaviourDescriptionTextView.getText().toString(), roleId);
        goToBehaviours(view, roleId);
    }

    public void goToBehaviours(View view, long roleId){
        Intent intent = new Intent(AddBehaviourActivity.this, BehavioursActivity.class);
        intent.putExtra("roleId", roleId);
        startActivity(intent);
    }
}
