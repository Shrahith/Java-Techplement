
import java.util.ArrayList;
import java.util.Scanner;

class Question {
    String questionText;
    ArrayList<String> options;
    int correctAnswerIndex;

    Question(String questionText, ArrayList<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    boolean checkAnswer(int answer) {
        return answer == correctAnswerIndex + 1;
    }
}

class Quiz {
    String title;
    ArrayList<Question> questions;

    Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    void addQuestion(Question question) {
        questions.add(question);
    }

    void takeQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {
            question.displayQuestion();
            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt();
            if (question.checkAnswer(userAnswer)) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Incorrect! The correct answer was " + (question.correctAnswerIndex + 1) + ".\n");
            }
        }

        System.out.println("You scored " + score + " out of " + questions.size());
    }
}

public class QuizGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Quiz> quizzes = new ArrayList<>();

        // Create a quiz with Java-related questions
        Quiz javaQuiz = new Quiz("Java Concepts Quiz");

        javaQuiz.addQuestion(new Question("What is the size of an int in Java?",
                new ArrayList<String>() {{
                    add("16 bits");
                    add("32 bits");
                    add("64 bits");
                    add("128 bits");
                }},
                1));

        javaQuiz.addQuestion(new Question("Which of the following is not a Java keyword?",
                new ArrayList<String>() {{
                    add("class");
                    add("interface");
                    add("extends");
                    add("implement");
                }},
                3));

        javaQuiz.addQuestion(new Question("What is the default value of a boolean variable in Java?",
                new ArrayList<String>() {{
                    add("true");
                    add("false");
                    add("0");
                    add("null");
                }},
                1));

        javaQuiz.addQuestion(new Question("Which of the following is a marker interface?",
                new ArrayList<String>() {{
                    add("Serializable");
                    add("Cloneable");
                    add("Remote");
                    add("All of the above");
                }},
                3));

        javaQuiz.addQuestion(new Question("Which method must be implemented by all threads?",
                new ArrayList<String>() {{
                    add("start()");
                    add("run()");
                    add("stop()");
                    add("main()");
                }},
                1));

        javaQuiz.addQuestion(new Question("Which of these classes are the direct subclasses of the Throwable class?",
                new ArrayList<String>() {{
                    add("RuntimeException");
                    add("Error");
                    add("Exception");
                    add("All of the above");
                }},
                3));

        javaQuiz.addQuestion(new Question("Which collection class allows you to access its elements by associating a key with an element's value and provides synchronization?",
                new ArrayList<String>() {{
                    add("java.util.ArrayList");
                    add("java.util.HashMap");
                    add("java.util.TreeMap");
                    add("java.util.Hashtable");
                }},
                3));

        javaQuiz.addQuestion(new Question("Which of the following is true about a constructor?",
                new ArrayList<String>() {{
                    add("It is called at the time of object creation");
                    add("It is called explicitly");
                    add("It has a return type");
                    add("It can be abstract");
                }},
                0));

        javaQuiz.addQuestion(new Question("What is the return type of the hashCode() method in the Object class?",
                new ArrayList<String>() {{
                    add("Object");
                    add("int");
                    add("long");
                    add("void");
                }},
                1));

        javaQuiz.addQuestion(new Question("What does the keyword 'synchronized' mean?",
                new ArrayList<String>() {{
                    add("Only one thread can access the resource at a time");
                    add("Multiple threads can access the resource at a time");
                    add("It is not used in multithreading");
                    add("None of the above");
                }},
                0));

        quizzes.add(javaQuiz);

        while (true) {
            System.out.println("1. Create a new quiz");
            System.out.println("2. Take a quiz");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter quiz title: ");
                    String title = scanner.nextLine();
                    Quiz quiz = new Quiz(title);

                    System.out.print("Enter the number of questions: ");
                    int numQuestions = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    for (int i = 0; i < numQuestions; i++) {
                        System.out.print("Enter question " + (i + 1) + ": ");
                        String questionText = scanner.nextLine();

                        ArrayList<String> options = new ArrayList<>();
                        for (int j = 0; j < 4; j++) {
                            System.out.print("Enter option " + (j + 1) + ": ");
                            options.add(scanner.nextLine());
                        }

                        System.out.print("Enter the number of the correct option: ");
                        int correctAnswerIndex = scanner.nextInt() - 1;
                        scanner.nextLine();  // Consume newline

                        Question question = new Question(questionText, options, correctAnswerIndex);
                        quiz.addQuestion(question);
                    }

                    quizzes.add(quiz);
                    break;

                case 2:
                    if (quizzes.isEmpty()) {
                        System.out.println("No quizzes available.");
                        break;
                    }

                    System.out.println("Available quizzes:");
                    for (int i = 0; i < quizzes.size(); i++) {
                        System.out.println((i + 1) + ". " + quizzes.get(i).title);
                    }

                    System.out.print("Choose a quiz to take: ");
                    int quizChoice = scanner.nextInt() - 1;
                    scanner.nextLine();  // Consume newline

                    if (quizChoice >= 0 && quizChoice < quizzes.size()) {
                        quizzes.get(quizChoice).takeQuiz();
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}
