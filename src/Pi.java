import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Pi extends Remote {
    BigDecimal computePi() throws RemoteException;
}
