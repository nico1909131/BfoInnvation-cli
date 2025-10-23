// Basisklasse (Vererbung)
public class Person {
  private String firstName, lastName;

  public Person(String f, String l) {
    this.firstName = f;
    this.lastName = l;
  }

  // überladener Standard-Konstruktor (Demo/Tests)
  public Person() {
    this("Max", "Mustermann");
  }

  public String getFirstName() { return firstName; }
  public String getLastName()  { return lastName; }
  public String fullNameUnderscored() { return lastName + "_" + firstName; }
}
