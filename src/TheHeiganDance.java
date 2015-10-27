import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class TheHeiganDance {

    public static boolean gameOver = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double playerDamage = Double.parseDouble(scanner.nextLine());

        Player player = new Player(playerDamage);
        Heigan heigan = new Heigan();

        while (true) {
            Spell spellName = Spell.valueOf(scanner.next());
            int attackedRow = scanner.nextInt();
            int attackedCol = scanner.nextInt();

            player.doDamage(heigan);
            if (player.isLongSpellOn()) {
                heigan.doDamage(player, Spell.Cloud);
                player.setLongSpellOn(false);
            }

            heigan.checkAlive();
            player.checkAlive();
            if (heigan.isAlive() && player.isAlive()) {
                heigan.attack(player, spellName, attackedRow, attackedCol);
            }

            if(!player.checkAlive() || !heigan.checkAlive()) {
                gameOver = true;
            }

            if(gameOver) {
                printResults(player, heigan);
                return;
            }
        }
    }

    private static void printResults(Player player, Heigan heigan) {
        if (!heigan.checkAlive()) {
            System.out.println("Heigan: Defeated!");
        } else {
            System.out.printf("Heigan: %.2f\n", heigan.getHealth());
        }

        if(!player.checkAlive()) {
            System.out.printf("Player: Killed by %s\n",
                    (player.getLastHitBy().getSpellName().equals("Cloud") ? "Plague Cloud" : player.getLastHitBy()));
        } else {
            System.out.printf("Player: %d\n",
                    player.getRemainingHitPoints());
        }

        System.out.printf("Final position: %d, %d",
                player.getPosition().getRow(),
                player.getPosition().getCol());
    }
}

class Player {
    private final int CENTER = 7;
    private final int HIT_POINTS = 18500;

    private boolean playerAlive;
    private boolean longSpellOn;
    private double playerDamage;
    private int remainingHitPoints;
    private Cell position;
    private Spell lastHitBy;

    Player(double playerDamage) {
        this.playerDamage = playerDamage;

        this.playerAlive = true;
        this.longSpellOn = false;
        this.remainingHitPoints = this.HIT_POINTS;
        this.position = new Cell(this.CENTER, this.CENTER);
    }

    public Cell getPosition() {
        return position;
    }

    public void takeDamage(Spell spell) {
        this.remainingHitPoints -= spell.getSpellDamage();
        if (spell.ordinal() == 1) {
            this.longSpellOn = true;
        }

        this.lastHitBy = spell;
    }

    public void doDamage(Heigan heigan) {
        heigan.takeDamage(this.playerDamage);
    }

    public boolean isLongSpellOn() {
        return longSpellOn;
    }

    public void setLongSpellOn(boolean longSpellOn) {
        this.longSpellOn = longSpellOn;
    }

    public boolean checkIfInDamageZone(Set<Cell> attackedCells) {
        return attackedCells.contains(this.position);
    }

    public boolean tryToEscape(Set<Cell> attackedCells) {
        int row = this.position.getRow();
        int col = this.position.getCol();

        Cell newCell = new Cell(row - 1, col);
        if(!attackedCells.contains(newCell)
                && possibleCell(row - 1, col)) {
            this.position = newCell;
            return true;
        }

        newCell = new Cell(row, col + 1);
        if(!attackedCells.contains(newCell)
                && possibleCell(row, col + 1)) {
            this.position = newCell;
            return true;
        }

        newCell = new Cell(row + 1, col);
        if(!attackedCells.contains(newCell)
                && possibleCell(row + 1, col)) {
            this.position = newCell;
            return true;
        }

        newCell = new Cell(row, col - 1);
        if(!attackedCells.contains(newCell)
                && possibleCell(row, col - 1)) {
            this.position = newCell;
            return true;
        }

        return false;
    }

    private boolean possibleCell (int row, int col) {
        return (row >= 0) && (col >= 0) && (row <= (this.CENTER + this.CENTER)) && (col <= (this.CENTER + this.CENTER));
    }

    public int getRemainingHitPoints() {
        return remainingHitPoints;
    }

    public boolean checkAlive() {
        if(this.remainingHitPoints <= 0) {
            this.playerAlive = false;
            return false;
        }

        return true;
    }

    public boolean isAlive() {
        return this.playerAlive;
    }

    public Spell getLastHitBy() {
        return lastHitBy;
    }
}

class Heigan {
    private final double HEALTH = 3000000;

    private boolean isHeiganAlive;
    private double health;

    Heigan() {
        isHeiganAlive = true;
        health = this.HEALTH;
    }

    public double getHealth() {
        return health;
    }

    public void takeDamage(double damage) {
        this.health -= damage;
    }

    public void doDamage(Player player, Spell spell) {
        player.takeDamage(spell);
    }

    public boolean isAlive() {
        return isHeiganAlive;
    }

    public void attack(Player player, Spell attackingSpell, int attackRow, int attackCol) {
        Set<Cell> attackedCells = new HashSet<>();
        for (int row = attackRow - 1; row <= attackRow + 1; row++) {
            for (int col = attackCol - 1; col <= attackCol + 1; col++) {
                attackedCells.add(new Cell(row, col));
            }
        }
        
        boolean playerInRange = player.checkIfInDamageZone(attackedCells);
        if(playerInRange) {

            boolean playerEscaped = player.tryToEscape(attackedCells);
            if(!playerEscaped) {
                player.takeDamage(attackingSpell);
            }
        }
    }

    public boolean checkAlive() {
        if (this.health <= 0) {
            this.isHeiganAlive = false;

            return false;
        }

        return true;
    }
}

class Cell {

    private int row;
    private int col;

    Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (row != cell.row) return false;
        return col == cell.col;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}

enum Spell {
    Eruption(6000),
    Cloud(3500);

    private int spellDamage;

    Spell(int power) {
        this.spellDamage = power;
    }

    public int getSpellDamage() {
        return spellDamage;
    }

    public String getSpellName() {
        return this.name();
    }
}