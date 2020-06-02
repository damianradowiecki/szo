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
import pl.dritms.model.Role;

public class AddBehaviourActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private long roleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_behaviour);

        ConstraintLayout constraintLayout = findViewById(R.id.add_behaviour_activity);
        constraintLayout.setBackgroundColor(ColorGlobalSettings.BACKGROUND_COLOR);
        ((TextView)findViewById(R.id.newBehaviourBaner)).setTextColor(ColorGlobalSettings.TEXT_COLOR);
        ((TextView)findViewById(R.id.behaviourNameText)).setTextColor(ColorGlobalSettings.TEXT_COLOR);
        ((TextView)findViewById(R.id.behaviourName)).setTextColor(ColorGlobalSettings.TEXT_COLOR);
        ((TextView)findViewById(R.id.behaviourDescriptionText)).setTextColor(ColorGlobalSettings.TEXT_COLOR);
        ((TextView)findViewById(R.id.behaviourDescription)).setTextColor(ColorGlobalSettings.TEXT_COLOR);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ColorGlobalSettings.BACKGROUND_COLOR));
        getSupportActionBar().setTitle("");

        Button cancelButton = findViewById(R.id.cancelAddBehaviourButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBehaviours(roleId);
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

        TextView title = findViewById(R.id.newBehaviourBaner);
        title.setText("Nowe zachowanie dla roli - " + role.getName());


    }

    @Override
    public void onBackPressed() {
        goToBehaviours(roleId);
    }

    private void saveAndGoToBehaviours(View view, long roleId) {
        TextView behaviourNameTextView = findViewById(R.id.behaviourName);
        TextView behaviourDescriptionTextView = findViewById(R.id.behaviourDescription);
        dbHelper.addBehaviour(behaviourNameTextView.getText().toString(), behaviourDescriptionTextView.getText().toString(), roleId);
        goToBehaviours(roleId);
    }

    public void goToBehaviours(long roleId){
        Intent intent = new Intent(AddBehaviourActivity.this, BehavioursActivity.class);
        intent.putExtra("roleId", roleId);
        startActivity(intent);
    }
}
