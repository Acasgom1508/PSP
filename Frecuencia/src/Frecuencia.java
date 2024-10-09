import java.util.Scanner;

public class Frecuencia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //Pedimos texto
        System.out.print("Introduce el texto: ");
        String texto = scanner.nextLine();
        
        //Declaramos las variables
        int contador_a = 0;
        int contador_e = 0;
        int contador_o = 0;
        
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
        }
        
        System.out.println(contador_a + " " + contador_e + " " + contador_o);
    }
}
