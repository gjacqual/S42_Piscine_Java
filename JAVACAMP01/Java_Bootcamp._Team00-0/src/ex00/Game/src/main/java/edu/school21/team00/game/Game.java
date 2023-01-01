package edu.school21.team00.game;

import edu.school21.team00.logic.enemy.Enemy;
import edu.school21.team00.logic.map.Map;
import edu.school21.team00.logic.player.Player;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

@Parameters(separators = "=")
public class Game {
    private char enemyChar;
    private char playerChar;
    private char wallChar;
    private char goalChar;
    private char emptyChar;
    private String enemyColor;
    private String playerColor;
    private String wallColor;
    private String goalColor;
    private String emptyColor;
    private boolean gameOver;

    @Parameter(names = "--enemiesCount", description = "Number of enemies", required = true)
    private int enemiesCount;

    @Parameter(names = "--wallsCount", description = "Number of walls", required = true)
    private int wallsCount;

    @Parameter(names = "--size", description = "Field size", required = true)
    private int size;

    @Parameter(names = "--profile", description = "Profile", required = true)
    private String profile;

    private void run(String[] args) {
        if (args.length != 4) {
            throw new IllegalParametersException("Wrong arguments.\n Expected arguments like: " +
                    "--enemiesCount=n --wallsCount=m --size=l --profile=<name>");
        }
        JCommander.newBuilder().addObject(this).build().parse(args);
        if (enemiesCount + wallsCount + 3 > size * size || "".equals(profile)
                || enemiesCount < 0 || wallsCount < 0 || size < 2) {
            throw new IllegalParametersException("Wrong arguments.\n Expected arguments like: " +
                    "--enemiesCount=n --wallsCount=m --size=l --profile=<name>");
        }
        parseProperties();
    }

    private void parseProperties() {
        Map map;
        Properties properties = new Properties();
        try {
            properties.load(Game.class.getClassLoader().getResourceAsStream("application-"
                    + profile + ".properties"));
            enemyChar = properties.getProperty("enemy.char").charAt(0);
            playerChar = properties.getProperty("player.char").charAt(0);
            wallChar = properties.getProperty("wall.char").charAt(0);
            goalChar = properties.getProperty("goal.char").charAt(0);
            emptyChar = properties.getProperty("empty.char").replaceAll("\'", "").charAt(0);
            enemyColor = properties.getProperty("enemy.color");
            playerColor = properties.getProperty("player.color");
            wallColor = properties.getProperty("wall.color");
            goalColor = properties.getProperty("goal.color");
            emptyColor = properties.getProperty("empty.color");
        } catch (IOException e) {
            throw new InvalidFilePropertiesException(e);
        }
        gameOver = false;
        map = MapGenerator.createMap(enemiesCount, wallsCount, size, enemyChar,
                playerChar, wallChar, goalChar, emptyChar);
        MapPrinter mapPrinter = new MapPrinter(enemyColor, playerColor, wallColor, goalColor, emptyColor,
                                    enemyChar, playerChar, wallChar, goalChar, emptyChar);
        Scanner s = new Scanner(System.in);
        boolean devWaitEnemy = false;

        while (!gameOver) {
            mapPrinter.printMap(size, profile, map.getTilesArr());

            if (!devWaitEnemy) {
                System.out.println("Please make your move: 1 (up) 2 (left) 3 (down) 4 (right) or 9 (game over)");
            } else {
                System.out.println("Please to confirm the move of the enemies enter 8");
            }
            String next = s.next();
            if (next.length() == 1 &&
                    ((next.charAt(0) >= '1' && next.charAt(0) <= '4') ||
                      next.charAt(0) == '9' ||
                     (next.charAt(0) <= '8' && profile.equals("dev")))) {
                int choice = (next.charAt(0) - '0');
                if (choice >= 1 && choice <= 4) {
                    if (!devWaitEnemy) {
                        gameOver = Player.playerMove(map, choice);
                        if (profile.equals("dev")) {
                            devWaitEnemy = true;
                        }
                    }
                    if (gameOver) {
                        return;
                    }
                } else if (choice == 9) {
                    System.out.println("Game over!");
                    return;
                } else if (choice == 8) {
                    devWaitEnemy = false;
                }
                if (!devWaitEnemy) {
                    for (Enemy currEnemy : map.getEnemiesArr()) {
                        gameOver = currEnemy.enemyMove(map);
                        if (gameOver == true) {
                            break;
                        }
                    }
                }
            } else {
                System.err.println("Wrong direction specifier!");
            }
        }
    }

    public static void main(String[] args) {
        new Game().run(args);
    }
}
