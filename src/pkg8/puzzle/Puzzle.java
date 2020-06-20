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
        //cambio A[i][j] por A[i][j+1]
        int l=A[i][j];
        A[i][j]=A[i][j+1];
        A[i][j+1]=l;
        
    }
    public static void movI(int i, int j, int A[][]){
        //cambio A[i][j] por A[i][j-1]
        int l=A[i][j];
        A[i][j]=A[i][j-1];
        A[i][j-1]=l;
        
    }
    public static void movA(int i, int j, int A[][]){
        //cambio A[i][j] por A[i-1][j]
        int l=A[i][j];
        A[i][j]=A[i-1][j];
        A[i-1][j]=l;
        
    }
    public static void movAb(int i, int j, int A[][]){
        //cambio A[i][j] por A[i+1][j]
        int l=A[i][j];
        A[i][j]=A[i+1][j];
        A[i+1][j]=l;        
    }  
    
    public static int aleatorio(int dimension){
        Random r = new Random();
        return r.nextInt(dimension)+1;
    }
    public static void clonar(int A[][],int comparacion[][]){
        
        for(int a=0;a<3;a++){
                for(int b=0;b<3;b++){
                    comparacion[a][b]=A[a][b];         
                }
            }
    }
    public static boolean comparar(int A[][],int B[][]){
        boolean compara;
        int k=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(A[i][j]==B[i][j]){
                    k++;
                }
            }
        }
        if(k==9){
            compara=false;
            return compara;
        }
        else{
            compara=true;
            return compara;
        }
    }
    public static int menordetres(int h1,int h2,int h3){
        int menor=h1;
        if(h1>h2){
            menor=h2;
            if(h2>h3){
                menor=h3;
            }
        }
        else{
            if(h1>h3){
                menor=h3;
            }
        }
        return menor;
    }
    public static int menordecuatro(int h1, int h2, int h3, int h4){
        int []A={h1,h2,h3,h4};
        int menor=A[0];
        for(int i = 0; i < A.length; i++)
        {
            if(menor>A[i])
            {
                    menor=A[i];
            }
        }
        return menor;
    }
    
        
    public static int h(int comparacion[][], int original[][]){
        int h=0;
            for(int a=0;a<3;a++){
                for(int b=0;b<3;b++){
                    if(comparacion[a][b]!=original[a][b]){
                        if(comparacion[a][b]==1){
                            h+=a+b;
                        }
                        else if(comparacion[a][b]==2){
                            h+=a+Math.abs(b-1); 
                        }
                        else if(comparacion[a][b]==3){
                            h+=Math.abs(a-0)+Math.abs(b-2);
                        }
                        else if(comparacion[a][b]==4){
                            h+=Math.abs(a-1)+Math.abs(b-0);
                        }
                        else if(comparacion[a][b]==5){
                            h+=Math.abs(a-1)+Math.abs(b-1);
                        }
                        else if(comparacion[a][b]==6){
                            h+=Math.abs(a-1)+Math.abs(b-2);
                        }
                        else if(comparacion[a][b]==7){
                            h+=Math.abs(a-2)+Math.abs(b-0);
                        }
                        else if(comparacion[a][b]==8){
                            h+=Math.abs(a-2)+Math.abs(b-1);
                        } 
                    }  
                }
            }
        return h;
    }
    
    public static int[][] BPL(int nodo[][], int objetivo[][], int profundidad, int i, int j, boolean arriba,boolean abajo,boolean derecha,boolean izquierda,Stack pila){ 
        if(profundidad >= 0){
            if(nodo == objetivo){
                return nodo;
            }
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    movI(i,j,nodo);
                    j=j-1;
                    derecha=false;
                    movAb(i,j,nodo);
                    i=i+1;
                    abajo=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                        movAb(i,j,nodo);
                        i=i+1;
                        arriba=false;
                        abajo=true;
                        movAb(i,j,nodo);
                        i=i+1;
                        pila.push(nodo);
                        BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    movAb(i,j,nodo);
                    i=i+1;
                    movD(i,j,nodo);
                    j=j+1;
                    arriba=false;
                    derecha=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    movA(i,j,nodo);
                    i=i-1;
                    movD(i,j,nodo);
                    j=j+1;
                    abajo=false;
                    derecha=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    movI(i,j,nodo);
                    j=j-1;
                    movAb(i,j,nodo);
                    i=i+1;
                    derecha=false;
                    abajo=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    movD(i,j,nodo);
                    j=j+1;
                    movA(i,j,nodo);
                    i=i-1;
                    izquierda=false;
                    arriba=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    movAb(i,j,nodo);
                    i=i+1;
                    arriba=false;
                    movAb(i,j,nodo);
                    i=i+1;
                    abajo=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    movA(i,j,nodo);
                    i=i-1;
                    abajo=false;
                    movD(i,j,nodo);
                    j=j+1;
                    derecha=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    abajo=true;
                    movAb(i,j,nodo);
                    i=i+1;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    movA(i,j,nodo);
                    i=i-1;
                    abajo=false;
                    movA(i,j,nodo);
                    i=i-1;
                    arriba=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    movI(i,j,nodo);
                    j=j-1;
                    derecha=false;
                    movA(i,j,nodo);
                    i=i-1;
                    arriba=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                }
                else if(izquierda=true){
                    profundidad=profundidad-1;
                    movI(i,j,nodo);
                    j=j-1;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
                    movD(i,j,nodo);
                    j=j+1;
                    izquierda=false;
                    movD(i,j,nodo);
                    j=j+1;
                    derecha=true;
                    pila.push(nodo);
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
                    BPL(nodo,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
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
        return nodo;
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
        //boolean ref=false;
        
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
        
        // se hace una pila en donde se guardan los nodos del arbol:
        // A 
        Stack pila = new Stack();
        pila.push(A);
        /*while(!pila.isEmpty()){
            System.out.println(pila.peek().toString());
            pila.pop();
            //pila.peek().toString();
        }*/    
        
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
        
        int [][] nod=BPL(A,objetivo,profundidad,i,j,arriba,abajo,derecha,izquierda,pila);
        for(int k=0;k<3;k++){
            System.out.print("\n");
            for(int l=0;l<3;l++){
                System.out.println(nod[k][l]);
            }
        }
        
            
        while(!pila.isEmpty()){
            System.out.println(pila.peek());
            pila.pop();
        }
    }
    
}
