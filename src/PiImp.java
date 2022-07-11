import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class PiImp extends UnicastRemoteObject implements Pi {
    protected PiImp() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    private static final BigDecimal FOUR = new BigDecimal("4");

    @Override
    public BigDecimal computePi() throws RemoteException {
        Scanner reader = new Scanner(System.in);
        System.out.println("Digite o número de digitos aproximado para Pi:");
        int numDigitos = reader.nextInt();

        int calcDigitos = numDigitos + 10;

        System.out.println("Computando Pi em " + numDigitos + " Dígitos.");

        // 4 * arctan (1/5) - arctan (1/239)
        return FOUR.multiply((FOUR.multiply(arccot(5, calcDigitos))).subtract(arccot(239, calcDigitos)).setScale(numDigitos, RoundingMode.DOWN));
    }

    private static BigDecimal arccot(int x, int escala) {
        BigDecimal resultado, fracao, term;
        BigDecimal invX = BigDecimal.valueOf(x);
        BigDecimal invX2 = BigDecimal.valueOf(x * x);

        fracao = BigDecimal.ONE.divide(invX, escala, RoundingMode.DOWN);
        resultado = fracao;

        int i = 1;
        do {
            fracao = fracao.divide(invX2, escala, RoundingMode.DOWN);
            int denominador = 2 * i + 1;
            term = fracao.divide(BigDecimal.valueOf(denominador), escala, RoundingMode.DOWN);

            if ((i % 2) != 0) {
                resultado = resultado.subtract(term);
            } else {
                resultado = resultado.add(term);
            }
            i++;
        } while (term.compareTo(BigDecimal.ZERO) != 0);

        return resultado;
    }
}
