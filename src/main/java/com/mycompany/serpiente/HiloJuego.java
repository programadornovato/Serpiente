/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serpiente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Dell
 */
public class HiloJuego implements Runnable {
    //RECIBIMOS EL TEXT AREA DEL FRAME (DONDE SE DIBUJA EL JUEGO)
    JTextArea taJuego;
    //RECIBIMOS EL INPUTTEXT DEL FRAME (DONDE SE ESCRIBEN LAS LETRAS)
    JTextField txtLetras;
    //LA ULTIMA LETRA PRESIONADA (FORZAMOS QUE SEA LA d QUE MUEVA ADELANTE)
    public String ultima = "d";
    //DEFINIMOS LA DIRECCION HACIA DONDE SE VA A MOVER LA SERPIENTE (adelante)
    public String movimiento = "adelante";
    //DECLARAMOS TODO EL LIENZO DE UNA MEDIDA DE 33X13
    public String[][] lienzo = new String[33][13];
    //DECLARAMOS TODA LA SERPIENTE CON LAS MISMAS MEDIDAS QUE ELIENZO
    public String[][] serpiente = new String[33][13];
    //EL TAMAÑO INICIAL DE LASERPIENTE ES DE 4
    public int serpienteTam = 4;
    //ARREGLO DINAMICO DE LA SERPIENTE QUE COMENZARA A CRESER INDEFINIDAMENTE
    public List<Integer> posX;
    public List<Integer> posY;
    //DEFINIMOS LA MATRIZ DE LA COMIDA QUE PUEDE ESTAR EN CUALQUIER PARTE DEL LIENZO
    public String[][] comida = new String[33][13];
    //ASIGNAMOS LA POSICION DE LA COMIDA EN X Y Y (DE FORMA ALEATORIA)
    public int comidaPosX = 0;
    public int comidaPosY = 0;
    //CONSTRUCTOR DEL HILO DEL JUEGO
    HiloJuego(JTextArea taJuego, JTextField txtLetras) {
        //INICIALIZAMOS EL ARREGLO DINAMICO DE LA SERPIENTE QUE COMENZARA A CRESER INDEFINIDAMENTE
        this.posX = new ArrayList<Integer>();
        this.posY = new ArrayList<Integer>();
        //ASIGNAMOS EL VALOR DEL TEXTAREA PARA MANIPULARLO EN ESTE HILO
        this.taJuego = taJuego;
        //ASIGNAMOS EL VALOR DEL INPUTTEXT PARA MANIPULARLO DESDE EL HILO
        this.txtLetras = txtLetras;
        //INICIAMOS LA SERPIENTE PARA QUE NO DE ERROR DE NULL POINT
        limpiSerpiente();
        //INICIAMOS LA POSICION DE LA SERPIENTE EN ESTAS CORDENADAS X y Y
        posX.add(10);
        posY.add(6);

        posX.add(9);
        posY.add(6);

        posX.add(8);
        posY.add(6);

        posX.add(7);
        posY.add(6);
        //COLOCA LAS CORDENADAS DE LA SERPIENTE
        posisionMatrizSerpiente();
        //INICIAMOS LA POSICION DE LA COMIDA
        limpiaComida();
        //COLOCAMOS LA COMIDA EN UNA POSISION RANDOM EN EL LIENZO
        posisionAleatoriaComida();
    }

    //CORREMOS EL HILO
    public void run() {
        //TRY QUE DETECTA CUANDO LA SERPIENTE SE SALGA Y REINICIA EL JUEGO
        try {
            //CICLO INFINITO
            while (true) {
                //ESPERAMO 300 MILISEGUNDOS
                pausa(300);
                //RECIBIMOS LA ULTIMA LETRA ESCRITA EN EL INPUTTEXT
                ultima = ultimaLetra(this.txtLetras.getText());
                //RESIBIMOS EL STRING SI SE MUEVE HACIA ARRIBA, ABAJO, ATRAS O ADELANTE
                movimiento = seMueve(ultima);
                //DIBUJA MARCO EN AL MATRIZ
                for (int y = 0; y < 13; y++) {
                    for (int x = 0; x < 33; x++) {
                        //DIBUJAMOS EL LIENZO CON ESPACIO VACIO
                        lienzo[x][y] = " ";
                        //SI ES MARCO DIBUJAMOS ASTERICO
                        if (y == 0 || y == 12) {
                            lienzo[x][y] = "*";
                        }
                        if ( (x == 0 || x == 32)  ) {
                            lienzo[x][y] = "*";
                        }
                        //SI ES SERPIENTE DIBUJAMOS EL VALOR DE LA SERPIENTE
                        if (serpiente[x][y].length() > 0) {
                            lienzo[x][y] = serpiente[x][y];
                        }
                        //SI ES COMIDA DIBUJAMOS EL VALOR DE LA COMIDA
                        if (comida[x][y].length() > 0) {
                            lienzo[x][y] = comida[x][y];
                        }
                        //SI LA CABEZA DE LA SERPIENTE TOCA LA COMIDA
                        if( serpiente[x][y].equals("@") && comida[x][y].equals("O") ){
                            //INCREMENTA EL TAMAÑO DE LA SERPIENTE
                            serpienteTam++;
                            posX.add(x);
                            posY.add(y);
                            //QUITAMOS LA COMIDA
                            limpiaComida();
                            //POSISIONAMOS LA COMIDA EN UN NUEVO LUGAR
                            posisionAleatoriaComida();
                        }
                    }
                }
                //VARIABLE QUE RECIBE EL STRING A DIBUJAR
                String aDibujar = "";
                //DIBUJA MARCO EN EL TEXTAREA CON STRING
                for (int y = 0; y < 13; y++) {
                    for (int x = 0; x < 33; x++) {

                        aDibujar = aDibujar + lienzo[x][y];
                    }
                    aDibujar = aDibujar + "\n";
                }
                //ESCRIBIMOS EL LIENZO Y LA SEPIENTE EN EL TEXTAREA
                taJuego.setText(aDibujar);
                //COLOCA LAS CORDENADAS DE LA SERPIENTE
                posisionMatrizSerpiente();
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            Logger.getLogger(HiloJuego.class.getName()).log(Level.SEVERE, null, e);
            taJuego.setText(taJuego.getText()+"PERDISTE");
        }
    }
    
    //COLOCA LAS CORDENADAS DE LA SERPIENTE
    public void posisionMatrizSerpiente() {
        //CREAMOS UNAS LISTAS QUE ALAMACENARAN LA POSISIONES DE LA SERPIENTE PARA RECORRER LA POSISIONES DE LA COLA
        List<Integer> posX2 = new ArrayList<Integer>();
        List<Integer> posY2 = new ArrayList<Integer>();
        //RECORREMOS TODA LA SERPIENTE Y ASINAMOS LAS POSISIONES DE LA SERPIENTE A LAS NUEVAS POSISIONES
        for (int i = 0; i < serpienteTam; i++) {
            posX2.add(posX.get(i));
            posY2.add(posY.get(i));
        }
        //RECORREMOS TODO EL ARREGLO DE LA SERPIENTE
        for (int i = 0; i < serpienteTam; i++) {
            //SI ES LA CABEZA DE LA SERPIENTE
            if (i == 0) {
                //SI SE MANDA A MOVER HACIA ADELANTE
                if (movimiento.equals("adelante")) {
                    posX.set(i, posX.get(i) + 1);
                }
                //SI SE MANDA A MOVER HACIA ATRAS
                if (movimiento.equals("atras")) {
                    posX.set(i, posX.get(i) - 1);
                }
                //SI SE MANDA A MOVER HACIA ARRIBA
                if (movimiento.equals("arriba")) {
                    posY.set(i, posY.get(i) - 1);
                }
                //SI SE MANDA A MOVER HACIA ABAJO
                if (movimiento.equals("abajo")) {
                    posY.set(i, posY.get(i) + 1);
                }
            }
            //SI ES EL CUERPO DE LA SERPIENTE
            if (i > 0) {
                //RESIBIMOS LA POSISION DEL ELEMENTO ANTERIOR
                posX.set(i, posX2.get(i - 1));
                posY.set(i, posY2.get(i - 1));
            }
        }        
        //COLOCAMOS LOS VALORES DE LA SERPIENTE EN ""
        limpiSerpiente();
        //COLOCA @ SI ES CABEZA X SI ES COLA
        for (int i = 0; i < serpienteTam; i++) {
            if (i == 0) {
                serpiente[posX.get(i)][posY.get(i)] = "@";
            } else {
                serpiente[posX.get(i)][posY.get(i)] = "X";
            }
        }
    }

    //RECIBE UNA LETRA Y DECIDE HACIA DONDE SE MUEVE DEPENDIENDO DE LA LETRA
    public String seMueve(String ultima) {
        if (ultima.equals("w") || ultima.equals("W") || ultima.equals("8")) {
            return "arriba";
        }
        if (ultima.equals("s") || ultima.equals("S") || ultima.equals("5")) {
            return "abajo";
        }
        if (ultima.equals("a") || ultima.equals("A") || ultima.equals("4")) {
            return "atras";
        }
        if (ultima.equals("d") || ultima.equals("D") || ultima.equals("6")) {
            return "adelante";
        }
        return "";
    }
    //COLOCAMOS LOS VALORES DE LA SERPIENTE EN ""
    private void limpiSerpiente() {
        for (int y = 0; y < 13; y++) {
            for (int x = 0; x < 33; x++) {
                serpiente[x][y] = "";
            }
        }
    }
    //COLOCAMOS LOS VALORES DE LA SERPIENTE EN ""
    private void limpiaComida() {

        for (int y = 0; y < 13; y++) {
            for (int x = 0; x < 33; x++) {
                comida[x][y] = "";
            }
        }
    }
    //COLOCAMOS LA COMIDA EN UNA POSISION RANDOM EN EL LIENZO
    private void posisionAleatoriaComida(){
        comidaPosX = 0;
        Random rd = new Random();
        comidaPosX = rd.nextInt(32);
        comidaPosY = 0;
        comidaPosY = rd.nextInt(12);
        for (int y = 0; y < 13; y++) {
            for (int x = 0; x < 33; x++) {
                if(comidaPosX==x && comidaPosY==y)
                comida[x][y] = "O";
            }
        }
    }
    //EXTRAEMOS LA ULTIMA LETRA COLOCADA EN EL INPUT TEXT
    public String ultimaLetra(String texto) {
        try {
            return texto.substring(texto.length() - 1);
        } catch (Exception e) {
            return "";
        }
    }

    public void pausa(long sleeptime) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException ex) {
        }
    }
}



