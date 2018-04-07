import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

class DrawCards extends JPanel {

    Image cards;
    GetCardsRank help;
    GetCardsRank[] card = new GetCardsRank[52];
    ArrayList<Image> Card = new ArrayList();

    int N = 0;
    int b;
    int sumrank = 0;

    DrawCards(int b){
        //create array with rank,and image of cards
        this.b = b;
        for (int i = 0; i < 4; i++){
            for (int j = 2; j <= 14; j++){
                card[N] = new GetCardsRank(N,j,i + "" + j + ".png");
                N++;
            }
        }
        //need to set empty field in start
            try{
                cards = ImageIO.read(DrawCards.class.getResourceAsStream("cards/b1fv.png"));
            }catch (IOException e){}
    }

    public int getSumRank(){ return sumrank; }

    // method for set empty array to players
    public void removeAll(){
        sumrank = 0;
        Card = new ArrayList();
    }

    //method witch set image and add it to array of images
    public void paintCards(int b){

        int i = 0;
        while (i<b){
            try {
                help = card[getRandom(0,card.length)];
                String cardfront = help.getFront();
                int rank = help.getRank(sumrank);

                sumrank = sumrank + rank;
                cards = ImageIO.read(DrawCards.class.getResourceAsStream("cards/" + cardfront));
                Card.add(cards);
            } catch (IOException e) { }
            i++;
        }
    }

    //method fot paint cards
    private void onRepaint(Graphics g){
        int x = 20;
        for (int i = 0; i < Card.size();i++){
            g.drawImage(Card.get(i),x,50,null);
            x = x + 30;
        }
    }

    //get random card
    private int getRandom(int min, int max){
        return ((int)Math.floor (Math.random()*(max-min))+min);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0,0,this.getWidth(),this.getHeight());

        onRepaint(g);//cause method for paint cards in player field
        }
}


class DrawInterface extends JPanel{
  private int x, y, wd, hg;

    DrawInterface(int x, int y, int wd, int hg){ //need to set(paint) correct size
        this.x = x;
        this.y = y;
        this.wd = wd;
        this.hg = hg;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.fillRect(x, y,wd,hg);
    }
}