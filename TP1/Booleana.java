import java.util.Scanner;

public class Booleana {

    static String clona(int c, String aux) {
        String clone = "";
        if (aux.charAt(c) == ')') {
            return clone;
        } else {
            clone = aux.charAt(c) + clona(c + 1, aux);
            return clone;
        }
    }

    static String substituiAnd(int c, String aux, String amodificar) {
        String resultado = amodificar;
        if (c >= aux.length()) {
            resultado = amodificar.replace(aux, "0");
        } else {
            if (aux.charAt(c) == '0') {
                resultado = amodificar.replace(aux, "0");
                return resultado;
            } else if (aux.charAt(c) == '1') {
                resultado = resultado + substituiAnd(c + 1, aux, amodificar);
            }
        }
        return resultado;
    }

    static String substituiOr(int c, String aux, String amodificar) {
        String resultado = amodificar;
        if (c >= aux.length()) {
            resultado = amodificar.replace(aux, "1");
        } else {
            if (aux.charAt(c) == '1') {
                resultado = amodificar.replace(aux, "1");
                return resultado;
            } else if (aux.charAt(c) == '0') {
                resultado = substituiOr(c + 1, aux, amodificar);
            }
        }
        return resultado;
    }

    static String substituiNot(int c, String aux, String amodificar) {
        String resultado = amodificar;
        if (c >= aux.length()) {
            return resultado;
        } else {
            if (aux.charAt(c) == '1') {
                resultado = amodificar.replace(aux, "0") + substituiNot(c + 1, aux, amodificar);
            } else if (aux.charAt(c) == '0') {
                resultado = amodificar.replace(aux, "1") + substituiNot(c + 1, aux, amodificar);
            }
        }
        return resultado;
    }

    static String or(int pos, String resultado) {
        String aux = clona(pos, resultado);
        aux = aux + ')';
        resultado = substituiOr(0, aux, resultado);
        return resultado;
    }

    static String and(int pos, String resultado) {
        String aux = clona(pos, resultado);
        aux = aux + ')';
        resultado = substituiAnd(0, aux, resultado);
        return resultado;
    }

    static String not(int pos, String resultado) {
        String aux = clona(pos, resultado);
        aux = aux + ')';
        resultado = substituiNot(0, aux, resultado);
        return resultado;
    }

    static int contaParenteses(String resultado) {
        int ab = 0;
        for (int i = 0; i < resultado.length(); i++) {
            if (resultado.charAt(i) == '(') {
                ab++;
            }
        }
        return ab;
    }

    public static void main(String[] args) {
        int ab;
        int n, a, b, c = 0;
        String exp, resultado = "";
        do {
            exp = new Scanner(System.in).nextLine();
            Scanner scanner = new Scanner(exp);
            if (!exp.equals("0")) {
                n = scanner.nextInt();
                a = scanner.nextInt();
                b = scanner.nextInt();
                String expMenor = "";
                if (n == 3) {
                    c = scanner.nextInt();
                }
                if (n == 2) {
                    for (int i = 6; i < exp.length(); i++) {
                        expMenor = expMenor + exp.charAt(i);
                    }
                }
                if (n == 3) {
                    for (int i = 8; i < exp.length(); i++) {
                        expMenor = expMenor + exp.charAt(i);
                    }
                }
                resultado = expMenor.replace("A", String.valueOf(a))
                        .replace("B", String.valueOf(b))
                        .replace("and", ("&"))
                        .replace("or", ("|"))
                        .replace("not", ("!"))
                        .replace("C", String.valueOf(c));
                ab = contaParenteses(resultado);

                while (ab != 0) {
                    String op = "";
                    int posi = 0;
                    for (int i = resultado.length() - 1; i >= 0; i--) {
                        if (resultado.charAt(i) == '!' || resultado.charAt(i) == '&' || resultado.charAt(i) == '|') {
                            posi = i;
                            op = op + resultado.charAt(i);
                            i = 0;
                        }
                    }

                    ab = contaParenteses(resultado);
                    if (op.charAt(0) == '&') {
                        resultado = and(posi, resultado);
                        ab = contaParenteses(resultado);
                    } else if (op.charAt(0) == '!') {
                        resultado = not(posi, resultado);
                        ab = contaParenteses(resultado);
                    } else if (op.charAt(0) == '|') {
                        resultado = or(posi, resultado);
                        ab = contaParenteses(resultado);
                    }
                }
            }
            System.out.println(resultado);
            scanner.close();
        } while (!exp.equals("0"));
    }
}
