package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;

public abstract class BinairyOperation extends Expression<Boolean> {

	private final Expression<Boolean> left;
	private final Expression<Boolean> right;

	protected BinairyOperation(Expression<Boolean> left, Expression<Boolean> right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * @return the left
	 */
	public Expression<Boolean> getLeft() {
		return left;
	}

	/**
	 * @return the right
	 */
	public Expression<Boolean> getRight() {
		return right;
	}

}
