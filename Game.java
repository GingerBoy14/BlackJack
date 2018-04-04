import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    JFrame frame = new JFrame();
    JLabel Bank, labelDiler, labelPlayer, Bet;
    JLabel playersumcards, dilersumcards;
    JTextField getBet;


    JButton Start = new JButton("Start");
    JButton Hit = new JButton("Hit");
    JButton Stand = new JButton("Stand");
    JButton DoubleDown = new JButton("Double Down");
    JButton submitBet = new JButton("Submit");
    JButton clear = new JButton("clear");

    int bank = 1000;
    int bet = 0;
    int playerSumRank, dilerSumRank;
    boolean checking = false;
    boolean firstTry = true;


    public static void main(String[] args) {
        Game game = new Game();
        game.getFrame();

    }

    public void getFrame() {




        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((int) screenSize.getWidth() / 2 - 400, (int) (screenSize.getHeight() / 2 - 325), 800, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        int hg = frame.getHeight();
        int wd = frame.getWidth();


        DrawCards player = new DrawCards(1);
        DrawCards diler = new DrawCards(1);
        diler.setLayout(null);
        player.setLayout(null);


        diler.setBounds(0, 0, (int) (wd * 0.8), (int) (hg * 0.4));
        int x = diler.getWidth();
        int y = diler.getHeight();
        labelDiler = new JLabel();
        labelDiler.setBounds(x / 2 - 25, 0, x, 50);
        labelDiler.setFont(new Font("Arial", Font.BOLD, 30));
        labelDiler.setForeground(Color.BLACK);
        labelDiler.setText("Diler");
        diler.add(labelDiler);

        dilersumcards = new JLabel();
        dilersumcards.setBounds(0, 0, 150, 50);
        dilersumcards.setFont(new Font("Arial", Font.BOLD, 15));
        dilersumcards.setForeground(Color.BLACK);
        dilersumcards.setText("Sum of cards = " + diler.getSumRank());
        diler.add(dilersumcards);

        frame.add(diler);


        player.setBounds(0, y + 95, (int) (wd * 0.8), ((int) (hg * 0.4)));
        labelPlayer = new JLabel();
        labelPlayer.setBounds(x / 2 - 25, 0, x, 50);
        labelPlayer.setFont(new Font("Arial", Font.BOLD, 30));
        labelPlayer.setForeground(Color.BLACK);
        labelPlayer.setText("Player");
        player.add(labelPlayer);

        playersumcards = new JLabel();
        playersumcards.setBounds(0, 0, 150, 50);
        playersumcards.setFont(new Font("Arial", Font.BOLD, 15));
        playersumcards.setForeground(Color.BLACK);
        playerSumRank = player.getSumRank();
        playersumcards.setText("Sum of cards = " + playerSumRank);
        player.add(playersumcards);

        frame.add(player);


        DrawInterface info = new DrawInterface(0, 0, wd, hg);
        DrawInterface button = new DrawInterface(0, y, (int) (wd * 0.8), (int) (hg * 0.15));
        info.setLayout(null);
        button.setLayout(null);


        info.setBounds(x, 0, (int) (wd * 0.2), hg);

        Bank = new JLabel();
        Bank.setBounds(0, 0, 160, 30);
        Bank.setFont(new Font("Arial", Font.BOLD, 20));
        Bank.setForeground(Color.BLACK);
        Bank.setText("Bank:" + bank);
        info.add(Bank);

        getBet = new JTextField();
        getBet.setBounds(0, 30, 70, 20);
        getBet.setText("0");
        info.add(getBet);
        Bet = new JLabel();
        Bet.setBounds(0, 55, 160, 30);
        Bet.setFont(new Font("Arial", Font.BOLD, 20));
        Bet.setForeground(Color.BLACK);
        Bet.setText("Bet:" + bet);
        info.add(Bet);
        submitBet.setBounds(30, 90, 75, 25);

        submitBet.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                do {
                    checking = submitBet();
                    firstTry = false;
                }while (firstTry == true);

                Start.setEnabled(true);
            }
        });
        info.add(submitBet);
        frame.add(info);


        button.setBounds(0, y, (int) (wd * 0.8), (int) (hg * 0.15));
        Start.setBounds(20, y + 10, 70, 25);
        Hit.setBounds(180, y + 30, 50, 30);
        Stand.setBounds(270, y + 30, 70, 30);
        DoubleDown.setBounds(390, y + 30, 130, 30);

        clear.setBounds(20, y + 60, 65, 25);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
                diler.removeAll();
                diler.repaint();
                player.removeAll();
                player.repaint();
                submitBet.setEnabled(true);

                clear.setEnabled(false);
                if (bank==0){
                    int n = JOptionPane.showConfirmDialog(
                            frame,
                            "Do you want play one more game?",
                            "Question",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {

                        clearAll();
                    } else if (n == JOptionPane.NO_OPTION) {

                        System.exit(0);
                    }
                }
            }
        });
        button.add(clear);
        clear.setEnabled(false);
        Hit.setEnabled(false);
        Stand.setEnabled(false);
        DoubleDown.setEnabled(false);
        Start.setEnabled(false);

        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (checking == true) {

                    player.paintCards(2);
                    diler.paintCards(1);
                    playerSumRank = player.getSumRank();
                    dilerSumRank = diler.getSumRank();
                    playersumcards.setText("Sum of cards = " + playerSumRank);
                    dilersumcards.setText("Sum of cards = " + dilerSumRank);
                    player.repaint();
                    diler.repaint();
                    Start.setEnabled(false);
                    submitBet.setEnabled(false);
                    Hit.setEnabled(true);
                    Stand.setEnabled(true);
                    DoubleDown.setEnabled(true);
                    if (playerSumRank == 21) {
                        bank = bank + bet*2;
                        bet = 0;
                        Bet.setText("Bet: " + bet);
                        Bank.setText("Bank: " + bank);
                        labelPlayer.setText("Player: WIN");
                        labelDiler.setText("Diler: Lose");
                        clear.setEnabled(true);
                        Hit.setEnabled(false);
                        Stand.setEnabled(false);
                        DoubleDown.setEnabled(false);
                    }

                }else{
                    checking = submitBet();
                }
                Start.setEnabled(false);


            }
        });
        button.add(Start);

        Hit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.paintCards(1);
                player.repaint();
                playerSumRank = player.getSumRank();
                playersumcards.setText("Sum of cards = " + playerSumRank);
                Stand.doClick();
            }
        });
        button.add(Hit);

        Stand.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {

                while (diler.getSumRank() < 17) {
                    diler.paintCards(1);
                    diler.repaint();
                    dilerSumRank = diler.getSumRank();
                    dilersumcards.setText("Sum of cards = " + dilerSumRank);

                }
                getStatusGame(playerSumRank,dilerSumRank);
                clear.setEnabled(true);
            }
        });
        button.add(Stand);

        DoubleDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int b = bet;
                bet *= 2;
                bank = bank + b;
                if ( bet > bank) {
                    JOptionPane.showMessageDialog(frame, "You can't upp your bet. YOU haven't anouph money.","Incorrect value", JOptionPane.ERROR_MESSAGE);
                }else{

                    int n = JOptionPane.showConfirmDialog(
                            frame,
                            "Do you want 1 extra card??",
                            "Question",
                            JOptionPane.YES_NO_OPTION);
                    do {

                        if (n != JOptionPane.YES_OPTION && n != JOptionPane.NO_OPTION){

                            n = JOptionPane.showConfirmDialog(
                                    frame,
                                    "Do you want 1 extra card??",
                                    "Question",
                                    JOptionPane.YES_NO_OPTION);
                        }
                        if (n == JOptionPane.YES_OPTION) {
                            player.paintCards(1);
                            player.repaint();
                            playerSumRank = player.getSumRank();
                            playersumcards.setText("Sum of cards = " + playerSumRank);
                            bank = bank - bet;
                            Bet.setText("Bet: " + bet);
                            Bank.setText("Bank: " + bank);
                            Stand.doClick();
                        } else if (n == JOptionPane.NO_OPTION) {

                            bank = bank - bet;
                            Stand.doClick();
                            Bet.setText("Bet: " + bet);
                            Bank.setText("Bank: " + bank);
                        }
                    }while (n != JOptionPane.NO_OPTION && n != JOptionPane.YES_OPTION );
                }
            }
        });
        button.add(DoubleDown);
        frame.add(button);
        frame.validate();




    }



    public boolean submitBet() {

        String str = getBet.getText();
        do {
            if (str.equals("")) {
                JOptionPane.showMessageDialog(frame, "Enter your bet.","Incorrect value", JOptionPane.ERROR_MESSAGE);
                break;
            }
            int getbet = Integer.parseInt(getBet.getText());
            if (getbet < 1 || getbet > bank) {
                JOptionPane.showMessageDialog(frame, "Enter CORRECT bet. Please.","Incorrect value", JOptionPane.ERROR_MESSAGE);
                break;
            } else {
                bank = bank - getbet;
                bet = bet + getbet;
                Bet.setText("Bet: " + bet);
                Bank.setText("Bank: " + bank);
                getBet.setText("0");
                checking = true;
            }
        } while (checking == false);
return checking;
    }
private void clearAll(){
     bank = 1000;
     bet = 0;
     checking = false;
     firstTry = true;

    Bet.setText("Bet: " + bet);
    Bank.setText("Bank: " + bank);
    getBet.setText("0");
    labelPlayer.setText("Player");
    labelDiler.setText("Diler");
    playersumcards.setText("Sum of cards = " + 0);
    dilersumcards.setText("Sum of cards = " + 0);

}
private void clear(){

     checking = false;


    Bet.setText("Bet: " + bet);
    Bank.setText("Bank: " + bank);
    getBet.setText("0");
    labelPlayer.setText("Player");
    labelDiler.setText("Diler");
    playersumcards.setText("Sum of cards = " + 0);
    dilersumcards.setText("Sum of cards = " + 0);

}
public void getStatusGame( int playerSumRank, int dilerSumRank){

            if (playerSumRank > dilerSumRank && playerSumRank<=21 || dilerSumRank>21 && playerSumRank<=21 ){

                bank = bank + bet*2;
                bet = 0;
                Bet.setText("Bet: " + bet);
                Bank.setText("Bank: " + bank);
                labelPlayer.setText("Player: WIN");
                labelDiler.setText("Diler: Lose");


                Hit.setEnabled(false);
                Stand.setEnabled(false);
                DoubleDown.setEnabled(false);

            }else if (playerSumRank == dilerSumRank){



                Hit.setEnabled(false);
                Stand.setEnabled(false);
                DoubleDown.setEnabled(false);

                bank = bank + bet;
                bet = 0;
                Bet.setText("Bet: " + bet);
                Bank.setText("Bank: " + bank);

            }else if (dilerSumRank > playerSumRank && dilerSumRank <=21 || playerSumRank > 21 || dilerSumRank == 21){


                bet = 0;
                bank = bank - bet;
                Bet.setText("Bet: " + bet);
                Bank.setText("Bank: " + bank);
                labelPlayer.setText("Player: Lose");
                labelDiler.setText("Diler: Win");


                Hit.setEnabled(false);
                Stand.setEnabled(false);
                DoubleDown.setEnabled(false);
            }



    }

}



