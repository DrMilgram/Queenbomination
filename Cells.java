package op21.chess.main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import op21.chess.Cell;
import op21.chess.ChessBoard;

public class Cells implements Iterator<Cell>{
	
	private List<Cell> cells = new LinkedList<Cell>();
	private ChessBoard cb;
	
	private void reset(){
		for(int i = 0; i < this.cb.getRows(); i++){
			for(int j = 0; j < this.cb.getColumns(); j++){
				this.cells.add(Cell.of(i,  j));
			}
		}
	}

	public Cells(ChessBoard cb){
		this.cb = cb;
		this.reset();
	}
	
	public Cells(Cells cs) {
		this.cells = new LinkedList<Cell>(cs.cells);
		this.cb = cs.cb;
	}

	public boolean blacklist(List<Cell> banned){
		return this.cells.removeAll(banned);
	}
	public boolean blacklist(Cell banned){
		return this.cells.remove(banned);
	}

	@Override
	public boolean hasNext() {
		return !this.cells.isEmpty();
	}

	@Override
	public Cell next() {
		return this.cells.remove(0);
	}
	


}
