package com.maxbotero;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String fraseAlreves = reverse("mensaje");
        System.out.println(fraseAlreves);

        //1. Array unidimensioal - crea, recorre e imprime valores:
        String[] unidimensional = {"casa", "carro", "beca"};
        for (String s : unidimensional) {
            System.out.println(s);
        }

        //2. Array bidimensional de enteros, muestra posición y valor de cada elemento:
        int[][] bidimensional = {
                {20, 6, 9, 81, 4, 7, 3},
                {66, 1, 44, 5, 8, 33, 12}
        };
        for (int i = 0; i < bidimensional.length; i++) {
            for (int j = 0; j < bidimensional[i].length; j++) {
                System.out.println("Posición Fila " + i + ", columna " + j + " -> " + bidimensional[i][j]);
            }
        }

        //3. Vector de 5 elementos, elimina segundo y tercer elemento(del vector inicial en este caso), muestra resultado final
        Vector<Integer> vector = new Vector();
        vector.add(5);
        vector.add(32);
        vector.add(7);
        vector.add(9);
        vector.add(77);
        vector.remove(1);//el segundo elemento del vector inicial
        vector.remove(1);//el tercer elemento del vector inicial que pasó a ser el segundo
        System.out.println("Datos del vector: " + vector);
        //4. Cual es problema de utilizar un vector con capacidad por defecto si fueramos a añadir 1000 elementos?
        //Respuesta: Dado que el vector por defecto tiene una capacidad de 10, se desbordará muchas veces teniendo que copiar
        // el vector muchas veces hasta tener la capacidad para albergar 1000 elementos, lo cual supone un gran costo computacional
        //**********************************************************************************************************************//

        //5. Crea un ArrayList de tipo String, con 4 elementos, copialo a una LinkedList, recorre ambos mostrando valor de cada elemento:
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("perro");
        lista.add("gato");
        lista.add("gallina");
        lista.add("conejo");
        LinkedList<String> listaEnlazada = new LinkedList<>(lista);
        System.out.println("Lista ArrayList:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
        System.out.println("Lista LinkedList:");
        for (int i = 0; i < listaEnlazada.size(); i++) {
            System.out.println(listaEnlazada.get(i));
        }
        //**********************************************************************************************************************//

        //6. Crea ArrayList de enteros, rellenar mediante bucle con elementos del 1 al 10, a continuación en otro bucle recorrer y eliminar numeros pares,
        //por último volver a recorrer y mostar el ArrayList final
        ArrayList<Integer> listaEnteros = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            listaEnteros.add(i);
        }
        System.out.println("Lista inicial: " + listaEnteros);

        for (int i = 0; i < listaEnteros.size(); i++) {
            if (listaEnteros.get(i) % 2 == 0) {
                listaEnteros.remove(i);
            }
        }
        System.out.println("Lista final despues de eliminar los pares:");
        for (int i = 0; i < listaEnteros.size(); i++) {
            System.out.println(listaEnteros.get(i));
        }
        //*****************************************************************************************************//

        //7. Este código captura dos valores por teclado y llama a la función divideporcero
        Scanner captura = new Scanner(System.in);
        System.out.print("Introduce dos numeros: ");
        int numeroA = captura.nextInt();
        int numeroB = captura.nextInt();
        try {
            System.out.println("El resultado de la division de " + numeroA + "por " + numeroB + "es " + divideporcero(numeroA, numeroB));
        } catch(Exception mi_excepcion){
            System.out.println("Esto no puede hacerse!");
            System.out.println("Excepción: " + mi_excepcion.toString());
        }finally {
            System.out.println("Demo de código");
        }

        //8. Utilizando InputStream y PrintStream, crea una función que reciba 2 parametros, fileIn y fileOut, la función debe realizar
        // una copia del fichero fileIn en el fichero fileOut
        String fileIn = "F:\\prueba.txt";
        String fileOut = "F:\\prueba_copia.txt";
        copiar(fileIn,fileOut);

        maxim();//llama a la función maxim que resuelve el punto 9 de los ejercicios.
    }//cierra método main


    //Esta función devuelve una frase dada al revés:
    public static String reverse(String texto) {

        StringBuilder alreves = new StringBuilder();
        for (int i = texto.length() - 1; i >= 0; i--) {
            alreves.append(texto.charAt(i));
        }
        return alreves.toString();
    }

    //7. función divide por cero (Actualmente JAVA asigna el valor infinity a las divisiones por cero, al menos en el caso de los tipos double y float,
    // por lo cual he tenido que generar mi propia excepción:
    public static double divideporcero(double numeroA, double numeroB) throws ArithmeticException{
        double resultado = 0;
        if(numeroB != 0) {
            resultado = numeroA / numeroB;
        } else{
            throw new ArithmeticException();
        }
        return resultado;
    }

    //8. funcion copiar hace una copia de un fichero dado
    public static void copiar (String fileIn, String fileOut){
        try{
            InputStream fichero = new FileInputStream(fileIn);
            try {
                byte datos[] = fichero.readAllBytes();
                PrintStream ficheroCopia = new PrintStream(fileOut);
                ficheroCopia.write(datos);
            }catch(IOException e){
                System.out.println("No se puede leer el fichero " + e.getMessage());
            }

        }catch(FileNotFoundException e){
            System.out.println("El programa arroja la excepción: " + e.getMessage());
        }
    }

    //9. esta función primero crea un HashMap de 3 elementos(clave-valor), luego copia los elementos del HashMap a un array de tipo Object,
    //luego escribe en un fichero los datos del array haciendo uso de PrintStream, por ultimo lee el fichero haciendo uso de FileInputStream
    // e imprime por pantalla el contenido.
    public static void maxim(){
        HashMap<String, String> datos = new HashMap<>();
        datos.put("Pedro", "31142165845");
        datos.put("Fulgencio", "31756214587");
        datos.put("Ruperto", "31565874521");
        Object directorio[] = new Object[3];

        int contador = 0;
        for(Map.Entry elemento : datos.entrySet()){

            directorio[contador] = elemento;
            contador++;
        }

        try {
            PrintStream fichero = new PrintStream("F:\\prueba.txt");
            fichero.write(Arrays.toString(directorio).getBytes());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        File file = new File("F:\\prueba.txt");
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = fis.readAllBytes();
            String str = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("Contenido del fichero " + file.getName() + ": " + str);

        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}
