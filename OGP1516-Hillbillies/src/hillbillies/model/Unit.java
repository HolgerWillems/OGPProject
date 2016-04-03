package hillbillies.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.items.Item;
import hillbillies.model.items.rawmaterials.Boulder;
import hillbillies.model.items.rawmaterials.Log;
import hillbillies.model.items.rawmaterials.RawMaterial;



/**
 * This class is all about the units of the game.
 * @author Holger Willems & Sebastiaan Van Overschee
 * @date 20/02/2016
 * @Version 0.0
 * 
 */

/** 
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
 *       
 * @invar  The activity of each Unit must be a valid activity for any
 *         Unit.
 *       | isValidActivity(getActivity())
 *       
 * @invar  The nextPosition of each Unit must be a valid position for any
 *         Unit.
 *       | isValidPosition(getNextPosition())
 *       
 * @invar  The targetPosition of each Unit must be a valid position for any
 *         Unit.
 *       | isValidPosition(getTargetPosition())
 * @invar  The XP of each unit must be a valid XP for any
 *         unit.
 *       | isValidXP(getXP())      
 * @invar  Each unit can have the faction as faction
 * 		 | canHaveAsFacion(getFaction())
 * @invar  Each unit can have his world as world
 * 		| canHaveAsWorld(getWorld())
 * @invar Each unit can have his item as item
 * 		| canHaveAsItem(getItem())
 */            

public class Unit { 
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTANTS---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	//TODO Place all constants here
	
	private static final String ALLOWED_NAME_PATTERN = "[a-zA-Z \"']+";
	
	private static final float PI = (float) Math.PI;

	private static final double REST_INTERVAL = 60*3;
	private static final double NOTHING_INTERVAL = 10;
	
	
	/*___________________________________________________________________
	 * __________________________________________________________________
 * -----------------------VARIABLES---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/

	/**
	* Variable registering the Name of this Unit.
	*/
	private String name;

	/**
	* Variable registering the Position of this Unit.
	* The length is set to 3 and will not change.
	*/
	private double[] position = new double[3];

	/**
	* Variable registering the weight of this Unit.
	*/
	private int weight;

	/**
	* Variable registering the Strength of this Unit.
	*/
	private int strength;

	/**
	* Variable registering the toughness of this Unit.
	*/
	private int toughness;

	/**
	* Variable registering the Agility of this Unit.
	*/
	private int agility;

	/**
	* Variable registering the hitpoints of this Unit.
	*/
	private int hitpoints;

	/**
	* Variable registering the stamina of this Unit.
	*/
	private double stamina;

	/**
	* Variable registering the orientation of this Unit.
	*/
	private float orientation;

	/**
	 * Variable registering the speed of this Unit.
	 */
	private double speed;

	/**
	 * The position where the unit is heading to
	 */
	private double[] cube;

	/**
	 * The next position of the unit
	 */

	private double[] nextPosition;

	/**
	 * Variable registering if the unit is sprinting
	 */
	private boolean isSprinting;

	/**
	 * The time a unit needs to conduct an activity
	 */
	private float  progressTime;

	/**
	 * Variable registering the Activity of this Unit.
	 */
	private Activity activity;
	/**
	 * Variable registering the nextActivity of this Unit.
	 */
	private Activity nextActivity;
	/**
	 * Variable registering the time till mandatory rest
	 */
	private double counterTillRest = 0.0;
	/**
	 * the time till default behavior is activated
	 */
	private double counterTillDefault = 0.0;
	/**
	 * the step the unit is currently making
	 */
	private int[] step;
	/**
	 * indicates if the unit is operating in default behavior
	 */
	private boolean hasDefaultBehaviorEnabled;
	/**
	 * Indicates if the unit is still moving to the center of the next cube
	 */
	private boolean isMovingToNext;
	/**
	 * Variable registering the faction of this unit.
	 */
	private Faction faction;
	/**
	 * Variable registering the world of this unit.
	 */
	private World world;
	/**
	 * Boolean indicating whether the unit is alive
	 */
	private boolean isAlive;
	/**
	 * Variable registering the XP of this unit.
	 */
	private int XP;
	
/*___________________________________________________________________
 * __________________________________________________________________
 * -----------------------CONSTRUCTOR--------------------------------
 *___________________________________________________________________
 *___________________________________________________________________*/	

/**
 * Create a new unit with the given attributes.
 * 
 * PARAMETERS
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
 * ____________________________________________________________
 * 
 * @effect The Name of this new Unit is set to
 *         the given Name.
 *       | this.setName(name)
 *       
 * @effect The Position of this new Unit is set to
 *         the given Position.
 *       | this.setPosition(position)
 *       
 * @effect The nextPosition of this new Unit is set to
 *         the given Position.
 *       | this.setNextPosition(position)
 *       
 * @effect the orientation of this unit will be set to PI/2
 * 		| this.setOrientation(PI/2)
 * 
 * @effect the hitpoints will be set to the maximal amount of hitpoints
 * 		| this.setHitpoints(this.getMaxHitpoints))
 * 
 * @effect the stamina will be set to the maximal amount of stamina
 * 		| this.setStamina(this.getMaxStamina))
 * 
 * @effect The XP of this new unit is set to 0 XP
 *       | this.setXP(0)
 *       
 * 
 * @post   If the given Strength is a valid Strength for any Unit,
 *         the Strength of this new Unit is equal to the given
 *         Strength. Otherwise, the Strength of this new Unit is equal
 *         to 100.
 *       | if (isValidInitialStrength(strength))
 *       |   then new.getStrength() == strength
 *       |   else new.getStrength() == 100
 *
 * @post   If the given Agility is a valid Agility for any Unit,
 *         the Agility of this new Unit is equal to the given
 *         Agility. Otherwise, the Agility of this new Unit is equal
 *         to 100.
 *       | if (isValidInitialAgility(agility))
 *       |   then new.getAgility() == agility
 *       |   else new.getAgility() == 100
 *
 * @post   If the given toughness is a valid toughness for any Unit,
 *         the toughness of this new Unit is equal to the given
 *         toughness. Otherwise, the toughness of this new Unit is equal
 *         to getToughness.
 *       | if (isValidInitialToughness(toughness))
 *       |   then new.getToughness() == toughness
 *       |   else new.getToughness() == 100
 *
 * @post   If the given weight is a valid weight for any Unit,
 *         the weight of this new Unit is equal to the given
 *         weight. Otherwise, the weight of this new Unit is equal
 *         to 100.
 *       | if (isValidInitialWeight(weight) && isValidWeight(weight))
 *       |   then new.getWeight() == weight
 *       |   else new.getWeight() == 100
 * @post if the given boolean enableDefaultBehavior is true,
 * 		 the unit will go into default behavior. 
 * 		 Else the activity of the unit is set to "none"
 * 		| if(enableDefaultBehavior)
 *	    |	new.hasDefaultBehavior() == true;
 *	    | else
 *		|	new.getCurrentActivity() == "none";
 * @post the unit will be a living unit
 * 		| this.isAlive() == true;
 *       
 */
public Unit(String name, int[] initialPosition, int weight, int agility,
			int strength, int toughness, boolean enableDefaultBehavior)
			throws IllegalArgumentException {
	
	
	setName(name);
	
	setPosition(Utils.getCubeCenter(initialPosition));
	
	setNextPosition(Utils.getCubeCenter(initialPosition));
	
	if (!isValidInitialStrength(strength))
		strength = 100;
	setStrength(strength);
	
	if (!isValidInitialAgility(agility))
		agility = 100;
	setAgility(agility);
	
	if (!isValidInitialToughness(toughness))
		toughness = 100;
	setToughness(toughness);
	
	if (!isValidInitialWeight(weight) || !isValidWeight(weight))
		weight = 100;
	setWeight(weight);
	
	setHitpoints(getMaxHitpoints());
	
	setStamina(getMaxStamina());
	
	setOrientation(PI/2);
	
	if(enableDefaultBehavior)
		this.startDefaultBehavior();
	else
		this.stopDefaultBehaviour();
	
	this.setXP(0);
	
	//A unit must always belong to a faction
	Faction faction = new Faction();
	this.setFaction(faction);
	
	this.isAlive = true;
}

/*___________________________________________________________________
 * __________________________________________________________________
 * -----------------------DESTRUCTOR--------------------------------
 *___________________________________________________________________
 *___________________________________________________________________*/	


/**
 * Terminates this unit
 * 
 * 
 * @post The unit is now dead
 * 		|!new.isAlive()
 * @post The unit will no longer belong to a faction
 * 		| new.getFaction == null;
 * @post The unit will no longer belong to a world
 * 		| new.getWorld == null;
 * 
 * @effect The unit will drop the item he is carrying
 * 		|this.dropItem();
 * 
 * @effect The world of this unit will not have the unit as unit anymore
 * 		| (new this.getWorld()).removeAsUnit(this)
 * @effect The faction of this unit will not have the unit as unit anymor
 * 		| (new this.getFaction()).removeAsUnit(this)
 * @effect The unit hitpoints will be brought to 0
 * 		|this.setHitpoints(0);
 * @effect the units speed will be brought to 0
 * 		|this.setSpeed(0);
 * @effect The unit will stop sprinting
 * 		|this.stopSprinting();
 */
 public void terminate() {
	 
	 if (hasItem())
		 this.dropItem(this.getItem());
	 
	 this.setHitpoints(0);
	 this.setSpeed(0);
	 this.stopSprinting();
	 
	 this.isAlive = false;
	 
	 Faction faction = this.getFaction();
	 World world = this.getWorld();
	 
	 faction.removeUnit(this);
	 world.removeUnit(this);
	 
	 //delete empty factions
	 if(faction.getNbUnits()==0){
		 faction.terminate();
		 world.removeAsFaction(faction);
	 }
 }

 
 /**
  * Returns a boolean indicating whether this unit is alive.
  */
 @Basic @Raw
 public boolean isAlive() {
	 return this.isAlive;
 }

/*
 * ------------------INITIAL CHECKERS-----------------------------
 */

/**
 * Checks whether the given value is a valid initial value for toughness.
 * 
 * @param  toughness
 *         The initial toughness to check.
 *         
 * @return true if and only if the given toughness is not larger than 100 or smaller than 25
 * 		| result == (toughness <= 100 && toughness >= 25)
 */
private boolean isValidInitialToughness(int toughness ){
	return (toughness <= 100 && toughness >= 25);
}

/**
 * Checks whether the given value is a valid initial value for strength.
 * 
 * @param  strength
 *         The initial strength to check.
 *         
 * @return true if and only if the given strength is not larger then 100 or smaller then 25
 * 		| result (strength <= 100 && strength >= 25)
 */
private boolean isValidInitialStrength(int strength ){
	return (strength <= 100 && strength >= 25);
}

/**
 * 
 *  Checks whether the given value is a valid initial value for agility.
 *  
 *	@param  agility
 *         The initial agility to check.
 *         
 * @return true if and only if the given agility is not larger then 100 or smaller then 25
 * 		| result == (agility <= 100 && agility >= 25)
 */
private boolean isValidInitialAgility(int agility ){
	return (agility <= 100 && agility >= 25);
}

/**
 *
 * Checks whether the given value is a valid initial value for weight.
 * 
 *  @param weight
 *         The initial weight to check.
 *         
 * @return true if and only if the given weight is not larger then 100 or smaller then 25
 * 		| result == (weight <= 100 && weight >= 25)
 */
private boolean isValidInitialWeight(int weight){
	return (weight <= 100 && weight >= 25);
}

/*___________________________________________________________________
 * __________________________________________________________________
 * -----------------------PROPERTIES---------------------------------
 *___________________________________________________________________
 *___________________________________________________________________*/


//------------------------GETTERS
/**
 * Return the Name of this Unit.
 */
@Basic @Raw
public String getName() {
	return this.name;
}

/**
 * Return the Position of this Unit.
 */
@Basic @Raw
public double[] getPosition() {
	return this.position.clone();
}

/**
 * Return the weight of this Unit.
 */
@Basic @Raw
public int getWeight() {
	return this.weight;
}

/**
 * Return the Strength of this Unit.
 */
@Basic @Raw
public int getStrength() {
	return this.strength;
}

/**
 * Return the Agility of this Unit.
 */
@Basic @Raw
public int getAgility() {
	return this.agility;
}

/**
 * Return the toughness of this Unit.
 */
@Basic @Raw
public int getToughness() {
	return this.toughness;
}

/**
 * Return the hitpoints of this Unit.
 */
@Basic @Raw
public int getHitpoints() {
	return this.hitpoints;
}

/**
 * Return the stamina of this Unit.
 */
@Basic @Raw
public double getStamina() {
	return this.stamina;
}

/**
 * Return the orientation of this Unit.
 */
@Basic @Raw
public float getOrientation() {
	return this.orientation;
}

/**
* Return the speed of this Unit.
*/
@Basic @Raw
public double getSpeed(){
	return this.speed;
}

/**
 * Return the nextActivity of this Unit.
 */
@Basic @Raw
public Activity getNextActivity() {
	return this.nextActivity;
}

/**
 * Return the XP of this unit.
 */
@Basic @Raw
public int getXP() {
	return this.XP;
}


/*
 * ---------------------SETTERS 
 */

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
public void setName(String name) throws IllegalArgumentException {
	if (! isValidName(name))
		throw new IllegalArgumentException();
	this.name = name;
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
@Raw
public void setPosition( @Raw double[] position) throws IllegalArgumentException {
	
	if (! isValidPosition(position))
		throw new IllegalArgumentException();
	
	this.position = position;	
}

/**
 * Set the weight of this Unit to the given weight.
 * 
 * @param  weight
 *         The new weight for this Unit.
 * @post   If the given weight is a valid weight for any Unit,
 *         the weight of this new Unit is equal to the given
 *         weight else we don't change anything.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 *       
 */
@Raw
public void setWeight(int weight) {
	if (isValidWeight(weight))
		this.weight = weight;
}


/**
 * Set the Strength of this Unit to the given Strength.
 * 
 * @param  strength
 *         The new Strength for this Unit.
 * @post   If the given Strength is a valid Strength for any Unit,
 *         the Strength of this new Unit is equal to the given
 *         Strength else we don't change anything.
 *       | if (isValidStrength(strength))
 *       |   then new.getStrength() == strength
 */
@Raw
public void setStrength(int strength) {
	if (isValidStrength(strength))
		this.strength = strength;
}

/**
 * Set the Agility of this Unit to the given Agility.
 * 
 * @param  agility
 *         The new Agility for this Unit.
 * @post   If the given Agility is a valid Agility for any Unit,
 *         the Agility of this new Unit is equal to the given
 *         Agility else we don't change anything.
 *       | if (isValidAgility(agility))
 *       |   then new.getAgility() == agility
 */
@Raw
public void setAgility(int agility) {
	if (isValidAgility(agility))
		this.agility = agility;
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
@Raw @Model
private void setHitpoints(int hitpoints) {
	assert isValidHitpoints(hitpoints);
	this.hitpoints = hitpoints;
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
@Raw @Model
private void setStamina(double stamina) {
	assert this.isValidStamina(stamina);
	this.stamina = stamina;
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
 * @post If the  given orientation is not a valid orientation for any Unit,
 *         the orientation will be orientation modulo 2*PI
 *        | if (!isValidOrientation(orientation))
 *        |  	then new.getOrientation() == orientation%2*PI
 */
@Raw
public void setOrientation(float orientation) {
	if (isValidOrientation(orientation))
		this.orientation = orientation;
	else{
		this.orientation = orientation%(2*PI);
	}
}

/**
 * Sets the speed of this unit to the given speed.
 * @param	speed
 * 			The new speed of this unit.
 * @post	The speed of this unit is equal to the given speed.
 * 			| new.getSpeed() == speed
 */

@Raw
public void setSpeed(double speed){
	this.speed = speed;
}

/**
 * Set the nextActivity of this Unit to the given nextActivity.
 * 
 * @param  nextActivity
 *         The new nextActivity for this Unit.
 * @post   The nextActivity of this new Unit is equal to
 *         the given nextActivity.
 *       | new.getnextActivity() == nextActivity
 */
@Raw
private void setNextActivity(Activity nextActivity) {
	this.nextActivity = nextActivity;
}

/**
 * Set the XP of this unit to the given XP.
 * 
 * @param  XP
 *         The new XP for this unit.
 * @post   The XP of this new unit is equal to
 *         the given XP.
 *       | new.getXP() == XP
 * @throws IllegalArgumentException
 *         The given XP is not a valid XP for any
 *         unit.
 *       | ! isValidXP(getXP())
 */
@Raw
public void setXP(int XP) throws IllegalArgumentException {
	if (! isValidXP(XP))
		throw new IllegalArgumentException();
	this.XP = XP;
}

/*------------------------CHECKERS
/**
 * Check whether the given Name is a valid Name for
 * any Unit.
 *  
 * @param  Name
 *         The Name to check.
 * @return true if and only if the name is longer then 2 and
 * 			the first character is an upper case letter and
 * 			the name exists of only letters, numbers, spaces and quotes.
 *       | result == (name.length()>2)&&
 *       (Character.isUpperCase(name.charAt(0))&&
 *       (name.matches("[A-Za-z\"' ]+")))
*/
public static boolean isValidName(String name) {
	return (name.length()>2)&&
			(Character.isUpperCase(name.charAt(0))&&
			(name.matches(ALLOWED_NAME_PATTERN)));
}


/**
 * Check whether the given Position is a valid Position for
 * any Unit.
 *  
 * @param  Position
 *         The Position to check.
 * @return 
 * 			The position needs to be inside the game world and must not be solid
 * 			| result == world.isValidPosition(getCubePosition(position)) &&  
 *   		| !world.isSolidCube(getCubePosition(position))
 * @return 
 * 			The unit is not yet attached to the world
 * 			| this.getWorld() == null
*/
@Raw
public boolean isValidPosition(@Raw double[] position) {
	return this.getWorld() == null || (world.isValidPosition(Utils.getCubePosition(position)) &&
			!world.isSolidCube(Utils.getCubePosition(position)));
}

/**
 * Check whether the given weight is a valid weight for
 * any Unit.
 *  
 * @param  weight
 *         The weight to check.
 * @return true if and only if the weight is between 0 and 200 inclusively and
 * 			the weight must be at least the minimum weight.
 * 			If the unit has an item, the items weight will be added to the units weight.
 *       | result == (1<=weight&&weight<=200)&&(weight>getMinWeight(strength,agility))
 *       
*/
public boolean isValidWeight(int weight) {
	if(hasItem()){
		int weightItem = this.getItem().getWeight();
		weight = weight-weightItem;
	}	
	return 	(1<=weight)
			&&(weight<=200)
			&&(weight>=this.getMinWeight()) ;
	
}


/**
 * Check whether the given Strength is a valid Strength for
 * any Unit.
 *  
 * @param  strength
 *         The Strength to check.
 * @return 
 *       | result == (1<=strength&&strength<=200)
*/
public static boolean isValidStrength(int strength) {
	return (1<=strength&&strength<=200);
	
}

/**
 * Check whether the given Agility is a valid Agility for
 * any Unit.
 *  
 * @param  agility
 *         The Agility to check.
 * @return  true if and only if the agility lays between 1 and 200
 *       | result == (1<=agility && agility<=200)
*/
public static boolean isValidAgility(int agility) {
	return (1<=agility&&agility<=200);
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
 * Check whether the given hitpoints is a valid hitpoints for
 * any Unit.
 *  
 * @param  hitpoints
 *         The hitpoints to check.
 * @return true if and only if the hitpoints are between 0 and getMaxHitpoints()
 *       | result == (0<=hitpoints && hitpoints <= this.getMaxHitpoints()
*/
public boolean isValidHitpoints(int hitpoints) {
	return (0<=hitpoints && hitpoints <= this.getMaxHitpoints());
}


/**
 * Check whether the given stamina is a valid stamina for
 * any Unit.
 *  
 * @param  stamina
 *         The stamina to check.
 * @return 
 *       | result == (0<=stamina && stamina<= this.getMaxStamina())
*/
public boolean isValidStamina(double stamina) {
	return (0<=stamina && stamina<= this.getMaxStamina());
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
 * Check whether the given XP is a valid XP for
 * any unit.
 *  
 * @param  XP
 *         The XP to check.
 * @return The XP should be a positive integer and smaller than 10
 *       | result == 0<=XP&& XP<10;
*/
public static boolean isValidXP(int XP) {
	return 0<=XP && XP<10;
}


/*--------------------HELPER METHODS
//WEIGTH

/**
 * The minimum weight
 * 
 * @return the minimum weight of this unit
 * 		|result ==  (strength+agility)/2
 */
@Model
private int getMinWeight( ){
	return (this.getStrength()+ this.getAgility())/2;
}

//HITPOINTS

/**
 * The maximum amount of hitpoints a unit can have
 * 
 * @return The maximum HP a unit can have is the closest integer
 * 			 to 200 * weight/100 * toughness/100
 * 		| result == Math.ceil(0.02*this.getWeight()*this.getToughness())
 */
public int getMaxHitpoints(){
	
	return (int) Math.ceil(0.02*this.getWeight()*this.getToughness());
}

/**
 * Checks if the unit is fully recovered
 * 
 * @return true if the unit is fully recovered
 * 			|this.getHitpoints() == getMaxHitpoints(this.getWeight(), this.getToughness())&&
			(this.getStamina()==getMaxStamina(this.getWeight(), this.getToughness()))
 */
private boolean isFullyHealed() {
	return this.getHitpoints() == getMaxHitpoints()&&
			(this.getStamina()==getMaxStamina());
}


//STAMINA

/**
 * The maximum amount of stamina a unit can have
 * 
 * @return The maximum Stamina a unit can have is the closest integer
 * 			 to 2 * weight * toughness / 100
 * 		| result == Math.ceil(2 * weight * toughness / 100)
 */
public int getMaxStamina(){
	return (int) Math.ceil(0.02*this.getWeight()*this.getToughness());
}

//ORIENTATION

/**
 * returns the moving orientation of the unit
 * 
 * @param velocityVector
 *			the vector indication the velocity in each direction
 * @return the moving orientation of the unit
 * 			| result == (float) Math.atan2(velocityVector[1], velocityVector[0])
 */
@Model
private static float getMovingOrientation(double[] velocityVector){
	float orientation = (float) Math.atan2(velocityVector[1], velocityVector[0]);
	return orientation;
}
/**
 * Update the orientations of the units who are 
 * @param attacker
 * 			the attacking unit
 * @param defender
 * 			the defending unit
 * @post The orientation of the attacking unit will be updated
 * 			| attacker.getOrientation() = (float) Math.atan2(dPosition[1]-aPosition[1], dPosition[0]-aPosition[1])
 * 			| defender.getOrientation() = (float) Math.atan2(aPosition[1]-dPosition[1], aPosition[0]-dPosition[1])
 */
@Model
private static void updateOrientation(Unit attacker, Unit defender){ 
	double[] aPosition = attacker.getPosition();
	double[] dPosition = defender.getPosition();
	float aOrientation = (float) Math.atan2(dPosition[1]-aPosition[1], dPosition[0]-aPosition[0]);
	float dOrientation = (float) Math.atan2(aPosition[1]-dPosition[1], aPosition[0]-dPosition[0]);
	attacker.setOrientation(aOrientation);
	defender.setOrientation(dOrientation);
}

//SPEED


/**
* Update the speed of the unit with the information we have about that unit
* 
* @param dz
* 		the difference in z coordinates
* @post the new speed of the unit is the speed of the unit
* 		 according the conditions of this unit
*/
public void updateSpeed(int dz){
	double realSpeed;
	if(this.isMoving()){
		double baseSpeed = (double) 0.75*(this.getStrength()+this.getAgility())/(this.getWeight());
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
		realSpeed = walkingSpeed;
		
		if (this.isSprinting())
			realSpeed = walkingSpeed*2;
	}else{
		realSpeed = 0.0;
	}
	
	this.speed = realSpeed;
}

//XP
/**
 * The unit will update his XP and will be rewarded for every 10 XP
 * @param XP
 * 		The XP to be added
 * @effect
 * 		The units XP will be set to the current XP + the given XP modulo 10
 * 		|this.setXP((this.getCurrentXP()+XP)%10);
 * @effect
 * 		Or the unit will be rewarded with extra strength
 * 		| this.setStrength(this.getStrength()+1);
 * 		Or the unit will be rewarded with extra agility
 * 		| this.setAgility(this.getAgility()+1);
 * 		Or the unit will be rewarded with extra toughness
 * 		| this.setToughness(this.getToughness()+1);
 * 	
 * 		
 */
public void updateXP(int XP){
	int currentXP = this.getXP();
	int totalXP = currentXP+XP;
	int newXP = totalXP%10;
	this.setXP(newXP);
	
	int gains = (totalXP-newXP)/10;
	
	for(int i = 0; i<gains; i++){
		int attribute = (int) (Math.random()*3);
		
		if(attribute == 0)
			this.setStrength(this.getStrength()+1);
		if(attribute == 1)
			this.setAgility(this.getAgility()+1);
		if(attribute == 2)
			this.setToughness(this.getToughness()+1);
		}
	}
	

/*___________________________________________________________________
 * __________________________________________________________________
 * -----------------------ADVANCE TIME-------------------------------
 *___________________________________________________________________
 *___________________________________________________________________*/

//TODO further optimize advanceTime

public void advanceTime(double dt) throws IllegalArgumentException, IllegalStateException {
	
	if (!(0.0<=dt&&dt<=0.2))
		throw new IllegalArgumentException();
	
	if(!this.isAlive())
		throw new IllegalStateException();
	
    counterTillRest += dt;
    
    //set counter to the moment the unit needs to rest
    if(counterTillRest >= REST_INTERVAL && this.isAbleToRest()){
    	rest();
    	counterTillRest = 0.0;
    }
    
    //if the unit is not connected to solid cubes anymore, he needs to fall
    //if(this.getActivity()!=Activity.FALLING && !this.getWorld().hasSolidAdjacents(Utils.getCubePosition(this.getPosition())))
    //	this.fall();
    
    // if the unit gets a new task, he first have to move to the next position
    if(!this.isMoving() && this.getActivity() != Activity.FALLING && 
    		!Utils.equals(this.getPosition(),this.getNextPosition())|| this.isMovingToNext()){
    	if(!this.isMovingToNext()){
	    	this.setNextActivity(this.getActivity());
	    	this.setActivity(Activity.MOVING);
	    	this.setMovingToNext(true);
    	} else if(Utils.equals(this.getPosition(),this.getNextPosition())){
    		this.setActivity(this.getNextActivity());
    		this.setMovingToNext(false);
    	}
    	
    }
	
	//if the unit is in default mode, it can randomly start to sprint while moving
	if(this.hasDefaultBehavior() && !this.isSprinting() && this.isMoving() && this.isAbleToSprint()){
		double randomSprint = Math.random();
		if(randomSprint<0.01)
			this.startSprinting();
	}
	
	// continue moving after you are again able to move
	if((this.getTargetPosition()!= null) && !Utils.equals(this.getPosition(),this.getTargetPosition())&&this.isAbleToMoveFurther()){
			this.moveToTarget(Utils.getCubePosition(this.getTargetPosition()));
	}
	
	if(this.hasDefaultBehavior() && this.getActivity()==Activity.NOTHING){
	
			int randomActivity = (int) (Math.random()*3);
			
			if (randomActivity == 0) {
				int[] targetPosition = this.getWorld().getRandomPositionForUnit();
				this.moveToTarget(targetPosition);
			}
			if (randomActivity == 1) {
				work();
			}
			if (randomActivity == 2) {
				rest();
			}
	}
	
	/*
	 * --------------------------HANDEL DIFFERENT ACTIVITIES-----------------------------
	 */
	
	Activity activity =this.getActivity();
	
	switch(activity) {
	
	case FALLING:
		
		falling(dt);
		break;
		
	case MOVING: 
		
			if(this.isSprinting()){
				if(this.getStamina()>=10*dt){
					this.setStamina((this.getStamina()-10*dt));
				}else{
					this.setStamina(0);
					this.stopSprinting();
				}	
			}
			
			double[] cPosition = this.getPosition();
			double[] nPosition = this.getNextPosition();
			double[] tPosition = this.getTargetPosition();
			
			
			if(Utils.equals(cPosition,nPosition)){
				// CENTER OF CUBE
				if(tPosition == null || Utils.equals(cPosition, tPosition)){
					//TARGET REACHED
					this.setActivity(Activity.NOTHING);
					counterTillDefault = 0;
					this.stopSprinting();
					this.setSpeed(0);
				}else{
					//TARGET NOT YET REACHED
					int[] step = new int[3];
					//pathfinding algorithm
					for(int i = 0; i<3; i++){
						if (cPosition[i] == tPosition[i]){
							step[i] = 0;
						}else if(cPosition[i] < tPosition[i]){
							step[i] = 1;
						}else{
							step[i] = -1;
						}
						this.moveToAdjacent(step[0],step[1],step[2]);
						nPosition = this.getNextPosition();
					
//					int[] currentCube = Utils.getCubePosition(getPosition());
//					int[] nextPosWithSequence = null;
//					
//					for (int[] posWithSequence: queue){
//						int[] nextPos = {posWithSequence[0],posWithSequence[1],posWithSequence[2]};
//						int sequenceNumber = posWithSequence[3];
//						
//						if(Utils.areAdjacent(currentCube,nextPos))
//							if(nextPosWithSequence == null || nextPosWithSequence[3]>sequenceNumber)
//								nextPosWithSequence = posWithSequence;		
//					}
//					if(nextPosWithSequence != null){
//						
//					int dx = nextPosWithSequence[0]-currentCube[0];
//					int dy = nextPosWithSequence[1]-currentCube[2];
//					int dz = nextPosWithSequence[1]-currentCube[2];
//					
//					moveToAdjacent(dx, dy, dz);
//					nPosition = this.getNextPosition();
					} 
				}
			}	
			
			double[] iPosition = this.getIntermediatePosition(this.getStep()[0],this.getStep()[1],this.getStep()[2], dt);
			//TO CENTER OF CUBE
			if(Utils.inBetween(cPosition, nPosition, iPosition)){
				this.setPosition(iPosition);
			}else{
				this.setPosition(nPosition);
				this.updateXP(1);
			}
			break;
	
	
	case WORKING: 
		
			this.setProgressTime((float)(this.getProgressTime()+dt));
			if (this.getProgressTime() >= this.getWorkingTime()) {
				this.setActivity(Activity.NOTHING);
				counterTillDefault =0;
			}
			break;
	
	case ATTACKING: 
		
			this.setProgressTime((float)(this.getProgressTime()+dt));
			if (this.getProgressTime() >= this.getFightingTime()) {
				this.setActivity(Activity.NOTHING);
				counterTillDefault = 0;
			}
			break;
	
	case RESTING: 
		
			if (this.isFullyHealed()) {
				this.setActivity(Activity.NOTHING);
				counterTillDefault = 0;
			} else {
				this.setProgressTime((float)(this.getProgressTime()+dt));
				
					if(this.getProgressTime()>this.getMinimalHitpointTime() && 
							this.getHitpoints()<getMaxHitpoints()){
						
						this.setHitpoints(this.getHitpoints()+1);
						this.setProgressTime(this.getProgressTime()-this.getMinimalStaminaTime());

					}else if (this.getProgressTime()>this.getMinimalHitpointTime() && 
							this.getStamina()<getMaxStamina()){
						
						this.setStamina((int)this.getStamina()+1);
						this.setProgressTime(this.getProgressTime()-this.getMinimalStaminaTime());
					}
					
				}
			break;
				
			
			
	case NOTHING: 
		
			counterTillDefault = counterTillDefault+dt;
			if(counterTillDefault >= NOTHING_INTERVAL){
				counterTillDefault = 0.0;
				this.startDefaultBehavior();
				this.setProgressTime(0);
				
			}
			break;
	}
	
}

/**
 * The unit is falling
 * 
 * @param dt
 * 		the time the unit will fall
 * 
 * @post the position will be updated
 * @post if the ground is reached, 
 * 				its next position will be set to the current cube center
 * 				it will suffer damage according to the height
 * 				its activity will be set to nothing
 */
private void falling(double dt) {
	//FALLING
	double[] currentFallingPosition = this.getPosition();
	currentFallingPosition[2] = currentFallingPosition[2]-3.0*dt;
	this.setPosition(currentFallingPosition);
	
	//IF SOLID UNDER CURRENT NEW POSITION
	if(this.getWorld().isSolidUnder(Utils.getCubePosition(currentFallingPosition))){
		
		double[] startedFallingPosition = this.getNextPosition();
		int heightFalled = (int) Math.floor(startedFallingPosition[2]-currentFallingPosition[2]);
		double[] nextPosition = Utils.getCubeCenter(Utils.getCubePosition(currentFallingPosition));
		
		this.setNextPosition(nextPosition);
		this.setActivity(Activity.NOTHING);
		this.takeDamage(heightFalled*10);
	}
}


/*___________________________________________________________________
 * __________________________________________________________________
 * -----------------------MOVING-------------------------------------
 *___________________________________________________________________
 *___________________________________________________________________*/


/*------------------GETTERS
 
/**
 * Get the targetPosition of this unit
 */
@Basic @Raw
public double[] getTargetPosition() {
	return this.cube;
}

/**
 * Get the nextPosition of this unit
 */
@Basic @Raw
public double[] getNextPosition() {
	return this.nextPosition;
}
/**
 * 
 * Get the step to be taken by the unit
 */
@Basic @Raw
public int[] getStep(){
	return this.step;
}

/*-----------------SETTERS
/**
 * 
 * Set the target position of the unit
 * 
 * @param targetPosition
 * 			the position where the unit is heading to
 * @throws IllegalArgumentException
 * 			if the position is not valid
 * 			| (!isValidPosition(targetPosition))
 * @post the units target position is targetPosition
 * 			|new.getTargetPosition() == targetPosition
 */
@Raw @Model
private void setTargetPosition(double[] targetPosition) throws IllegalArgumentException  {
	if (!isValidPosition(targetPosition))
		throw new IllegalArgumentException();
	this.cube = targetPosition;
}

/**
 * 
 * Set the target position of the unit
 * 
 * @param nextPosition
 * 			the position where the unit is heading to
 
 * @post the units next position is nextPosition
 * 			|new.getNextPosition() == nextPosition
 * 
 * @throws IllegalArgumentException
 * 			if the position is not valid
 * 			| (!isValidPosition(nextPosition))
 */
@Raw @Model
private void setNextPosition(double[] nextPosition) throws IllegalArgumentException  {
	if (!isValidPosition(nextPosition))
		throw new IllegalArgumentException();
	this.nextPosition = nextPosition;
}

/**
 * 
 * Set the step to be taken by the unit
 * 
 * @param step
 * 			the position where the unit is heading to
 * 
 * @post the units step is set to step
 * 			|new.getStep() == step
 * 
 * @throws IllegalArgumentException
 * 			if the position is not valid
 * 			| !isValidStep(step)
 * 
 */
@Raw @Model
private void setStep(int[] step) throws IllegalArgumentException {
	if(!isValidStep(step[0],step[1],step[2]))
		throw new IllegalArgumentException();
	this.step = step;
}
/**
 * 
 * Set the step to be taken by the unit
 * 
 * @param b
 * 			indicates that the unit still have to move
 * 
 * @post the isMovingToNext will be set to the boolean b value
 */
@Raw @Model
private void setMovingToNext(boolean b) {
	this.isMovingToNext = b;
	
}

/*
 * -------------------MOVING-------------------
 */

/**
 * Let the move to a target cube
 * 
 * @param cube
 * 			the cube where the unit will move to
 * 
 * @post the current activity of the unit will change to "moving"
 * 			|new.getCurrentActivity == "moving"
 * @post the target position will be set to the center of cube
 * 			| new.getTargetPosition == getCubeCenter(cube)
 * @throws IllegalArgumentException
 * 			will be thrown if the cube is out of bounds
 * 				|!isValidPosition(getCubeCenter(cube)
 */
public void moveToTarget(int[] cube) throws IllegalArgumentException, IllegalStateException{
	
	if(!isValidPosition(Utils.getCubeCenter(cube)))
		throw new IllegalArgumentException();
	
	if(!this.isAbleToMove())
		throw new IllegalStateException();
	
	this.setActivity(Activity.MOVING);
	this.setNextPosition(this.getPosition());
	this.setTargetPosition(Utils.getCubeCenter(cube));
	
//	
//	//reset the queues
//	queue =  new LinkedList<int[]>();
//	//load first elements
//	queue.add(new int[]{cube[0], cube[1],cube[2],0});
//	
//	int index = 0;
//	int[] nextPosWithSequence;
//	//Algorithm to find all path beginning from the current location
//	System.out.println("start looking for paths");
//	while(!queue.contains(Utils.getCubePosition(getPosition())) && queue.size()>index){
//		nextPosWithSequence = ((LinkedList<int[]>) queue).get(index);
//		search(nextPosWithSequence);
//		index++;
//	}
//	if(queue.size()>index){
//		this.setTargetPosition(this.getNextPosition());
//		System.out.println("path not found");
//	}
//	System.out.println("path found");
}

/**
 * 
 * Let the unit move to an adjacent position
 * 
 * @param dx
 * 		The difference in cubes to go in x direction
 * @param dy
 * 		The difference in cubes to go in y direction 
 * @param dz
 * 		The difference in cubes to go in z direction
 * 
 * @effect the acivity of the unit will be set to moving
 * 			| this.setCurrentActivity("moving")
 * 
 * @effect The speed will be updated according to the movement in z direction
 * 			|this.updateSpeed(dz)
 * 
 * @post The unit will get to the new position,
 * 		this is the center of a neighboring cube
 * 			| 	double[] newPosition = {
 *			| cubeCenter[0]+dx,
 *			| cubeCenter[1]+dy,
 *			| cubeCenter[2]+dz};
 *			| new.position == setTargetPosition(newPosition)
 * @post the unit will update his targetposition when only advanceTime is pressed
 * 			|if(this.getTargetPosition() != null && equals(this.getPosition(),this.getTargetPosition()))
			|	new.getTargetPosition = nextPosition
 *
 * @throws IllegalArgumentException
 * 		If at least one of the parameters is not -1, 0, or 1
 * 		| (Math.abs(dx)>1||Math.abs(dy)>1||Math.abs(dz)>1)
 * @throws IllegalStateException
 * 		If the calculated destination is out of bounds
 * 		| !isValidPosition(newPosition)
 */
public void moveToAdjacent(int dx, int dy, int dz) throws IllegalArgumentException, IllegalStateException{
		if (!isValidStep(dx, dy, dz)){
			throw new IllegalArgumentException();
		}
		if(!this.isAbleToMove())
			throw new IllegalStateException();

		double[] cubeCenter = Utils.getCubeCenter(Utils.getCubePosition(this.getPosition()));
		double[] nextPosition = new double[] {cubeCenter[0]+dx,cubeCenter[1]+dy,cubeCenter[2]+dz};
		
		if(!isValidPosition(nextPosition) && !Utils.equals(this.getTargetPosition(), this.getPosition())){
			this.setTargetPosition(cubeCenter);
			this.setNextPosition(cubeCenter);
			return;
		}
		
		if (!isValidPosition(nextPosition))
			throw new IllegalArgumentException("Invalid next position");

		// only when hitting moveToAdjacent
		if(this.getTargetPosition() != null &&
				Utils.equals(Utils.getCubePosition(getPosition()),Utils.getCubePosition(getTargetPosition())))
			this.setTargetPosition(nextPosition);
		
		this.setStep(new int[]{dx,dy,dz});
		this.setActivity(Activity.MOVING);
		this.updateSpeed(dz);
		this.setOrientation(getMovingOrientation(getVelocityVector(dx, dy, dz, this.getSpeed())));
		this.setNextPosition(nextPosition);
			
	}

/*
 * ----------------------HELPER METHODS--------------------------
 */

private Queue<int[]> queue =  new LinkedList<int[]>();
private Queue<int[]> queuePositions = new LinkedList<int[]>();
/**
 * TODO fix pathfinding
 * 
 * @param array
 * 		An int[] with a position and the position of that position
 * @post 
 * 		Adjacent cubes who are passable and have solid adjacent cubes
 * 		 will be added to the queue if they are not already part of the queue.
 * 		| queue.add(new int[] {adjacentCube[0],adjacentCube[1],adjacentCube[2],n+1});
 */
public void search(int[] array){
	
	int[] position = {array[0], array[1], array[2]};
	int n = array[3];
	
	List<int[]> adjacentCubes = this.getWorld().getAdjacentCubes(position);
	
	// loop trough all adjacent cubes
	for (int[] adjacentCube: adjacentCubes){

		if(this.getWorld().hasSolidAdjacents(adjacentCube)
				&& isPassable(adjacentCube)){
			//A candidate cube
			boolean toAdd = true;
			for (int[] queueArray: queue){
				//Already in queue?
				if (queueArray[0] == adjacentCube[0] &&
					queueArray[1] == adjacentCube[1] && 
					queueArray[2] == adjacentCube[2] && 
					queueArray[3]<n)
					toAdd = false;
			}
			if (toAdd){
				queuePositions.add(adjacentCube);
				queue.add(new int[] {adjacentCube[0],adjacentCube[1],adjacentCube[2],n+1});
			}
		}
	}
}

/**
 * 
 * get the intermediate position of the unit
 * 
 * @param dx
 * 		difference in x direction
 * @param dy
 * 		difference in y direction
 * @param dz
 * 		difference in z direction
 * @param dt
 * 		time
 * @return the intermediate position of unit who is walking
 * 			 from his original position to his new position
 * 		|Utils.getIntermediatePosition(step, dt, position, velocityVector);
 * 
 */
public double[] getIntermediatePosition(int dx, int dy, int dz, double dt){
	double[] position = this.getPosition();
	double[] velocityVector = getVelocityVector(dx, dy, dz, this.getSpeed());
	int[] step = new int[] {dx, dy, dz};
	return Utils.getIntermediatePosition(step, dt, position, velocityVector);
};


/**
 * Get the velocity in the different directions of the unit
 * 
 * @param dx
 * 			difference in x direction
 * @param dy
 * 			difference in y direction
 * @param dz
 * 			difference in z direction
 * @param speed
 * 		The speed of the given unit
 * @return the velocity in all directions of the unit
 * 			|result == {speed*dx/distance,speed*dy/distance,speed*dz/distance}
 * 				
 */
@Model
private static double[] getVelocityVector(int dx, int dy, int dz, double speed){
	double distance = Math.sqrt(dx*dx+dy*dy+dz*dz);
	double[] velocity = new double[] {
			speed*dx/distance,
			speed*dy/distance,
			speed*dz/distance
	};
	return velocity;
};

/**
 * Checks if the step is a valid step
 * 
 * @param dx
 * 			difference in x direction
 * @param dy
 * 			difference in y direction
 * @param dz
 * 			difference in z direction
 * @return If the step goes to a neighboring cube
 * 		|result == Math.abs(dx)>1||Math.abs(dy)>1||Math.abs(dz)>1
 */
@Model
private static boolean isValidStep(int dx, int dy, int dz) {
	return !(Math.abs(dx)>1||Math.abs(dy)>1||Math.abs(dz)>1);
}
/**
 * Checks whether the unit is still moving to another cube
 */
@Basic @Model
private boolean isMovingToNext() {
	return this.isMovingToNext;
}



/**_____________________________________________________________
 * _____________________________________________________________
 * -------------------------ACTIVITIES--------------------------
 * _____________________________________________________________
 *_____________________________________________________________
 */

/*
 * -----------------GETTERS-----------------
 */

/**
 * Returns the activity this unit is doing.
 */
@Basic @Model @Raw
private Activity getActivity(){
	return this.activity;
}

/**
 * Returns the time this unit will continue its current activity.
 */
@Basic @Model @Raw
private float getProgressTime(){
	return this.progressTime;
}

/*
 * ----------------SETTERS----------------
 */

/**
 * Sets the current activity to the given activity.
 * 
 * @param	activity
 * 			The activity the unit will be performing.
 * @post	The activity of the unit will be the given activity.
 * 			| new.getCurrentActivity == activity
 * 
 * @effect If the unit is not moving anymore, he will stop sprinting
 * 			| !new.isSprinting()
 * @effect If the unit is not moving anymore, the speed will be set to 0
 * 			| new.getSpeed() == 0
 * 
 * @throws	IllegalArgumentException
 * 			The given activity is not a valid activity.
 */
@Model
private void setActivity(Activity activity) throws IllegalArgumentException{
	this.activity = activity;
	
	//if the unit stopped moving, we have to stop sprinting and stop the speed
		if(!this.isMoving()){
			if(!this.isSprinting())
				this.stopSprinting();
			if(this.getSpeed()>0)
				this.setSpeed(0);
		}
}

/**
 * Sets the current activity time to the given time.
 * 
 * @param	time
 * 			The time the unit will be performing its activity.
 * @post	The activity time of the unit will be the given time.
 * 			| new.getActivityTime == time
 */
@Model
private void setProgressTime(float time){
	this.progressTime = time;
}

/*
 * ----------------------ACTIVITY CHECKERS-----------------------
 */

/**
 * Tells whether the unit is currently moving.
 * @return
 * 		true if the unit is currently moving.
 * 		| result == (this.getCurrentActivity == "moving")
 */
public boolean isMoving(){
	return (this.getActivity()==Activity.MOVING);
}
/**
 * Tells whether the unit is currently working.
 * @return
 * 		true if the unit is currently working.
 * 		| result == (this.getCurrentActivity == "working")
 */
public  boolean isWorking(){
	return (this.getActivity()==Activity.WORKING);	
}
/**
 * Tells whether the unit is currently resting.
 * @return
 * 		true if the unit is currently resting.
 * 		| result == (this.getCurrentActivity == "resting")
 */
public boolean isResting(){
	return (this.getActivity()==Activity.RESTING);
}
/**
 * Tells whether the unit is currently attacking.
 * @return
 * 		true if the unit is currently attacking.
 * 		| result == (this.getCurrentActivity == "attacking")
 */
public boolean isAttacking(){
	return (this.getActivity()==Activity.ATTACKING);
}
/**
 * Tells whether the unit is currently in default behavior.
 * @return
 * 		true if the unit is currently in default behavior.
 * 		| result == (this.hasDefaultBehaviorEnabled)
 */
public boolean hasDefaultBehavior() {
	return this.hasDefaultBehaviorEnabled;
}

/**
* Returns true if the unit is sprinting
* @return
*  		true if the unit is currently sprinting.
* 		| result == this.isSprinting
*/
@Basic @Raw
public boolean isSprinting() {
	return this.isSprinting;
}

/**
* Returns true if the unit is doing nothing.
* @return
*  		true if the unit is currently doing nothing.
* 		| result == (this.getCurrentActivity() == Activity.NOTHING)
*/
public boolean isDoingNothing(){
	return (this.getActivity()==Activity.NOTHING);
}

/*
 * ---------------ACTIVITY INITIALISERS----------------------
 */

//WORKING

/**
 * 
 * The unit starts working
 * 
 * @post the progress time will be set to 0
 * 		| new.getProgressTime = 0
 * 
 * @post the activity of the unit is switched off to nothing
 * 		 | new.getCurrentAcivity() == Activity.WORKING
 * 
 * @throws IllegalStateException
*         the given unit is not able to work or is in default behavior.
*       | ! isAbleToWork()&&!hasDefaultBehavior())
 */
public void work() throws IllegalStateException{
	if (!this.isAbleToWork())
			throw new IllegalStateException();
	
	this.setActivity(Activity.WORKING);
	this.setProgressTime(0);
}

//RESTING

/** 
* The unit starts resting
* 
* 
* @post the activity of the unit is switched off to nothing
* 		 | new.getCurrentAcivity() == Activity.RESTING
* 
* @post the progress time will be set to 0
 * 		| new.getProgressTime = 0
* 
* @throws IllegalStateException
* 			if the  unit is not able to rest
* 			| !this.isAbleToRest()&&!this.hasDefaultBehavior()
*/
public void rest() throws IllegalStateException{
	if(!this.isAbleToRest())
		throw new IllegalStateException();
	
	this.setActivity(Activity.RESTING);	
	this.setProgressTime(0);
}
//FIGHTING

/**
 * Let the unit attack another unit
 * 
 * @post the activity time will be set to the maximal resting time
 * 		| new.getProgressTime = 0
 * 
 * @post the activity of the unit is switched off to nothing
 * 		 | new.getCurrentAcivity() == Activity.ATTACKING
 * 
 * @throws IllegalStateException
 * 			if the  unit is not able to attack or is in default behavior
 * 			| !this.isAbleToAttack()
 * 
 * @effect the orientation of both defender and attacker will be updated
 * 			| updateOrientation(this, defender)
 */
public void attack(Unit defender) throws IllegalStateException{
	if(!this.isAbleToAttack(defender))
		throw new IllegalStateException();
	
	defender.defend(this);
	
	updateOrientation(this, defender);
	this.setActivity(Activity.ATTACKING);
	this.setProgressTime(0);
}



/**
 * Makes the unit defend against a given attacker.
 * 
 * The defender has a 0.2*defender.agility/attacker.agility chance to dodge an attack.
 * If dodging is successful, the defending unit will move to a random adjacent square.
 * 
 * If dodging is unsuccessful, the defending unit will try to block the attack.
 * The chance of blocking is 0.25*(defender.strength*defender.agility)/(attacker.strength+attacker.agility)
 * If blocking is successful, the defending unit takes no damage.
 * 
 * If both dodging and blocking are unsuccessful, the defending unit takes damage.
 * The damage the unit takes equals attacker.strength/10
 * 
 * instantious response, no game time needed
 * 
 * @post or the unit changed his position
 * 			| new.getPosition() == getCubeCenter(getCubePosition(nextPosition);
 * 
 * @post or the unit blocked the attack and nothing happens
 * 			| /
 * 
 * @post or the defender loses some hit points
 * 			| new.getHitpoints() == this.getHitpoints() - attacker.getStrength()/10
 * @effect Successful dodging or blocking gives the defender 20 XP
 * 			| this.updateXP(20)
 * @effect Successful attacking gives the attacker 20XP
 * 			| attackerupdateXP(20)
 */

public void defend(Unit attacker){
	
	this.setActivity(Activity.NOTHING);
	
	//first Dodging
	double probDodging = 0.2*this.getAgility()/attacker.getAgility();
	
	if(Math.random()<probDodging){
		int step[] = new int[] {0,0,0};
		double[] currentPosition = this.getPosition();
		double[] nextPosition = currentPosition;
		while ((step[0]==0 && step[1]==0)||(!isValidPosition(nextPosition))){
			step[0] = -1 + (int)(Math.random()*3);
			step[1] = -1 + (int)(Math.random()*3);
			nextPosition[0] = currentPosition[0] + step[0];
			nextPosition[1] = currentPosition[1] + step[1];
		};
		//may move instantaneous
		this.setPosition(Utils.getCubeCenter(Utils.getCubePosition(nextPosition)));
		this.updateXP(20);
		return;
	};
	
	//then blocking
	double probBlocking = 0.25*(this.getStrength()+this.getAgility())/
			(attacker.getStrength()+attacker.getAgility());
	
	if(Math.random()<probBlocking){
		this.updateXP(20);
		return;
	};
	
	//then taking damage
	int damage = attacker.getStrength()/10;
	takeDamage(damage);
	attacker.updateXP(20);
	}
/**
 * The unit will lose an amount of hitpoints, reaching 0, the unit will die
 * 
 * @param damage
 * 		the damage to be taken
 * @effect the unit will have a new amount of hitpoints
 * 		| this.setHitpoints(this.getHitpoints() - damage)
 * @effect if the hitpoints of the unit reach 0, he will die
 * 		| this.die()
 * 		
 */
@Model
private void takeDamage(int damage) {
	int newHitpoints = this.getHitpoints()-damage;
	if(newHitpoints<=0){
		this.terminate();
	} else {
	this.setHitpoints(newHitpoints);
	};
}

//DEFAULTBEHAVIOUR

/**
 * Start the default behavior of this unit
 * 
 * @post the activity of the unit is switched to default behavior
 * 		 | new.hasDefaultBehavior() == true
 * 
 * @post the activity of the unit is switched off to nothing
 * 		 | new.getCurrentAcivity() == Activity.NOTHING
 * 
 */
public void startDefaultBehavior() {
		this.setProgressTime(0);
		this.setActivity(Activity.NOTHING);
		this.hasDefaultBehaviorEnabled = true; 
}

//SPRINTING

/**
* The Unit starts to sprint
* 
* @post  The unit will go in sprinting mode
*       | new.isSprinting() == true
*       
* @throws IllegalStateException
*         the given unit is not able to sprint
*       | ! this.isAbleToSprint()
*/
public void startSprinting() throws IllegalStateException{
	if (!this.isAbleToSprint())
		throw new IllegalStateException();
	this.isSprinting = true;
}

//FALLING
/**
 * Let the unit fall one z coordinate
 * 
 * @effect The unit will suffer 10 damage
 * 		|this.takeDamage(10);
 * @effect the acivity will be set on falling
 * 		| this.setActivity(Activity.FALLING);
 * @effect the speed will be set on 3
 * 		|this.setSpeed(3);
 * @effect the next position will be set one z level lower
 * 		| this.setNextPosition(Utils.getCubeCenter(Utils.getPositionUnder(currentCube))
 */
@Model
private void fall(){
	this.takeDamage(10);
	this.setActivity(Activity.FALLING);
	this.setSpeed(3);
	this.setStep(new int[] {0,0,-1});
	int[] currentCube = Utils.getCubePosition(this.getPosition());
	double[] nextPosition = Utils.getCubeCenter(Utils.getPositionUnder(currentCube));
	this.setNextPosition(nextPosition);
}







/*
 * --------------ACTIVITY TERMINATORS--------------------
 */

/**
 * Stop default behavior of Unit
 * 
 * @post the activity of the unit is switched off to nothing
 * 		 | new.getCurrentAcivity() == Activity.NOTHING
 * 
 * @post the default behavior is switched off
 * 		| new.hasDefaultBehaviour() == false
 * 
 */
public void stopDefaultBehaviour() {
	this.setActivity(Activity.NOTHING);
	this.hasDefaultBehaviorEnabled = false;
}


/**
* The unit will stop sprinting
* 
* @post  The unit will stop sprinting
*       | new.isSprinting() == false
*/
public void stopSprinting() {
	this.isSprinting = false;
}

/*
 * ---------------ACTIVITY TIME-------------------------
 */


/**
 * Gives the minimal time to recover one hitpoint.
 * The time it takes for a unit to restore one hitpoint
 * 
 * @return  the minimal 
 * 			|result == 40/getToughness()
 */ 
@Model
private float getMinimalHitpointTime(){
	return (float)40/this.getToughness();
}

/**
 * Gives the minimal time to recover one stamina.
 * The time it takes for a unit to restore one stamina
 * 
 * @return  the minimal time it takes to recover one stamina
 * 			|result == 20/getToughness()
 */ 
@Model
private float getMinimalStaminaTime(){
	return (float)20/this.getToughness();
}

/**
 * Gives the time it takes for a unit to carry out some work.
 * 
 * @return The time it takes for this unit to carry out some work, 
 * 			more specific 500/this.getStrength()
 * 		| result == 500/this.getStrength()
 */
@Model
private float getWorkingTime(){
	return (float)500/this.getStrength();
}

/**
 * Gives the time it takes for a unit to attack
 * 
 * @return The time it takes for a unit to attack
 * 		| result == 1
 */
@Immutable
private final float getFightingTime(){
	return (float)1;
}

/*
 * ---------------------------ABILITY CHECKERS-------------------------------
 */

/**
 * Checks if this unit can move.
 * A unit can move if it is not attacking and if it is not working.
 * @return	true if unit is not attacking and not working.
 * 			| result == this.getCurrentActivity()!=Activity.ATTACKING && this.getActivity() != Activity.FALLING
 */
public boolean isAbleToMove(){
	return this.getActivity()!=Activity.ATTACKING && this.getActivity() != Activity.FALLING ;
}

/**
 * Checks if this unit can move further.
 * A unit can move further if it has no activity
 * @return	true if unit is not attacking and not working.
 * 			| result == this.getCurrentActivity()!=Activity.NOTHING
 */
public boolean isAbleToMoveFurther(){
	return this.getActivity()==Activity.NOTHING && this.getActivity() != Activity.FALLING;
}

/**
 * Checks if this unit can rest.
 * A unit can rest if it is not attacking and it needs to recover
 * @return	true if unit is not attacking.
 * 			| result == this.getCurrentActivity()!=Activity.ATTACKING && !this.isFullyHealed()
 */
public boolean isAbleToRest(){
	return this.getActivity()!=Activity.ATTACKING && this.getActivity() != Activity.FALLING;
}

/**
 * Checks if this unit is currently able to sprint.
 * A unit can sprint if its stamina is above 0 and if it is already moving.
 * @return	true if the unit is moving and its stamina is higher than 0.
 * 			| result == this.isMoving() && getStamina()>0 && this.getActivity() != Activity.FALLING
 */
public boolean isAbleToSprint(){
	return this.isMoving() && getStamina()>0 &&this.getActivity() != Activity.FALLING;
}

/**
 * Checks if this unit is currently able to work.
 * A unit can work if it is not attacking.
 * @return	true if this unit is not attacking.
 * 			| result == this.getCurrentActivity() != Activity.ATTACKING&& 
 * 						this.getActivity() != Activity.FALLING
 */
public boolean isAbleToWork(){
	return this.getActivity() != Activity.ATTACKING 
			&& this.getActivity() != Activity.FALLING;
}
/**
 * Checks if this unit is currently able to attack.
 * A unit can work if it is not attacking.
 * @return	true if this unit is not attacking.
 * 			| result == (Math.abs(dx)<=1&&Math.abs(dy)<=1&&Math.abs(dz)==0) && 
 * 			  this.getActivity() != Activity.FALLING ||
 * 			  defender.getActivity() != Activity.FALLING;
 */
@Immutable
public boolean isAbleToAttack(Unit defender){
	if(this.getActivity() != Activity.FALLING || defender.getActivity() != Activity.FALLING)
		return false;
	
	int[] aPosition = Utils.getCubePosition(this.getPosition());
	int[] dPosition = Utils.getCubePosition(defender.getPosition());
	int dx = aPosition[0] - dPosition[0];
	int dy = aPosition[1] - dPosition[1];
	int dz = aPosition[2] - dPosition[2];

	return (Math.abs(dx)<=1&&Math.abs(dy)<=1&&Math.abs(dz)==0);
}

/*_____________________________________________________________
 * ____________________________________________________________
 *-------------------------WORLD-------------------------------
 *-------------------NON CONTROLLING CLASS
 * ____________________________________________________________
 *_____________________________________________________________
 */

/**
 * Return the world of this unit.
 */
@Basic @Raw @Immutable
public World getWorld() {
	return this.world;
}
/**
 * 
 * @param world
 * 			the new world for the unit
 * @post the unit will have the world world as his new world
 * 
 */

@Raw @Model
protected void setWorld(World world){
	this.world = world;
}

/**
 * Checks if the world can belong to this unit
 * 
 * @param world
 * 		the world to be checked
 * @return
 * 		| result = world == null || this.getWorld() == world
 * 		
 */
@Raw
public boolean canHaveAsWorld(World world){
	return world == null || this.getWorld() == world;
}


/**
 * Returns whether the cube on the position is passable
 * 
 * @param position
 * 		the position of the cube
 * @return
 * 		whether the cube is passable
 */
@Raw @Model
protected boolean isPassable(int[] position){
	return !this.getWorld().isSolidCube(position);
	
}


/*_____________________________________________________________
 * ____________________________________________________________
 *-------------------------FACTION-----------------------------
 *---------------------NON CONTROLLING CLASS-------------------
 * ____________________________________________________________
 *_____________________________________________________________
 */

/**
 * Return the faction of this unit.
 */
@Basic @Raw
public Faction getFaction() {
	return this.faction;
}

/**
 * Set the given faction as faction
 * 
 * @param faction
 * 			the new faction of this unit
 * @post the unit will have the faction faction as his new faction
 * 		| this.getFaction == faction
 */
@Raw
public void setFaction(Faction faction) throws IllegalArgumentException{
	this.faction = faction;
}

/**
 * Checks if the faction can belong to this unit
 * 
 * @param faction
 * 		the faction to be checked
 * @return
 * 		| result = faction != null
 * 		
 */
@Raw
public boolean canHaveAsFaction(Faction faction){
	return faction != null;
}


/*_____________________________________________________________
 * ____________________________________________________________
 *-------------------------ITEMS-----------------------------
 *---------------------CONTROLLING CLASS-------------------
 * ____________________________________________________________
 *_____________________________________________________________
 */

	//TODO PREPARE FOR MORE ITEMS

	/**
	* Variable registering the item of this Unit.
	*/
	private Item item;


	/**
	* Return the item of this Unit.
	*/
	@Basic @Raw
	public Item getItem() {
		return this.item;
	}
	

	/**
	 * Checks whether the unit has an item
	 * 
	 * @return this.getItem() != null
	 */
	public boolean hasItem(){
		return this.getItem() != null;
	}
	
	/**
 	* Set the item of this Unit to the given item.
 	* Set up the association
 	* 
 	* @param  item
 	*         The new item for this Unit.

 	* @post   The item of this new Unit is equal to
 	*         the given item.
 	*       | new.getItem() == item
 	*       
 	* @effect This item has now this unit as its unit
 	* 		| item.setUnit(unit)
 	* 
 	* @throws IllegalAgrumentException
 	*         The given item is not a valid item for this
 	*         Unit.
 	*       | ! canHaveAsItem(item)
 	*/
	@Raw
	public void addItem(Item item) throws IllegalArgumentException {
		
		if (! canHaveAsItem(item))
			throw new IllegalArgumentException();
		
		item.setUnit(this);
		this.item = item;
			

	}
	/**
 	* remove the item of this Unit.
 	* Break down the association
 	* 
 	* @param  item
 	*         The item to remove for this Unit.

 	* @post   The item of this Unit is equal to
 	*         null.
 	*       | new.getItem() == null
 	*       
 	* @effect This item has no unit as unit
 	* 		| item.setUnit(null)
 	* 
 	* @throws IllegalAgrumentException
 	*         The given item is not a valid item for this
 	*         Unit.
 	*       | item.getUnit() != this && this.getItem() == item
 	*/
	@Raw
	public void removeItem(Item item) throws IllegalArgumentException {
		if (item.getUnit() != this && this.getItem() == item)
			throw new IllegalArgumentException();
		//Break down association
		item.setUnit(null);
		this.item = null;
	}
	
	/**
 	* Check whether the given item is a valid item for
 	* this Unit.
 	*  
 	* @param item
 	* 		The item to check.
 	* @return
 	* 		result == item.getUnit() == null || item.getUnit() == this
 	*/
	@Raw @Model
	private boolean canHaveAsItem(Item item){
		return item.getUnit() == null || item.getUnit() == this;
	}
	
	/**
	 * The unit drops his item
	 * 
	 * @param
	 * 		The item to be dropped
	 * 
	 * @effect
	 * 		The item gets the units position
	 * 		|item.setPosition(this.getPosition());
	 * @effect
	 * 		The item will be removed by the unit
	 * 		|removeItem(item);
	 * @effect
	 * 		The world will add this item
	 * 		|world.addItem(item);
	 */
	private void dropItem(Item item){
		if(item != null){
		
			item.setPosition(this.getPosition());
			removeItem(item);
			this.getWorld().addItem(item);
			
			this.setWeight(this.getWeight() - item.getWeight());
		}
	}
	
	/**
	 * The unit picks up an item
	 * 
	 * @param item
	 * 		The item to be picked up
	 * 
	 * @effect
	 * 		The item will be added by the unit
	 * 		|addItem(item);
	 * @effect
	 * 		The world will remove this item
	 * 		|world.removeItem(item);
	 */
	private void pickUpItem(Item item) {
		if (item != null && canHaveAsItem(item) && !hasItem()){
			addItem(item);
			this.getWorld().removeItem(item);
			
			this.setWeight(this.getWeight()+item.getWeight());
		}
	}
	
	//-------------------------CARRYING HELPER
	
	/**
	 * Checks whether the unit is carrying a boulder
	 * 
	 * @return this.hasItem() && (this.getItem() instanceof Boulder)
	 */
	public boolean isCarryingBoulder(){
		return this.hasItem() && (this.getItem() instanceof Boulder);
	}
	
	/**
	 * Checks whether the unit is carrying a log
	 * 
	 * @return this.hasItem() && (this.getItem() instanceof Log)
	 */
	public boolean isCarryingLog(){
		return this.hasItem() && (this.getItem() instanceof Log);
	}
	
	

	
	
	

}