package op21.chess;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessBoard {
	private List<Piece> pieces;
	private final int rows, columns;
	
	public ChessBoard(int rows, int columns) {
		super();
		this.pieces = new LinkedList<Piece>();
		this.rows = rows;
		this.columns = columns;
	}
	
	public void populate(Piece p){
		this.pieces.add(p);
	}
	public ChessBoard branch(Piece p){
		ChessBoard result = new ChessBoard(this.rows, this.columns);
		result.pieces = new LinkedList<Piece>(this.pieces);
		result.populate(p);
		return result;
	}
	public Optional<Piece> rollback(){
		if(this.pieces.size()>0){
			return Optional.of(this.pieces.remove(this.pieces.size()-1));
		}else{
			return Optional.empty();
		}
	}

	public int getRows() {
		return rows;
	}
	public int getColumns() {
		return columns;
	}

	public List<Cell> checks(){
		return this.pieces.stream()
				.map(e -> e.check(this))
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				if(this.empty(Cell.of(i,  j))){
					builder.append("-");
				}else{
					builder.append("O");
				}
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	public boolean empty(Cell c) {
		return this.pieces.stream().noneMatch(e -> e.getCell().equals(c));
	}

	public boolean contains(Cell c) {
		return c.getColumn() >= 0 
				&& c.getColumn() < this.columns
				&& c.getRow() >= 0
				&& c.getRow() < this.rows;
	}
	public boolean isValid(){
		return this.pieces.stream().allMatch(e -> !e.isInvalid());
	}
	public void reset(){
		this.pieces.stream().forEach(e -> e.reset());
	}	
	public int population(){
		return this.pieces.size();
	}
	
}
