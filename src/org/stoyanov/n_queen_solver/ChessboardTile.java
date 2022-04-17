package org.stoyanov.n_queen_solver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ChessboardTile extends JPanel {

    private BufferedImage queenImage;
    public JLabel queenLabel;

    public ChessboardTile(Color tileBackground, int boardSize) {
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(tileBackground);
        try {
            queenImage = ImageIO.read(new File("queen.png"));
        } catch (Exception e) {
            System.err.println("Error opening queen image!");
        }
        queenLabel = new JLabel(new ImageIcon(queenImage.getScaledInstance(800/boardSize-10,
                                                    800/boardSize-10, Image.SCALE_SMOOTH)));
        this.add(queenLabel, BorderLayout.CENTER);
        queenLabel.setVisible(false);
    }
}
