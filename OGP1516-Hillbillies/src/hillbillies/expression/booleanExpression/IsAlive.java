package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;

public class IsAlive extends UnitInspector {


	public IsAlive(Expression<Unit> unit) {
		super(unit);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return getUnit().evaluate(taskHandler).isAlive();
	}

}
