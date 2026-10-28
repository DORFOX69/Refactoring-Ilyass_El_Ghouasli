package fr.efrei.transfret;

public enum TypeClient {
    PARTICULIER,
    ENTREPRISE {
        @Override
        public boolean siretValide(String siret) {
            return siret != null && siret.length() == LONGUEUR_SIRET;
        }

        @Override
        public void ajouterSiret(StringBuilder facture, Client client) {
            facture.append("SIRET : " + client.getSiret() + "\n");
        }
    },
    PREMIUM {
        @Override
        public void notifierSms(CanalNotification canalSms, Client client, String sujet, String message) {
            canalSms.envoyer(client.getTelephone(), sujet, message);
        }
    };

    public boolean siretValide(String siret) {
        return true;
    }

    public void ajouterSiret(StringBuilder facture, Client client) {
    }

    public void notifierSms(CanalNotification canalSms, Client client, String sujet, String message) {
    }

    private static final int LONGUEUR_SIRET = 14;
}
