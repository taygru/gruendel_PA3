import java.util.Scanner;
import java.security.SecureRandom;

public class gruendel_p1{

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        greet();
        int go = 1;
        while(go != 0){
            menu();
            int choice = input.nextInt();
            levels();
            int difficulty = input.nextInt();
            processChoices(choice, difficulty);
            System.out.print("Would you like to go again? Press a number other than 0 to continue: ");
            go = input.nextInt();
        }

    }

    public static void greet(){
        System.out.println("Welcome to math practice");
    }

    public static void menu(){
        //categories to choose from
        System.out.println("1) addition \n" +
                "2) subtraction\n" +
                "3) multiplication\n" +
                "4) division\n" +
                "5) random\n");
    }

    public static void levels(){
        System.out.println("1) easy \n" +
                "2) medium\n" +
                "3) hard\n" +
                "4) impossible\n");
    }

    public static void processChoices(int choice, int difficulty){
        if(choice < 1 || 5 < choice){
            System.out.println("Invalid choice defaulted to random...");
            choice = 5;
        }

        if(difficulty < 1 || 4 < difficulty){
            System.out.println("Invalid choice defaulted to easy...");
            choice = 1;
        }

        generateProblems(choice, difficulty);

    }


    //checks to see how many answers were corrects versus incorrect
    public static void generateProblems(int mode, int difficulty){
        String[] correct_messages = {"Very Good!","Excellent!", "Nice Work!", "Keep up the good work!"};
        String[] incorrect_messages = {"No. Please try again", "Wrong. Try once more", "Don't give up!", "No. Keep trying"};
        Scanner input = new Scanner(System.in);
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        getTenProblems(bytes, difficulty);
        int solution  = 0;
        int attempt = 0;
        int successes = 0;
        int state = 0;
        for(int i = 0; i < 10; i++){
            state = say_question(bytes, mode, i);
            solution = getSolution(bytes[i * 2], bytes[i * 2 + 1], state);
            attempt = input.nextInt();
            if(attempt == solution){
                successes++;
                System.out.println(correct_messages[(int)(Math.random() * 4)]);
            }
            else{
                System.out.println(incorrect_messages[(int)(Math.random() * 4)]);
            }
            System.out.println("\n\n");
        }
        if((int)(successes/10 * 100) >= 75){
            System.out.println("Congratulations You are ready for the next level");
        }
        else{
            System.out.println("Please ask you teacher for extra help...");
        }

    }

    public static int getSolution(int a, int b, int mode){
        if(mode == 5){
            mode = (int)(Math.random() * 4 + 1);
        }

        if(mode == 1){
            return a + b;
        }
        else if(mode == 2){
            return a - b;
        }
        else if(mode == 3){
            return a * b;
        }
        else{
            return a / b;
        }
    }

    //generates 10 random problems
    public static void getTenProblems(byte[] bytes, int difficulty){
        byte[] possible = new byte[2];
        SecureRandom random = new SecureRandom();
        for(int i = 0; i < bytes.length - 2; i = i + 2){
            random.nextBytes(possible);
            while(!follows_difficulty(possible, difficulty)){
                random.nextBytes(possible);
            }
            bytes[i] = possible[0];
            bytes[i+1] = possible[1];
        }
    }



    public static boolean follows_difficulty(byte[] possible, int difficulty){
        return length(Math.abs(possible[0])) <= difficulty && length(Math.abs(possible[1])) <= difficulty;
    }


    public static int length(int value){
        if(value == 0){
            return 1;
        }
        int i = 0;
        while(value > 0){
            value = value/10;
            i++;
        }
        return i;
    }

    //generates questions to ask based off category
    public static int say_question(byte[] bytes, int mode, int i){
        int position = 0;
        if(mode == 5){
            mode = (int)(Math.random() * 4);
            position = mode;
        }
        else {
            position = mode - 1;
        }

        if((mode == 4 || mode == 5) && bytes[i * 2 + 1] == 0){
            bytes[i * 2 + 1] = 1;
        }

        String[] operations = {" plus ", " minus ", " times ", " divided "};
        System.out.print("What is " + bytes[i * 2] + operations[position] + bytes[i * 2 + 1] + "? ");
        return mode;

    }
}