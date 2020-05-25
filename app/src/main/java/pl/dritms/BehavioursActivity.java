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
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pl.dritms.db.DBHelper;
import pl.dritms.model.Behaviour;
import pl.dritms.model.Role;

public class BehavioursActivity extends AppCompatActivity {

    private long roleId;
    ArrayAdapter<Behaviour> adapter;

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

        roleId = (long)getIntent().getExtras().get("roleId");

        loadBehaviours();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBehaviours();
    }

    public void goToAddBehaviour(View view) {
        Intent intent = new Intent(BehavioursActivity.this, AddBehaviourActivity.class);
        intent.putExtra("roleId", roleId);
        startActivity(intent);
    }

    private void loadBehaviours(){
        DBHelper db = new DBHelper(this);

        adapter = new ArrayAdapter<Behaviour>(this,android.R.layout.simple_list_item_1,db.getBehaviours(roleId)){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView item = (TextView) super.getView(position,convertView,parent);
                item.setTextColor(Color.WHITE);
                return item;
            }
        };

        final ListView listView = findViewById(R.id.behavioursList);
        listView.setAdapter(adapter);
    }

}
