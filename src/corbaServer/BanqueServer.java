package corbaServer;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import service.BanqueImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BanqueServer {
    public static void main(String[]args) {
        ORB orb = ORB.init(args, null);
        try {
                System.out.println("Démarrage du serveur CORBA Banque ");
                POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
                poa.the_POAManager().activate();
                BanqueImpl od = new BanqueImpl ();
                Context ctx = new InitialContext();
                ctx.rebind("BK", poa.servant_to_reference(od));
                System.out.println("Serveur Banque pret et en attente de requetes clients");
                orb.run();


        } catch (InvalidName e) {
            e.printStackTrace();
        } catch (AdapterInactive e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (ServantNotActive e) {
            e.printStackTrace();
        } catch (WrongPolicy e) {
            e.printStackTrace();
        }
    }
}
