package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Random;

public class HelloController {


    @FXML
    private Label currentColumn;
    @FXML
    private Label currentRow;
    @FXML
    private Label resultOfMove;
    @FXML
    private Label monsterHitPoints;
    @FXML
    private Label playerHitPoints;
    private int column=0;
    @FXML
    private Label monsterIndicator;
    @FXML
    private Label totalGold;
    private int row = 0;
    private static final int MAP_SIZE = 10;
    @FXML
    private boolean monsterEncountered = false;



    private final Room[][] rooms = initializeRooms();

    private final PlayerCharacter player = new PlayerCharacter();
    private boolean fightEnabled = false;
    private boolean goldClaimAttemptExhausted=false;
    private int previousRow = 0;
    private int previousColumn = 0;
    @FXML
    protected void moveRightCLick() {
        moveToNewRoom(row,column+1);
    }

    @FXML
    protected void moveLeftCLick() {
        moveToNewRoom(row, column-1);
    }

    @FXML
    protected void moveUpCLick() {
        moveToNewRoom(row-1, column);
    }

    @FXML
    protected void moveDownCLick() {
        moveToNewRoom(row+1, column);
    }

    private void moveToNewRoom(int row, int column) {


        try {
            totalGold.setText("player gold: " + player.getTotalGold());

            if (!monsterEncountered) {

            this.previousRow = this.row;
            this.previousColumn = this.column;
            this.row = row;
            this.column = column;
            goldClaimAttemptExhausted = false;
            currentRow.setText("current row: "+ row);
            currentColumn.setText("current Column: "+ column);
            monsterIndicator.setText("is there a monster? "+ rooms[row][column].containsMonster());
            monsterEncountered = rooms[row][column].containsMonster();
            if (monsterEncountered)
            {
                resultOfMove.setText("fight a monster!");
                monsterHitPoints.setText("monster hitpoints: " + rooms[row][column].getMonster().getHitPoints());
                playerHitPoints.setText("player hitpoints: " + player.getHitPoints());
                fightEnabled = true;
            } else {
                resultOfMove.setText("youre all clear to search for gold");
            }
            }
        } catch (Exception e)
        {
            resultOfMove.setText("you've reached the edge, cannot conintue");
        }
    }

    private Room[][] initializeRooms(){
        Room[][] tempRooms = new Room[MAP_SIZE][MAP_SIZE];
        for (int row =0;row<MAP_SIZE;row++)
        {
            for (int col=0;col<MAP_SIZE;col++)
            {
                tempRooms[row][col] = new Room();
            }
        }
        return tempRooms;
    }

    public void fight() {
        if (fightEnabled)
        {

        int monsterHitPoints = rooms[row][column].getMonster().getHitPoints();
        if (rooms[row][column].containsMonster() && monsterHitPoints > 0) {
            if (rollDice(20)>= rooms[row][column].getMonster().getDexterity())
            {
            int damageGiven = player.getStrength()/3;
            rooms[row][column].getMonster().reduceHitPoints(damageGiven);
                this.monsterHitPoints.setText("monster hitpoints: " + rooms[row][column].getMonster().getHitPoints());
                if (rooms[row][column].getMonster().getHitPoints()<=0)
                {
                    monsterEncountered = false;
                    rooms[row][column].resetMonster();
                    this.monsterHitPoints.setText("monster is dead search or leave");
                    resultOfMove.setText("");

                    fightEnabled = false;

                } else if (rooms[row][column].getMonster().getDexterity()<=rollDice(20)){
                    player.reducehitPoints(rooms[row][column].getMonster().getStrength()/3);
                    playerHitPoints.setText("player hitpoints: " + player.getHitPoints());
                    if (player.getHitPoints()<=0){
                        Platform.exit();
                    }
                }


            }


            }


        }


    }

    private int rollDice(int sides) {
        return new Random().nextInt(sides) + 1;
    }

    public void search() {
       if ( rollDice(20)< player.getIntelligence() && !fightEnabled && !goldClaimAttemptExhausted) {
           goldClaimAttemptExhausted = true;
           Random random = new Random();
           player.setTotalGold(random.nextInt(50) + 1);
           totalGold.setText("player gold: " + player.getTotalGold());
           resultOfMove.setText("you cant search for gold anymore move on");
       } else {
        goldClaimAttemptExhausted = true;
           resultOfMove.setText("you cant search for gold anymore move on");
       }

        if (fightEnabled) {
            resultOfMove.setText("you cant search for gold kill the monster");
        }
    }


    public void run() {
        if (rooms[row][column].getMonster().getIntelligence()<=rollDice(20)){
            player.reducehitPoints(rooms[row][column].getMonster().getStrength()/3);
            playerHitPoints.setText("player hitpoints: " + player.getHitPoints());

            if (player.getHitPoints()<=0){
            Platform.exit();
            }

        }
        this.row = this.previousRow;
        this.column = this.previousColumn;
        currentRow.setText("current row: "+ row);
        currentColumn.setText("current Column: "+ column);
    }

    public void sleep() {
        player.setHitPoints(20);
    }
}