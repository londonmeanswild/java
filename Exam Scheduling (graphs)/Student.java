import structure5.*;
/* Builds Student object
 */
public class Student {
    protected String name;

    // index 0 - 3 is student class 1-4
    protected Vector<String> courses;

    public Student(String name, Vector<String> courses) {
        this.courses = courses;
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public Vector<String> getCourses(){
        return courses;
    }
    // currently don't use these four. comment if I do use them.
    /* Get the student course stored at index 0
     * This is the first course.
     */
    public String getFirst(){
        return courses.get(0);
    }
    /* Get the student course stored at index 1
     * This is the second course.
     */
    public String getSecond(){
        return courses.get(1);
    }
    /* Get the student course stored at index 2
     * This is the third course.
     */
    public String getThird(){
        return courses.get(2);
    }
    /* Get the student course stored at index 3
     * This is the fourth course.
     */
    public String getFourth(){
        return courses.get(3);
    }
    /* returns true if a student has both the input courses
     */
    public boolean hasInCommon(String courseA, String courseB){
        return courses.contains(courseA) && courses.contains(courseB);
    }
}
