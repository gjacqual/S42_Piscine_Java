package edu.school21.team00.game;

import edu.school21.team00.logic.map.Map;

import java.util.*;

public class MapGenerator {
    private static char ENEMY_CHAR;
    private static char PLAYER_CHAR;
    private static char WALL_CHAR;
    private static char GOAL_CHAR;
    private static char EMPTY_CHAR;

    public static Map createMap(int enemiesCount, int wallsCount, int size,
                                char enemyChar, char playerChar, char wallChar,
                                char goalChar, char emptyChar) {
        ENEMY_CHAR = enemyChar;
        PLAYER_CHAR = playerChar;
        WALL_CHAR = wallChar;
        GOAL_CHAR = goalChar;
        EMPTY_CHAR = emptyChar;

        char[][] map = new char[size][size];
        Map mapWithEnemies;

        Random random = new Random();
        while (true) {
            List<Cell> cells = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Cell cell = new Cell();
                    cell.setRow(i);
                    cell.setCol(j);
                    cells.add(cell);
                    map[i][j] = EMPTY_CHAR;
                }
            }
            Collections.shuffle(cells, random);
            map[cells.get(0).getRow()][cells.get(0).getCol()] = PLAYER_CHAR;
            map[cells.get(1).getRow()][cells.get(1).getCol()] = GOAL_CHAR;
            for (int i = 0; i < wallsCount; i++) {
                map[cells.get(i + 2).getRow()][cells.get(i + 2).getCol()] = WALL_CHAR;
            }
            for (int i = 0; i < enemiesCount; i++) {
                map[cells.get(i + 2 + wallsCount).getRow()][cells.get(i + 2 + wallsCount).getCol()] = ENEMY_CHAR;
            }
            if (!canPlayerReachTarget(map)) {
                continue;
            }
            mapWithEnemies = Map.instantiateMap(map, size, enemiesCount, enemyChar,
                    playerChar, wallChar, emptyChar);
            break;
        }
        return mapWithEnemies;
    }

    private static boolean canPlayerReachTarget(char[][] map) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        int n = map.length;
        int m = map[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == PLAYER_CHAR) {
                    boolean[][] visited = new boolean[n][m];
                    Queue<int[]> q = new LinkedList<>();
                    visited[i][j] = true;
                    q.add(new int[]{i, j});
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        for (int d = 0; d < 4; d++) {
                            int nx = cur[0] + dx[d];
                            int ny = cur[1] + dy[d];
                            if (nx >= 0 && nx < n &&
                                    ny >= 0 && ny < m &&
                                    !visited[nx][ny] &&
                                    map[nx][ny] != WALL_CHAR) {
                                visited[nx][ny] = true;
                                if (map[nx][ny] == GOAL_CHAR) {
                                    return true;
                                }
                                q.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
