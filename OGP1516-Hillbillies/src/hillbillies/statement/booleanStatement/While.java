package hillbillies.statement.booleanStatement;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.statement.Statement;

public class While extends Statement {
 

	private Statement body;

	public While(Expression<Boolean> condition, Statement body) {
		super(condition);
		this.body = body;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		if((boolean) getExpression().evaluate(taskHandler)){
			setNext(getBody());
			getBody().setPrevious(this);
		}	else
			setNext(null);
	}

	/**
	 * @return the body
	 */
	public Statement getBody() {
		return body;
	}




}
