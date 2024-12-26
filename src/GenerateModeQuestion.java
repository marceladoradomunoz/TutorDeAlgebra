import java.util.Random;
import java.util.Scanner;

public class GenerateModeQuestion {

    Scanner scanner = new Scanner(System.in);

    public GenerateModeQuestion(){
    }

    public void generateQuestionnaire(int mode){
        int countCorrectQuestions = 0;
        int countGeneratedQuestions = 0;

        while (countCorrectQuestions < 3){
            double questionAnswer;
            countGeneratedQuestions++;
            if (mode == 1){
                questionAnswer = generateQuestionModeOne(); //Ejecutar cuestionario del modo 1
            } else if (mode == 2){
                questionAnswer = generateQuestionModeTwo(); //Ejecutar cuestionario del modo 2
            } else {
                questionAnswer = generateQuestionModeThree(); //Ejecutar cuestionario del modo 3
            }

            //Si el usuario no tiene 3 respuestas consecutivas correctas, se muestra pista para resolver la pregunta.
            if (countGeneratedQuestions > 3){
                showClue(mode);
            }
            showNotes();
            showTextRequestAnswerToUser();
            //Solicita al usuario ingresar la respuesta (numérica) a la pregunta
            double userAnswer = scanner.nextDouble();

            //Verifica si la respuesta ingresada por el usuario es igual a la generada por el programa
            if (questionAnswer == userAnswer){
                showInfoAboutCorrectAnswer();
                countCorrectQuestions++; //Aumenta contador de preguntas correctas consecutivas
            } else {
                showInfoIncorrectAnswer();
                countCorrectQuestions = 0; //Contador de respuestas correctas consecutivas vuelve a cero.
            }
        }
        //Mostrar número de respuestas correctas vs total de preguntas generadas.
        showSummary(countCorrectQuestions, countGeneratedQuestions);

    }

    /**
     * Genera la respuesta correcta (un valor Double, puede ser entero o flotante) para la pregunta modo 1
     */
    public Double generateQuestionModeOne(){
        showQuestionTextModeOne();
        int m = generateRandomNumber(); //m: pendiente de la recta
        int x = generateRandomNumber(); //x: punto en el eje X
        int b = generateRandomNumber(); //b: punto donde la recta corta al eje Y
        showValuesToTextQuestion("m", "x", "b", m, x, b);
        return getYvalue(m, x, b);
    }

    /**
     * Genera la respuesta correcta (un valor Double, puede ser entero o flotante) para la pregunta modo 2
     */
    public Double generateQuestionModeTwo(){
        showQuestionTextModeTwo();
        int y = generateRandomNumber(); //y: punto en el eje Y
        int x = generateRandomNumber(); //x: punto en el eje X
        int b = generateRandomNumber(); //b: punto donde la recta corta al eje Y
        showValuesToTextQuestion("y", "x", "b", y, x, b);
        double value = getMvalue(y, x, b);
        return roundOnlyTwoDecimals(value);
    }

    /**
     * Genera la respuesta correcta (un valor Double, puede ser entero o flotante) para la pregunta modo 3
     */
    public Double generateQuestionModeThree(){
        showQuestionTextModeThree();
        int y = generateRandomNumber(); //y: punto en el eje Y
        int m = generateRandomNumber(); //m: pendiente de la recta
        int x = generateRandomNumber(); //x: punto en el eje X
        showValuesToTextQuestion("y", "m", "x", y, m, x);
        return getBvalue(y, m, x);
    }

    /**
     * Retorna un número entero en el intervalo -100 a 100.
     */
    private int generateRandomNumber(){
        Random random = new Random();
        return random.nextInt(201) - 100;
    }

    /**
     * Retorna el valor de Y; punto perteneciente al plano cartesiano relativo al eje Y
     * @param m: pendiente de la recta
     * @param x: punto perteneciente al plano cartesiano relativo al eje X
     * @param b: punto del plano cartesiano donde la recta corta al eje  (ordenada)
     */
    private double getYvalue(double m, double x, double b){
        return m*x + b;
    }

    /**
     * Retorna el valor de m; pendiente de la recta
     * @param y: punto del eje Y
     * @param x: punto del eje X
     * @param b: punto del plano cartesiano donde la recta corta al eje Y (ordenada)
     */
    private double getMvalue(double y, double x, double b){
        //y = m*x + b
        //y-b = m*x -> (y-b)/x = m
        return (y-b)/x;
    }

    /**
     * Retorna el valor de b; punto del plano cartesiano donde la recta corta al eje Y (ordenada)
     * @param y: punto del eje Y
     * @param m: pendiente de la recta
     * @param x: punto del eje X
     */
    private double getBvalue(double y, double m, double x){
        //y - m*x = b
        return  y-(m*x);
    }

    /**
     * Imprimir mensaje del enunciado de la pregunta del modo 1
     */
    private void showQuestionTextModeOne(){
        System.out.println("Resolver el valor de y, dado los valores de m, x y b.");
    }

    /**
     * Imprimir mensaje del enunciado de la pregunta del modo 2
     */
    private void showQuestionTextModeTwo(){
        System.out.println("Resolver el valor de m, dado los valores de y, x y b.");
    }

    /**
     * Imprimir mensaje del enunciado de la pregunta del modo 3
     */
    private void showQuestionTextModeThree(){
        System.out.println("Resolver el valor de b, dado los valores de y, m y x.");
    }

    /**
     * Imprimir mensaje para solicitar al usuario al usuario ingresar su respuesta.
     */
    private void showTextRequestAnswerToUser(){
        System.out.print("Ingrese su respuesta: ");
    }

    /**
     * Imprimir mensaje cuando la respuesta fue correcta.
     */
    private void showInfoAboutCorrectAnswer(){
        System.out.println("Su respuesta es correcta!");
    }

    /**
     * Imprimir mensaje cuando la respuesta fue incorrecta.
     */
    private void showInfoIncorrectAnswer(){
        System.out.println("Su respuesta es incorrecta!");
    }

    /**
     * Imprimir mensaje con diferentes pistas según el modo seleccionado por el usuario y cómo debe calcular las operaciones.
     */
    private void showClue(int mode){
        if (mode == 1) {
            System.out.println("Para obtener el valor de y debes calcular y = m*x + b." + " Recuerda que debes reemplazar los valores de m, x y b, realizar el cálculo e ingresar el valor final para y.");
        } else if (mode == 2) {
            System.out.println("Para obtener el valor de m debes calcular m=(y-b)/x." + " Recuerda que debes reemplazar los valores de y, b y x, realizar el cálculo e ingresar el valor final para m.");
        } else {
            System.out.println("Para obtener el valor de b debes calcular b= y-(m*x)." + " Recuerda que debes reemplazar los valores de y, m y x, realizar el cálculo e ingresar el valor final para b.");
        }
    }

    /**
     * Imprimir las variables y su valor respectivo, usando String.format.
     */
    private void showValuesToTextQuestion(String firstVariable, String secondVariable, String thirdVariable,
                                          int firstValue, int secondValue, int thirdValue){
        String valuesToTextQuestion = String.format("Valores: " +
                "%s: %d \n" +
                "%s: %d \n" +
                "%s: %d \n", firstVariable, firstValue, secondVariable, secondValue, thirdVariable, thirdValue);
        System.out.println(valuesToTextQuestion);
    }

    /**
     * Mostrar notas de ayuda para indicar al usuario como deben ser ingresadas las entradas del programa.
     */
    private void showNotes(){
        System.out.println("Importante: Aquellos valores con decimales, sólo escribir los primeros dos decimales. Usar coma (,) para ingresar estos valores.");
    }

    /**
     * Imprimir mensaje final acerca de cuántas preguntas correctas hubo sobre el número total de preguntas.
     */
    private void showSummary(int countCorrectAnswers, int countGeneratedQuestions){
        String summary = String.format("N° de respuestas correctas (%d) / N° de preguntas generadas(%d)", countCorrectAnswers, countGeneratedQuestions);
        System.out.println(summary);
    }

    private Double roundOnlyTwoDecimals(double value){
        return Math.round(value * 100.0) / 100.0;
    }
}