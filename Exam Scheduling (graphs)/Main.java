import structure5.*;
import java.util.Scanner;
/* Main */

public class Main {
public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    Vector<Student> input = new Vector<Student>();
    while (sc.hasNext()){
        String name = sc.nextLine();
        Vector<String> courses = new Vector<String>();
        // one for each course in a student object
        courses.add(sc.nextLine());
        courses.add(sc.nextLine());
        courses.add(sc.nextLine());
        courses.add(sc.nextLine());

        input.add(new Student(name, courses));
    }

    Scheduler newSchedule = new Scheduler(input);

    // newSchedule.printGraph();
    newSchedule.solve();
    newSchedule.printAlphabetical();
}


}
