package op21.chess;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Queen extends Piece {

	public Queen(Cell cell) {
		super(cell);
	}

	private List<Cell> checkDirection(ChessBoard cb, Cell piece, Function<Cell, Cell> next) throws CheckMateException{
		List<Cell>result = new LinkedList<Cell>();
		Cell window = next.apply(piece);

		while(cb.contains(window)){
			if(cb.empty(window)){
				result.add(window);
				window = next.apply(window);
			}else{
				throw new CheckMateException();
			}
		}
		return result;
		
	}
	@Override
	public List<Cell> check(ChessBoard cb){
		List<Cell> result = new LinkedList<Cell>();
		
		Cell piece = super.getCell();
		
		for(Function<Cell, Cell> f : Queen.directions){
			List<Cell> candidates;
			try {
				candidates = this.checkDirection(cb, piece, f);
				result.addAll(candidates);
			} catch (CheckMateException e) {
				super.setInvalid();
				continue;
			}
		}
		result.add(piece);
		
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Queen ");
		builder.append(getCell());
		builder.append("");
		return builder.toString();
	}
	
	private static final List<Function<Cell, Cell>> directions = new LinkedList<Function<Cell,Cell>>();
	static{
		directions.add(e -> e.left());
		directions.add(e -> e.leftdown());
		directions.add(e -> e.leftup());
		directions.add(e -> e.right());
		directions.add(e -> e.rightdown());
		directions.add(e -> e.rightup());
		directions.add(e -> e.up());
		directions.add(e -> e.down());
	}

}
