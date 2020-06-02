package pl.dritms;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import pl.dritms.db.DBHelper;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ConstraintLayout constraintLayout = findViewById(R.id.settingsLayout);
        constraintLayout.setBackgroundColor(ColorGlobalSettings.BACKGROUND_COLOR);
        TextView textView = findViewById(R.id.settings_1_switch);
        textView.setTextColor(ColorGlobalSettings.TEXT_COLOR);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ColorGlobalSettings.BACKGROUND_COLOR));
        getSupportActionBar().setTitle("Ustawienia");
        final DBHelper dbHelper = new DBHelper(this);
        Switch switch_ = findViewById(R.id.switch1);
        switch_.setChecked(dbHelper.getSettingBoolean("darkMode"));
        switch_.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ColorGlobalSettings.BACKGROUND_COLOR = Color.BLACK;
                    ColorGlobalSettings.TEXT_COLOR = Color.WHITE;
                    dbHelper.updateSettings("darkMode", "true");
                }
                else{
                    ColorGlobalSettings.BACKGROUND_COLOR = Color.LTGRAY;
                    ColorGlobalSettings.TEXT_COLOR = Color.BLACK;
                    dbHelper.updateSettings("darkMode", "false");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        goToMainActivity();
    }

    public void goToMainActivity() {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }

}