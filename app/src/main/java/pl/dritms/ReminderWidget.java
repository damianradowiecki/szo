package pl.dritms;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.widget.RemoteViews;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import pl.dritms.db.DBHelper;
import pl.dritms.model.Behaviour;
import pl.dritms.model.Role;

/**
 * Implementation of App Widget functionality.
 */
public class ReminderWidget extends AppWidgetProvider {

    private static Random random = new Random();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        DBHelper dbHelper = new DBHelper(context);
        Pair<String, String> roleBehaviour = generateTitleContent(dbHelper);
        String widgetText = roleBehaviour.first + " : " + roleBehaviour.second;

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.reminder);
        views.setTextViewText(R.id.appwidget_text, widgetText);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static Pair<String, String> generateTitleContent(DBHelper dbHelper){
        String title = "Brak";
        String content = "Nie zdefiniowano żadnej roli z zachowaniami";
        Optional<Role> role = getRandomRole(dbHelper.getRoles());
        if(role.isPresent()){
            Optional<Behaviour> behaviour = getRandomBehaviour(dbHelper.getBehaviours(role.get()));
            if(behaviour.isPresent()){
                title = role.get().getName();
                content = behaviour.get().getName();
            }
        }
        return new Pair<>(title, content);
    }

    private static Optional<Role> getRandomRole(List<Role> roles)
    {
        if(roles != null && !roles.isEmpty()) {
            return Optional.of(roles.get(random.nextInt(roles.size())));
        }
        return Optional.empty();
    }

    private static Optional<Behaviour> getRandomBehaviour(List<Behaviour> behaviours){
        if(behaviours != null && !behaviours.isEmpty()) {
            return Optional.of(behaviours.get(random.nextInt(behaviours.size())));
        }
        return Optional.empty();
    }
}

