public class Main {

    public static void main(String[] args) {
        JavaBallApp app = new JavaBallApp("TeamsIn.txt", "ResultsIn.txt");
        JavaBallGUI gui = new JavaBallGUI(app);
    }
}
