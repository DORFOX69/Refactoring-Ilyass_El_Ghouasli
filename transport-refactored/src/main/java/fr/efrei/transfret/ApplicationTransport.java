package fr.efrei.transfret;

import java.util.Random;
import java.util.Map;

public class ApplicationTransport {

    private final InMemoryClientRepository clients = new InMemoryClientRepository();
    private final InMemoryExpeditionRepository expeditions = new InMemoryExpeditionRepository();
    private final EmailService emails = new EmailService();
    private final SmsService sms = new SmsService();
    private final Notificateur notificateur = new Notificateur(emails, sms);
    private final ServiceClient serviceClient = new ServiceClient(clients, notificateur);
    private final ServiceExpedition serviceExpedition = new ServiceExpedition(
            expeditions,
            new CalculateurPrix(
                    Map.of(
                            ModeTransport.ROUTE, new TarifRoute(),
                            ModeTransport.RAIL, new TarifRail(),
                            ModeTransport.MER, new TarifMer(),
                            ModeTransport.AIR, new TarifAir(),
                            ModeTransport.FLUVIAL, new TarifFluvial()),
                    Map.of(
                            TypeClient.PARTICULIER, new RemiseParticulier(),
                            TypeClient.ENTREPRISE, new RemiseEntreprise(),
                            TypeClient.PREMIUM, new RemisePremium())),
            new AffectationVehicule(new Flotte()),
            new ValidateurExpedition(),
            new EstimateurDelai(Map.of(
                    ModeTransport.ROUTE, new DelaiRoute(),
                    ModeTransport.RAIL, new DelaiRail(),
                    ModeTransport.MER, new DelaiMer(),
                    ModeTransport.AIR, new DelaiAir(),
                    ModeTransport.FLUVIAL, new DelaiFluvial())),
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
