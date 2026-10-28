package fr.efrei.transfret;

import java.util.List;
import java.util.ArrayList;

public class SmsService implements CanalNotification {

    private final List<NotificationEnvoyee> journal = new ArrayList<>();

    @Override
    public void envoyer(String numero, String sujet, String message) {
        if (message.length() > 160) {
            message = message.substring(0, 157) + "...";
        }
        System.out.println("[SMS] -> " + numero + " : " + message);
        journal.add(new NotificationEnvoyee(TypeCanalNotification.SMS, numero, message));
    }

    @Override
    public List<NotificationEnvoyee> notifications() {
        return List.copyOf(journal);
    }

    @Override
    public void reinitialiser() {
        journal.clear();
    }
}
