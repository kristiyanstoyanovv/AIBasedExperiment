package org.stoyanov.n_queen_solver;

import javax.swing.*;
import java.awt.*;
public class ChessboardForm extends JPanel {

    private int boardSize;

    public ChessboardTile[][] tiles;

    public ChessboardForm(int boardSize) {
        this.boardSize = boardSize;
        tiles = new ChessboardTile[boardSize][boardSize];
        this.setLayout(new GridLayout(boardSize,boardSize));
        this.setPreferredSize(new Dimension(800,800));
        initializeBoard();
    }

    private void initializeBoard() {
        boolean isNextTileWhite = false;
        for (int i = 0; i < boardSize; i++) {
            for (int x = 0; x < boardSize; x++) {
                if (!isNextTileWhite) {
                    tiles[i][x] = new ChessboardTile(Color.white, boardSize);
                } else {
                    tiles[i][x] = new ChessboardTile(Color.gray, boardSize);
                }
                isNextTileWhite = !isNextTileWhite;
                this.add(tiles[i][x]);
            }
            if (boardSize % 2 == 0) isNextTileWhite = !isNextTileWhite;
        }
    }

    public void showQueensByString(String queenPositions) {
        if (queenPositions.length() > boardSize) {
            System.err.println("Long string..");
            return;
        }

        for (int i = 0; i < queenPositions.length(); i++) {
            int temp = Character.getNumericValue(queenPositions.charAt(i));
            tiles[temp][i].queenLabel.setVisible(true);
        }
        this.repaint();
        System.out.println("queens show function");
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void hideAllQueens() {
        for (int i = 0; i < boardSize; i++) {
            for (int x = 0; x < boardSize; x++) {
                tiles[i][x].queenLabel.setVisible(false);
            }
        }
    }


}
