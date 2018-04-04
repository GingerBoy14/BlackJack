
class GetCardsRank {
    private int cardNumber;
    private int rank;
    private String front;

    DrawCards drawCards;

    GetCardsRank(int cardNumber, int rank, String front) {
        this.cardNumber = cardNumber;
        this.rank = rank;
        this.front = front;
    }

    public String getFront() {
        return front;
    }

    public int getRank(int sumrank) {
        int b = sumrank;
        if (rank == 14 && b > 10) {
            rank = 1;
        }
        if (rank == 11 || rank == 12 || rank == 13) {
            rank = 10;
        }

        if (rank == 14) {
            rank = 11;
        }


        return rank;
    }

}
