package fr.efrei.transfret;

import java.util.Random;

public class ApplicationTransport {

    private final InMemoryClientRepository clients = new InMemoryClientRepository();
    private final InMemoryExpeditionRepository expeditions = new InMemoryExpeditionRepository();
    private final EmailService emails = new EmailService();
    private final SmsService sms = new SmsService();
    private final Notificateur notificateur = new Notificateur(emails, sms);
    private final ServiceClient serviceClient = new ServiceClient(clients, notificateur);
    private final ServiceExpedition serviceExpedition = new ServiceExpedition(
            expeditions,
            new CalculateurPrix(),
            new AffectationVehicule(new Flotte()),
            new ValidateurExpedition(),
            new EstimateurDelai(),
            notificateur,
            new GenerateurIdentifiantExpedition(new Random()));

    public ClientRepository clients() {
        return clients;
    }

    public ExpeditionRepository expeditions() {
        return expeditions;
    }

    public ServiceClient serviceClient() {
        return serviceClient;
    }

    public ServiceExpedition serviceExpedition() {
        return serviceExpedition;
    }

    public Notificateur notificateur() {
        return notificateur;
    }
}
