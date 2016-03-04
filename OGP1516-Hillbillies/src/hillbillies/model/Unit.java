package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;


/**
 * This class is all about the units of the game.
 * @author Holger Willems & Sebastiaan Van Overschee
 * @date 20/02/2016
 * @Version 0.0
 * 
 */

/** TO BE ADDED TO CLASS HEADING
 * @invar  The Name of each Unit must be a valid Name for any
 *         Unit.
 *       | isValidName(getName())
 * @invar  The Position of each Unit must be a valid Position for any
 *         Unit.
 *       | isValidPosition(getPosition())
 *
 * @invar  The Strength of each Unit must be a valid Strength for any
 *         Unit.
 *       | isValidStrength(getStrength())
 *
 * @invar  The weight of each Unit must be a valid weight for any
 *         Unit.
 *       | isValidWeight(getWeight())
 *       
 * @invar  The Agility of each Unit must be a valid Agility for any
 *         Unit.
 *       | isValidAgility(getAgility())
 * @invar  The toughness of each Unit must be a valid toughness for any
 *         Unit.
 *       | isValidToughness(getToughness())
 * 
 * @invar  The hitpoints of each Unit must be a valid hitpoints for any
 *         Unit.
 *       | isValidHitpoints(getHitpoints())
 * 
 * @invar  The stamina of each Unit must be a valid stamina for any
 *         Unit.
 *       | isValidStamina(getStamina())
 * 
 * @invar  The orientation of each Unit must be a valid orientation for any
 *         Unit.
 *       | isValidOrientation(getOrientation())
 */

public class Unit { 
	
	private static final float PI = (float) Math.PI;
	private static final int CUBELENGTH = 1;
	
/**
 * Create a new unit with the given attributes.
	 * 
	 * @param name
	 *            The name of the unit.
	 * @param initialPosition
	 *            The initial position of the unit, as an array with 3 elements
	 *            {x, y, z}.
	 * @param weight
	 *            The initial weight of the unit
	 * @param agility
	 *            The initial agility of the unit
	 * @param strength
	 *            The initial strength of the unit
	 * @param toughness
	 *            The initial toughness of the unit
	 * @param enableDefaultBehavior
	 *            Whether the default behavior of the unit is enabled
	 *            
	 * Initialize this new Unit with default orientation.
	 * 
	 * @param  hitpoints
	 *         The hitpoints for this new Unit.
	 * 
	 * @return The generated unit
	 * 
	 *  
	 * 
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown

 * @effect The Name of this new Unit is set to
 *         the given Name.
 *       | this.setName(name)
 * @effect The Position of this new Unit is set to
 *         the given Position.
 *       | this.setPosition(position)
 *
 * @post   If the given weight is a valid weight for any Unit,
 *         the weight of this new Unit is equal to the given
 *         weight. Otherwise, the weight of this new Unit is equal
 *         to 100.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 *       |   else new.getWeight() == 100
 * 
 * @post   If the given Strength is a valid Strength for any Unit,
 *         the Strength of this new Unit is equal to the given
 *         Strength. Otherwise, the Strength of this new Unit is equal
 *         to 100.
 *       | if (isValidStrength(strength))
 *       |   then new.getStrength() == strength
 *       |   else new.getStrength() == 100
 *
 * @post   If the given Agility is a valid Agility for any Unit,
 *         the Agility of this new Unit is equal to the given
 *         Agility. Otherwise, the Agility of this new Unit is equal
 *         to 100.
 *       | if (isValidAgility(agility))&&(25<=agility&&agility<=100)
 *       |   then new.getAgility() == agility
 *       |   else new.getAgility() == 100
 * 
 * @pre    The given hitpoints must be a valid hitpoints for any Unit.
 *       | isValidHitpoints(hitpoints)
 * @post   The hitpoints of this new Unit is equal to the given
 *         hitpoints.
 *       | new.getHitpoints() == hitpoints
 * 
 * @param  toughness
 *         The toughness for this new Unit.
 * @post   If the given toughness is a valid toughness for any Unit,
 *         the toughness of this new Unit is equal to the given
 *         toughness. Otherwise, the toughness of this new Unit is equal
 *         to getToughness.
 *       | if (isValidToughness(toughness))
 *       |   then new.getToughness() == toughness
 *       |   else new.getToughness() == getToughness
 * 
 * @param  stamina
 *         The stamina for this new Unit.
 * @pre    The given stamina must be a valid stamina for any Unit.
 *       | isValidStamina(stamina)
 * @post   The stamina of this new Unit is equal to the given
 *         stamina.
 *       | new.getStamina() == stamina
 * 
 * @param  orientation
 *         The orientation for this new Unit.
 * @post   If the given orientation is a valid orientation for any Unit,
 *         the orientation of this new Unit is equal to the given
 *         orientation. Otherwise, the orientation of this new Unit is equal
 *         to PI/2.
 *       | if (isValidOrientation(orientation))
 *       |   then new.getOrientation() == orientation
 *       |   else new.getOrientation() == PI/2
 */
public Unit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness, int hitpoints,
		int stamina,boolean enableDefaultBehavior)
		throws IllegalArgumentException {
	
	this.setName(name);
	
	this.setPosition(position);
	
	if (!isValidWeight(weight,strength,agility))
		weight = 100;
	setWeight(weight);
	
	
	if (!(25<=strength&&strength<=100))
		strength = 100;
	setStrength(strength);
	
	if (!(25<=agility&&agility<=100))
		agility = 100;
	setAgility(agility);
	
	if (! isValidToughness(toughness))
		toughness = getToughness();
	setToughness(toughness);
	
	this.setHitpoints(hitpoints);
	
	this.setStamina(stamina);
	
	this.setOrientation(PI/2);
}

/**
 * Return the Name of this Unit.
 */
@Basic @Raw
public String getName() {
	return this.name;
}

/**
 * Check whether the given Name is a valid Name for
 * any Unit.
 *  
 * @param  Name
 *         The Name to check.
 * @return 
 *       | result == (name.length()>2)&&(Character.isUpperCase(name.charAt(0))&&(name.matches("[A-Za-z\"' ]+")))
*/
public static boolean isValidName(String name) {
	return (name.length()>2)&&(Character.isUpperCase(name.charAt(0))&&(name.matches("[A-Za-z\"' ]+")));
}

/**
 * Set the Name of this Unit to the given Name.
 * 
 * @param  name
 *         The new Name for this Unit.
 * @post   The Name of this new Unit is equal to
 *         the given Name.
 *       | new.getName() == name
 * @throws IllegalArgumentException
 *         The given Name is not a valid Name for any
 *         Unit.
 *       | ! isValidName(getName())
 */
@Raw
public void setName(String name) 
		throws IllegalArgumentException {
	if (! isValidName(name))
		throw new IllegalArgumentException();
	this.name = name;
}

/**
 * Variable registering the Name of this Unit.
 */
private String name;

/**
 * Return the Position of this Unit.
 */
@Basic @Raw
public double[] getPosition() {
	return this.position;
}

/**
 * Check whether the given Position is a valid Position for
 * any Unit.
 *  
 * @param  Position
 *         The Position to check.
 * @return The position has to be inside the game world
 *       | result ==
 *       | (0<=position[0]&&position[0]<=50)&&
 *       | (0<=position[1]&&position[1]<=50)&&
 *       | (0<=position[2]&&position[2]<=50)
 *       
*/
public static boolean isValidPosition(double[] position) {
	return (0<=position[0]&&position[0]<=50)&&
	       (0<=position[1]&&position[1]<=50)&&
	       (0<=position[2]&&position[2]<=50);
}

/**
 * Set the Position of this Unit to the given Position.
 * 
 * @param  position
 *         The new Position for this Unit.
 * @post   The Position of this new Unit is equal to
 *         the given Position.
 *       | new.getPosition() == position
 * @throws illegalArgumentException
 *         The given Position is not a valid Position for any
 *         Unit.
 *       | ! isValidPosition(getPosition())
 */
//TODO maak het mogelijk om als initiele waarde een int in te geven
@Raw
public void setPosition(double[] position) 
		throws IllegalArgumentException {
	if (! isValidPosition(position))
		throw new IllegalArgumentException();
	this.position = position;
}

/**
 * 
 * @param position
 *			The position of this Unit
 * @return The position of the cube where the Unit is located
 * 			| cubePosition[0] = (int) Math.floor(position[0]);
 *			| cubePosition[1] = (int) Math.floor(position[1]);
 *			| cubePosition[2] = (int) Math.floor(position[2]);
 * @throws illegalArgumentException
 *         The given Position is not a valid Position for any
 *         Unit.
 *       | ! isValidPosition(getPosition())
 */
public int[] getCubePosition(double[] position)
		throws IllegalArgumentException{
	if (! isValidPosition(position))
		throw new IllegalArgumentException();
	int[] cubePosition = new int[3];
	cubePosition[0] = (int) Math.floor(position[0]);
	cubePosition[1] = (int) Math.floor(position[1]);
	cubePosition[2] = (int) Math.floor(position[2]);
	return cubePosition;
	}


/**
 * Variable registering the Position of this Unit.
 * The length is set to 3 and can't be changed
 */
private double[] position = new double[3];


/**
 * Return the weight of this Unit.
 */
@Basic @Raw
public int getWeight() {
	return this.weight;
}

/**
 * Check whether the given weight is a valid weight for
 * any Unit.
 *  
 * @param  weight
 *         The weight to check.
 * @return 
 *       | result == (1<=weight&&weight<=200)&&(weight>(strength+agility)/2)
*/
public static boolean isValidWeight(int weight,int strength, int agility) {
	return (1<=weight&&weight<=200)&&(weight>(strength+agility)/2) ;
}

/**
 * Set the weight of this Unit to the given weight.
 * 
 * @param  weight
 *         The new weight for this Unit.
 * @post   If the given weight is a valid weight for any Unit,
 *         the weight of this new Unit is equal to the given
 *         weight.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 */
@Raw
public void setWeight(int weight) {
	if (isValidWeight(weight,this.getStrength(),this.getAgility()))
		this.weight = weight;
}

/**
 * Variable registering the weight of this Unit.
 */
private int weight;


/**
 * Return the Strength of this Unit.
 */
@Basic @Raw
public int getStrength() {
	return this.strength;
}

/**
 * Check whether the given Strength is a valid Strength for
 * any Unit.
 *  
 * @param  strength
 *         The Strength to check.
 * @return 
 *       | result == (1<=weight&&weight<=200)
*/
public static boolean isValidStrength(int strength) {
	return (1<=strength&&strength<=200);
	
}

/**
 * Set the Strength of this Unit to the given Strength.
 * 
 * @param  strength
 *         The new Strength for this Unit.
 * @post   If the given Strength is a valid Strength for any Unit,
 *         the Strength of this new Unit is equal to the given
 *         Strength.
 *       | if (isValidStrength(strength))
 *       |   then new.getStrength() == strength
 */
@Raw
public void setStrength(int strength) {
	if (isValidStrength(strength))
		this.strength = strength;
}

/**
 * Variable registering the Strength of this Unit.
 */
private int strength;


/**
 * Return the Agility of this Unit.
 */
@Basic @Raw
public int getAgility() {
	return this.agility;
}

/**
 * Check whether the given Agility is a valid Agility for
 * any Unit.
 *  
 * @param  agility
 *         The Agility to check.
 * @return 
 *       | result == (1<=agility && agility<=200)
*/
public static boolean isValidAgility(int agility) {
	return (1<=agility&&agility<=200);
}

/**
 * Set the Agility of this Unit to the given Agility.
 * 
 * @param  agility
 *         The new Agility for this Unit.
 * @post   If the given Agility is a valid Agility for any Unit,
 *         the Agility of this new Unit is equal to the given
 *         Agility.
 *       | if (isValidAgility(agility))
 *       |   then new.getAgility() == agility
 */
@Raw
public void setAgility(int agility) {
	if (isValidAgility(agility))
		this.agility = agility;
}

/**
 * Variable registering the Agility of this Unit.
 */
private int agility;


/**
 * Return the toughness of this Unit.
 */
@Basic @Raw
public int getToughness() {
	return this.toughness;
}

/**
 * Check whether the given toughness is a valid toughness for
 * any Unit.
 *  
 * @param  toughness
 *         The toughness to check.
 * @return 
 *       | result == (1<=toughness && toughness <= 200)
*/
public static boolean isValidToughness(int toughness) {
	return (1<=toughness && toughness<=200);
}

/**
 * Set the toughness of this Unit to the given toughness.
 * 
 * @param  toughness
 *         The new toughness for this Unit.
 * @post   If the given toughness is a valid toughness for any Unit,
 *         the toughness of this new Unit is equal to the given
 *         toughness.
 *       | if (isValidToughness(toughness))
 *       |   then new.getToughness() == toughness
 */
@Raw
public void setToughness(int toughness) {
	if (isValidToughness(toughness))
		this.toughness = toughness;
}

/**
 * Variable registering the toughness of this Unit.
 */
private int toughness;


/**
 * Return the hitpoints of this Unit.
 */
@Basic @Raw
public int getHitpoints() {
	return this.hitpoints;
}

/**
 * Check whether the given hitpoints is a valid hitpoints for
 * any Unit.
 *  
 * @param  hitpoints
 *         The hitpoints to check.
 * @return 
 *       | result == (0<=hitpoints && hitpoints <= (int) Math.ceil(200.0*weight/100*toughness/100)
*/
public static boolean isValidHitpoints(int hitpoints, int weight, int toughness) {
	return (0<=hitpoints && hitpoints <= getMaxHitpoints(weight, toughness));
}

//TODO write documentation
public static int getMaxHitpoints(int weight, int toughness){
	return (int) Math.ceil(200.0*weight/100*toughness/100);
}

/**
 * Set the hitpoints of this Unit to the given hitpoints.
 * 
 * @param  hitpoints
 *         The new hitpoints for this Unit.
 * @pre    The given hitpoints must be a valid hitpoints for any
 *         Unit.
 *       | isValidHitpoints(hitpoints)
 * @post   The hitpoints of this Unit is equal to the given
 *         hitpoints.
 *       | new.getHitpoints() == hitpoints
 */
@Raw
public void setHitpoints(int hitpoints) {
	assert isValidHitpoints(hitpoints, this.getWeight(),this.getToughness());
	this.hitpoints = hitpoints;
}

/**
 * Variable registering the hitpoints of this Unit.
 */
private int hitpoints;


/**
 * Return the stamina of this Unit.
 */
@Basic @Raw
public int getStamina() {
	return this.stamina;
}

/**
 * Check whether the given stamina is a valid stamina for
 * any Unit.
 *  
 * @param  stamina
 *         The stamina to check.
 * @return 
 *       | result == (0<=stamina && stamina<=Math.ceil(200.0*weight/100*toughness/100))
*/
public static boolean isValidStamina(int stamina, int weight, int toughness) {
	return (0<=stamina && stamina<= getMaxStamina(weight, toughness));
}

//TODO write documentation
public static int getMaxStamina(int weight, int toughness){
	return (int) Math.ceil(200.0*weight/100*toughness/100);
}

/**
 * Set the stamina of this Unit to the given stamina.
 * 
 * @param  stamina
 *         The new stamina for this Unit.
 * @pre    The given stamina must be a valid stamina for any
 *         Unit.
 *       | isValidStamina(stamina)
 * @post   The stamina of this Unit is equal to the given
 *         stamina.
 *       | new.getStamina() == stamina
 */
@Raw
public void setStamina(int stamina) {
	assert isValidStamina(stamina, this.getWeight(), this.getToughness());
	this.stamina = stamina;
}

/**
 * Variable registering the stamina of this Unit.
 */
private int stamina;


/**
 * Return the orientation of this Unit.
 */
@Basic @Raw
public float getOrientation() {
	return this.orientation;
}

/**
 * Check whether the given orientation is a valid orientation for
 * any Unit.
 *  
 * @param  orientation
 *         The orientation to check.
 * @return 
 *       | result == (0<=orientation && orientation<2*PI)
*/
public static boolean isValidOrientation(float orientation) {
	return (0<=orientation && orientation<2*PI);
}

/**
 * Set the orientation of this Unit to the given orientation.
 * 
 * @param  orientation
 *         The new orientation for this Unit.
 * @post   If the given orientation is a valid orientation for any Unit,
 *         the orientation of this new Unit is equal to the given
 *         orientation.
 *       | if (isValidOrientation(orientation))
 *       |   then new.getOrientation() == orientation
 *      	default orientation = PI/2
 */
@Raw
public void setOrientation(float orientation) {
	if (isValidOrientation(orientation))
		this.orientation = orientation;
	else{
		this.setOrientation(PI/2);
	}
}

/**
 * Variable registering the orientation of this Unit.
 */
private float orientation;


public void advanceTime(double dt) {
	if (!(0.0<=dt&&dt<=0.2))
		throw new IllegalArgumentException();
	
	private String activity;
	activity = this.getCurrentActivity();
	
	if (activity == "default") {
		int randomActivity = (int) (Math.random()*3);
		if (randomActivity == 0) {
			activity = "moving";
			this.setTargetPosition({Math.random()*50, Math.random()*50, Math.random()*50});
		}
		if (randomActivity == 1) {
			activity = "working";
		}
		if (randomActivity == 2) {
			activity = "rest";
		}
	}
	
	if (activity == "moving") {
		int[] cubePosition = this.getCubePosition(this.getPosition());
		int[] targetPosition = (int) this.getTargetPosition();
		int[] dCube = {targetPosition[0] - cubePosition[0],
					   targetPosition[1] - cubePosition[1],
					   targetPosition[2] - cubeposition[2]
		};
		this.updateMovingOrientation(this.getVelocityVector(dCube[0], dCube[1], dCube[2]));
		double[] nextPosition = this.getIntermediatePosition(dCube[0], dCube[1], dCube[2], dt);
		int[] dNext = {targetPosition[0] - nextPosition[0],
				       targetPosition[1] - nextPosition[1],
				       targetPosition[2] - nextposition[2]
		};
		
		if ((dCube[0]*dNext[0]<=0) && (dCube[1]*dNext[1]<=0) && (dCube[2]*dNext[2]<=0)) {
			this.setCurrentActivity("default");
			this.setPosition(targetPosition);
		}
	}
	
	if (activity == "working") {
		this.setActivityTime(this.getActivityTime-dt);
		if (this.getActivityTime <= 0) {
			this.setCurrentActivity("default");
		}
	}
	if (activity == "attack"){
		this.setActivityTime(this.getActivityTime-dt);
		if (this.getActivityTime <= 0) {
			this.setCurrentActivity("default");
		}
	}
	if (activity == "rest") {
		// Moet nog aan gewerkt worden.
		this.rest();
		this.restingTime += dt;
	}
}

//MOVING

private double[] targetPosition;

public void setTargetPosition(double position) {
	this.targetPosition = position;
}

public double getTargetPosition() {
	return this.targetPosition;
}

public void moveToAdjacent(int dx, int dy, int dz) throws IllegalArgumentException {
	
	if ((Math.abs(dx)>1||Math.abs(dy)>1||Math.abs(dz)>1)||
			((Math.abs(dx)==1||Math.abs(dy)==1)&&Math.abs(dz)==1)){
		throw new IllegalArgumentException();
	}
	
	this.setCurrentActivity("moving");
	int[] cubePosition = this.getCubePosition(this.getPosition());
	double[] newPosition = {
			cubePosition[0]+dx+CUBELENGTH/2,
			cubePosition[1]+dy+CUBELENGTH/2,
			cubePosition[2]+dz+CUBELENGTH/2
	};
	
	if (!isValidPosition(newPosition))
		throw new IllegalArgumentException();
	
	this.setTargetPosition(newPosition);
}



public double[] getVelocityVector(int dx, int dy, int dz){
	double distance = Math.sqrt(dx^2+dy^2+dz^2);
	double speed = this.getSpeed();
	double[] velocity = {
			speed*dx/distance,
			speed*dy/distance,
			speed*dz/distance
	};
	return velocity;
};

public double[] getIntermediatePosition(int dx, int dy, int dz, float dt){
	double[] position = this.getPosition();
	double[] velocityVector = this.getVelocityVector(dx, dy, dz);
	double[] newPosition = {
			position[0]+velocityVector[0]*dt,
			position[1]+velocityVector[1]*dt,
			position[2]+velocityVector[2]*dt	
	};
	return newPosition;
}

public void updateMovingOrientation(double[] velocityVector){
	//aandacht: functie atan2(y,x)!!
	float orientation = (float) Math.atan2(velocityVector[1], velocityVector[0]);
	this.setOrientation(orientation);
}


//Adaptibility: volgende opgaves zullen hindernissen bevatten
//kan onderbroken worden door: need to rest, enemy interaction
//na onderbreking --> vervolg pad
//nieuwe actie moveTo kan wel nog uitgevoerd worden
public void moveTo(int[] cube){
	int[] position = this.getCubePosition(this.getPosition());
	int[] step = new int[3];
	while ((position[0] != cube[0])&&
			(position[1] != cube[1])&&
			(position[2] != cube[2])){
		for(int i = 0; i<3; i++){
			if (position[i] == cube[i]){
				step[i] = 0;
			}else if(position[i] < cube[i]){
				step[i] = 1;
			}else{
				step[i] = -1;
			}
			this.moveToAdjacent(step[0],step[1],step[2]);
		}
	}
				
}

public void updateSpeed(int dz){
	if(this.isMoving()){
		double baseSpeed = (double) 1.5*(this.getStrength()+this.getAgility())
				/(200*this.getWeight()/100);
		double walkingSpeed;
		if (dz == 1){
			walkingSpeed = baseSpeed*0.5;
			}
		else if (dz == -1){
			walkingSpeed = baseSpeed*1.2;
			}
		else{
			walkingSpeed = baseSpeed;
		}
		this.speed = walkingSpeed;
	};
	else
		this.speed = 0.0;
}

/**
 * Return the speed of this Unit.
 */
@Basic @Raw
public double getSpeed(){
	return this.speed;
}

/**
 * Variable registering the speed of this Unit.
 */
private double speed;

/**
 * Return the isSprinting of this Unit.
 */
@Basic @Raw
public boolean isSprinting() {
	return this.isSprinting;
}


// Ik heb het gevoel dat deze documentatie niet bij deze methode hoort.
/**
 * Set the isSprinting of this Unit to the given isSprinting.
 * 
 * @param  isSprinting
 *         The new isSprinting for this Unit.
 * @post   The isSprinting of this new Unit is equal to
 *         the given isSprinting.
 *       | new.getIsSprinting() == isSprinting
 * @throws ExceptionName_Java
 *         The given isSprinting is not a valid isSprinting for any
 *         Unit.
 *       | ! isValidIsSprinting(getIsSprinting())
 */
public void startSprinting() {
	if (this.getStamina()>0 && this.isMoving()){
		if (!this.isSprinting){
			this.isSprinting = true;
			this.speed *=2;
		}
	}
	
}

public void stopSprinting() {
	if (this.isSprinting){
		this.isSprinting = false;
		this.speed /=2;
	}
}

/**
 * Variable registering the isSprinting of this Unit.
 */
private boolean isSprinting;

/**
 * Variable registering the isMoving of this Unit.
 */

private String activity;
private float activityTime;
private double restingTime;

public boolean isMoving(){
	return (this.getCurrentActivity()=="moving");
}

//ACTIVITIES


public void setCurrentActivity(String activity){
	if (this.getCurrentActivity() == "rest") {
		if (this.restingTime < 0.2) {
			return;
		}
	}
	this.activity = activity;
}
public String getCurrentActivity(){
	return this.activity;
}

public void setActivityTime(float time){
	this.activityTime = time;
}

public float getActivityTime(){
	return this.activityTime;
}

//WORKING

public void work(){
	this.setCurrentActivity("working");
	float gameTimeNeeded = 500/this.getStrength();
	this.setActivityTime(gameTimeNeeded);
}

public  boolean isWorking(){
	return (this.getCurrentActivity()=="working");
	
}

//FIGHTING

//lasts for 1 second
public void attack(){
	this.setCurrentActivity("attack");
	this.setActivityTime(1);
}

public void defend(Unit attacker){
	
	this.setCurrentActivity("defend");
	//first Dodging
	double probDodging = 0.2*this.getAgility()/attacker.getAgility();
	
	if(Math.random()<probDodging){
		int step[] = {0,0,0};
		int[] currentPosition = this.getPosition();
		int[] nextPosition = currentPosition;
		while ((step[0]==0 && step[1]==0)||(!isValidPostion(nextPosition)){
			step[0] = -1 + (int)(Math.random()*3);
			step[1] = -1 + (int)(Math.random()*3);
			nextPosition = {currentPosition[0] + step[0],
							currentPosition[1] + step[1],
							currentPosition[2]};
		};
		this.moveToAdjacent(step[0], step[1], step[2]);
		return;
	};
	
	//then blocking
	double probBlocking = 0.25*(this.getStrength()+this.getAgility())/
			(attacker.getStrength()+attacker.getAgility());
	
	if(Math.random()<probBlocking){
		return;
	};
	
	//then taking damage
	int damage = (int) attacker.getStrength()/10;
	int newHitpointss = this.getHitpoints()- damage;
	this.setHitpoints(newHitpointss);
	}
	
public void updateFightingOrientation(Unit attacker, Unit defender){ // Kan je deze methode ook statisch maken?
	double[] aPosition = attacker.getPosition();
	double[] dPosition = defender.getPosition();
	float aOrientation = (float) Math.atan2(dPosition[1]-aPosition[1], dPosition[0]-aPosition[1]);
	float dOrientation = (float) Math.atan2(aPosition[1]-dPosition[1], aPosition[0]-dPosition[1]);
	attacker.setOrientation(aOrientation);
	defender.setOrientation(dOrientation);
}

//RESTING

public void rest(){
	// this.setCurrentActivity("rest");
	int healingPoints = this.getToughness()/200;
	int maxHitpoints = getMaxHitpoints(this.getWeight(), this.getToughness());
	int newHitpoints = this.getHitpoints() + healingPoints;
	if(newHitpoints>= maxHitpoints){
		this.setHitpoints(maxHitpoints);
		healingPoints = newHitpoints - maxHitpoints;
		int maxStamina = getMaxStamina(this.getWeight(), this.getToughness());
		int newStamina = this.getStamina()+ healingPoints;
		
		if (newStamina>=maxStamina){
			this.setStamina(maxStamina);
			this.startDefaultBehaviour();
		}else{
			this.setStamina(newStamina);
		}
	} else {
		this.setHitpoints(this.getHitpoints()+ healingPoints);
	}
}

public void startResting() {
	this.setCurrentActivity("rest");
	this.restingTime = 0;
}

public boolean isResting(){
	return (this.getCurrentActivity()=="resting");
}

public void startDefaultBehaviour() {
	this.setCurrentActivity("default");
}
public void stopDefaultBehaviour() {
	this.setCurrentActivity(null);
}

}
