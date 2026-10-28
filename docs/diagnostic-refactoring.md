# Diagnostic du code historique

## Défauts observés

| Défaut | Emplacements | Principe concerné |
|---|---|---|
| Le calcul du prix connaît les modes, les types de colis et les clients ; les règles sont mêlées au calcul. | `transport-legacy/.../CalculateurPrix.java` | SRP, OCP |
| Le service d’expédition valide, choisit un véhicule, calcule tarifs et délais, persiste, gère le cycle de vie et notifie. | `transport-legacy/.../ServiceExpedition.java` | SRP |
| Le service construit directement ses dépendances et consulte des états globaux. | `transport-legacy/.../ServiceExpedition.java`, `ServiceClient.java` | DIP |
| La persistance, la flotte, les journaux de notification et le compteur sont statiques ou singletons. | `Database.java`, `Flotte.java`, `EmailService.java`, `SmsService.java`, `ServiceExpedition.java` | DIP, testabilité |
| L'entité expédition met en forme sa facture. | `Expedition.java` | SRP |
| Les véhicules reçoivent une interface qui contient des capacités non prises en charge par la plupart d'entre eux. | `IVehicule.java`, `Camion.java`, `Train.java`, `Bateau.java`, `Avion.java` | ISP |
| L'affectation dépend des classes concrètes, de `instanceof` et de la flotte statique. | `AffectationVehicule.java` | OCP, DIP |
| Le camion frigorifique refuse dans `charger` des colis acceptés par son parent. | `CamionFrigorifique.java` | LSP |
| Les modes, statuts et types client sont des chaînes ; le type marchandise est un entier. | `Client.java`, `Expedition.java`, `Colis.java` | Sûreté de types, OCP |
| Les règles tarifaires, délais et volumes contiennent des littéraux répétés. | `CalculateurPrix.java`, `ServiceExpedition.java`, `Expedition.java`, `Colis.java`, `Utils.java` | Lisibilité, maintenabilité |
| Le formatteur de date est un état global mutable et non sûr en cas d'accès concurrent. | `Utils.java` | Encapsulation, sûreté concurrente |

## Emplacements impactés par les extensions prévues

### Nouveau mode `FLUVIAL`

Sans polymorphisme, l'ajout toucherait le type de mode, le calcul du tarif, le calcul du délai,
les règles d'acceptation, l'affectation et la flotte, ainsi que l'adaptateur et les tests.
La stratégie de tarif, la politique de délai et la capacité du véhicule doivent permettre de
limiter ensuite l'extension aux nouvelles implémentations et à leur assemblage.

Le contrat actuel ne contient pas `FLUVIAL`. Conformément à la règle de ne pas modifier
`transport-contrat`, le mode ne pourra être exposé par `SystemeTransport` sans évolution
ultérieure et explicite du contrat.

### Supplément « hors gabarit »

Sans décorateur, l'ajout toucherait le calculateur de prix et le modèle/les règles portant sur
les dimensions, puis leurs tests. Avec les décorateurs, il doit nécessiter le nouveau
supplément et son ajout à l'assemblage uniquement.

## Principes de conservation

- `transport-legacy` sert de référence comportementale ; hors retrait d'une donnée sensible
  inutilisée, il reste inchangé.
- `transport-contrat` reste inchangé.
- Les tests de contrat doivent rester verts après chaque étape.
