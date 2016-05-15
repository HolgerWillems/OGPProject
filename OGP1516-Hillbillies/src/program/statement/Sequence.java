package program.statement;

import java.util.List;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.booleanExpression.True;

public class Sequence extends Statement {

	private final List<Statement> statements;
	private int counter;

	public Sequence(List<Statement> statements, SourceLocation sourceLocation) {
		super(new True(sourceLocation),sourceLocation);
		this.statements = statements;
		for(Statement statement: statements)
			statement.setPrevious(this);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		if(getNext() == null){
			setCounter(0);
		}
		if(getCounter()<statements.size()){
			setNext(statements.get(getCounter()));
			setCounter(getCounter() + 1);
		}else
			setNext(null);	
	}

	/**
	 * @return the statements
	 */
	public List<Statement> getStatements() {
		return statements;
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}
}
