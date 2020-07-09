/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author sarui
 */
public class Puzzle {
    
    public static int o_h(int [][] nodo){
        int cont=1;
        int contador=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(nodo[i][j]!=cont){
                    contador++;
                }
                cont++;
            }
        }
        return contador-1;
    }
    
    public static int[][] movD(int i, int j, int A[][]){
        int l=A[i][j];
        A[i][j]=A[i][j+1];
        A[i][j+1]=l;
        return A;
    }
    public static int[][] movI(int i, int j, int A[][]){
        int aux[][]=A;
        int l=aux[i][j];
        aux[i][j]=aux[i][j-1];
        aux[i][j-1]=l;
        return aux;        
    }
    public static int[][] movA(int i, int j, int A[][]){
        int l=A[i][j];
        A[i][j]=A[i-1][j];
        A[i-1][j]=l;
        return A;
    }
    public static int[][] movAb(int i, int j, int A[][]){
        int l=A[i][j];
        A[i][j]=A[i+1][j];
        A[i+1][j]=l;
        return A;
    }
    public static int aleatorio(int dimension){
        Random r = new Random();
        return r.nextInt(dimension)+1;
    }
    
    public static class nodo{
        boolean derecha;
        boolean izquierda;
        boolean arriba;
        boolean abajo;
        boolean despliegue;
        int g;
        int h;
        int f;
        public nodo(boolean derecha,boolean izquierda,boolean arriba,boolean abajo, boolean despliegue,int g,int h,int f){
            this.derecha=derecha;
            this.izquierda=izquierda;
            this.arriba=arriba;
            this.abajo=abajo;
            this.despliegue=despliegue;
            this.g=g;
            this.h=h;
            this.f=f;
        }
        public void setdespliegue(boolean despliegue){
            this.despliegue=despliegue;
        }
        
        public boolean getderecha()
        {
            return derecha;
        }
        public boolean getizquierda()
        {
            return izquierda;
        }
        public boolean getarriba()
        {
            return arriba;
        }
        public boolean getabajo()
        {
            return abajo;
        }
        public boolean getdespliegue()
        {
            return despliegue;
        }
        public int getg()
        {
            return g;
        }
        public int geth()
        {
            return h;
        }
        public int getf()
        {
            return f;
        }     
    } 
    
    public static Stack BPL(int nodo[][], int objetivo[][], int profundidad, int i, int j, boolean arriba,boolean abajo,boolean derecha,boolean izquierda,Stack pila){ 
        int aux[][]={{0,0,0},{0,0,0},{0,0,0}};
        if(profundidad==0 && Arrays.equals(nodo,objetivo)){
            for(int p=0;p<3;p++){
                System.out.print("\n");
                for(int l=0;l<3;l++){
                    System.out.print(nodo[p][l]);
                }
            }
            return pila;
        }
        else if(profundidad>0)
        {
            for(int p=0;p<3;p++){
                System.out.print("\n");
                for(int l=0;l<3;l++){
                    System.out.print(nodo[p][l]);
                }
            }
            System.out.print("\n");
            //expando al padre y para cada hijo calculo BPL
            if(i==0 && j==0){
                if(arriba==true){
                    profundidad=profundidad-1;
                    movD(i,j,nodo);
                    j=j+1;
                    derecha=true;
                    arriba=false;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(izquierda==true){
                    profundidad=profundidad-1;
                    movAb(i,j,nodo);
                    i=i+1;
                    izquierda=false;
                    abajo=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else{
                    profundidad=profundidad-1;
                    movD(i,j,nodo);
                    j=j+1;
                    derecha=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                        }
                    }
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            nodo[h][t]=aux[h][t];
                        }
                    }
                    movI(i,j,nodo);
                    j=j-1;
                    derecha=false;
                    movAb(i,j,nodo);
                    i=i+1;
                    izquierda=false;
                    abajo=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
            }
            else if(i==0 && j==1){
                if(derecha==true){
                    profundidad=profundidad-1;
                    movD(i,j,nodo);
                    j=j+1;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                        }
                    }
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            nodo[h][t]=aux[h][t];
                        }
                    }
                    movI(i,j,nodo);
                    j=j-1;
                    movAb(i,j,nodo);
                    i=i+1;
                    abajo=true;
                    derecha=false;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(izquierda==true){
                    profundidad=profundidad-1;
                    movI(i,j,nodo);
                    j=j-1;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                        }
                    }
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            nodo[h][t]=aux[h][t];
                        }
                    }
                    movD(i,j,nodo);
                    j=j+1;
                    movAb(i,j,nodo);
                    i=i+1;
                    izquierda=false;
                    abajo=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(arriba==true){
                    profundidad=profundidad-1;
                    movD(i,j,nodo);
                    j=j+1;
                    derecha=true;
                    arriba=false;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                        }
                    }
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            nodo[h][t]=aux[h][t];
                        }
                    }
                    movI(i,j,nodo);
                    j=j-1;
                    derecha=false;
                    movI(i,j,nodo);
                    j=j-1;
                    izquierda=true;
                    arriba=false;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else{
                    profundidad=profundidad-1;
                    movD(i,j,nodo);
                    j=j+1;
                    derecha=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                        }
                    }
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            nodo[h][t]=aux[h][t];
                        }
                    }
                    movI(i,j,nodo);
                    j=j-1;
                    derecha=false;
                    movAb(i,j,nodo);
                    i=i+1;
                    abajo=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                        }
                    }
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            nodo[h][t]=aux[h][t];
                        }
                    }
                    movA(i,j,nodo);
                    i=i-1;
                    abajo=false;
                    movI(i,j,nodo);
                    j=j-1;
                    izquierda=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
            }
            else if(i==0 && j==2){
                if(arriba==true){
                    profundidad=profundidad-1;
                    movI(i,j,nodo);
                    j=j-1;
                    izquierda=true;
                    arriba=false;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    
                }
                else if(derecha==true){
                    profundidad=profundidad-1;
                    movAb(i,j,nodo);
                    i=i+1;
                    abajo=true;
                    derecha=false;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else{
                    
                    profundidad=profundidad-1;
                    movI(i,j,nodo);
                    j=j-1;
                    izquierda=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                        }
                    }
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            nodo[h][t]=aux[h][t];
                        }
                    }
                    movD(i,j,nodo);
                    j=j+1;
                    izquierda=false;
                    movAb(i,j,nodo);
                    i=i+1;
                    abajo=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
            }
            else if(i==1 && j==0){
                    if(arriba==true){
                        profundidad=profundidad-1;
                        movA(i,j,nodo);
                        i=i-1;
                        pila.push(nodo);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                        }
                        movAb(i,j,nodo);
                        i=i+1;
                        movD(i,j,nodo);
                        j=j+1;
                        derecha=true;
                        arriba=false;
                        pila.push(nodo);
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    }
                    else if(izquierda==true){
                        profundidad=profundidad-1;
                        movA(i,j,nodo);
                        i=i-1;
                        arriba=true;
                        izquierda=false;
                        pila.push(nodo);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                        }
                        movAb(i,j,nodo);
                        i=i+1;
                        movAb(i,j,nodo);
                        i=i+1;
                        arriba=false;
                        abajo=true;
                        pila.push(nodo);
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    }
                    else if(abajo==true){
                        profundidad=profundidad-1;
                        movAb(i,j,nodo);
                        i=i+1;
                        pila.push(nodo);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                        }
                        movA(i,j,nodo);
                        i=i-1;
                        movD(i,j,nodo);
                        j=j+1;
                        abajo=false;
                        derecha=true;
                        pila.push(nodo);
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila); 
                    }
                    else{
                        profundidad=profundidad-1;
                        movA(i,j,nodo);
                        i=i-1;
                        arriba=true;
                        pila.push(nodo);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                        }
                        movAb(i,j,nodo);
                        i=i+1;
                        arriba=false;
                        abajo=true;
                        movAb(i,j,nodo);
                        i=i+1;
                        pila.push(nodo);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                        }
                        movA(i,j,nodo);
                        i=i-1;
                        movD(i,j,nodo);
                        j=j+1;
                        abajo=false;
                        derecha=true;
                        pila.push(nodo);
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    }
            }
            else if(i==1 && j==1){
                if(arriba==true){
                    profundidad=profundidad-1;
                    movA(i,j,nodo);
                    i=i-1;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movAb(i,j,nodo);
                    i=i+1;
                    movD(i,j,nodo);
                    j=j+1;
                    arriba=false;
                    derecha=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movI(i,j,nodo);
                    j=j-1;
                    derecha=false;
                    izquierda=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(abajo==true){
                    profundidad=profundidad-1;
                    movAb(i,j,nodo);
                    i=i+1;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movA(i,j,nodo);
                    i=i-1;
                    movD(i,j,nodo);
                    j=j+1;
                    abajo=false;
                    derecha=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movI(i,j,nodo);
                    j=j-1;
                    derecha=false;
                    movI(i,j,nodo);
                    j=j-1;
                    izquierda=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(derecha==true){
                    profundidad=profundidad-1;
                    movD(i,j,nodo);
                    j=j+1;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movI(i,j,nodo);
                    j=j-1;
                    movAb(i,j,nodo);
                    i=i+1;
                    derecha=false;
                    abajo=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movA(i,j,nodo);
                    i=i-1;
                    abajo=false;
                    movA(i,j,nodo);
                    i=i-1;
                    arriba=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(izquierda==true){
                    profundidad=profundidad-1;
                    movI(i,j,nodo);
                    j=j-1;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movD(i,j,nodo);
                    j=j+1;
                    movA(i,j,nodo);
                    i=i-1;
                    izquierda=false;
                    arriba=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movAb(i,j,nodo);
                    i=i+1;
                    arriba=false;
                    movAb(i,j,nodo);
                    i=i+1;
                    abajo=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else{
                    profundidad=profundidad-1;
                    movA(i,j,nodo);
                    i=i-1;
                    arriba=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movAb(i,j,nodo);
                    i=i+1;
                    arriba=false;
                    movAb(i,j,nodo);
                    i=i+1;
                    abajo=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movA(i,j,nodo);
                    i=i-1;
                    abajo=false;
                    movD(i,j,nodo);
                    j=j+1;
                    derecha=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movI(i,j,nodo);
                    j=j-1;
                    derecha=false;
                    movI(i,j,nodo);
                    j=j-1;
                    izquierda=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
            }
            else if(i==1 && j==2){
                if(arriba==true){
                    profundidad=profundidad-1;
                    movA(i,j,nodo);
                    i=i-1;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movAb(i,j,nodo);
                    i=i+1;
                    arriba=false;
                    movI(i,j,nodo);
                    j=j-1;
                    izquierda=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(derecha==true){
                    profundidad=profundidad-1;
                    movAb(i,j,nodo);
                    i=i+1;
                    derecha=false;
                    abajo=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movA(i,j,nodo);
                    i=i-1;
                    abajo=false;
                    movA(i,j,nodo);
                    arriba=true;
                    i=i-1;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(abajo==true){
                    profundidad=profundidad-1;
                    movAb(i,j,nodo);
                    i=i+1;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                        }
                    }
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    for(int h=0;h<3;h++){
                        for(int t=0;t<3;t++){
                            nodo[h][t]=aux[h][t];
                        }
                    }
                    
                    movA(i,j,nodo);
                    i=i-1;
                    movI(i,j,nodo);
                    j=j-1;
                    abajo=false;
                    izquierda=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else{
                    profundidad=profundidad-1;
                    movA(i,j,nodo);
                    i=i-1;
                    arriba=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movAb(i,j,nodo);
                    i=i+1;
                    arriba=false;
                    abajo=true;
                    movAb(i,j,nodo);
                    i=i+1;
                    pila.push(nodo);                    
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movA(i,j,nodo);
                    i=i-1;
                    abajo=false;
                    movI(i,j,nodo);
                    j=j-1;
                    izquierda=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
            }
            else if(i==2 && j==0){
                if(izquierda==true){
                    profundidad=profundidad-1;
                    movA(i,j,nodo);
                    i=i-1;
                    izquierda=false;
                    arriba=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(abajo==true){
                    profundidad=profundidad-1;
                    movD(i,j,nodo);
                    j=j+1;
                    abajo=false;
                    derecha=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else{
                    profundidad=profundidad-1;
                    movA(i,j,nodo);
                    i=i-1;
                    arriba=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movAb(i,j,nodo);
                    i=i+1;
                    arriba=false;
                    movD(i,j,nodo);
                    j=j+1;
                    derecha=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
            }
            else if(i==2 && j==1){
                if(abajo==true){
                    profundidad=profundidad-1;
                    movI(i,j,nodo);
                    j=j-1;
                    abajo=false;
                    izquierda=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movD(i,j,nodo);
                    j=j+1;
                    movD(i,j,nodo);
                    j=j+1;
                    izquierda=false;
                    derecha=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(derecha==true){
                    profundidad=profundidad-1;
                    movD(i,j,nodo);
                    j=j+1;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movI(i,j,nodo);
                    j=j-1;
                    derecha=false;
                    movA(i,j,nodo);
                    i=i-1;
                    arriba=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(izquierda==true){
                    profundidad=profundidad-1;
                    movI(i,j,nodo);
                    j=j-1;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movD(i,j,nodo);
                    j=j+1;
                    movA(i,j,nodo);
                    i=i-1;
                    izquierda=false;
                    arriba=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else{
                    profundidad=profundidad-1;
                    movI(i,j,nodo);
                    j=j-1;
                    izquierda=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movD(i,j,nodo);
                    j=j+1;
                    izquierda=false;
                    movD(i,j,nodo);
                    j=j+1;
                    derecha=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movI(i,j,nodo);
                    j=j-1;
                    derecha=false;
                    movA(i,j,nodo);
                    i=i-1;
                    arriba=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
            }
            else if(i==2 && j==2){
                if(abajo==true){
                    profundidad=profundidad-1;
                    movI(i,j,nodo);
                    j=j-1;
                    abajo=false;
                    izquierda=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(derecha==true){
                    profundidad=profundidad-1;
                    movA(i,j,nodo);
                    i=i-1;
                    derecha=false;
                    arriba=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else{
                    profundidad=profundidad-1;
                    movA(i,j,nodo);
                    i=i-1;
                    arriba=true;
                    pila.push(nodo);
                    for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                            aux[h][t]=nodo[h][t];
                            }
                        }
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        for(int h=0;h<3;h++){
                            for(int t=0;t<3;t++){
                                nodo[h][t]=aux[h][t];
                            }
                    }
                    movAb(i,j,nodo);
                    i=i+1;
                    arriba=false;
                    movI(i,j,nodo);
                    j=j-1;
                    izquierda=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }  
            }
        }
        else if(profundidad==0){
            for(int p=0;p<3;p++){
                System.out.print("\n");
                for(int l=0;l<3;l++){
                    System.out.print(nodo[p][l]);
                }
            }
            System.out.print("\n");
            return null;
        }
        return null;
    }
        
    public static void main(String[] args) {
        //defino puzzle como una matriz 3*3
        int [][] A = {{1,2,3},{4,5,6},{7,8,0}};
        int i=2;
        int j=2;
        boolean arriba=false;
        boolean abajo=false;
        boolean derecha=false;
        boolean izquierda=false;   
        int profundidad=14;
        //hago el desorden con 14 movimientos
        for(int cont=0;cont<profundidad;cont++){
            //mover(i,j,arriba,abajo,derecha,izquierda,A);
            if(i==0 && j==0){
                if(arriba==true){
                    movD(i,j,A);
                    j=j+1;
                    derecha=true;
                    arriba=false;
                }
                else if(izquierda==true){
                    movAb(i,j,A);
                    i=i+1;
                    izquierda=false;
                    abajo=true;
                }
            }
            else if(i==0 && j==1){
                if(derecha==true){
                    int alea=aleatorio(2);
                    if(alea==1){
                        //muevo a la derecha
                        movD(i,j,A);
                        j=j+1;
                    }
                    if(alea==2){
                        //muevo hacia arriba
                        movAb(i,j,A);
                        i=i+1;
                        abajo=true;
                        derecha=false;
                    }
                }
                
                else if(izquierda==true){
                    int alea=aleatorio(2);
                    if(alea==1){
                        //muevo a la izquierda
                        movI(i,j,A);
                        j=j-1;
                        
                    }
                    if(alea==2){
                        //muevo hacia abajo
                        movAb(i,j,A);
                        i=i+1;
                        izquierda=false;
                        abajo=true;
                    } 
                }
                
                else if(arriba==true){
                    int alea=aleatorio(2);
                    if(alea==1){
                        //muevo a la derecha
                        movD(i,j,A);
                        j=j+1;
                        derecha=true;
                        arriba=false;
                    }
                    if(alea==2){
                        //muevo a la izquierda
                        movI(i,j,A);
                        j=j-1;
                        izquierda=true;
                        arriba=false;
                    }
                }
            }
            
            else if(i==0 && j==2){
                if(arriba==true){
                    movI(i,j,A);
                    j=j-1;
                    izquierda=true;
                    arriba=false;
                }
                else if(derecha==true){
                    movAb(i,j,A);
                    i=i+1;
                    abajo=true;
                    derecha=false;
                }
            }
            
            else if(i==1 && j==0){
                    if(arriba==true){
                        int alea=aleatorio(2);
                        if(alea==1){
                            movA(i,j,A);
                            i=i-1;
                        }
                        if(alea==2){
                            movD(i,j,A);
                            j=j+1;
                            derecha=true;
                            arriba=false;
                        }
                    }
                    else if(izquierda==true){
                        int alea=aleatorio(2);
                        if(alea==1){
                            movA(i,j,A);
                            i=i-1;
                            arriba=true;
                            izquierda=false;
                        }
                       if(alea==2){
                            movAb(i,j,A);
                            i=i+1;
                            abajo=true;
                            izquierda=false;
                       }
                    }
                    else if(abajo==true){
                        //abajo o derecha
                        int alea=aleatorio(2);
                        if(alea==1){
                            movAb(i,j,A);
                            i=i+1;
                        }
                        if(alea==2){
                            movD(i,j,A);
                            j=j+1;
                            abajo=false;
                            derecha=true;
                        }
                    }                
            }
            else if(i==1 && j==1){
                if(arriba==true){
                    //no me muevo para abajo
                    int alea=aleatorio(3);
                    if(alea==1){
                        movA(i,j,A);
                        i=i-1;
                    }
                    if(alea==2){
                        movD(i,j,A);
                        j=j+1;
                        arriba=false;
                        derecha=true;
                    }
                    if(alea==3){
                        movI(i,j,A);
                        j=j-1;
                        arriba=false;
                        izquierda=true;
                    }
                }
                else if(abajo==true){
                    //no me muevo para arriba
                    int alea=aleatorio(3);
                    if(alea==1){
                        movAb(i,j,A);
                        i=i+1;
                    }
                    if(alea==2){
                        movD(i,j,A);
                        j=j+1;
                        abajo=false;
                        derecha=true;
                    }
                    if(alea==3){
                        movI(i,j,A);
                        j=j-1;
                        abajo=false;
                        izquierda=true;
                    }
                }
                else if(derecha==true){
                    //no me muevo para la izquierda
                    int alea=aleatorio(3);
                    if(alea==1){
                        movAb(i,j,A);
                        i=i+1;
                        derecha=false;
                        abajo=true;
                    }
                    if(alea==2){
                        movD(i,j,A);
                        j=j+1;
                    }
                    if(alea==3){
                        movA(i,j,A);
                        i=i-1;
                        derecha=false;
                        arriba=true;
                    }
                }
                else if(izquierda==true){
                    //no me muevo a la derecha
                    int alea=aleatorio(3);
                    if(alea==1){
                        movI(i,j,A);
                        j=j-1;
                    }
                    if(alea==2){
                        movA(i,j,A);
                        i=i-1;
                        izquierda=false;
                        arriba=true;
                    }
                    if(alea==3){
                        movAb(i,j,A);
                        i=i+1;
                        izquierda=false;
                        abajo=true;
                    }
                }
            }
            
            else if(i==1 && j==2){
                if(arriba==true){
                    //no me muevo para abajo
                    int alea=aleatorio(2);
                    if(alea==1){
                        movA(i,j,A);
                        i=i-1;
                    }
                    if(alea==2){
                        movI(i,j,A);
                        j=j-1;
                        arriba=false;
                        izquierda=true;
                    }
                }
                
                else if(derecha==true){
                    //no me muevo para la izquierda
                    int alea=aleatorio(2);
                    if(alea==1){
                        movAb(i,j,A);
                        i=i+1;
                        derecha=false;
                        abajo=true;
                    }
                    if(alea==2){
                        movA(i,j,A);
                        i=i-1;
                        derecha=false;
                        arriba=true;
                    }
                }
                
                else if(abajo==true){
                    //no me muevo para arriba
                    int alea=aleatorio(2);
                    if(alea==1){
                        movAb(i,j,A);
                        i=i+1;
                    }
                    
                    if(alea==2){
                        movI(i,j,A);
                        j=j-1;
                        abajo=false;
                        izquierda=true;
                    }
                }
            }
            
            else if(i==2 && j==0){
                if(izquierda==true){
                    movA(i,j,A);
                    i=i-1;
                    izquierda=false;
                    arriba=true;
                }
                else if(abajo==true){
                    movD(i,j,A);
                    j=j+1;
                    abajo=false;
                    derecha=true;
                }
            }
            
            else if(i==2 && j==1){
                if(abajo==true){
                    int alea=aleatorio(2);
                    if(alea==1){
                        movI(i,j,A);
                        j=j-1;
                        abajo=false;
                        izquierda=true;
                    }
                    if(alea==2){
                        movD(i,j,A);
                        j=j+1;
                        abajo=false;
                        derecha=true;
                    }
                }
                else if(derecha==true){
                    int alea=aleatorio(2);
                    if(alea==1){
                        movA(i,j,A);
                        i=i-1;
                        derecha=false;
                        arriba=true;
                    }
                    if(alea==2){
                        movD(i,j,A);
                        j=j+1;
                    }
                }
                else if(izquierda=true){
                    int alea=aleatorio(2);
                    if(alea==1){
                        movA(i,j,A);
                        i=i-1;
                        izquierda=false;
                        arriba=true;
                    }
                    if(alea==2){
                        movI(i,j,A);
                        j=j-1;
                    }
                }                
            }
            
            else if(i==2 && j==2){
                if(abajo==true){
                    movI(i,j,A);
                    j=j-1;
                    abajo=false;
                    izquierda=true;
                }
                else if(derecha==true){
                    movA(i,j,A);
                    i=i-1;
                    derecha=false;
                    arriba=true;
                }
                else{
                    
                    int alea=aleatorio(2);
                    if(alea==1){
                        //muevo hacia arriba
                        movA(i,j,A);
                        i=i-1;
                        arriba=true;
                    }
                    if(alea==2){
                        //muevo hacia la izquierda
                        movI(i,j,A);
                        j=j-1;
                        izquierda=true;
                    } 
                    
                }  
            }
            
            for(int a=0;a<3;a++){
                System.out.print("\n");
                for(int b=0;b<3;b++){
                    System.out.print(A[a][b]);
                }
            }
            System.out.print("\n");
        }        
        
        ///se hace búsqueda de profundidad limitada
        
        System.out.print("se hace el arbol de busqueda en profundidad limitada:");
        Stack pila = new Stack();
        pila.push(A);
        int [][] start={{0,0,0},{0,0,0},{0,0,0}};
        for(int Y=0;Y<3;Y++){
            for(int Z=0;Z<3;Z++){
                start[Y][Z]=A[Y][Z];
            }
        }
        
        int [][] objetivo = {{1,2,3},{4,5,6},{7,8,0}};
        arriba=false;
        abajo=false;
        derecha=false;
        izquierda=false;
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                if(A[a][b]==0){
                    i=a;
                    j=b;
                }
            }
        }
        MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage beforeHeapMemoryUsage = mbean.getHeapMemoryUsage();
        
        System.out.print("\n");
        long startTime = System.nanoTime();
        Stack pila2=BPL(A,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
        long endTime = (long) ((System.nanoTime()-startTime)/1e6);
        System.out.print("\n");
        MemoryUsage afterHeapMemoryUsage = mbean.getHeapMemoryUsage();
        long consumed =  afterHeapMemoryUsage.getUsed() - beforeHeapMemoryUsage.getUsed();
        
        ///////////////termina busqueda en profundidad limitada
        
        MemoryUsage beforeHeapMemoryUsage2 = mbean.getHeapMemoryUsage();
        
        System.out.print("se hace el arbol de busqueda A*:");
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                if(start[a][b]==0){
                    i=a;
                    j=b;
                }
            }
        }
        nodo arbol[]=new nodo[15000]; // se hace arbol vacio
        ArrayList<int[][]> nodos = new ArrayList<>();
        int modulo[][]={{0,0,0},{0,0,0},{0,0,0}};
        int h=o_h(start);//calcula la heuristica inicial
        int f=0+h;
        int indice=0; //nodo indice
        nodos.add(start);
        arbol[indice]=new nodo(false,false,false,false,false,0,h,f);
        int actual=1; //nodo que actualiza
        
        int heuristica=o_h(start);
        Stack ruta= new Stack();
        ruta.push(0);
        
        long startTime2 = System.nanoTime();
 
        while(heuristica>0){
            int[][] corrent=nodos.get(indice);
            for(int I=0;I<3;I++){
                System.out.print("\n");
                for(int J=0;J<3;J++){
                    System.out.print(corrent[I][J]);
                }
            }
            System.out.print("\n");
            if(i==0 && j==0){
                if(arbol[indice].getarriba()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    h=o_h(change);
                    f=g+h;
                    arbol[actual]=new nodo(true,false,false,false,false,g,h,f); //añado nodo
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getizquierda()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    h=o_h(change);
                    arbol[actual]=new nodo(false,false,false,true,false,g,h,g+f); //añado nodo
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else{
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    h=o_h(change);
                    arbol[actual]=new nodo(true,false,false,false,false,g,h,g+h);
                    //////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    int ch[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            ch[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,ch);
                    ruta.push(actual);
                    nodos.add(ch);
                    g=arbol[indice].getg()+1;
                    h=o_h(ch);
                    arbol[actual]=new nodo(true,false,false,false,false,g,h,g+h);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        }
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
            }
            else if(i==0 && j==1){
                
                if(arbol[indice].getderecha()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    h=o_h(change);
                    arbol[actual]=new nodo(true,false,false,false,false,g,h,g+h);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    ///////////////////
                    ///////////////////
                    int ch[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            ch[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,ch);
                    ruta.push(actual);
                    nodos.add(ch);
                    g=arbol[indice].getg()+1;
                    h=o_h(ch);
                    arbol[actual]=new nodo(false,false,false,true,false,g,h,g+h);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getizquierda()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    ///////////////////////////
                    ////////////////////////
                    int ch[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            ch[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,ch);
                    ruta.push(actual);
                    nodos.add(ch);
                    g=arbol[indice].getg()+1;
                    hh=o_h(ch);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getarriba()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    /////////////////////
                    ////////////////////
                    int ch[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            ch[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,ch);
                    ruta.push(actual);
                    nodos.add(ch);
                    g=arbol[indice].getg()+1;
                    hh=o_h(ch);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        }
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else{
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,change);
                    nodos.add(change);
                    ruta.push(actual);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    /////////////////////////
                    ////////////////////////
                    int cha[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            cha[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,cha);
                    ruta.push(actual);
                    nodos.add(cha);
                    g=arbol[indice].getg()+1;
                    hh=o_h(cha);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    actual+=1;
                    //////////////////
                    ///////////////////
                    int ch[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            ch[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,ch);
                    ruta.push(actual);
                    nodos.add(ch);
                    g=arbol[indice].getg()+1;
                    hh=o_h(ch);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
            }
            else if(i==0 && j==2){
                
                if(arbol[indice].getarriba()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getderecha()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else{
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    h=o_h(nodos.get(actual));
                    int g=arbol[indice].getg()+1;
                    f=h+g;
                    arbol[actual]=new nodo(false,true,false,false,false,h,g,f);
                    actual+=1;
                    ////////////////////////
                    int ch[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            ch[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,ch);
                    ruta.push(actual);
                    nodos.add(ch);
                    h=o_h(nodos.get(actual));
                    g=arbol[indice].getg()+1;
                    f=h+g;
                    arbol[actual]=new nodo(false,false,false,true,false,h,g,f);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
            }
            else if(i==1 && j==0){
                if(arbol[indice].getarriba()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    actual+=1;
                    /////////////////////
                    ////////////////////
                    int chan[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chan[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,chan);
                    ruta.push(actual);
                    nodos.add(chan);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chan);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getizquierda()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,change);
                    nodos.add(change);
                    ruta.push(actual);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    actual+=1;
                    /////////////
                    ////////////
                    int chan[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chan[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,chan);
                    ruta.push(actual);
                    nodos.add(chan);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chan);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getabajo()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    actual+=1;
                    ////////////////
                    ////////////////
                    int chan[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chan[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,chan);
                    ruta.push(actual);
                    nodos.add(chan);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chan);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else{
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    ////////////////
                    ////////////////
                    int chan[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chan[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,chan);
                    ruta.push(actual);
                    nodos.add(chan);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chan);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    actual+=1;
                    ////////////////////////
                    ////////////////////////
                    int ch[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            ch[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,ch);
                    ruta.push(actual);
                    nodos.add(ch);
                    g=arbol[indice].getg()+1;
                    hh=o_h(ch);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
            }
            else if(i==1 && j==1){
                if(arbol[indice].getarriba()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    //////////////
                    /////////////
                    int chang[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chang[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,chang);
                    ruta.push(actual);
                    nodos.add(chang);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chang);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    actual+=1;
                    /////////////
                    ////////////
                    int cha[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            cha[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,cha);
                    ruta.push(actual);
                    nodos.add(cha);
                    g=arbol[indice].getg()+1;
                    hh=o_h(cha);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getabajo()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    /////////////////
                    ////////////////
                    int chang[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chang[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,chang);
                    ruta.push(actual);
                    nodos.add(chang);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chang);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    actual+=1;
                    //////////////////////
                    /////////////////////
                    int cha[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            cha[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,cha);
                    ruta.push(actual);
                    nodos.add(cha);
                    g=arbol[indice].getg()+1;
                    hh=o_h(cha);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getderecha()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    ////////////////
                    /////////////////
                    int chang[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chang[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,chang);
                    ruta.push(actual);
                    nodos.add(chang);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chang);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    actual+=1;
                    //////////////
                    //////////////
                    int chan[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chan[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,chan);
                    ruta.push(actual);
                    nodos.add(chan);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chan);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getizquierda()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    //////////////////
                    /////////////////
                    int chang[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chang[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,chang);
                    ruta.push(actual);
                    nodos.add(chang);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chang);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    actual+=1;
                    //////////////////////////
                    ////////////////////////
                    int chan[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chan[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,chan);
                    ruta.push(actual);
                    nodos.add(chan);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chan);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else{
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    ///////////////////
                    ///////////////////
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    int chang[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chang[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,chang);
                    ruta.push(actual);
                    nodos.add(chang);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chang);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    ///////////
                    //////////
                    actual+=1;
                    int chan[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chan[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,chan);
                    ruta.push(actual);
                    nodos.add(chan);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chan);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    actual+=1;
                    ////////////
                    ///////////
                    int cha[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            cha[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,cha);
                    ruta.push(actual);
                    nodos.add(cha);
                    g=arbol[indice].getg()+1;
                    hh=o_h(cha);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
            }
            else if(i==1 && j==2){
                
                if(arbol[indice].getarriba()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    ////////////
                    ///////////
                    int chan[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chan[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,chan);
                    ruta.push(actual);
                    nodos.add(chan);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chan);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getderecha()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    ////////////
                    ///////////
                    int chan[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chan[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,chan);
                    ruta.push(actual);
                    nodos.add(chan);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chan);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getabajo()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(nodos.get(actual));
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    //////////////
                    /////////////
                    int chan[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chan[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    
                    movAb(i,j,chan);
                    ruta.push(actual);
                    nodos.add(chan);
                    g=arbol[indice].getg()+1;
                    hh=o_h(nodos.get(actual));
                    
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else{
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    int chang[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chang[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movAb(i,j,chang);
                    ruta.push(actual);
                    nodos.add(chang);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chang);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    int chan[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chan[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,chan);
                    ruta.push(actual);
                    nodos.add(chan);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chan);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
            }
            else if(i==2 && j==0){
                if(arbol[indice].getizquierda()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,false,true,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getabajo()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else{
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(nodos.get(actual));
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    int ch[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            ch[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,ch);
                    ruta.push(actual);
                    nodos.add(ch);
                    hh=o_h(ch);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    
                    indice=actual;
                    
                    int min=arbol[actual].getf();
                   
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
            }
            else if(i==2 && j==1){
                if(arbol[indice].getabajo()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    int chang[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chang[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,chang);
                    ruta.push(actual);
                    nodos.add(chang);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chang);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getderecha()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    int chang[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chang[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,chang);
                    ruta.push(actual);
                    nodos.add(chang);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chang);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getizquierda()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    int chang[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chang[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,chang);
                    ruta.push(actual);
                    nodos.add(chang);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chang);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else{
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    int chang[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chang[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,chang);
                    ruta.push(actual);
                    nodos.add(chang);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chang);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    actual+=1;
                    int chan[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chan[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movD(i,j,chan);
                    ruta.push(actual);
                    nodos.add(chan);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chan);
                    arbol[actual]=new nodo(true,false,false,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
            }
            else if(i==2 && j==2){
                if(arbol[indice].getabajo()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else if(arbol[indice].getderecha()==true){
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        } 
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }
                else{
                    int change[][]={{0,0,0},{0,0,0},{0,0,0}};
                    arbol[indice].setdespliegue(true);
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            change[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movI(i,j,change);
                    ruta.push(actual);
                    nodos.add(change);
                    int g=arbol[indice].getg()+1;
                    int hh=o_h(change);
                    arbol[actual]=new nodo(false,true,false,false,false,g,hh,g+hh);
                    actual+=1; //para agregar otro nodo, sumo 1 al indice actual
                    int chang[][]={{0,0,0},{0,0,0},{0,0,0}};
                    for(int I=0;I<3;I++){
                        for(int J=0;J<3;J++){
                            chang[I][J]=nodos.get(indice)[I][J]+modulo[I][J];
                        }
                    }
                    movA(i,j,chang);
                    ruta.push(actual);
                    nodos.add(chang);
                    g=arbol[indice].getg()+1;
                    hh=o_h(chang);
                    arbol[actual]=new nodo(false,false,true,false,false,g,hh,g+hh);
                    indice=actual;
                    int min=arbol[actual].getf();
                    for(int c=0;c<=actual;c++){
                        if(arbol[c].getf()<=min && arbol[c].getdespliegue()==false){
                            min=arbol[c].getf();
                            indice=c;
                        }
                    }
                    while((int) ruta.peek()>indice){
                        ruta.pop();
                    }
                }  
            }
            int[][] current=nodos.get(indice);
            for(int a=0;a<3;a++){
                for(int b=0;b<3;b++){
                    if(current[a][b]==0){
                        i=a;
                        j=b;
                    }
                }   
            }
           actual++;
           heuristica=o_h(nodos.get(indice));
           if(heuristica==0){
                corrent=nodos.get(indice);
                for(int I=0;I<3;I++){
                    System.out.print("\n");
                    for(int J=0;J<3;J++){
                        System.out.print(corrent[I][J]);
                    }
                }
           }
        }
        System.out.print("\n");
        
        long endTime2 = (long) ((System.nanoTime()-startTime2)/1e6);
        MemoryUsage afterHeapMemoryUsage2 = mbean.getHeapMemoryUsage();
        long consumed2 =  afterHeapMemoryUsage2.getUsed() - beforeHeapMemoryUsage2.getUsed();
        System.out.print("Tiempo BPL: "+endTime+"ms");
        System.out.print("\n");
        System.out.print("Tiempo A*: "+endTime2+"ms");
        System.out.print("\n");
        System.out.println("Memoria consumida BPL:" + consumed+" Bytes");
        System.out.println("Memoria consumida A*:" + consumed2+" Bytes");
        
        
        /*while (!ruta.empty()){
            int t=(int)ruta.peek();
            int corrent[][]=nodos.get(t);
            for(int I=0;I<3;I++){
                System.out.print("\n");
                for(int J=0;J<3;J++){
                System.out.print(corrent[I][J]);
                }
            }
            System.out.print("\n");
            ruta.pop();
        }*/
        //System.out.println(ruta.pop());
        
    }
    
}
