class TestTribune {
    public static void main(String[] args) {

        String[] na = new String[2];
        na[0] = "ole";
        na[1] = "eskil";

        StandingTribune standT = new StandingTribune("standing", 100, 5);
        System.out.println(standT.buyTickets(na));
        System.out.println(standT.buyTickets(3));
        System.out.println(standT + "\n\n");



        SittingTribune sitT = new SittingTribune("sitting", 25, 2, 5);
        System.out.println(sitT.buyTickets(na));
        System.out.println(sitT.buyTickets(5));
        System.out.println(sitT.buyTickets(1)); // Fails, should return null
        System.out.println(sitT + "\n\n");

        VIPTribune vT = new VIPTribune("vip", 10, 6, 5);
        System.out.println(vT.buyTickets(na));
        System.out.println(vT.buyTickets(1));
        System.out.println(vT.buyTickets(na));
        System.out.println(vT + "\n\n");
    }
}
