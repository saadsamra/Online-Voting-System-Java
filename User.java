public class User {
    protected String userId;
    protected String fullName;
    protected int age;
    protected String email;

    public User(String userId, String fullName, int age, String email) {
        this.userId = userId;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
    }

    public String getUserId() {
         return userId;
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

    public void displayRole() {
        System.out.println("Generic User: " + fullName);
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    
    public boolean isValidEmail() {
        return email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}
