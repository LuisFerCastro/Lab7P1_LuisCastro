/*//Fila 2 Asiento 12
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab7p1_luiscastro;//Fila 2 Asiento 12

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lfern
 */
public class Lab7P1_LuisCastro {
static Scanner leer = new Scanner(System.in);
static Random random = new Random();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("-----Menu------");
        System.out.println("1. Tres en raya");
        System.out.println("2. Puntos de silla.");
        System.out.println("Cualquier otro numero termina el programa.");
        System.out.println("Ingrese su opcion: ");
        int opcion = leer.nextInt();
        while(opcion> 0 && opcion < 3){
            switch(opcion){
                case 1:
                    System.out.println("Tres en raya.");
                    char respuesta = 's';
                    boolean victoria = false, victoria1 = false, posVal = false;
                    int posX, posY;
                    int cont = 0;
                    int cont2 =0;
                    char jugador = 'X', maquina = '0';
                    char [][]matriz = new char [3][3];
                    while(respuesta == 's'||respuesta == 'S'){
                        System.out.println("----Bienvenido al tres en raya----");
                        System.out.println("Tablero actual.");
                        matriz = generarTablero(matriz);
                        imprimirChar(generarTablero(matriz));
                        
                        victoria = verificarVictoria(matriz, jugador);
                        victoria1 = verificarVictoria(matriz,maquina);
                        while(victoria==false && victoria1 == false && cont2 !=10){
                            if(cont == 0){
                                System.out.println("Es el turno de: "+jugador);
                                System.out.println("Ingrese la fila (0, 1, 2): ");
                                posX = leer.nextInt();
                                System.out.println("Ingrese la columna (0, 1, 2):");
                                posY = leer.nextInt();
                                
                                posVal = verificarPosicionValida(matriz, posX, posY);
                                
                                while(posVal==false){
                                    System.out.println("Posiciones invalidas.");
                                    System.out.println("Ingrese de nuevo:");
                                    System.out.println("Ingrese la fila (0, 1, 2): ");
                                    posX = leer.nextInt();
                                    System.out.println("Ingrese la columna (0, 1, 2):");
                                    posY = leer.nextInt();
                                    posVal = verificarPosicionValida(matriz, posX, posY);
                                }
                                
                                System.out.println("El usuario ha elegido la posicion: "+"("+posX+" ,"+posY+")");
                                matriz[posX][posY] = jugador;
                                imprimirChar(matriz);
                                victoria = verificarVictoria(matriz,jugador);
                                victoria1 = verificarVictoria(matriz,maquina);
                                cont++;
                                cont2++;
                                
                            }else if(cont > 0){
                                System.out.println("Es el turno de: "+maquina);
                                
                                posX = random.nextInt((3-1)+1);
                                posY = random.nextInt((3-1)+1);
                                posVal = verificarPosicionValida(matriz,posX,posY);
                                
                                while(posVal==false){
                                    posX = random.nextInt((3-1)+1);
                                    posY = random.nextInt((3-1)+1);
                                    posVal = verificarPosicionValida(matriz, posX, posY);
                                }
                                
                                System.out.println("La maquina ha elegido la posicion"+"("+posX+" ,"+posY+")");
                                matriz[posX][posY] = maquina;
                                imprimirChar(matriz);
                                victoria = verificarVictoria(matriz,jugador);
                                victoria1 = verificarVictoria(matriz,maquina);
                                cont = 0;
                                cont2++;
                            }
                        }
                        if(victoria == true){
                            System.out.println("¡X ha ganado!");
                        }else if(victoria1 == true){
                            System.out.println("¡0 ha ganado!");
                        }else if(cont2 == 10){
                            System.out.println("¡Es un empate!");
                        }
                        
                        System.out.println("Desea jugar otra partida? (S/N).");
                        respuesta = leer.next().charAt(0);
                        cont2 = 0;
                    }
                    break; //fin ejercicio 1
                case 2:
                    System.out.println("Puntos de silla.");
                    System.out.println("Tiene que ser una matriz cuadrada.");
                    System.out.println("Ingrese el # de filas de la matriz: ");
                    int filas = leer.nextInt();
                    System.out.println("Ingrese el # de columnas de la matriz: ");
                    int columnas = leer.nextInt();
                    
                    while(filas != columnas){
                        System.out.println("Tiene que ser una matriz cuadrada.");
                        System.out.println("Ingrese el # de filas de la matriz: ");
                        filas = leer.nextInt();
                        System.out.println("Ingrese el # de columnas de la matriz: ");
                        columnas = leer.nextInt();
                    }
                    
                    int [][]matrizInt = new int [filas][columnas];
                    
                    matrizInt = generarIntMatrizAleatoria(filas,columnas);
                    System.out.println("Matriz generada: ");
                    imprimir_matriz(matrizInt);
                    
                    encontrarPuntosSilla(matrizInt);
                    break; //fin ejercicio2
            }//fin switch externo
            System.out.println("-----Menu------");
            System.out.println("1. Tres en raya.");
            System.out.println("2. Puntos de silla.");
            System.out.println("Cualquier otro numero termina el programa.");
            System.out.println("Ingrese su opcion: ");
            opcion = leer.nextInt();
        }//fin while externo
    }//fin del main
    
    public static void imprimir_matriz(int [][]matriz){
        for(int i = 0; i < matriz.length;i++){
            for(int j = 0; j < matriz[i].length;j++){
                System.out.print("["+matriz[i][j]+"]");
            }
            System.out.println("");
        }
    }//fin metodo imprimir
   
    public static void imprimirChar(char[][]matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print("["+matriz[i][j]+"]");
            }
            System.out.println("");
        }
    }//fin metodo imprimir char
    
    public static char[][] generarTablero(char[][]matriz){
        char[][]temporal = new char[3][3];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {   
                temporal[i][j]= ' ';
            }
        }
        return temporal;
    }//fin metodo generar tablero
    
    public static boolean verificarPosicionValida(char[][]matriz, int posX, int posY){
        boolean posVal = false;
        if((posX < 0 || posX > 2 || posY < 0 ||posY > 2)){
            posVal = false;
        }else if(matriz[posX][posY] != ' '){
            posVal = false;
        }else{
            posVal = true;
        }
        return posVal;     
    }//fin metodo verificar posicion valida
    
    public static boolean verificarVictoria(char[][]matriz, char valor){
        boolean victoria = false;
        if (matriz[0][0]== valor && matriz[1][1]== valor && matriz [2][2] == valor){
            victoria = true;
            //break;
            return victoria;
        }
        if (matriz[0][2]== valor && matriz[1][1]== valor && matriz [2][0] == valor){
            victoria = true;
            return victoria;
        }
        if(matriz[0][0]== valor && matriz[1][0]== valor && matriz [2][0] == valor){
            victoria = true;
            return victoria;
        }  
        if(matriz[0][1]== valor && matriz[1][1]== valor && matriz [2][1] == valor){
            victoria = true;
            return victoria;
        }   
        if(matriz[0][2]== valor && matriz[1][2]== valor && matriz [2][2] == valor){
            victoria = true;
            return victoria;
        }
        if(matriz[0][0]== valor && matriz[0][1]== valor && matriz [0][2] == valor){
            victoria = true;
            return victoria;
        }
        if(matriz[1][0]== valor && matriz[1][1]== valor && matriz [1][2] == valor){
            victoria = true;
            return victoria;
        }
        if(matriz[2][0]== valor && matriz[2][1]== valor && matriz [2][2] == valor){
            victoria = true;
            return victoria;
        }
        return victoria;
    }//fin metodo verificar victoria
   
    public static int[][]generarIntMatrizAleatoria(int filas, int columnas){
        int [][]temporal = new int[filas][columnas];
        for(int i = 0; i < filas;i++){
            for(int j = 0; j < columnas;j++){
                temporal[i][j] = random.nextInt(100);
            }
        }
        return temporal;
    }//fin metodo generarIntMatrizAleatoria
    public static void encontrarPuntosSilla(int[][]matriz){
        int minfila=0, maxcolumna=0;
        int posx = 0, posy = 0;
        int valorx=0;
        for (int i = 0; i < matriz.length; i++) {
            maxcolumna =maxcol(matriz, i);
            for (int j = 0; j < matriz[i].length; j++) {
                minfila = minfila(matriz, j); 
               if (minfila == maxcolumna){
                   valorx = minfila;
               }
               
            }
        }   
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if(matriz[i][j]== minfila && matriz[i][j] == maxcolumna){
                    posx = i;
                    posy = j;
                }
            }
        }
        if(valorx != 0){
            System.out.println("Punto de silla en Matriz "+"["+posx+","+posy+"]:"+valorx);
        }else{
            System.out.println("No se encontraron puntos de silla en la matriz.");
        }
    }//fin metodo encontrar Puntos Sillas
    public static int minfila(int [][]matriz, int fila){
        int minf = 101;
        for(int i = 0; i < matriz[fila].length;i++){ 
            if(minf > matriz[fila][i]){
                minf = matriz[fila][i];
            }
            
        }
        return minf;
    }//fin metodo minimo fila
    
    public static int maxcol(int[][]matriz,int columna){
        int maxc = 0;
        for (int j = 0; j < matriz[columna].length; j++) {
            if(maxc < matriz[j][columna])
                maxc = matriz[j][columna];
        }
        return maxc;
    }//fin metodo max columna
}
