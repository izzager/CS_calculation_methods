import part2.Determinant;
import part2.Gauss;
import part2.MethodProgonki;
import part2.MethodSimpleIteration;
import part3.DifferenceMethod;
import part3.EulerMethods;
import part3.OdnorodnayaZadacha;
import part3.UndefinedCoefficientsMethod;
import part4.IntegralEquationDegenerateCore;
import part4.QudraturMethod;

import static part4.IntegralEquationDegenerateCore.getX;

public class Main {

    public static void main(String[] args) {
        //ArrayList<java.Pair<Double, Double>> f = Utilits.readF();
        //int n = f.size() - 1;

        //Utilits.printF(f);

        //Lagrange.print(f);

        //Newton.print(f);

        //Splain.print(f);
        //Splain.showWork();

        Gauss.showWork();

        Determinant.showWork();

        MethodProgonki.showWork();

        MethodSimpleIteration.showWork();

        EulerMethods.showWork();

        DifferenceMethod.showWork();

        OdnorodnayaZadacha.showWork();

        UndefinedCoefficientsMethod.showWork();

        IntegralEquationDegenerateCore.showWork();

        QudraturMethod.showWork();
    }
}