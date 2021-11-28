package matrices;

/**
 *
 * @author usuario
 */
public class Matrices {

    public static void main(String[] args) {
        MiMatriz mA = new MiMatriz(500, 500);
        MiMatriz mB = new MiMatriz(500, 500);
        mA.llenarMatriz();
        mB.llenarMatriz();
        MiMatriz mC = new MiMatriz();
        long init = System.currentTimeMillis();
        mC = mA.multiplicar(mB);
        //mC = mA.multiplicarHilo(mB);
        System.out.println("Tiempo de ejecución: "+ (System.currentTimeMillis() - init));
        mC.pintarMatriz();

    }

}
