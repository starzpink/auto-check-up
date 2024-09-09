package com.example.autocheckup;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class MyNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        DbHelper dbHelper = new DbHelper(context);

        scheduleNotification(context, dbHelper, "Atualização da ficha do veiculo", 1, 2629800000L);
        scheduleNotification(context, dbHelper, "Troca de óleo", 2, 15778800000L);
        scheduleNotification(context, dbHelper, "Revisão completa", 3, 15778800000L);

        String trocaDeOleo = intent.getStringExtra("trocaDeOleo");
        String revisaoCompleta = intent.getStringExtra("revisaoCompleta");
    }

    private void scheduleNotification(Context context, DbHelper dbHelper, String tipoNotificacao, int notificationId, long interval) {
        long nextNotificationTime = calculateNextNotification(dbHelper, tipoNotificacao, interval, context);

        if (nextNotificationTime > 0) {
            schedule(context, tipoNotificacao, notificationId, nextNotificationTime);
        }
    }

    private long calculateNextNotification(DbHelper dbHelper, String tipoNotificacao, long interval, Context context) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT MAX(data_notificacao) FROM notificacao WHERE tipo = ?";
        Cursor cursor = db.rawQuery(query, new String[]{tipoNotificacao});
        long ultimoTempoNotificacao = 0;

        if (cursor != null && cursor.moveToFirst()) {
            ultimoTempoNotificacao = cursor.getLong(0);
            cursor.close();
        }

        long currentTime = System.currentTimeMillis();
        long diff = currentTime - ultimoTempoNotificacao;

        if (diff >= interval) {
            showNotification(context, tipoNotificacao);
            return currentTime + interval;
        }

        return 0;
    }

    private void schedule(Context context, String tipoNotificacao, int notificationId, long time) {
        Intent intent = new Intent(context, MyNotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        }
    }

    private void showNotification(Context context, String tipoNotificacao) {
        // Lógica para exibir a notificação de acordo com o tipo
        String mensagem = "";
        String descricao = "";
        int notificationId = 0;

        if (tipoNotificacao.equals("Atualização da ficha do veiculo")) {
            descricao = "Atualização da ficha do veiculo";
            mensagem = "Olá! Já faz um mês desde a última atualização do registro do seu veículo. O que acha de atualizar agora?";
            notificationId = 1;
        } else if (tipoNotificacao.equals("Troca de óleo")) {
            descricao = "Troca de óleo";
            mensagem = "Olá! Já fazem seis meses desde a última troca de óleo do seu veículo. O que acha de agendar uma visita ao mecânico?";
            notificationId = 2;
        } else if (tipoNotificacao.equals("Revisão completa")) {
            descricao = "Revisão completa";
            mensagem = "Olá! Já fazem seis meses desde a última revisão do seu veículo. O que acha de agendar uma visita ao mecânico?";
            notificationId = 3;
        }

        String channelId = "Alertas do Veiculo";

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Alertas do Veiculo",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.notifications)
                .setContentTitle(descricao)
                .setContentText(mensagem)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification notification = builder.build();

        if (notificationManager != null) {
            notificationManager.notify(notificationId, notification);
        }
    }
}
