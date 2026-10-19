import java.util.*;

public class EVotingSystem {
    private ArrayList<Voter> voters = new ArrayList<>();
    private ArrayList<Candidate> candidates = new ArrayList<>();
    private Stack<String> voteUndoStack = new Stack<>();
    private Queue<String> voteQueue = new LinkedList<>();
    private VoteHistory voteHistory = new VoteHistory();
   private HashMap<String, Voter> voterMap = new HashMap<>();


    // Register a new voter
    public void registerVoter(Voter voter) {
        voters.add(voter);
    }
    //file handling methods
   public void setCandidateList(List<Candidate> candidates) {
    this.candidates = (ArrayList<Candidate>) candidates;
}

public void setVoterMap(Map<String, Voter> voters) {
    this.voterMap = voterMap;
}
public HashMap<String, Voter> getVoterMap() {
    return voterMap;
}

public VoteHistory getVoteHistory() {
    return this.voteHistory;
}
//end

    // Add a new candidate
    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    // Get candidate list
    public List<Candidate> getCandidateList() {
        return candidates;
    }

    // Display candidates
    public void displayCandidates() {
        System.out.println("\nCandidates List:");
        for (int i = 0; i < candidates.size(); i++) {
            Candidate c = candidates.get(i);
            System.out.println("ID: " + c.getId() + ", Name: " + c.getName() + ", Agenda: " + c.getAgenda());
        }
    }

    // Check if voter is eligible and hasn't voted
    public boolean isEligibleToVote(String voterId) {
        for (int i = 0; i < voters.size(); i++) {
            Voter v = voters.get(i);
            if (v.getUserId().equals(voterId) ) {
                return !v.hasVoted();
            } 
        }
        return false;
    }
    //check if voter exist
    public boolean isVoterIdExists(String voterId) {
        for (int i = 0; i < voters.size(); i++) {
            Voter v = voters.get(i);
            if (v.getUserId().equalsIgnoreCase(voterId)) {
                return true;
            }
        }
        return false;
    }

    // Cast vote for a candidate
    public boolean castVote(String voterId, int candidateId) {
        Voter voter = findVoterById(voterId);
        if (voter == null || voter.hasVoted()) {
            return false;
        }
        Candidate candidate = findCandidateById(candidateId);
        if (candidate == null) {
            return false;
        }
        candidate.incrementVotes();
        voter.vote(candidateId);

        String record = "Voter " + voterId + " voted for " + candidate.getName();
        voteUndoStack.push(record);
        voteQueue.offer(record);
        voteHistory.add(record);

        return true;
    }

    /*Undo the last vote
    public boolean undoLastVote() {
        if (voteUndoStack.isEmpty()) return false;

        String lastVote = voteUndoStack.pop();
        String[] parts = lastVote.split(" ");
        String voterId = parts[1];
        String candidateName = lastVote.substring(lastVote.lastIndexOf(" ") + 1);

        Voter voter = findVoterById(voterId);
        Candidate candidate = findCandidateByName(candidateName);

        if (voter != null && candidate != null) {
            voter.resetVote();
            candidate.decrementVotes();
            voteHistory.removeLast();
            return true;
        }

        return false;
    }*/

    // View voting history
    public void viewVoteHistory() {
        List<String> history = voteHistory.getHistoryAsList();
        if (history.isEmpty()) {
            System.out.println("No votes have been cast yet.");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println(history.get(i));
            }
        }
    }

      //search voter by id
    private Voter findVoterById(String id) {
        for (int i = 0; i < voters.size(); i++) {
            Voter v = voters.get(i);
            if (v.getUserId().equals(id)){
                return v;
            } 
        }
        return null;
    }
      //search candidate by id's
    private Candidate findCandidateById(int id) {
        for (int i = 0; i < candidates.size(); i++) {
            Candidate c = candidates.get(i);
            if (c.getId() == id){
                 return c;
            }
        }
        return null;
    }
    //search candidate by names
    private Candidate findCandidateByName(String name) {
        for (int i = 0; i < candidates.size(); i++) {
            Candidate c = candidates.get(i);
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
    public void sortCandidatesByVotes() {
    for (int i = 0; i < candidates.size() - 1; i++) {
        for (int j = i + 1; j < candidates.size(); j++) {
            if (candidates.get(j).getVotes() > candidates.get(i).getVotes()) {
                Candidate temp = candidates.get(i);
                candidates.set(i, candidates.get(j));
                candidates.set(j, temp);
            }
        }
    }
}

    
}
