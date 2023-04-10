/** 
 * Creates a robot and provides several methods.
 * @author Claire Logan Hankla
 */

import java.util.ArrayList;

public class Robot implements Contract{
    /** Name of the robot */
    private String name; 
    /** Robot size */
    private int size;
    /** Robot's energy level out of 100 */
    private int energyLevel;
    /** Robot's X Position */
    private int xPosition;
    /** Robot's Y Position */
    private int yPosition;
    /** Robot's inventory of items */
    private ArrayList<String> inventory;

    /** Robot constructor creates new 6ft tall robot with full energy at position (0,0).
     * @param name Robot name
     */
    public Robot(String name){
        this.name = name;
        System.out.println("Your robot's name is " + name + ". Please note that " + name + " must adhere to the Three Laws of Robotics: \n 1. A robot may not injure a human being or, through inaction, allow a human being to come to harm. \n 2. A robot must obey orders given it by human beings except where such orders would conflict with the First Law. \n 3. A robot must protect its own existence as long as such protection does not conflict with the First or Second Law.");
        this.size = 6;
        this.energyLevel = 100;
        this.xPosition = 0;
        this.yPosition = 0;
        this.inventory = new ArrayList<String>();
    }
    
    /** Tells Robot to grab and item and add it to inventory
     * @param item Item to add to inventory
     */
    public void grab(String item){
        this.inventory.add(item);
    }

    /** Tells Robot to remove an item from the inventory
     * @param item Item to remove from inventory
     * @return String indicated that the item has been removed
     * @throws RuntimeException If item is not currently part of the inventory
     */
    public String drop(String item){
        if(this.inventory.contains(item)){
            this.inventory.remove(item);
            return item + "removed from" + this.name + "'s inventory.";
        } else{
            throw new RuntimeException(item + " is not in " + this.name + "'s inventory.");
        }
    }

    /** Tells Robot to examine an item without adding it to the inventory
     * @param item Item to examine
     */
    public void examine(String item){
        this.energyLevel -= 10;
        System.out.println(this.name + " is examining " + item +". To put " + item + " in the inventory, tell " + this.name + " to grab() it. " + this.name + "'s energy level is " + this.energyLevel + "/100.");
    }

    /** Tells Robot to use an item, removes item from inventory after use
     * @param item Item to use
     * @throws RuntimeException If item is not in inventory
     */
    public void use(String item){
        if(this.inventory.contains(item)){
            this.inventory.remove(item);
            this.energyLevel -= 10;
            System.out.println(this.name + " has used " + item + ". " + this.name + "'s energy level is " + this.energyLevel + "/100.");
        } else{
            throw new RuntimeException(item + " is not in the inventory. To use it, please grab() it first.");
        }
    }

    /** Tells robot to move 5 units in the x direction, using energy
     * @param direction Direction to move
     * @return boolean for whether or not the robot can walk depending on energy levels
     */
    public boolean walk(String direction){
        if(this.energyLevel >= 10){
            this.energyLevel -= 10;
            this.xPosition += 5;
            System.out.println(this.name + " walked " + direction + ", and " + this.name + "'s new X position is " + this.xPosition + ".");
            return true;
        } else{
            return false;
        }
    }

    /** Tells robot to fly, moving in both x and y planes, uses energy
     * @param x units to fly in X plane
     * @param y units to fly in Y plane
     * @return boolean for whether the robot can fly as specified, assuming a (100, 100) limit
     */
    public boolean fly(int x, int y){
        if(x + this.xPosition <= 100 && y + this.yPosition <=100 ){
            this.xPosition += x;
            this.yPosition += y;
            this.energyLevel -= 10;
            System.out.println(this.name + "'s X Position is " + this.xPosition + ", and " + this.name + "'s Y Position is " + this.yPosition + ".");
            return true;
        } else{
            return false;
        }
    }

    /** Makes the robot shrink in size
     * @throws RuntimeException If robot is told to shrink to 0 feet tall
     * @return A number reflecting the robot's new size
     */
    public Number shrink(){
        if(this.size >=2){
            this.size -= 1;
            System.out.println(this.name + " is now " + this.size + " feet tall.");
        } else{
            throw new RuntimeException(this.name + " cannot shrink under 1 foot tall.");
        }
        return this.size;
    }

    /** Makes the robot grow in size
     * @return A number reflecting the robot's new size
     */
    public Number grow(){
        this.size += 1;
        System.out.println(this.name + " is now " + this.size + " feet tall.");
        return this.size;
    }

    /** Increases the robot's energy levels by 10
     * @throws RuntimeException If robot doesn't need rest
     */
    public void rest(){
        if(this.energyLevel <= 90){
            this.energyLevel += 10;
            System.out.println(this.name + "'s energy level after resting is " + this.energyLevel + "/100.");
        } else{
            throw new RuntimeException(this.name + " already has full energy.");
        }
    }

    /** Overloaded version of undo() that shows the user options for methods that can be undone */
    public void undo(){
        System.out.println("You can undo the following actions: \n * rest() \n * grow() \n * shrink() \n * grab() \n * drop() \n * use() \n To do so, please use undo() with the method name as the first parameter. If the method takes input, please include it as the second parameter. For example: undo(\"grab\") OR undo(\"grab\", \"item\")");
    }

    /** Overloaded version of undo() that allows user to specify which method they'd like to undo. Only works on methods that don't take input
     * @param undoMethod The name of the method the user would like to undo
     * @throws RuntimeException If the method can't be undone
     */
    public void undo(String undoMethod){
        if(undoMethod == "rest"){
            this.energyLevel -= 10;
        } else if(undoMethod == "grow"){
            this.shrink();
        } else if(undoMethod.contains("shrink")){
            this.grow();
        } else{
            throw new RuntimeException("This action cannot be undone. Use undo() to check which actions may be undone. Also, check that you've included any parameters.");
        }
    }

    /** Overloaded version of undo() that allows user to specify which method they'd like to undo as well as the input for the method.
     * @param undoMethod The name of the method the user would like to undo
     * @param methodParam The input parameters for the method the user would like to undo
     * @throws RuntimeException If the method can't be undone
     */
    public void undo(String undoMethod, String methodParam){
        if(undoMethod == "use"){
            this.rest();
            this.grab(methodParam);
        } else if(undoMethod == "drop"){
            this.grab(methodParam);
        } else if(undoMethod == "grab"){
            this.drop(methodParam);
        } else{
            throw new RuntimeException("This action cannot be undone.");
        }
    }

    /** Tells robot to stab item so long as it does not violate the Laws of Robotics.
     * @param item Item to stab
     * @throws RuntimeException If stabbing would harm a human
     */
    public void stab(String item){
        if(!item.contains("uman")){
            this.energyLevel -= 10;
            System.out.println("You have stabbed " + item + ". ");
        } else{
            throw new RuntimeException("This action violates the First and Second Law of Robotics.");
        }
    }

    public static void main(String[] args) {
        Robot robbie = new Robot("Robbie");
        robbie.grow();
        robbie.shrink();
        robbie.fly(2, 3);
        robbie.walk("north");
        //robbie.undo();
        robbie.grab("Phone");
        //robbie.drop("Laptop");
       // robbie.drop("Phone");
        robbie.examine("T-shirt");
        robbie.use("Phone");
        System.out.println("Robbie's inventory: " + robbie.inventory);
        robbie.rest();
        robbie.rest();
        robbie.rest();
        robbie.rest();
        robbie.grab("Cat");
        robbie.use("Cat");
        robbie.undo();
        robbie.undo("use", "Cat");
        robbie.grow();
        robbie.undo("grow");
        robbie.stab("creamer");
        robbie.stab("human");
    }
}
