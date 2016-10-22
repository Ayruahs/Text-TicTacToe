import java.util.Scanner;
public class TTT{
   public static char[][] tictactoe ;
   public static int row, col ;
   public static Scanner in = new Scanner(System.in) ;
   public static char player = 'O'; 
   public static boolean gameOver ;
   public static boolean tie ;

   public static void main(String[] args){
       tictactoe = new char[3][3] ;
       gameOver = false ;
       drawGame() ;
       
       playGame() ;
    }
    
    public static void drawGame(){
        for(int i = 0; i < 3; i++){
            System.out.println() ;
            for(int j = 0; j < 3; j++){
                if(j == 0){
                    System.out.print("| ") ;
                }
                tictactoe[i][j] = '_' ;
                
                System.out.print(tictactoe[i][j] + " | ") ;
            }
        }
        System.out.println() ;

    }
    public static void cellLocation(){
       System.out.print("Enter row: ") ;
       row = in.nextInt() - 1;
       System.out.print("Enter column: ") ;
       col = in.nextInt() - 1;
    }
    public static void playerChange(){
        if (player == 'X'){
            player = 'O' ;
            return ;
        }
        player = 'X' ;
        return ;
    }
    public static boolean GameStatus(int rowNum, int colNum){
       if(tictactoe[rowNum][0]==tictactoe[rowNum][1] 
            && tictactoe[rowNum][0]==tictactoe[rowNum][2]){
                return true;
       }
       if(tictactoe[0][colNum]==tictactoe[1][colNum] 
            && tictactoe[0][colNum]==tictactoe[2][colNum]){
                return true;
       }
       if(tictactoe[0][0]==tictactoe[1][1] && tictactoe[0][0]==tictactoe[2][2]
           && tictactoe[1][1]!='_'){
               return true;
           }
       if(tictactoe[0][2]==tictactoe[1][1] && tictactoe[0][2]==tictactoe[2][0]
           && tictactoe[1][1]!='_'){
               return true;
           }  
       return false;   
    } 
    public static void tictactoeChange(int rowNum, int colNum){
        tictactoe[rowNum][colNum] = player ;
        for(int i = 0; i < 3; i++){
            System.out.println() ;
            for (int j = 0; j < 3; j++){
                if (j == 0)
                    System.out.print("| ");
                System.out.print(tictactoe[i][j] + " | ") ;
            }
        } 
        System.out.println() ;    
    }
    public static boolean inRange(int rowNum, int colNum){
        if((rowNum < 0) || (rowNum > 2) || (colNum < 0) || (colNum > 2)){
            System.out.println("Please enter values between 1 and 3 for row and column.") ;
            cellLocation();
            return inRange(row, col) ;
        }
        return true;
    }
    public static boolean cellEmpty(int rowNum, int colNum){
           if(tictactoe[row][col] == '_'){
               return true;
           }else {
               System.out.println() ;
               System.out.println("That cell is already filled! Enter values for another cell.") ;
               cellLocation() ;
               return cellEmpty( row, col) ;
           }   
    }
    public static void playGame(){
       while (!gameOver){
           playerChange() ;
           
           System.out.println("It is player " + player + "'s turn.'") ;
           
           cellLocation() ;

           boolean validRange = inRange(row, col) ;         //program will only proceed when validRange is true
           boolean isCellEmpty = cellEmpty(row,col) ;       //program will only proceed when isCellEmpty is true 

           tictactoeChange(row, col) ;
                      
           gameOver = GameStatus(row, col) ;
           
           int ctr = 0 ;
           for(int i = 0; i < 3; i++){
               for(int j = 0; j < 3; j++){                      
                   if (tictactoe[i][j] == '_')                          
                       ctr += 1 ;
               } 
           }           

           if(ctr == 0 && (!gameOver)){
               gameOver = true ;
               tie = true ;
           }                                  
       }
       if(!tie)
           System.out.println("Player " + player + " wins!") ;
       else
           System.out.println("Game is a draw.") ;             
    }
}