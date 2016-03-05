package hillbillies.part1.facade;

import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

public class Facade implements IFacade{

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		
		try {
			Unit unit = new Unit(name, initialPosition, weight, agility, strength, toughness, enableDefaultBehavior);
			return unit;
		} catch (IllegalArgumentException e) {
			throw new ModelException();
		}
	}
	

	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		double[] position = unit.getPosition();
		return position;
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		int[] cubeCoordinate = Unit.getCubePosition(unit.getPosition());
		return cubeCoordinate;
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		String name = unit.getName();
		return name;
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		try {
			unit.setName(newName);
		} catch (IllegalArgumentException e) {
			throw new ModelException();
		}
	}

	@Override
	public int getWeight(Unit unit) throws ModelException {
		int weight = unit.getWeight();
		return weight;
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
			unit.setWeight(newValue);
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		int strength = unit.getStrength();
		return strength;
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		unit.setStrength(newValue);
	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		int agility = unit.getAgility();
		return agility;
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		unit.setAgility(newValue);
	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		int toughness = unit.getToughness();
		return toughness;
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		unit.setToughness(newValue);
	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		int maxHitPoints = Unit.getMaxHitpoints(unit.getWeight(), unit.getToughness());
		return maxHitPoints;
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		int hitPoints = unit.getHitpoints();
		return hitPoints;
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		int maxStamina = Unit.getMaxStamina(unit.getWeight(), unit.getToughness());
		return maxStamina;
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		int stamina = unit.getStamina();
		return stamina;
	}

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		// TODO Auto-generated method stub
		unit.advanceTime(dt);
	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		// TODO Auto-generated method stub
		unit.moveToAdjacent(dx, dy, dz);
	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		double speed = unit.getSpeed();
		return speed;
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		boolean moving = unit.isMoving();
		return moving;
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		unit.startSprinting();
	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		unit.stopSprinting();
	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		boolean sprinting = unit.isSprinting();
		return sprinting;
	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		double orientation = unit.getOrientation();
		return orientation;
	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		// TODO Auto-generated method stub
		unit.moveTo(cube);
	}

	@Override
	public void work(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		unit.work();
	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		boolean working = unit.isWorking();
		return working;
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		// TODO Auto-generated method stub
		attacker.attack();
		defender.defend(attacker);
	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		boolean attacking = (unit.getCurrentActivity()=="attack");
		return attacking;
	}

	@Override
	public void rest(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		unit.startResting();
	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		boolean resting = unit.isResting();
		return resting;
	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		// TODO Auto-generated method stub
		if (value) {
			unit.startDefaultBehaviour();
		}
		else
			unit.stopDefaultBehaviour();
	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		boolean defaultBehaviour = (unit.getCurrentActivity()=="default");
		return defaultBehaviour;
	}

}
