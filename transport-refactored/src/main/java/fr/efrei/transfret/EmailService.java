package fr.efrei.transfret;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class EmailService implements CanalNotification {

    private static final String SMTP_HOST = "smtp.transfret.fr";
    private static final String EXPEDITEUR = "noreply@transfret.fr";
    private final List<NotificationEnvoyee> journal = new ArrayList<>();

    @Override
    public void envoyer(String destinataire, String sujet, String corps) {
        if (destinataire == null || !destinataire.contains("@")) {
            throw new IllegalArgumentException("Adresse email invalide");
        }
        System.out.println("[EMAIL via " + SMTP_HOST + "] " + EXPEDITEUR + " -> " + destinataire + " | " + sujet);
        System.out.println("        " + corps);
        journal.add(new NotificationEnvoyee(TypeCanalNotification.EMAIL, destinataire, sujet));
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
