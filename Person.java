public class Person {
    protected String fullName;
    protected int age;
    protected String email;

    public Person(String fullName, int age, String email) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
    }

    public String getFullName() { 
        return fullName;
     }
    public int getAge() { 
        return age;
     }
    public String getEmail() {
         return email;
         }

    public boolean isValidEmail() {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}
