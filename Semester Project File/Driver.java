/**
 * --------------------------------------------------------------------------
 * File name: Driver.java
 * Project name: Semester Project
 * --------------------------------------------------------------------------
 * Creator's name and email: Michael Ng, ngmw01@etsu.edu
 * Course: CSCI 1250-942
 * Creation Date: 10/8/2020
 * Completion Date: 11/16/2020
 * @version 1.00
 * --------------------------------------------------------------------------
 */
//imports needed in order to play background music.
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


import javax.swing.JOptionPane;

/**
 * The Driver Class creates objects and accepts user input in order to advance
 * the game.
 *
 * Date created: October 8, 2020
 * 
 * @author Michael Ng, ngmw01@etsu.edu
 */
public class Driver
{
    static String heroClass;
    static String heroName;
    //These fields are used primarily in the enemy class.
    static String difficulty = "Easy";
    static String multiplier = "x1";
    static boolean postGame = false;

    static Ally zacharias;
    static Ally anthiera;
    static Ally currentAlly;
    static Statistics stats;
    static Skills heroSkills;
    static StatIncrease increaseStat;
    static Map area;
    static SimpleAudioPlayer SAP;
    /**
     * Main method used to run the program. Accept button input from the user and
     * adjusts attributes/classes based on input.
     * 
     * Date created: October 8, 2020
     * 
     * @param args
     * @throws LineUnavailableException
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        SAP = new SimpleAudioPlayer("18.TitleScreen-Echoes-FireEmblemEchoes.wav", true);
        int menuChoice;

        do
        {
            String[] menuOptions = {"New Game", "Settings"};
            menuChoice = JOptionPane.showOptionDialog(null, "Welcome to The Dungeon of Dystopia!", "Welcome!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menuOptions, menuOptions[0]);
            
            if(menuChoice == 1)
            {
                int menuChoice2;
                do
                {
                    String[] settingOptions = {"Change Difficulty", "Change XP Multiplier", "Leave Settings"};
                    menuChoice2 = JOptionPane.showOptionDialog(null, "Difficulty:  " + difficulty + "\nCurrent XP Modifier: " + multiplier + "\n\nYou will not be able to change these in-game.", "Settings", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, settingOptions, settingOptions[0]);
                    if(menuChoice2 == 0)
                    {
                        if(difficulty.equals("Easy"))
                        {
                            difficulty = "Medium";
                            JOptionPane.showMessageDialog(null, "Your difficulty has been set to Medium.\nEnemies now have a 1.5x bonus to their attributes.", "Difficulty is Medium", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(difficulty.equals("Medium"))
                        {
                            difficulty = "Hard";
                            JOptionPane.showMessageDialog(null, "Your difficulty has been set to Hard.\nEnemies now have a 2x bonus to their attributes.", "Difficulty is Hard", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(difficulty.equals("Hard"))
                        {
                            difficulty = "Easy";
                            JOptionPane.showMessageDialog(null, "Your difficulty has been set to Easy.\nEnemies do not have any bonuses to their attributes.", "Difficulty is Easy", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else if(menuChoice2 == 1)
                    {
                        if(multiplier.equals("x1"))
                        {
                            multiplier = "x2";
                            JOptionPane.showMessageDialog(null, "Your XP Multiplier is now set to x2.\nYou will now get double XP from battles.", "XP Multiplier is 2.0", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(multiplier.equals("x2"))
                        {
                            multiplier = "x3";
                            JOptionPane.showMessageDialog(null, "Your XP Multiplier is now set to x3.\nYou will now get triple XP from battles.", "XP Multiplier is 3.0", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(multiplier.equals("x3"))
                        {
                            multiplier = "x1";
                            JOptionPane.showMessageDialog(null, "Your XP Multiplier is now set to x1.\nYou will not recieve any additional XP from battles.", "XP Multiplier is 1.0", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }while(menuChoice2 != 2 && menuChoice2 != -1);
            }
        }while(menuChoice != 0 && menuChoice != -1);

        
        stopAudio();
        //Story Dialog, with options as dialog choices (it's not really a choice).
        System.out.println("***Welcome to The Dungeon of Dystopia!***");
        System.out.println("*EVENT LOG*\nAll events that have occured will be posted here.");

        //Plays soundtrack number 1 from the file.
        SAP = new SimpleAudioPlayer("1.IntroSequence-FireEmblemEchoes-Serenity.wav", true);
        String[] response1 = {"Zacharias? What happened?"};
        JOptionPane.showOptionDialog(null, "You are shaken awake by your friend Zacharias. \nUpon your waking, he breathes a sigh of relief. \n\nYou appear to be in a cave of some kind.\n", "Prologue", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, response1, response1[0]);

        String[] response2 = {"Is Fendale City alright?"};
        JOptionPane.showOptionDialog(null, "\"We barely made it out of Fendale city\", he says. \"You passed out trying to fight off the Monster Legion\". \nZacharias finishes wiping one of your wounds with an alcohol wipe and sits beside you.\n", "Prologue", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, response2, response2[0]);

        String[] response3 = {"Oh..."};
        JOptionPane.showOptionDialog(null, "Zacharias shakes his head. He looks tired, as if his fatigue has finally set in. \n\n\"I'm afraid not\", he says. \n\"It's such a shame. Fendale city is the last safe haven known to mankind\" \n", "Prologue", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, response3, response3[0]);

        String[] response4 = {"Perhaps."};
        JOptionPane.showOptionDialog(null, "\"Oh, Indeed. We have nowhere else to go to.\" \nHe looked up at the rock spikes above him. \n\"Perhaps this cave could prove otherwise.\" \n", "Prologue", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, response4, response4[0]);

        String[] response5 = {"Thank you."};
        JOptionPane.showOptionDialog(null, "Zacharias offers you a hand. \n\"Come on. Let's see what this cave has to offer.\" \n\"Maybe there is someone who can help us.\"\n", "Prologue", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, response5, response5[0]);

        //boolean variable used for do-while loops within the constructor.
        boolean temp = false;
        //Gets the name of the Hero and prints accordingly based on input.
        do
        {
            heroName = JOptionPane.showInputDialog(null, "\nWhat is the name of your Hero?", "Hero Name", JOptionPane.DEFAULT_OPTION);

            if(heroName.length() <= 15)
            {
                if(heroName.isBlank())
                {
                    heroName = "Javar";
                }
                heroName = heroName.trim();
                String[] options = {"Yes", "No"};
                int confirmation = JOptionPane.showOptionDialog(null, "Is " + heroName + " the name of your Hero?", "Hero Name Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                
                if(confirmation == 0)
                {
                    temp = true;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Sorry! Your name cannot be more than 15 characters long.", "Error - Too Many Characters", JOptionPane.ERROR_MESSAGE);
            }
            

        }while(temp == false);
        //Resets the boolean temp.
        temp = false;
        //Asks the user for the Hero's class specialization and responds to input accordingly.
        do
        {
            String[] options = {"Mage", "Warrior"};
            int choice = JOptionPane.showOptionDialog(null, "What class does your Hero specialize in?", "Class Specialization", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if(choice == 0)
            {
                heroClass = "Mage";
            }
            else
            {
                heroClass = "Warrior";
            }


            String[] confirmationOptions = {"Yes", "No"};
            choice = JOptionPane.showOptionDialog(null, "Are you sure you want to specialize as a " + heroClass + "?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmationOptions, confirmationOptions[0]);
            if(choice == 0)
            {
                temp = true;
            }

        }while(temp == false);

        //initializes all defined static objects in the driver.
        initialize();

        String[] response6 = {"I agree."};
        JOptionPane.showOptionDialog(null, "\"" + heroName + ", it's good to have you by my side\" Zacharias says. \n\"We've been friends for as long as I can remember.\"\n", "Prologue", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, response6, response6[0]);

        String[] response7 = {"Look out!"};
        JOptionPane.showOptionDialog(null, "Suddenly, a Goblin jumps from out of nowhere.\nYou can recognize the Goblin as part of the Monster Legion.\n", "Prologue", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, response7, response7[0]);
        
        String[] response8 = {"BEGIN BATTLE"};
        JOptionPane.showOptionDialog(null, "\"Foolish Humans! You will not take one step further into this cave!\" \nZacharias looks at you and nods, knowing what should be done.\n", "Prologue", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, response8, response8[0]);
        stopAudio();
        Enemy Goblin = new Enemy("Goblin");
        Battle bat = new Battle(stats, heroSkills, currentAlly, Goblin);
        bat.getResult(increaseStat);

        String[] response9 = {"Thanks, Zacharias."};
        JOptionPane.showOptionDialog(null, "\"That was quite the fight,\" Zacharias says. \n\"Good job, " + heroName + ".\"\n", "Prologue", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, response9, response9[0]);

        String[] response10 = {"Noted."};
        JOptionPane.showOptionDialog(null, "\"Let's be more careful.\" Zacharias says. \"We can't let the legion know we are here.\"\n", "Prologue", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, response10, response10[0]);

        //starts background audio. This background audio changes depending on location.
        resumeBGAudio();
        int choice;
        do
        {
            //MAIN MENU
            String[] consoleOptions = {"Attribute Info", "Attributes", "Open Shop", "Skills", "New Scenario", "Rush Location", "Practice Battle", "Friendly Duel", "Switch Ally", "Leave"};
            choice = JOptionPane.showOptionDialog(null, "Welcome to the Command Console! Please Choose an Action. \nYour Group is Level " + increaseStat.getHeroLevel() + ".\n\nLocation:\n" + area.getLocation(), "Command Console", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null, consoleOptions, consoleOptions[0]);

            //Attribute Description
            switch(choice)
            {
                //Attribute Info - shows a message dialog showing the uses of each stat.
                case 0:
                    JOptionPane.showMessageDialog(null, stats.attributeDescription(heroClass), "Attribute Description", JOptionPane.INFORMATION_MESSAGE);
                    break;
                //Attributes - shows a message dialog containing the hero's attributes.
                case 1:
                    JOptionPane.showMessageDialog(null, stats.listAttributes(), "Hero Attributes", JOptionPane.INFORMATION_MESSAGE);
                    break;
                //Open Shop - Opens the shop menu.
                case 2:
                    openShop(stats);
                    break;
                //Skills - returns the hero's available skills.
                case 3:
                    JOptionPane.showMessageDialog(null, heroSkills.listAllSkillDamage(), "Skill Damage", JOptionPane.INFORMATION_MESSAGE);
                    break;
                //New Scenario - Gets a random scenario and increments area by 1.
                case 4:
                    area.getScenario();
                    break;
                //rush location - calls getScenario 9 times.
                case 5:
                    String[] options = {"Yes", "No"};
                    int optionChoice = JOptionPane.showOptionDialog(null, "Rush Location will give you 9 different scenarios without stopping.\nAre you sure you want to proceed?\n(Rush Location will always stop on every 10th floor.)", "Rush Location", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
                    if(optionChoice == 0)
                    {
                        area.rushLocation();
                    }
                    break;
                //Practice Battle - allows the user to enter a fight with a random enemy.
                case 6:
                    stopAudio();
                    bat = new Battle(stats, heroSkills, currentAlly, area.getArea());
                    bat.getResult(increaseStat);
                    resumeBGAudio();
                    break;
                //Friendly Duel - Allows the uesr to enter battle against an ally.
                case 7:
                    //if anthiera has already been unlocked.
                    if(anthiera != null)
                    {
                        String[] allies = {"Zacharias", "Anthiera"};
                        int optionChosen = JOptionPane.showOptionDialog(null, "Challenge Who?", "Who to Challenge", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, allies, allies[0]);

                        if(optionChosen == 0)
                        {
                            JOptionPane.showMessageDialog(null, "You decided to ask Zacharias for a friendly competition.", "Friendly Duel", JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(null, "\"Sure thing, " + heroName + ",\" Zacharias says.\n\"Hold nothing back!\"", "Friendly Duel", JOptionPane.INFORMATION_MESSAGE);
                            stopAudio();
                            //Allies will be unaffected by difficulty.
                            Enemy zach = new Enemy(zacharias); 
                            bat = new Battle(stats, heroSkills, zach);
                            bat.getDuelResults(increaseStat);
                            resumeBGAudio();
                        }
                        if(optionChosen == 1)
                        {
                            JOptionPane.showMessageDialog(null, "You decided to ask Anthiera for a friendly competition.", "Friendly Duel", JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(null, "\"Of course, " + heroName + ",\" Anthiera says.\n\"I'll show you what my Royal Guard training has led to!\"", "Friendly Duel", JOptionPane.INFORMATION_MESSAGE);
                            stopAudio();
                            //Allies will be unaffected by difficulty.
                            Enemy anth = new Enemy(anthiera); 
                            bat = new Battle(stats, heroSkills, anth);
                            bat.getDuelResults(increaseStat);
                            resumeBGAudio();
                        }
                    }
                    else //if anthiera has not been unlocked
                    {
                        JOptionPane.showMessageDialog(null, "You decided to ask Zacharias for a friendly competition.", "Friendly Duel", JOptionPane.INFORMATION_MESSAGE);
                        JOptionPane.showMessageDialog(null, "\"Sure thing, " + heroName + ",\" Zacharias says.\n\"Hold nothing back!\"", "Friendly Duel", JOptionPane.INFORMATION_MESSAGE);
                        stopAudio();
                        //Allies will be unaffected by difficulty.
                        Enemy zach = new Enemy(currentAlly); 
                        bat = new Battle(stats, heroSkills, zach);
                        bat.getDuelResults(increaseStat);
                        resumeBGAudio();
                    }
                    break;
                //Switch Ally - Allows the user to bring a different ally to battle.
                case 8:
                    if(anthiera != null)
                    {
                        if(currentAlly.equals(zacharias))
                        {
                            currentAlly = anthiera;
                            area.setAlly(anthiera);
                        }
                        else //if(currentAlly.equals(anthiera))
                        {
                            currentAlly = zacharias;
                            area.setAlly(zacharias);
                        }
                        JOptionPane.showMessageDialog(null, "Successfully switched allies! \nYou new ally is " + currentAlly.getAllyName(), "Switched Allies", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Zacharias is your only available ally at the moment.", "No Other Allies", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                //Leave
                case 9:
                    JOptionPane.showMessageDialog(null, "Leaving Command Console.\nThank you for playing The Dungeon of Dystopia!\n\n", "Leaving Console", JOptionPane.INFORMATION_MESSAGE);
                    break;
                //Pressing the 'X' on the top-right.
                case -1:
                    JOptionPane.showMessageDialog(null, "Leaving Command Console.\nThank you for playing The Dungeon of Dystopia!\n\n", "Leaving Console", JOptionPane.INFORMATION_MESSAGE);
                    break;
                //Invalid Command
                default:
                    JOptionPane.showMessageDialog(null, "Invalid command. Please try again.\n", "Invalid Command", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }while(choice != 9 && choice != -1);

        //Exits the program.
        System.exit(0);
    }
    /**
     * Opens the shopping menu. Allows the user to buy a selection of goods.
     * 
     * Date Created: October 11, 2020
     * 
     */
    public static void openShop(Statistics heroStats)
    {
        //moneyAvailable gets the hero's total amount of money.
        boolean completePurchases = false;

        do
        {
            String[] choices = {"MaxHP", "Constitution", "Affinity", "Armor Mod", "Resistance", "Speed", "Leave"};
            int pick = JOptionPane.showOptionDialog(null, "Welcome to the Shop! What will you buy?\nYou have: $" + heroStats.getMoney() + "\n\nMaxHP Potion: $100 \n - A MaxHP Potion raises the Hero's Max HP by 20.\n\nConstitution Potion: $100 \n - A Constitution Potion raises the Hero's Constitution by 4.\n\nAffinity Potion: $100\n - An Affinity Potion raises the Hero's Affinity by 4.\n\nArmor Potion: $100\n - An Armor Modification raises the Hero's Armor by 4. \n\nResistance Training: $100\n - Resistance Training raises the Hero's Resistance by 4.\n\nSpeed Training: $100\n - Speed Training raises the Hero's Speed by 4.\n\n", "Shop", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);

            
            //Used for button options in the shop class.
            String[] confirmationOptions = {"Yes", "No"};

            //Buying a MaxHP Potion
            if(pick == 0)
            {
                int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to buy a MaxHP Potion?\nYour Money: $" + heroStats.getMoney() + "\nYour Max HP: " + heroStats.getMaxHP(), "Buy MaxHP Potion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmationOptions, confirmationOptions[0]);
                if(choice == 0 && heroStats.getMoney() >= 100)
                {
                    heroStats.addMaxHP(20);
                    heroStats.setHP(heroStats.getMaxHP());
                    heroStats.addMoney(-100);
                    JOptionPane.showMessageDialog(null, "Thank you for your purchase!\nYour Max HP: " + heroStats.getMaxHP() + "\nReturning to the Shopping Command Console...\n", "Thank you!", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(choice == 0 && heroStats.getMoney() < 100)
                {
                    JOptionPane.showMessageDialog(null, "Sorry! You do not have enough money. You only have $" + heroStats.getMoney() + " out of the $100. \nReturning to the Shopping Command Console...\n", "Not Enough Money", JOptionPane.ERROR_MESSAGE);
                }
                else if(choice == 1)
                {
                    JOptionPane.showMessageDialog(null, "Returning to the Shopping Command Console...", "Returning to Shop", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid input. Returning to the Shopping Command Console...", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }

            //Buying a Constitution Potion
            if(pick == 1)
            {
                int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to buy a Constitution Potion?\nYour Money: $" + heroStats.getMoney() + "\nYour Constitution: " + heroStats.getConstitution(), "Buy Constitution Potion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmationOptions, confirmationOptions[0]);
                if(choice == 0 && heroStats.getMoney() >= 100)
                {
                    heroStats.addConstitution(4);
                    heroStats.addMoney(-100);
                    JOptionPane.showMessageDialog(null, "Thank you for your purchase!\nYour Constitution: " + heroStats.getConstitution() + "\nReturning to the Shopping Command Console...\n", "Thank you!", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(choice == 0 && heroStats.getMoney() < 100)
                {
                    JOptionPane.showMessageDialog(null, "Sorry! You do not have enough money. You only have $" + heroStats.getMoney() + " out of the $100. \nReturning to the Shopping Command Console...\n", "Not Enough Money", JOptionPane.ERROR_MESSAGE);
                }
                else if(choice == 1)
                {
                    JOptionPane.showMessageDialog(null, "Returning to the Shopping Command Console...", "Returning to Shop", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid input. Returning to the Shopping Command Console...", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }

            //Buying an Affinity Potion
            if(pick == 2)
            {
                int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to buy an Affinity Potion?\nYour Money: $" + heroStats.getMoney() + "\nYour Affinity: " + heroStats.getAffinity(), "Buy Affinity Potion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmationOptions, confirmationOptions[0]);
                if(choice == 0 && heroStats.getMoney() >= 100)
                {
                    heroStats.addAffinity(4);
                    heroStats.addMoney(-100);
                    JOptionPane.showMessageDialog(null, "Thank you for your purchase!\nYour Affinity: " + heroStats.getAffinity() + "\nReturning to the Shopping Command Console...\n", "Thank you!", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(choice == 0 && heroStats.getMoney() < 100)
                {
                    JOptionPane.showMessageDialog(null, "Sorry! You do not have enough money. You only have $" + heroStats.getMoney() + " out of the $100. \nReturning to the Shopping Command Console...\n", "Not Enough Money", JOptionPane.ERROR_MESSAGE);
                }
                else if(choice == 1)
                {
                    JOptionPane.showMessageDialog(null, "Returning to the Shopping Command Console...", "Returning to Shop", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid input. Returning to the Shopping Command Console...", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }

            //Buying an Armor Potion
            if(pick == 3)
            {
                int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to buy an Armor Modification?\nYour Money: $" + heroStats.getMoney() + "\nYour Armor: " + heroStats.getArmor(), "Buy Armor Potion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmationOptions, confirmationOptions[0]);
                if(choice == 0 && heroStats.getMoney() >= 100)
                {
                    heroStats.addArmor(4);
                    heroStats.addMoney(-100);
                    JOptionPane.showMessageDialog(null, "Thank you for your purchase!\nYour Armor: " + heroStats.getArmor() + "\nReturning to the Shopping Command Console...\n", "Thank you!", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(choice == 0 && heroStats.getMoney() < 100)
                {
                    JOptionPane.showMessageDialog(null, "Sorry! You do not have enough money. You only have $" + heroStats.getMoney() + " out of the $100. \nReturning to the Shopping Command Console...\n", "Not Enough Money", JOptionPane.ERROR_MESSAGE);
                }
                else if(choice == 1)
                {
                    JOptionPane.showMessageDialog(null, "Returning to the Shopping Command Console...", "Returning to Shop", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid input. Returning to the Shopping Command Console...", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }

            //Buying a Resistance Potion
            if(pick == 4)
            {
                int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to buy a Resistance Training Session?\nYour Money: $" + heroStats.getMoney() + "\nYour Resistance: " + heroStats.getResistance(), "Buy Resistance Potion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmationOptions, confirmationOptions[0]);
                if(choice == 0 && heroStats.getMoney() >= 100)
                {
                    heroStats.addResistance(4);
                    heroStats.addMoney(-100);
                    JOptionPane.showMessageDialog(null, "Thank you for your purchase!\nYour Resistance: " + heroStats.getResistance() + "\nReturning to the Shopping Command Console...\n", "Thank you!", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(choice == 0 && heroStats.getMoney() < 100)
                {
                    JOptionPane.showMessageDialog(null, "Sorry! You do not have enough money. You only have $" + heroStats.getMoney() + " out of the $100. \nReturning to the Shopping Command Console...\n", "Not Enough Money", JOptionPane.ERROR_MESSAGE);
                }
                else if(choice == 1)
                {
                    JOptionPane.showMessageDialog(null, "Returning to the Shopping Command Console...", "Returning to Shop", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid input. Returning to the Shopping Command Console...", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
            //Buying a Speed Potion
            if(pick == 5)
            {
                int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to buy a Speed Training Session?\nYour Money: $" + heroStats.getMoney() + "\nYour Speed: " + heroStats.getSpeed(), "Buy Speed Potion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmationOptions, confirmationOptions[0]);
                if(choice == 0 && heroStats.getMoney() >= 100)
                {
                    heroStats.addSpeed(4);
                    heroStats.addMoney(-100);
                    JOptionPane.showMessageDialog(null, "Thank you for your purchase!\nYour Speed: " + heroStats.getSpeed() + "\nReturning to the Shopping Command Console...\n", "Thank you!", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(choice == 0 && heroStats.getMoney() < 100)
                {
                    JOptionPane.showMessageDialog(null, "Sorry! You do not have enough money. You only have $" + heroStats.getMoney() + " out of the $100. \nReturning to the Shopping Command Console...\n", "Not Enough Money", JOptionPane.ERROR_MESSAGE);
                }
                else if(choice == 1)
                {
                    JOptionPane.showMessageDialog(null, "Returning to the Shopping Command Console...", "Returning to Shop", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid input. Returning to the Shopping Command Console...", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
            //End of Shopping Potions

            //Used to Leave the Shop.
            if(pick == 6)
            {
                int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to leave the shop?", "Leave Shop", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmationOptions, confirmationOptions[0]);
                if(choice == 0)
                {
                    JOptionPane.showMessageDialog(null, "Thank you for your time! Returning to the Dungeon...\n" , "Returning to Dungeon", JOptionPane.INFORMATION_MESSAGE);
                    completePurchases = true;
                }
                else if(choice == 1)
                {
                    JOptionPane.showMessageDialog(null, "Returning to the Shopping Command Console...", "Returning to Shop", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid input. Returning to the Shopping Command Console...", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        }while(completePurchases == false);
    }
    /**
     * Initializes all objects of the Driver class.
     * 
     * Date Created: 11/15/2020
     */
    public static void initialize()
    {
        zacharias = new Ally(heroClass, "Zacharias");
        currentAlly = zacharias;
        stats = new Statistics(heroClass, heroName);
        heroSkills = new Skills(stats, heroClass);
        increaseStat = new StatIncrease(stats, heroClass, heroSkills, zacharias);
        area = new Map(stats, increaseStat, heroSkills, zacharias, heroClass);
    }
    /**
     * A one-time method used to intialize the ally Anthiera.
     * 
     * Date Created: 11/19/2020
     */
    public static void initializeAnthiera()
    {
        anthiera = new Ally(heroClass, "Anthiera");
        JOptionPane.showMessageDialog(null, anthiera.addSkills(increaseStat.getHeroLevel()), "Skills Added", JOptionPane.INFORMATION_MESSAGE);
        increaseStat.initializeAlly2(anthiera);
    }
    /**
     * Stops the audio. SAP will need to be reinitialized in order to play again.
     * 
     * Date Created: 11/16/2020
     */
    public static void stopAudio()
    {
        try
        {
            SAP.stop();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();

        }
    }
    /**
     * Plays the menu's Background music.
     * 
     * Date Created: 11/16/2020
     */
    public static void resumeBGAudio()
    {
        try
        {
            if(area.getArea() < 40)
            {
                SAP = new SimpleAudioPlayer("4.NextStep-FireEmblemAwakening-Destiny.wav", true);
            }
            else if(area.getArea() >= 40 && area.getArea() < 60)
            {
                //Plays a new audio track when area is equal to 41 or above.
                SAP = new SimpleAudioPlayer("12.TheFinalRoad-PokemonLetsGoPikachu-Eevee.wav", true);
            }
            else if(Driver.postGame)
            {


                SAP = new SimpleAudioPlayer("19.Aftermath-FireEmblemEchoes-WithPrideinYourHeart.wav", true);
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }
}
