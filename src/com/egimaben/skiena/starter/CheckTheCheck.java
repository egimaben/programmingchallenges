/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egimaben.skiena.starter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author egimaben
 */
public class CheckTheCheck {

    private static List<String[][]> createChessBoards(String rawInput) {
        List<String[][]> chessBoards = new ArrayList<>();

        String[] splitRawData = rawInput.split("\n\n");
        for (String board : splitRawData) {
            String[] rows = board.split("\n");
            String[][] currBoard = new String[8][8];
            boolean validBoard = true;
            for (int i = 0, len = rows.length; i < len; i++) {
                String row = rows[i];
                for (int k = 0; k < 8; k++) {
                    String character = Character.toString(row.charAt(k));
                    if (!character.equals(".")) {
                        validBoard = true;
                    }
                    currBoard[i][k] = character;
                }
            }
            if (validBoard) {
                chessBoards.add(currBoard);
            }

        }
        return chessBoards;
    }

    private static String movePawn(int i, int j, String[][] board) {
        String piece = board[i][j];
        if (!(piece.equals("p") || piece.equals("P"))) {
            return null;
        }
        boolean white = isUpper(piece);

        if (white) {
            if (i != 0) {
                i -= 1;
                if (j != 0) {
                    String victim = board[i][j - 1];
                    if (victim.equals("k") || victim.equals("K")) {
                        return victim;
                    }
                }
                if (j != 7) {
                    String victim = board[i][j + 1];
                    if (victim.equals("k") || victim.equals("K")) {
                        return victim;
                    }
                }
            }
        } else {
            if (i != 7) {
                i += 1;
                if (j != 0) {
                    String victim = board[i][j - 1];
                    if (victim.equals("k") || victim.equals("K")) {
                        return victim;
                    }
                }
                if (j != 7) {
                    String victim = board[i][j + 1];
                    if (victim.equals("k") || victim.equals("K")) {
                        return victim;
                    }
                }
            }

        }
        return null;

    }

    private static String moveKing(int i, int j, String[][] board) {
        String piece = board[i][j];
        if (!(piece.equals("k") || piece.equals("K"))) {
            return null;
        }
        if (j != 0) {
            String victim = board[i][j - 1];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }

        }
        if (j != 7) {
            String victim = board[i][j + 1];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        if (i != 0) {
            String victim = board[i - 1][j];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        if (i != 7) {
            String victim = board[i + 1][j];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        if (i != 0 && j != 0) {
            String victim = board[i - 1][j - 1];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        if (i != 0 && j != 7) {
            String victim = board[i - 1][j + 1];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        if (i != 7 && j != 0) {
            String victim = board[i + 1][j - 1];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        if (i != 7 && j != 7) {
            String victim = board[i + 1][j + 1];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        return null;

    }

    private static String moveBishop(int i, int j, String[][] board) {
        String piece = board[i][j];
        if (!(piece.equals("b") || piece.equals("B"))) {
            return null;
        }
        int tmpi = i;
        int tmpj = j;
        //move up-left
        while (--i > -1 && --j > -1) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }

        }
        i = tmpi;
        j = tmpj;
        //move up-right
        while (--i > -1 && ++j < 8) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        i = tmpi;
        j = tmpj;
        //move down-left
        while (++i < 8 && --j > -1) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        i = tmpi;
        j = tmpj;
        //move down-right
        while (++i < 8 && ++j < 8) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        return null;
    }

    private static String moveRook(int i, int j, String[][] board) {
        String piece = board[i][j];
        if (!(piece.equals("r") || piece.equals("R"))) {
            return null;
        }
        int tmpi = i;
        int tmpj = j;
        while (--i > -1) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        i = tmpi;
        j = tmpj;
        while (++i < 8) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        i = tmpi;
        j = tmpj;
        while (--j > -1) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        i = tmpi;
        j = tmpj;
        while (++j < 8) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        return null;
    }

    private static String moveQueen(int i, int j, String[][] board) {
        String piece = board[i][j];
        if (!(piece.equals("q") || piece.equals("Q"))) {
            return null;
        }
        int tmpi = i;
        int tmpj = j;
        while (--i > -1) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        i = tmpi;
        j = tmpj;
        while (++i < 8) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        i = tmpi;
        j = tmpj;
        while (--j > -1) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        i = tmpi;
        j = tmpj;
        while (++j < 8) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        i = tmpi;
        j = tmpj;
        //move up-left
        while (--i > -1 && --j > -1) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        i = tmpi;
        j = tmpj;
        //move up-right
        while (--i > -1 && ++j < 8) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        i = tmpi;
        j = tmpj;
        //move down-left
        while (++i < 8 && --j > -1) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        i = tmpi;
        j = tmpj;
        //move down-right
        while (++i < 8 && ++j < 8) {
            String victim = board[i][j];
            if (!victim.equals(".")) {
                if (victim.equals("k") || victim.equals("K")) {
                    return victim;
                } else {
                    break;
                }
            }
        }
        return null;
    }

    private static String moveKnight(int i, int j, String[][] board) {
        String piece = board[i][j];
        if (!(piece.equals("n") || piece.equals("N"))) {
            return null;
        }

        if (i >= 1 && j != 0) {
            String victim = board[i - 2][j - 1];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        if (i >= 1 && j != 7) {
            String victim = board[i - 2][j + 1];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        if (i <= 5 && j != 0) {
            String victim = board[i + 2][j - 1];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        if (i <5 && j != 7) {
            String victim = board[i + 2][j + 1];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }

        if (j >= 2 && i != 0) {
            String victim = board[i - 1][j - 2];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        if (j >= 2 && i != 7) {
            String victim = board[i + 1][j - 2];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        if (j <5 && i != 0) {
            String victim = board[i - 1][j + 2];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        if (j <= 5 && i != 7) {
            String victim = board[i + 1][j + 2];
            if (victim.equals("k") || victim.equals("K")) {
                return victim;
            }
        }
        return null;
    }

    private static boolean isUpper(String s) {
        return Character.isUpperCase(s.codePointAt(0));
    }

    private static String printArrs(List<String[][]> arrs) {
        StringBuilder sb = new StringBuilder();
        for (String[][] board : arrs) {
            sb.append("\n#board");
            for (int i = 0; i < board.length; i++) {
                sb.append("\n");
                for (int j = 0; j < board[i].length; j++) {
                    sb.append(board[i][j]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = ""
                + "..k.....\n"
                + "ppp.pppp\n"
                + "........\n"
                + ".R...B..\n"
                + "........\n"
                + "........\n"
                + "PPPPPPPP\n"
                + "K.......\n"
                + "\n"
                + "rnbqk.nr\n"
                + "ppp..ppp\n"
                + "....p...\n"
                + "...p....\n"
                + ".bPP....\n"
                + ".....N..\n"
                + "PP..PPPP\n"
                + "RNBQKB.R\n"
                + "\n"
                + "........\n"
                + "........\n"
                + "........\n"
                + "........\n"
                + "........\n"
                + "........\n"
                + "........\n"
                + "........\n";
        List<String[][]> chessBoards = createChessBoards(input);
        int count = 0;
        for (String[][] board : chessBoards) {
            boolean blackChecked=false;
            boolean whiteChecked=false;
            int game=0;
            
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (!board[i][j].equals(".")) {
                        String victim=null;
                        if (board[i][j].equals("p") || board[i][j].equals("P")) {
                            victim = movePawn(i, j, board);
                        } else if (board[i][j].equals("n") || board[i][j].equals("N")) {
                            victim = moveKnight(i, j, board);
                        } else if (board[i][j].equals("b") || board[i][j].equals("B")) {
                            victim = moveBishop(i, j, board);
                        } else if (board[i][j].equals("r") || board[i][j].equals("R")) {
                            victim = moveRook(i, j, board);
                        } else if (board[i][j].equals("q") || board[i][j].equals("Q")) {
                            victim = moveQueen(i, j, board);
                        } else if (board[i][j].equals("k") || board[i][j].equals("K")) {
                            victim = moveKing(i, j, board);
                        }
                        
                        if (victim != null&&!blackChecked&&victim.equals("k")) {
                            count += 1;
                            game=count;                           
                            blackChecked=true;
                            whiteChecked=false;
                        }
                        if(victim!=null&&!whiteChecked&&victim.equals("K")){
                            count+=1;
                            game=count;
                            whiteChecked=true;
                            blackChecked=false;

                        }
                    }
                }
            }
            if(blackChecked)
            System.out.println("Game #" + game + ":black king is in check.");
            else if(whiteChecked)
            System.out.println("Game #" + game + ":white king is in check.");
            else if(blackChecked)
            System.out.println("Game #" + count + ":no king is in check.");

        }

    }

}
