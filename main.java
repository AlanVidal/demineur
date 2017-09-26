
import java.util.Scanner;

public class main{

    public static boolean[][] mask = new boolean[10][10];
    public static int[][] resProxy = new int[10][10];

    public static void afficherTab(boolean[][] res){
      String lineX =""; // A deplacer dans une seconde fonction
        for(int i =0; i < 10; i++){
            for(int ii =0; ii < 10; ii++){
                String separ = " |";
                if(res[i][ii]){separ = "  |";} 
                lineX = lineX + res[i][ii] + separ ;

            }
            lineX = lineX + "\n";
        }
        System.out.println(lineX);
    
    }
    public static void afficherTab(int[][] res){
      String lineX =""; // A deplacer dans une seconde fonction
        for(int i =0; i < 10; i++){
            for(int ii =0; ii < 10; ii++){
                lineX = lineX + res[i][ii] +" | " ;
            }
            lineX = lineX + "\n";
        }
        System.out.println(lineX);
    
    }
    public static int[][] proxyMines(boolean[][] res){
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                    int compteur = 0;
                    
                    if(y < 9 && res[x][y+1]){
                        compteur ++;
                    }
                     if(y > 0 && res[x][y-1]){
                        compteur ++;
                    }
                     if(x > 0 && res[x-1][y]){
                        compteur ++;
                    }
                     if( x < 9 && res[x+1][y] ){
                        compteur ++;
                    }
                     if( x < 9 && y < 9 && res[x+1][y+1] ){
                        compteur ++;
                    }
                     if( x > 0 &&  y > 0 && res[x-1][y-1] ){
                        compteur ++;
                    }
                    
                    resProxy[x][y] = compteur; 
            }        
            
        }
        afficherTab(resProxy);
        return resProxy;

    }
    
    
    public static boolean[][] creerMines(){
        int nbMines = 20;
        int x;
        int y;
        boolean[][] res = new boolean[10][10];
        
        while(nbMines > 0){
            x = (int) (Math.random()*10);
            y = (int) (Math.random()*10);
            if(! res[x][y]){
            
                res[x][y] = true;
                nbMines--;
            }
        }
        afficherTab(res);
        return res;
    }
    
    public static int[] choixJoueur(Scanner scan){
        int[] res = new int[2];
        String input;
        boolean ok = true;
        System.out.println("Entrez les deux coordonnées séparées par une virgule");
        do{
            ok = true;
            input = scan.nextLine();
            String[] stab = input.split(",");
            if (stab.length != 2){
                ok = false;
                System.out.println("Vous devez rentrer les coordonnées séparées par une virgule.");
            }
            try{
                for (int i=0; i<2; i++){
                    res[i] = Integer.parseInt(stab[i].trim());
                    if (res[i]<0 || res[i]>9){
                        System.out.println("Les nombres entrés doivent être compris entre 0 et 9");
                        ok = false;
                    }
                }
            }catch(NumberFormatException ex){
                System.out.println("Vous devez rentrer deux nombres séparés par une virgule.");
                ok = false;
            }

        }while(!ok);
        return res;
    }
   
    
    public static boolean[][] masque(boolean[][] resA, int[] choixA){
    
        System.out.println(resA[choixA[0]][choixA[1]]);
        
        
        
        if(resA[choixA[0]][choixA[1]] ) {
            return null; 
        }
        
        boolean vecteur = false;
        int x = choixA[1];
        int y = choixA[0];
        while(vecteur == false && x < 9){
            x++;
            if(resProxy[x][y] > 0){
                mask[x][y] = true; 
            
            }else{
                break;
            }

        }
        vecteur = false;
        
        while( vecteur == false && y < 9){
            y++;
            if(resProxy[x][y] > 0){
                mask[x][y] = true; 
            
            }else{
                break;
            }

        }
        
        while( vecteur == false && y > 0){
            y--;
            if(resProxy[x][y] > 0){
                mask[x][y] = true; 
            
            }else{
                break;
            }

        }

        while( vecteur == false && x > 0){
            x--;
            if(resProxy[x][y] > 0){
                mask[x][y] = true; 
            
            }else{
                break;
            }

        }
        
        while(vecteur == false && x < 9 && y < 9){
            if(x<9){x++;}
            if(x<9){y++;}
            if(resProxy[x][y] > 0){
                mask[x][y] = true; 
            
            }else{
                break;
            }

        }
        
        while(vecteur == false && x > 0 && y > 0){
            if(x>0){x--;}
            if(x>0){y--;}
            if(resProxy[x][y] > 0){
                mask[x][y] = true; 
            
            }else{
                break;
            }

        }
        
        return resA;
    }
    
    /*
    public static void affichageFinal(){
    
         String lineX =""; 
        for(int i =0; i < 10; i++){
            for(int ii =0; ii < 10; ii++){
                String separ = " |";
                if(res[i][ii]){separ = "  |";} 
                if(mask[i][ii]){ lineX = lineX + resProxy[i][ii];}
                lineX = lineX + res[i][ii] + separ ;
                
            }
            lineX = lineX + "\n";
        }
        System.out.println(lineX);
    
    }
    */
    
    
    }
    
     
    public static void main(String[] args){
       boolean[][] res = creerMines();
        proxyMines(res);
        Scanner sc = new Scanner(System.in);
        while(true){
            afficherTab(mask);
            
            
            int[] choix = new int[2];
            choix =choixJoueur(sc);
            
            if(masque(res, choix) == null){
                System.out.println("BOOM ! ");
                break;
            }

        
        }
    
    }
}
