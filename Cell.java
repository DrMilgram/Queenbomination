package op21.chess;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Cell {
	private int row;
	private int column;
	
	private Cell(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	
	public Cell left(){
		return Cell.of(this.getRow()-1, this.getColumn());
	}
	public Cell right(){
		return Cell.of(this.getRow()+1, this.getColumn());
	}
	public Cell up(){
		return Cell.of(this.getRow(), this.getColumn()-1);
	}
	public Cell down(){
		return Cell.of(this.getRow(), this.getColumn()+1);
	}
	public Cell leftup(){
		return Cell.of(this.getRow()-1, this.getColumn()-1);
	}
	public Cell rightup(){
		return Cell.of(this.getRow()+1, this.getColumn()-1);
	}
	public Cell leftdown(){
		return Cell.of(this.getRow()-1, this.getColumn()+1);
	}
	public Cell rightdown(){
		return Cell.of(this.getRow()+1, this.getColumn()+1);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[row=");
		builder.append(row);
		builder.append(", column=");
		builder.append(column);
		builder.append("]");
		return builder.toString();
	}
	
	private static List<Cell> cells = new LinkedList<Cell>();
	public static Cell of(int row, int column){
		Optional<Cell> candidate = cells.stream().filter(e -> e.getRow() == row && e.getColumn() == column).findFirst();
		if(candidate.isPresent()){
			return candidate.get();
		}else{
			Cell c = new Cell(row, column);
			cells.add(c);
			return c;
		}
	}

	
}
