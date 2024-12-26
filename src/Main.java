import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner scanner = new Scanner(System.in);
        GenerateModeQuestion generateModeQuestion = new GenerateModeQuestion();
        showMenu();
        //Menú principal. Valida la entrada siempre y cuando no sea una opción correcta. Al ser opción correcta, ejecuta el programa.
        int option = scanner.nextInt();
        while (option >= 1 && option <= 3){
            generateModeQuestion.generateQuestionnaire(option);
            showMenu();
            option = scanner.nextInt();
        }
    }

    private static void showMenu(){
        System.out.println("1) Resolver el valor de y, dado los valores de m, x y b.\n" +
                "2) Resolver el valor de m, dado los valores de y, x y b.\n" +
                "3) Resolver el valor de b, dado los valores de y, m y x.\n" +
                "Otro número, salir!\n");
    }
}