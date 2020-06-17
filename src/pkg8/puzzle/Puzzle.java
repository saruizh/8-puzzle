/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;

import java.util.Random;

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
    
        
    public static void main(String[] args) {
        //defino puzzle como una matriz 3*3
        int [][] A = {{1,2,3},{4,5,6},{7,8,0}};
        int i=2;
        int j=2;
        
        boolean arriba=false;
        boolean abajo=false;
        boolean derecha=false;
        boolean izquierda=false;
        boolean ref=false;
        
        //hago el desorden con 14 movimientos
        
        for(int cont=0;cont<6;cont++){
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
        
        ///se hace búsqueda A*
        
        
        // se hacen matriz de comparacion
        int [][] B={{0,0,0},{0,0,0},{0,0,0}};
        int [][] C={{0,0,0},{0,0,0},{0,0,0}};
        int [][] D={{0,0,0},{0,0,0},{0,0,0}};
        int [][] E={{0,0,0},{0,0,0},{0,0,0}};
        int [][] original={{1,2,3},{4,5,6},{7,8,0}};
        //h es el numero de movimientos que debe hacerse para llegar al real, este es el numero que se optimiza
        //g es la profundidad del arbol
        //f=g+h
        System.out.print("AHORA SE HACE EL MOVIMIENTO, PUTITOS");
        int h=0,h1=0,h2=0,h3=0,h4=0;
        int g;
        int f;
        boolean unsolved=true;
        
        //se mete esto en un while/// me salgo de wuile cuando encuentre la soluciòn
        //se hace un booleano y cuando lo encuentre booleano es falso y se sale del while
        int Bet=0;
        /*
        while(Bet<14){
            unsolved=comparar(A,original);
            if(unsolved==false){
                break;
            }
            if(i==0 && j==0){
                clonar(A,B);
                movAb(i,j,B);
                h1=h(B,original);
                /////////////////
                clonar(A,C);
                movD(i,j,C);
                h2=h(C,original);
                if(h1<=h2){
                    clonar(B,A);
                }
                else{
                    clonar(C,A);
                }
                for(int a=0;a<3;a++){
                    for(int b=0;b<3;b++){
                        if(A[a][b]==0){
                            i=a;
                            j=b;
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
            else if(i==0 && j==1){
                //mueve para iz,abajo,derecha
                clonar(A,B);
                movI(i,j,B);
                h1=h(B,original);
                
                clonar(A,C);
                movAb(i,j,C);
                h2=h(C,original);
                
                clonar(A,D);
                movD(i,j,D);
                h3=h(C,original);
                
                int num=menordetres(h1,h2,h3); //hallo el menor de los 3 numeros
                
                if(num==h1){
                    clonar(B,A);
                }
                if(num==h2){
                    clonar(C,A);
                }
                if(num==h3){
                    clonar(D,A);
                }
                for(int a=0;a<3;a++){
                    for(int b=0;b<3;b++){
                        if(A[a][b]==0){
                            i=a;
                            j=b;
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
            else if(i==0 && j==2){
                
                clonar(A,B);
                movAb(i,j,B);
                h1=h(B,original);
                /////////////////
                clonar(A,C);
                movI(i,j,C);
                h2=h(C,original);
                if(h1<=h2){
                    clonar(B,A);
                }
                else{
                    clonar(C,A);
                }
                for(int a=0;a<3;a++){
                    for(int b=0;b<3;b++){
                        if(A[a][b]==0){
                            i=a;
                            j=b;
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
            else if(i==1 && j==0){
                
                clonar(A,B);
                movA(i,j,B);
                h1=h(B,original);
                
                clonar(A,C);
                movAb(i,j,C);
                h2=h(C,original);
                
                clonar(A,D);
                movD(i,j,D);
                h3=h(C,original);
                
                int num=menordetres(h1,h2,h3); //hallo el menor de los 3 numeros
                
                if(num==h1){
                    clonar(B,A);
                }
                if(num==h2){
                    clonar(C,A);
                }
                if(num==h3){
                    clonar(D,A);
                }
                for(int a=0;a<3;a++){
                    for(int b=0;b<3;b++){
                        if(A[a][b]==0){
                            i=a;
                            j=b;
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
            else if(i==1 && j==1){
                
                clonar(A,B);
                movA(i,j,B);
                h1=h(B,original);
                
                clonar(A,C);
                movAb(i,j,C);
                h2=h(C,original);
                
                clonar(A,D);
                movD(i,j,D);
                h3=h(C,original);
                
                clonar(A,E);
                movI(i,j,E);
                h4=h(E,original);
                
                int num=menordecuatro(h1,h2,h3,h4); //hallo el menor de los 4 numeros
                
                if(num==h1){
                    clonar(B,A);
                }
                if(num==h2){
                    clonar(C,A);
                }
                if(num==h3){
                    clonar(D,A);
                }
                if(num==h4){
                    clonar(E,A);
                }
                
                for(int a=0;a<3;a++){
                    for(int b=0;b<3;b++){
                        if(A[a][b]==0){
                            i=a;
                            j=b;
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
            else if(i==1 && j==2){
                
                clonar(A,B);
                movA(i,j,B);
                h1=h(B,original);
                
                clonar(A,C);
                movAb(i,j,C);
                h2=h(C,original);
                
                clonar(A,D);
                movI(i,j,D);
                h3=h(C,original);
                
                int num=menordetres(h1,h2,h3); //hallo el menor de los 3 numeros
                
                if(num==h1){
                    clonar(B,A);
                }
                if(num==h2){
                    clonar(C,A);
                }
                if(num==h3){
                    clonar(D,A);
                }
                for(int a=0;a<3;a++){
                    for(int b=0;b<3;b++){
                        if(A[a][b]==0){
                            i=a;
                            j=b;
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
            else if(i==2 && j==0){
                
                clonar(A,B);
                movA(i,j,B);
                h1=h(B,original);
                /////////////////
                clonar(A,C);
                movD(i,j,C);
                h2=h(C,original);
                if(h1<=h2){
                    clonar(B,A);
                }
                else{
                    clonar(C,A);
                }
                for(int a=0;a<3;a++){
                    for(int b=0;b<3;b++){
                        if(A[a][b]==0){
                            i=a;
                            j=b;
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
            else if(i==2 && j==1){
                
                clonar(A,B);
                movA(i,j,B);
                h1=h(B,original);
                
                clonar(A,C);
                movD(i,j,C);
                h2=h(C,original);
                
                clonar(A,D);
                movI(i,j,D);
                h3=h(C,original);
                
                int num=menordetres(h1,h2,h3); //hallo el menor de los 3 numeros
                
                if(num==h1){
                    clonar(B,A);
                }
                if(num==h2){
                    clonar(C,A);
                }
                if(num==h3){
                    clonar(D,A);
                }
                for(int a=0;a<3;a++){
                    for(int b=0;b<3;b++){
                        if(A[a][b]==0){
                            i=a;
                            j=b;
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
            else if(i==2 && j==2){
                clonar(A,B);
                movA(i,j,B);
                h1=h(B,original);
                /////////////////
                clonar(A,C);
                movI(i,j,C);
                h2=h(C,original);
                if(h1<=h2){
                    clonar(B,A);
                }
                else{
                    clonar(C,A);
                }
                for(int a=0;a<3;a++){
                    for(int b=0;b<3;b++){
                        if(A[a][b]==0){
                            i=a;
                            j=b;
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
            
            Bet++;
        }
        */
        
        
        
        
        
        
        
    }
    
}
