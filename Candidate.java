import java.util.ArrayList;
import java.util.List;

public class Candidate {
    private int id;
    private String name;
    private String party;
    private String agenda;
    private int votes;

    public Candidate(int id, String name, String party, String agenda) {
        this.id = id;
        this.name = name;
        this.party = party;
        this.agenda = agenda;
        this.votes = 0;
    }

    public int getId() {
         return id; 
        }
    public String getName() {
         return name;
         }
    public String getParty() {
         return party;
         }
    public String getAgenda() {
         return agenda;
     }
    public int getVotes() {
         return votes;
         }

    public void addVote() { 
        votes++; 
    }

    public void resetVotes() { 
        votes = 0;
     }
       @Override
public String toString() {
    return "ID: " + id + ", Name: " + name + ", Party: " + party + ", Agenda: " + agenda+ ", Votes: " + votes;
}

    public void updateInfo(String name, String party, String agenda) {
        this.name = name;
        this.party = party;
        this.agenda = agenda;
    }
     public void decrementVotes() {
        if (votes > 0) votes--;
    }
     public void incrementVotes() { votes++; }
     
    //Check if agenda is a palindrome
    public boolean isAgendaPalindrome() {
        String cleaned = agenda.replaceAll("\\s+", "").toLowerCase();
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }
    public static List<Candidate> mergeSortByVotes(List<Candidate> list) {
    if (list.size() <= 1) return list;

    int mid = list.size() / 2;
    List<Candidate> left = mergeSortByVotes(list.subList(0, mid));
    List<Candidate> right = mergeSortByVotes(list.subList(mid, list.size()));

    return merge(left, right);
}

private static List<Candidate> merge(List<Candidate> left, List<Candidate> right) {
    List<Candidate> result = new ArrayList<>();
    int i = 0, j = 0;

    while (i < left.size() && j < right.size()) {
        if (left.get(i).getVotes() >= right.get(j).getVotes()) {
            result.add(left.get(i++));
        } else {
            result.add(right.get(j++));
        }
    }

    while (i < left.size()) result.add(left.get(i++));
    while (j < right.size()) result.add(right.get(j++));

    return result;
}

}
