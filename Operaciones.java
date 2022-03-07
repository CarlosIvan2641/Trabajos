import java.util.ArrayList;

public class Operaciones {

    protected ArrayList<Character> Simbolos = new ArrayList<>();

    Operaciones(){
        Simbolos.add('+');
        Simbolos.add('-');
        Simbolos.add('*');
        Simbolos.add('/');
        Simbolos.add('^');
        Simbolos.add('²');
        //Simbolos.add('');
    }

    protected boolean Verific(String Op){

        for (int i = 0; i <= Op.length()-1; i++){

            char X = Op.charAt(i);
            if (Simbolos.contains(X)){
                return true;
            }
        }
        return false;
    }

    protected double Calcular(char Op, double X, double Y){

        switch (Op){
            case '+':
                return X+Y;
            case '-':
                return X-Y;
            case '*':
                return X*Y;
            case '/':
                return X/Y;
            case '^':
                return Math.pow(X, Y);
        }
        return 0;

    }
}

    //  VERSION 1
    
class JerarquiaV1 extends Operaciones{

    public double Descomponer(String Op){

        LinealV1 Brother = new LinealV1();
        char X;
        int Contador = 0;

        for (int i = 0; i<=Op.length()-1; i++){

            X = Op.charAt(i);
            if (X == '/' ||  X == '*'){
                int Index = Verific(0, Op, i);
                int End = Verific(1, Op, i);
                String Aux = Op.substring(Index, End);
                Op = Op.replace(Aux, String.valueOf(Brother.Descomponer(Aux)));
                i = 0;
            }
        }
        return Brother.Descomponer(Op);
    }

    public int Verific(int Camino, String Op, int pos){

        char X;

        if (Camino == 0){
            while(true){
                pos--;
                X = Op.charAt(pos);

                if(pos == 0){ return 0; }
                if(super.Simbolos.contains(X)){ return pos+1; }
            }
        }else{

            while(true){
                pos++;
                X = Op.charAt(pos);

                if(pos == Op.length()-1){ return Op.length();}
                if(super.Simbolos.contains(X)){return pos;}
            }
        }
    }
}

class LinealV1 extends Operaciones{

    public double Descomponer(String Op){

        double AUX = 0;

        if(Verific_Op(Op)){

            int contador = 0;

            char Operador = '0';
            String aux = "";

            for (int i = 0; i<=Op.length()-1; i++){

                char X = Op.charAt(i);

                switch(X){

                    case '+':
                        contador++;
                        if (contador <= 1){
                            AUX = Double.parseDouble(aux);
                        }else{
                            AUX = Calcular(AUX, aux, Operador);
                        }
                        Operador = '+';
                        aux = "";
                        break;

                    case '-':
                        contador++;
                        if (contador <= 1){
                            AUX = Double.parseDouble(aux);
                        }else{
                            AUX = Calcular(AUX, aux, Operador);
                        }
                        Operador = '-';
                        aux = "";
                        break;

                    case '*':
                        contador++;
                        if (contador <= 1){
                            AUX = Double.parseDouble(aux);
                        }else{
                            AUX = Calcular(AUX,aux, Operador);
                        }
                        Operador = '*';
                        aux = "";
                        break;

                    case '/':
                        contador++;
                        if (contador <= 1){
                            AUX = Double.parseDouble(aux);
                        }else{
                            AUX = Calcular(AUX,aux, Operador);
                        }
                        Operador = '/';
                        aux = "";
                        break;

                    default:
                        aux += X;
                        break;
                }

            }
            AUX = Calcular(AUX, aux, Operador);

        }else{
            return Double.parseDouble(Op);
        }

        return AUX;
    }

    private boolean Verific_Op(String Op){

        for (int i = 0; i <= Op.length()-1; i++){

            char X = Op.charAt(i);
            if (X == '+' || X == '-' || X == '*' || X  == '/'){

                return true;
            }
        }
        return false;
    }

    private double Calcular(double X, String Y, char Op){

        switch (Op){
            case '+':
                return X+Double.parseDouble(Y);
            case '-':
                return X-Double.parseDouble(Y);
            case '*':
                return X*Double.parseDouble(Y);
            case '/':
                return X/Double.parseDouble(Y);
        }
        return 0;
    }

}