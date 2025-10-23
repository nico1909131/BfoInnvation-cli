// Student erbt von Person + implementiert Interface
public class Student extends Person implements Submittable {
  private boolean submitted = false;

  public Student(String f, String l) {
    super(f, l);
  }

  // überladener Konstruktor
  public Student(String f, String l, boolean s) {
    super(f, l);
    this.submitted = s;
  }

  public void markSubmitted() { submitted = true; }
  public boolean isSubmitted() { return submitted; }
}
