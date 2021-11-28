package matrices;

/**
 *
 * @author usuario
 */
public class Resultado extends Thread {

    private MiMatriz a;
    private MiMatriz b;
    private int i;
    private int j;
    private int res;

    public Resultado(MiMatriz a, MiMatriz b, int i, int j) {
        this.a = a;
        this.b = b;
        this.i = i;
        this.j = j;
        this.res = 0;
    }

    public void run() {
        for (int k = 0; k < this.a.getRow(); k++) {
            this.res += this.a.getM()[this.i][k] * this.b.getM()[k][this.j];
        }
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public MiMatriz getA() {
        return a;
    }

    public void setA(MiMatriz a) {
        this.a = a;
    }

    public MiMatriz getB() {
        return b;
    }

    public void setB(MiMatriz b) {
        this.b = b;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

}
