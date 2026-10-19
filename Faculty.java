import java.util.*;

public class Faculty extends User {
    private String department;
    private boolean isAdmin;

    public Faculty(String facultyId, String fullName, int age, String email, String department, boolean isAdmin) {
        super(facultyId, fullName, age, email);
        this.department = department;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    // Add a candidate to the list
    public void addCandidate(List<Candidate> candidates, Candidate candidate) {
        candidates.add(candidate);
    }

    // Remove candidate by ID using traditional for loop
    public void removeCandidate(List<Candidate> candidates, int id) {
        for (int i = 0; i < candidates.size(); i++) {
            if (candidates.get(i).getId() == id) {
                candidates.remove(i);
                break;
            }
        }
    }

    // Reset all candidates' votes and all voters' vote status
    public void resetAllVotes(List<Candidate> candidates, Map<String, Voter> voters) {
        for (int i = 0; i < candidates.size(); i++) {
            candidates.get(i).resetVotes();
        }

        for (String key : voters.keySet()) {
            Voter v = voters.get(key);
            v.resetVote();
        }
    }

    // Display role of the faculty
    @Override
    public void displayRole() {
        System.out.println("Faculty: " + fullName + " (" + department + ")");
    }

    // Get Top N Candidates by Votes (Selection Sort, descending)
    public List<Candidate> getTopCandidates(List<Candidate> candidates, int n) {
        for (int i = 0; i < candidates.size() - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < candidates.size(); j++) {
                if (candidates.get(j).getVotes() > candidates.get(maxIdx).getVotes()) {
                    maxIdx = j;
                }
            }
            // Swap
            Candidate temp = candidates.get(i);
            candidates.set(i, candidates.get(maxIdx));
            candidates.set(maxIdx, temp);
        }

        List<Candidate> topList = new ArrayList<>();
        for (int i = 0; i < Math.min(n, candidates.size()); i++) {
            topList.add(candidates.get(i));
        }
        return topList;
    }

    // Sort candidates by name using Insertion Sort (A-Z)
    public static void sortCandidatesByName(List<Candidate> list) {
        for (int i = 1; i < list.size(); i++) {
            Candidate key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getName().compareToIgnoreCase(key.getName()) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
    
    //  Sort candidates by ID (ascending)
    public static void sortCandidatesById(List<Candidate> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getId() > list.get(j + 1).getId()) {
                    Candidate temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}
