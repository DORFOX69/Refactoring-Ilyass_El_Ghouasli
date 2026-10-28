package fr.efrei.transfret;

import java.util.ArrayList;
import java.util.List;

public class Notificateur {

    private final CanalNotification email;
    private final CanalNotification sms;

    public Notificateur(CanalNotification email, CanalNotification sms) {
        this.email = email;
        this.sms = sms;
    }

    public void notifier(Client client, String sujet, String corps, String sms) {
        email.envoyer(client.getEmail(), sujet, corps);
        client.getType().notifierSms(this.sms, client, sujet, sms);
    }

    public void bienvenue(Client client) {
        email.envoyer(client.getEmail(), "Bienvenue chez TransFret",
                "Bonjour " + client.getNom() + ", votre compte " + client.getType().name().toLowerCase() + " a été créé.");
    }

    public List<NotificationEnvoyee> notifications() {
        List<NotificationEnvoyee> result = new ArrayList<>(email.notifications());
        result.addAll(sms.notifications());
        return List.copyOf(result);
    }

    public void reinitialiser() {
        email.reinitialiser();
        sms.reinitialiser();
    }
}
