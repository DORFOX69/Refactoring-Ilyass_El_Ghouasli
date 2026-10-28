package fr.efrei.transfret;

import java.util.List;

public interface CanalNotification {
    void envoyer(String destinataire, String sujet, String message);

    List<NotificationEnvoyee> notifications();

    void reinitialiser();
}
