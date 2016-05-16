package hillbillies.expression;

import hillbillies.model.TaskHandler;

public abstract class Expression<T> implements IExpression<T> {

	public Expression() {};

	/**
	 * Method to evaluate an expression
	 * 
	 * @param taskHandler
	 * 		the class who handles the task
	 * @return T t
	 */
	@Override
	public abstract T evaluate(TaskHandler taskHandler);

}
