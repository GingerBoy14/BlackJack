class GetCardsRank {
    private int cardNumber;
    private int rank;
    private String front;



    GetCardsRank(int cardNumber, int rank, String front) {
        this.cardNumber = cardNumber;
        this.rank = rank;
        this.front = front;
    }

    // get image
    public String getFront() {
        return front;
    }

    //correcting rank
    public int getRank(int sumrank) {
        int b = sumrank;
        if (rank == 14 && b > 10) {//if sumcard in hand of player > 10 Ace = 1
            rank = 1;
        }
        if (rank == 11 || rank == 12 || rank == 13) { //Queen, King, Jack = 10
            rank = 10;
        }

        if (rank == 14) { // Ace = 11
            rank = 11;
        }


        return rank;
    }

}
