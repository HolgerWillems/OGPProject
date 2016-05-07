package program.statement.booleanStatement;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.statement.Statement;

public class While extends Statement<Boolean> {
 

	private Statement<?> body;

	public While(Expression<Boolean> condition, Statement<?> body, SourceLocation sourceLocation) {
		super(condition,sourceLocation);
		this.body = body;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @return the body
	 */
	public Statement<?> getBody() {
		return body;
	}



}
