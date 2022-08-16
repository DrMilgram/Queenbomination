package op21.chess;

import java.util.List;

public abstract class Piece {
	private Cell cell;
	protected boolean invalid = false;

	public Piece(Cell cell) {
		super();
		this.cell = cell;
	}

	public Cell getCell(){
		return this.cell;
	}
	public boolean isInvalid() {
		return this.invalid;
	}
	public void setInvalid() {
		this.invalid = true;
	}
	public void reset(){
		this.invalid = false;
	}
	
	public abstract List<Cell> check(ChessBoard cb);

	
}
