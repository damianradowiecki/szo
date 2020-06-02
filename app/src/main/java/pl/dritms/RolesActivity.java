package pl.dritms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pl.dritms.db.DBHelper;
import pl.dritms.model.Behaviour;
import pl.dritms.model.Role;

public class RolesActivity extends AppCompatActivity {

    ArrayAdapter<Role> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles);
        ConstraintLayout constraintLayout = findViewById(R.id.rolesActivity);
        constraintLayout.setBackgroundColor(ColorGlobalSettings.BACKGROUND_COLOR);
        ((TextView)findViewById(R.id.roles)).setTextColor(ColorGlobalSettings.TEXT_COLOR);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ColorGlobalSettings.BACKGROUND_COLOR));
        getSupportActionBar().setTitle("");

        FloatingActionButton fab = findViewById(R.id.floatingAddRoleButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddRole(view);
            }
        });

        loadRoles();
    }

    @Override
    public void onBackPressed() {
        goToMainActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRoles();
    }

    public void goToMainActivity() {
        Intent intent = new Intent(RolesActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goToBehaviours(View view, long roleId) {
        Intent intent = new Intent(RolesActivity.this, BehavioursActivity.class);
        intent.putExtra("roleId", roleId);
        startActivity(intent);
    }

    public void goToAddRole(View view){
        Intent intent = new Intent(RolesActivity.this, AddRoleActivity.class);
        startActivity(intent);
    }

    private void loadRoles(){
        DBHelper db = new DBHelper(this);

        adapter = new ArrayAdapter<Role>(this,android.R.layout.simple_list_item_1,db.getRoles()){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView item = (TextView) super.getView(position,convertView,parent);
                item.setTextColor(ColorGlobalSettings.TEXT_COLOR);
                return item;
            }
        };

        final ListView listView = findViewById(R.id.rolesList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Role role = (Role)listView.getItemAtPosition(position);
                goToBehaviours(view, role.getId());
            }
        });
    }
}
