/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author sarui
 */
public class Puzzle {
    
    public static void movD(int i, int j, int A[][]){
        int l=A[i][j];
        A[i][j]=A[i][j+1];
        A[i][j+1]=l; 
    }
    public static void movI(int i, int j, int A[][]){
        int l=A[i][j];
        A[i][j]=A[i][j-1];
        A[i][j-1]=l;
        
    }
    public static void movA(int i, int j, int A[][]){
        int l=A[i][j];
        A[i][j]=A[i-1][j];
        A[i-1][j]=l;
    }
    public static void movAb(int i, int j, int A[][]){
        int l=A[i][j];
        A[i][j]=A[i+1][j];
        A[i+1][j]=l;        
    }
    public static int aleatorio(int dimension){
        Random r = new Random();
        return r.nextInt(dimension)+1;
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
        //hago el desorden con 14 movimientos
        for(int cont=0;cont<2;cont++){
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
        
        
        
        
        
        
        System.out.print("se hace el arbol");
        // se hace una pila en donde se guardan los nodos del arbol:
        // A 
        Stack pila = new Stack();
        pila.push(A);
        
        int [][] objetivo = {{1,2,3},{4,5,6},{7,8,0}};
        int profundidad=2;
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
        Stack pila2=BPL(A,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
        
        
        
        
        
    }
    
}
