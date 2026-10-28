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
`transport-contrat`, le mode est donc ajouté à l'implémentation interne et testé séparément ;
il ne peut pas être demandé via `SystemeTransport`.

### Supplément « hors gabarit »

Sans décorateur, l'ajout toucherait le calculateur de prix et le modèle/les règles portant sur
les dimensions, puis leurs tests. Avec les décorateurs, il doit nécessiter le nouveau
supplément et son ajout à l'assemblage uniquement. Il est activé pour `FLUVIAL` afin de
préserver les tarifs des modes historiques et les tests de contrat.

## Principes de conservation

- `transport-legacy` sert de référence comportementale ; hors retrait d'une donnée sensible
  inutilisée, il reste inchangé.
- `transport-contrat` reste inchangé.
- Les tests de contrat doivent rester verts après chaque étape.

## Preuve par l'extension

- `FLUVIAL` est ajouté au domaine interne : 0,40 €/km + 0,02 €/kg, minimum 300 €,
  délai `ceil(distance / 360) + 1`, sans urgence ni réfrigéré.
- La péniche `PEN-0001` a une capacité de 2 500 tonnes ; Paris–Le Havre est défini à 365 km.
- Le supplément hors gabarit ajoute 10 % aux colis dépassant 200 cm, pour les expéditions
  fluviales seulement, afin que les prix historiques des autres modes restent inchangés.
- L'étape 8 a modifié 12 fichiers déjà présents dans le module et ajouté neuf classes
  d'implémentation ainsi qu'un test d'extension. Au total, 20 fichiers déjà présents dans le
  module ont été modifiés depuis sa mise en place ; les autres ajouts sont de nouvelles
  stratégies, décorateurs, adaptateurs de capacités et tests.
- Le contrat est inchangé : l'API commune ne peut donc pas encore accepter une demande `FLUVIAL`.
- Les deux suites de contrat (68 tests chacune) et les sept tests unitaires ciblés passent.
