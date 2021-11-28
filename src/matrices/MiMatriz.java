package matrices;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class MiMatriz extends Thread {

    private int[][] m;
    private int row;
    private int column;
    private ArrayList<Resultado> listaResultado;

    public MiMatriz(int row, int column) {
        this.column = column;
        this.row = row;
        this.m = new int[row][column];
        listaResultado = new ArrayList();
    }

    public MiMatriz() {
    }

    public ArrayList<Resultado> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(ArrayList<Resultado> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public int[][] getM() {
        return m;
    }

    public void setM(int[][] m) {
        this.m = m;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void llenarMatriz() {
        Random rand = new Random();
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.m[i][j] = rand.nextInt(100);
            }
        }
    }

    public MiMatriz multiplicar(MiMatriz m) {
        if (this.getColumn() != m.getRow()) {
            throw new IllegalArgumentException("Las filas de A: " + this.column + " no coinciden con las columnas de B: " + m.getRow());
        }

        MiMatriz result = new MiMatriz(this.getRow(), m.getColumn());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < m.getColumn(); j++) {
                result.getM()[i][j] = 0;
            }
        }

        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < m.getColumn(); j++) {
                for (int k = 0; k < this.getColumn(); k++) {
                    result.getM()[i][j] += this.getM()[i][k] * m.getM()[k][j];
                }
            }
        }
        return result;
    }

    public MiMatriz multiplicarHilo(MiMatriz m) {
        if (this.getColumn() != m.getRow()) {
            throw new IllegalArgumentException("Las filas de A: " + this.column + " no coinciden con las columnas de B: " + m.getRow());
        }

        MiMatriz result = new MiMatriz(this.getRow(), m.getColumn());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < m.getColumn(); j++) {
                result.getM()[i][j] = 0;
            }
        }

        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < m.getColumn(); j++) {
                Resultado res = new Resultado(this, m, i, j);
                res.start();
                this.getListaResultado().add(res);
            }
        }
        this.run();
        for (int k = 0; k < this.listaResultado.size(); k++) {
            result.getM()[this.listaResultado.get(k).getI()][this.listaResultado.get(k).getJ()]
                    = this.listaResultado.get(k).getRes();
        }
        return result;
    }

    public void pintarMatriz() {
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                System.out.println(this.getM()[i][j] + " ");
            }
        }
    }

    public void run() {
        for (int i = 0; i < this.listaResultado.size(); i++) {
            try {
                this.listaResultado.get(i).join();
            } catch (InterruptedException ex) {
                Logger.getLogger(MiMatriz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
