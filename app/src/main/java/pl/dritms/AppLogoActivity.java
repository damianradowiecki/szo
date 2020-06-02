package pl.dritms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import pl.dritms.db.DBHelper;

public class AppLogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasView(this));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ColorGlobalSettings.BACKGROUND_COLOR));
        getSupportActionBar().setTitle("");
        readColorMode();
    }

    private void readColorMode() {
        DBHelper dbHelper = new DBHelper(this);
        if(dbHelper.getSettingBoolean("darkMode")){
            ColorGlobalSettings.BACKGROUND_COLOR = Color.BLACK;
            ColorGlobalSettings.TEXT_COLOR = Color.WHITE;
        }
        else{
            ColorGlobalSettings.BACKGROUND_COLOR = Color.LTGRAY;
            ColorGlobalSettings.TEXT_COLOR = Color.BLACK;
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }

    static class CanvasView extends View
    {
        private Context context;
        private AppLogoActivity activity;
        public CanvasView(AppLogoActivity activity)
        {
            super(activity);
            this.context = activity;
            this.activity = activity;
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            goToMainActivity();
            return super.onTouchEvent(event);
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(ColorGlobalSettings.TEXT_COLOR);
            int textSize = 300;
            String text = "SZO";
            paint.setTextSize(textSize);

            int xPos = (int) (getWidth() / 2 - text.length()/2 * textSize);
            int yPos = (int) ((getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
            canvas.drawARGB(255, 0, 0, 0);
            canvas.drawText(text, xPos, yPos, paint);
        }

        public void goToMainActivity(){
            Intent intent = new Intent(activity, MainActivity.class);
            context.startActivity(intent);
        }
    }
}
