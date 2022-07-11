import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) {
        try {
            Pi stub = (Pi) Naming.lookup("rmi://localhost:1099/Hello");
            System.out.println("O resultado de Pi eh " + stub.computePi());
            System.out.println("Comparacao com numero de Pi aprox. 20 casas: 3.14159265358979323846");

        } catch (NotBoundException | MalformedURLException | RemoteException  e) {
            e.printStackTrace();
        }
    }
}
