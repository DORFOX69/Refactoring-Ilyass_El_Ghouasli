package fr.efrei.transfret;

import fr.efrei.transport.contrat.Canal;
import fr.efrei.transport.contrat.ColisDto;
import fr.efrei.transport.contrat.DemandeClient;
import fr.efrei.transport.contrat.DemandeDevis;
import fr.efrei.transport.contrat.DemandeExpedition;
import fr.efrei.transport.contrat.EtatExpedition;
import fr.efrei.transport.contrat.Notification;
import fr.efrei.transport.contrat.Statut;
import fr.efrei.transport.contrat.SystemeTransport;

import java.time.temporal.ChronoUnit;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.Date;

/**
 * Branche le code historique sur les tests de contrat.
 * <p>
 * Tout le « bricolage » nécessaire pour tester l'ancien code (état statique à vider,
 * conversion des codes {@code String}/{@code int}…) est confiné ici.
 */
public class NouveauSystemeAdapter implements SystemeTransport {

    private ApplicationTransport application;

    @Override
    public void reinitialiser() {
        application = new ApplicationTransport();
    }

    @Override
    public Optional<String> creerClient(DemandeClient d) {
        Client client = application.serviceClient().creerClient(d.nom(), d.email(), d.telephone(), d.adresse(), TypeClient.valueOf(d.type().name()), d.siret());
        return Optional.ofNullable(client).map(Client::getEmail);
    }

    @Override
    public Optional<String> creerExpedition(DemandeExpedition d) {
        Client client = application.clients().trouver(d.refClient()).orElse(null);
        Expedition expedition = application.serviceExpedition().creerExpedition(client, versColis(d.colis()), d.villeDepart(),
                d.villeArrivee(), ModeTransport.valueOf(d.mode().name()), d.urgent(), d.assurance());
        return Optional.ofNullable(expedition).map(Expedition::getId);
    }

    @Override
    public Optional<EtatExpedition> consulter(String id) {
        return application.expeditions().trouver(id).map(e -> {
            int delai = (int) ChronoUnit.DAYS.between(jour(e.getDateCreation()), jour(e.getDateLivraisonPrevue()));
            return new EtatExpedition(e.getId(), Statut.valueOf(e.getStatut().name()), e.getPrixHT(), e.getPrixTTC(),
                    delai, e.getVehicule().getImmatriculation(), e.getFraisAnnulation());
        });
    }

    @Override
    public OptionalDouble devisTTC(DemandeDevis d) {
        double devis = application.serviceExpedition().calculerDevis(versColis(d.colis()), d.villeDepart(), d.villeArrivee(),
                ModeTransport.valueOf(d.mode().name()), d.urgent(), d.assurance(), TypeClient.valueOf(d.typeClient().name()));
        return devis == -1 ? OptionalDouble.empty() : OptionalDouble.of(devis);
    }

    @Override
    public boolean demarrer(String id) {
        return application.serviceExpedition().demarrerExpedition(id);
    }

    @Override
    public boolean livrer(String id) {
        return application.serviceExpedition().livrerExpedition(id);
    }

    @Override
    public boolean annuler(String id) {
        return application.serviceExpedition().annulerExpedition(id);
    }

    @Override
    public Optional<String> facture(String id) {
        return application.expeditions().trouver(id).map(new GenerateurFacture()::generer);
    }

    @Override
    public List<Notification> notifications() {
        return application.notificateur().notifications().stream()
                .map(n -> new Notification(Canal.valueOf(n.canal().name()), n.destinataire(), n.contenu()))
                .toList();
    }

    private static java.time.LocalDate jour(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static List<Colis> versColis(List<ColisDto> colis) {
        return colis.stream().map(NouveauSystemeAdapter::versColis).collect(Collectors.toCollection(ArrayList::new));
    }

    private static Colis versColis(ColisDto c) {
        return new Colis(c.description(), c.poidsKg(), c.longueurCm(), c.largeurCm(), c.hauteurCm(),
                TypeMarchandise.valueOf(c.type().name()), c.valeurDeclaree());
    }
}
