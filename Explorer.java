package op21.chess;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import op21.chess.Cell;
import op21.chess.ChessBoard;
import op21.chess.Piece;
import op21.chess.Queen;

public class Explorer {
	private final int population;
	
	public Explorer(int population) {
		this.population = population;
	}
	
	private List<ChessBoard> explore(ChessBoard cb, Cells cs){
		List<ChessBoard> result = new LinkedList<ChessBoard>();
		cs = cs == null? new Cells(cb): cs;
		
		while(cs.hasNext()){
			Cell next = cs.next();
			Queen q = new Queen(next);
			ChessBoard tmp = cb.branch(q);
			Cells tcs = new Cells(cs);
			
			tcs.blacklist(tmp.checks());
			if(tmp.population() == this.population){
				result.add(tmp);
			}
			result.addAll(explore(tmp, tcs));
		}
		
		return result;
	}
	public List<ChessBoard> start(ChessBoard cb){
		return explore(cb, null);
	}
	
	public static void main(String[] args) {
		ChessBoard cb = new ChessBoard(8, 8);
		Explorer ex = new Explorer(8);
		List<ChessBoard> result = ex.start(cb);
		
		System.out.println(result.size());
		
	}
}
