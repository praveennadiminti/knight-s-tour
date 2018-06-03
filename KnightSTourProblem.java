
package knight.s.tour.problem;
class Position{
    public int x;
    public int y;
    Position(int a,int b){
        x = a;
        y = b;
    }
}

public class KnightSTourProblem {
    public static void place_knight(Position p, int[][] chessBoard, int count){
        chessBoard[p.x][p.y] = count;
    }
    
    public static boolean isValid(Position next_p,int[][] chessBoard){
        if( next_p.x<8 && next_p.x >=0 && next_p.y <8 && next_p.y >= 0){
            if( chessBoard[next_p.x][next_p.y] == 0)return true;
            else return false;
        }else return false;
    }
    
    public static boolean isSolvable(Position p,int[][] chessBoard,int count,
            int[] nextX, int[] nextY){
        if(count == 65){
            //print the board and return true;
            for(int i =0; i<8; i++){
                for(int j =0; j<8; j++){
                    System.out.print(chessBoard[i][j]+ "\t");
                }
                System.out.println();
            }
            return true;
        }else{
            for(int k =0; k<8; k++){
                Position next_p = new Position(p.x + nextX[k],p.y + nextY[k]);
                if(isValid(next_p, chessBoard)){
                    place_knight(next_p, chessBoard, count);
                    count++;
                    if(isSolvable(next_p, chessBoard, count, nextX, nextY)){
                        return true;
                    }else{
                        chessBoard[next_p.x][next_p.y] = 0;
                    }
                    count--;
                }
            }
            return false;
        }
    }
    
    public static void solve_tour(){
        //first create the chess board.
        int[][] chessBoard = new int[8][8];
        int count = 1;
        Position p = new Position(4,3); 
        int[] nextX = {2,2,-2,-2,1,1,-1,-1};
        int[] nextY = {1,-1,1,-1,2,-2,2,-2};
        
        //Place the knight 
        place_knight(p,chessBoard,count);
        count++;
        //check the next position.
        for(int k=0; k<8; k++){
            //check if it is a valid position.
            //if it is valid position place it and check whether it is solvable.
            Position next_p = new Position(p.x+nextX[0],p.y+nextY[0]);
            if( isValid(next_p, chessBoard)){
                place_knight(next_p,chessBoard,count);
                count++;
                if(!isSolvable(next_p, chessBoard,count,nextX,nextY)){
                    chessBoard[next_p.x][next_p.y] = 0;
                }else{
                    break;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        solve_tour();
    }
    
}
