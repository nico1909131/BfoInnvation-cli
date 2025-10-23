import java.util.*;

// Aggregation: ClassRoom hält Students
public class ClassRoom {
  private final List<Student> students = new ArrayList<>();

  public void add(Student s) { students.add(s); }
  public List<Student> getStudents() { return students; }

  public Student findByNames(String first, String last) {
    for (Student s : students) {
      if (s.getFirstName().equalsIgnoreCase(first)
       && s.getLastName().equalsIgnoreCase(last)) {
        return s;
      }
    }
    return null;
  }
}
