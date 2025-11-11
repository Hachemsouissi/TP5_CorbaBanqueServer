package service;

import corbaBanque.Compte;
import corbaBanque.IBanqueRemoteOperations;
import corbaBanque.IBanqueRemotePOA;
import org.omg.PortableServer.Servant;

import java.util.*;

public class BanqueImpl extends IBanqueRemotePOA  implements IBanqueRemoteOperations {
    private List<Compte> comptes = new ArrayList<>();

    @Override
    public void creerCompte(Compte cpte) {
        comptes.add(cpte);

    }

    @Override
    public void verser(float mt, int code) {
        for (Compte cpte : comptes) {
            if (cpte.code == code) {
                cpte.solde+=mt;
                System.out.println("Versement de " + mt + " DT effectué sur le compte " + code +
                        ". Nouveau solde : " + cpte.solde);
                return;
            }
                System.out.println("code invalide");

        }

    }

    @Override
    public void retirer(float mt, int code) {
        for (Compte cpte : comptes) {
            if (cpte.code == code) {
                cpte.solde -= mt;
                System.out.println("Retirement de " + mt + " DT effectué sur le compte " + code +
                        ". Nouveau solde : " + cpte.solde);
                return;
            }
                System.out.println("code invalide");

        }

    }

    @Override
    public Compte getCompte(int code) {
        for (Compte cpte : comptes) {
            if (cpte.code == code) {
                System.out.println("compte creer avec succes");
                return cpte;
            }
            System.out.println("code invalide");
        }
        return null;
    }

    @Override
    public Compte[] getComptes() {
      return comptes.toArray(new Compte[comptes.size()]);
    }

    @Override
    public double conversion(float mt) {
        double taux=3.0;
        double resultat=mt*taux;
        System.out.println("Conversion demandée : " + mt + "€ → " + resultat + " DT");
        return resultat;
    }
}
