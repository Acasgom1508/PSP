package frecuencia;


import java.util.Scanner;

public class Frecuencia {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el texto: ");
        String texto = scanner.nextLine().toLowerCase();
        
        System.out.println(texto);
        //Pedimos texto

        //Declaramos las variables
        int contador_a = 0;
        int contador_e = 0;
        int contador_o = 0;


        for (char vocal : texto.toCharArray()) {
            switch (vocal) {
                case 'a':
                    contador_a++;
                    break;
                case 'e':
                    contador_e++;
                    break;
                case 'o':
                    contador_o++;
                    break;
            }
        }

        /*
        // Recorremos el texto buscando las vocales a e y o
        for (int i = 0; i < texto.length(); i++) {
            char letra = Character.toLowerCase(texto.charAt(i));
            switch (letra) {
                case 'a':
                    contador_a++;
                    break;
                case 'e':
                    contador_e++;
                    break;
                case 'o':
                    contador_o++;
                    break;
            }
        }*/
        System.out.println("A  |  E  |  O");
        System.out.println("--------------");
        System.out.println(contador_a + "     " + contador_e + "     " + contador_o);
    }
}
