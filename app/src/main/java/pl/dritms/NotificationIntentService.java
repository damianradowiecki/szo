package pl.dritms;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Pair;

import androidx.core.app.NotificationManagerCompat;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import pl.dritms.db.DBHelper;
import pl.dritms.model.Behaviour;
import pl.dritms.model.Role;

public class NotificationIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 1;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    public static final String NOTIFICATION_CHANNEL_NAME = "NOTIFICATION_CHANNEL_NAME";

    private Random random = new Random();

    public NotificationIntentService() {
        super("NotificationIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Pair<String, String> titleContent = generateTitleContent();

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(titleContent.first);
        builder.setContentText(titleContent.second);
        builder.setSmallIcon(R.drawable.notification);

        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, MyReceiver.REQUEST_CODE, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            managerCompat.createNotificationChannel(notificationChannel);
        }
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
    }

    private Pair<String, String> generateTitleContent(){
        DBHelper dbHelper = new DBHelper(this);
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

    private Optional<Role> getRandomRole(List<Role> roles)
    {
        if(roles != null && !roles.isEmpty()) {
            return Optional.of(roles.get(random.nextInt(roles.size())));
        }
        return Optional.empty();
    }

    private Optional<Behaviour> getRandomBehaviour(List<Behaviour> behaviours){
        if(behaviours != null && !behaviours.isEmpty()) {
            return Optional.of(behaviours.get(random.nextInt(behaviours.size())));
        }
        return Optional.empty();
    }
}
