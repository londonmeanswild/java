import structure5.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Comparator;
/* Builds Graph
 */
public class Scheduler {
    // vector of student names and their courses. populated by constructor.
    protected Vector<Student> students;
    protected Graph<String, Integer> graph;
    protected OrderedVector<String> courses;
    protected Vector<String> examSchedule;

    // constructor to populate our vector of students
    // Student(String name, Vector<String> courses)
    public Scheduler(Vector<Student> students){
        this.courses = new OrderedVector<String>();
        this.students = students;
        graph = new GraphListUndirected<String, Integer>();
        // divide student object into course names and name of student
        for (Student s : students){
            for(String course : s.getCourses()){
                if (!graph.contains(course)){
        // add courses to a vector
            courses.add(course);
                    graph.add(course);
                }

            }

        }
        // this is an n^3 operation. For, with a while, while.
        // for loop out front because each student's schedule is different

        for (Student s : students){
        // iterate through our graph by vertex.

        // for each element we have, do iterator, string,
        // construct another iterator. Like a nested for loop
        // but with iterators. These are two separate iterators to
        // go through our graph

        // each student gets their turn to add adjacencies between courses.
        // this is our first iterator. for dom to move
        // sub has to go through all possible adjacencies.
            Iterator<String> domIter = graph.iterator();

            while (domIter.hasNext()){
                String paired = domIter.next();
                Iterator<String> subIter = graph.iterator();
        // dom and sub iter will go to same destination at exactly
        // one point in their run.
                while (subIter.hasNext()){
                    String relationship = subIter.next();
                    // if this, iterator moves on.
                    // checking to see if the relationship doesn't exist, if the courses !=
                    // and if graph already contains an edge
                    if(!paired.equals(relationship) && s.hasInCommon(paired,relationship)
                        && !graph.containsEdge(paired, relationship)){

                        graph.addEdge(paired, relationship, 0);
                }
            }
        }
    }
}

public void printGraph(){

    Iterator<String> vertices = graph.iterator();
    while (vertices.hasNext()){
        System.out.println(vertices.next());
    }
    Iterator<Edge<String, Integer>> edges = graph.edges();
    while (edges.hasNext()) System.out.println(edges.next());


}
public void solve(){
    examSchedule = new Vector<String>();
    Random rand = new Random();
    // take number of vertices, pass to random generator, get a random number
    // bound by 0 to number of vertices

    // wrap in a for loop that runs as long as graph isn't empty
    for (int i = 1; !graph.isEmpty(); i++){

        // pick a course
        String firstCourse = graph.iterator().next();

        // set up neighbors
        Vector<String> nonNeighbor = new Vector<String>();
        nonNeighbor.add(firstCourse);

        // Remaining
        Iterator<String> remaining = graph.iterator();
        while (remaining.hasNext()){
            // we asssume that remaining.hasNext works
            // if it works, found is always true because it doesn't contain an edge

            String neighborVertex = remaining.next();
            boolean found = true;

            for (String n : nonNeighbor) if (n.equals(neighborVertex)
                || graph.containsEdge(n, neighborVertex)){
            // found is false, break, go back up, get new neighbor vertex and try again
                    found = false;
                    break;
                }

            if (found) nonNeighbor.add(neighborVertex);
        }
        String toAdd = "Exam slot " + i + ": ";
        for (String s : nonNeighbor){
        toAdd += s + " ";
            graph.remove(s);
        }
        examSchedule.add(toAdd);
    }
    System.out.println("Printing the exam schedule by time slot: \n");
    for (String s: examSchedule){
        System.out.println(s);
    }
    System.out.println("\n");
}
    public void printAlphabetical(){
        System.out.println("Printing the exam schedule in alphabetical order by course: \n");
    for (String course: courses){
    int i = 1;
        for(String slot : examSchedule){
            if (slot.contains(course)) {
                System.out.println(course + " : slot " + i);
                break;
            }
        i++; }
    }
    }
}




