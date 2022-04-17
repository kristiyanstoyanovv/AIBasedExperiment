package org.stoyanov.n_queen_solver;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class UI extends JFrame {

    Problem currentProblem;
    private ChessboardForm chessboardForm;
    private JPanel controlPanel;
    private JPanel[] subPanel = new JPanel[3];
    private JTextPane titlePane;
    private JLabel boardSizeLabel, delayLabel, populationSizeLabel;
    private JTextField boardSizeTB, delayTB, populationSizeTB;
    private JButton startButton, stopButton;

    private int generatedPopulation;

    private ActionListener taskPerformer;
    private Timer timer;

    public UI() {
        initializeControlPanel();
        this.setPreferredSize(new Dimension(1100,800));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.add(controlPanel, BorderLayout.EAST);
        this.setTitle("NQueen solver");
        try {
            BufferedImage icon = ImageIO.read(new File("queen.png"));
            this.setIconImage(icon);
        } catch (Exception e) {
            System.err.println("Can't find icon image.");
        }
        pack();
        this.setVisible(true);
    }

    private void initializeControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 1));
        controlPanel.setPreferredSize(new Dimension(300, 800));
        controlPanel.setBackground(new Color(246,230,203));
        controlPanel.setBorder(new EmptyBorder(125,0,0,0));

        for (int i = 0; i < subPanel.length; i++) {
            subPanel[i] = new JPanel();
            subPanel[i].setBackground(new Color(246, 230, 203));
            subPanel[i].setBorder(new EmptyBorder(10, 10, 10, 10));
            controlPanel.add(subPanel[i]);
        }

        // Title
        titlePane = new JTextPane();
        titlePane.setFont(new Font("Consolas", Font.ITALIC, 20));
        titlePane.setText("NQueen solver,\nusing genetic algorithm,\ndeveloped by: Stoyanovv");
        titlePane.setEditable(false);
        titlePane.setOpaque(false);
        subPanel[0].add(titlePane);

        // Board size
        boardSizeLabel = new JLabel("Board size (max size - 10):");
        boardSizeLabel.setHorizontalAlignment(JLabel.CENTER);
        boardSizeTB = new JTextField("8");
        boardSizeTB.setPreferredSize(new Dimension(50,20));
        subPanel[1].add(boardSizeLabel);
        subPanel[1].add(boardSizeTB);

        // Delay
        delayLabel = new JLabel("Updating board delay (ms): ");
        delayLabel.setHorizontalAlignment(JLabel.CENTER);
        delayTB = new JTextField("100");
        delayTB.setPreferredSize(new Dimension(50,20));
        subPanel[1].add(delayLabel);
        subPanel[1].add(delayTB);

        // Population size
        populationSizeLabel = new JLabel("Population size (max size - 1000):");
        populationSizeLabel.setHorizontalAlignment(JLabel.CENTER);
        populationSizeTB = new JTextField("10");
        populationSizeTB.setPreferredSize(new Dimension(50,20));
        subPanel[1].add(populationSizeLabel);
        subPanel[1].add(populationSizeTB);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop!");
        startButton.setPreferredSize(new Dimension(70,25));
        stopButton.setPreferredSize(new Dimension(70,25));

        startButton.addActionListener(e -> {
            if (timer != null) return;
            if (initializeChessboard()) {
                timer.start();
            }
        });

        stopButton.addActionListener(e -> {
            if (timer != null)  {
                timer.stop();
                timer = null;
            }
        });

        subPanel[2].add(startButton);
        subPanel[2].add(stopButton);
    }

    private void initializeUpdateTimer(int updateDelay) {
        generatedPopulation = 0;
        taskPerformer = evt -> {
            chessboardForm.hideAllQueens();
            generatedPopulation++;
            chessboardForm.showQueensByString(currentProblem.getBestBoard().getQueensPosAsString());
            if (currentProblem.solveProblem()) {
                ((Timer) evt.getSource()).stop();
                JOptionPane.showMessageDialog(this,
                        "Solution found at generation number:  " + generatedPopulation);
                timer = null;
            }
        };
        timer = new Timer(updateDelay,taskPerformer);
    }

    private boolean initializeChessboard() {
        try {
            int updateDelay = Integer.parseInt(delayTB.getText());
            int boardSize = Integer.parseInt(boardSizeTB.getText());
            int populationSize = Integer.parseInt(populationSizeTB.getText());


            if (boardSize < 6 || boardSize > 10) return false;
            if (populationSize < 10 || populationSize > 1000) return false;
            if (updateDelay < 0 || updateDelay > 5000) return false;
            if (chessboardForm != null) this.remove(chessboardForm);


            chessboardForm = new ChessboardForm(boardSize);
            this.add(chessboardForm,BorderLayout.CENTER);
            this.repaint();

            currentProblem = new Problem(populationSize,boardSize);
            initializeUpdateTimer(updateDelay);

            return true;
        } catch (Exception e) {
            System.err.println("Can't initialize gaming board, probably bad string parse.");
        }
        return false;
    }
}