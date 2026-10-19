public class Voter extends User {
    private boolean hasVoted;
    private int candidateVotedFor;
    private String CNIC;


    // Constructor
    public Voter(String userId, String fullName, int age, String email, String CNIC) {
        super(userId, fullName, age, email);
        this.hasVoted = false;
        this.candidateVotedFor = -1;
        this.CNIC=CNIC;
    }

    public boolean hasVoted() {
        return hasVoted;
    }
    public String getCNIC(){
        return this.CNIC;
    }

    public void vote(int candidateId) {
        this.hasVoted = true;
        this.candidateVotedFor = candidateId;
    }

    public void resetVote() {
        this.hasVoted = false;
        this.candidateVotedFor = -1;
    }

    public int getCandidateVotedFor() {
        return candidateVotedFor;
    }

    // Voter ID format (e.g., V1234)
    public boolean isValidVoterId() {
        return userId.matches("V[0-9]{3,5}");
    }

    //Display role
    @Override
    public void displayRole() {
        System.out.println("Voter: " + fullName);
    }

    //Check if voter is eligible based on age and email domain
    public boolean isEligible() {
        return age >= 18 && email.toLowerCase().endsWith(".edu");
    }
}
