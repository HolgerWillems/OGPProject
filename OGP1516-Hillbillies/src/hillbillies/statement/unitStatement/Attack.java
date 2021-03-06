package hillbillies.statement.unitStatement;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.statement.Statement;

public class Attack extends Statement {

	public Attack(Expression<Unit> unit) {
		super(unit);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		Unit attacker = taskHandler.getUnit();
		try{
			Unit defender = (Unit) getExpression().evaluate(taskHandler);
			attacker.attack(defender);
			
		} catch(Exception e){
			taskHandler.interruptTask();
			System.out.println(e.toString());
			throw new Error("attack not executable ");
		}
		
		
	}

}
