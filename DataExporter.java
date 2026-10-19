import java.io.*;
import java.util.*;

public class DataExporter {

    private static final String CANDIDATES_FILE = "candidates.txt";
    private static final String VOTERS_FILE = "voters.txt";
    private static final String HISTORY_FILE = "vote_history.txt";

    // Save candidates to file
    public static void saveCandidates(List<Candidate> candidates) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CANDIDATES_FILE))) {
            for (Candidate c : candidates) {
                writer.write(c.getId() + "," + c.getName() + "," + c.getParty() + "," + c.getAgenda() + "," + c.getVotes());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving candidates: " + e.getMessage());
        }
    }

    // Load candidates from file
    public static List<Candidate> loadCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        File file = new File(CANDIDATES_FILE);
        if (!file.exists()) return candidates;

        try (BufferedReader reader = new BufferedReader(new FileReader(CANDIDATES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5); // ID, Name, Party, Agenda, Votes
                Candidate c = new Candidate(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3]
                );
                for (int i = 0; i < Integer.parseInt(parts[4]); i++) {
                    c.addVote();
                }
                candidates.add(c);
            }
        } catch (IOException e) {
            System.out.println("Error loading candidates: " + e.getMessage());
        }
        return candidates;
    }

    // Save voters to file
    public static void saveVoters(Map<String, Voter> voters) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VOTERS_FILE))) {
            for (Voter v : voters.values()) {
                writer.write(v.getUserId() + "," + v.getFullName() + "," + v.getAge() + "," + v.getEmail() + "," + v.hasVoted() + "," + v.getCandidateVotedFor());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving voters: " + e.getMessage());
        }
    }

    // Load voters from file
    public static Map<String, Voter> loadVoters() {
        Map<String, Voter> voters = new HashMap<>();
        File file = new File(VOTERS_FILE);
        if (!file.exists()) return voters;

        try (BufferedReader reader = new BufferedReader(new FileReader(VOTERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 6);
                Voter v = new Voter(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], "dummy"); // department placeholder
                if (Boolean.parseBoolean(parts[4])) {
                    v.vote(Integer.parseInt(parts[5]));
                }
                voters.put(parts[0], v);
            }
        } catch (IOException e) {
            System.out.println("Error loading voters: " + e.getMessage());
        }
        return voters;
    }

    // Save vote history (doubly linked list) to file
    public static void saveHistory(VoteHistory history) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE))) {
            for (String log : history.getHistoryAsList()) {
                writer.write(log);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving history: " + e.getMessage());
        }
    }

    // Load vote history from file into VoteHistory object
    public static void loadHistory(VoteHistory history) {
        File file = new File(HISTORY_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.log(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading history: " + e.getMessage());
        }
    }
}
