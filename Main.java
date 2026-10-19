import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EVotingSystem system = new EVotingSystem();

        // Load saved data from files
        system.setCandidateList(DataExporter.loadCandidates());
        system.setVoterMap(DataExporter.loadVoters());
        DataExporter.loadHistory(system.getVoteHistory());

        // Add Sample Candidates
system.addCandidate(new Candidate(1, "Imran Khan", "PTI", "Reforms"));
system.addCandidate(new Candidate(2, "Nawaz Sharif", "PML-N", "Development"));
system.addCandidate(new Candidate(3, "Bilawal Bhutto", "PPP", "Collaboration"));
//sample voters
ArrayList<Voter> voters = new ArrayList<>();
voters.add(new Voter("V1001", "Ali Khan", 25, "ali.khan@example.com", "34101-5008653-9"));
voters.add(new Voter("V1002", "Sara Ahmed", 32, "sara.ahmed@example.com", "34101-5008653-8"));
voters.add(new Voter("V1020", "Sana Jamil", 21, "sana.jamil@example.com", "42101-0123456-0"));


        for (int i = 0; i < voters.size(); i++) {
    system.registerVoter(voters.get(i));
    int randomCandidateId = 1 + new Random().nextInt(3); // IDs 1-3
    system.castVote(voters.get(i).getUserId(), randomCandidateId);
}



        while (true) {
            System.out.println("\n==== Online-Voting System ====");
            System.out.println("1. Register Voter");
            System.out.println("2. Cast Vote");
            System.out.println("3. View Top Candidates (Faculty)");
            System.out.println("4. View Vote History");
            System.out.println("5. Faculty Panel");
            System.out.println("6. Exit");
            System.out.println("7. Export Data to Files");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n-- Register Voter --");
                    System.out.print("Enter Voter ID: ");
                    String voterId = sc.nextLine().trim();
                    while (!voterId.matches("[a-zA-Z]\\d{3}")) {
                        System.out.println("Invalid ID! Format should be like A123 or z456.");
                        System.out.print("Enter ID again: ");
                        voterId = sc.nextLine().trim();
                    }

                    if (system.isVoterIdExists(voterId)) {
                        System.out.println("This Voter ID is already registered!");
                        break;
                    }

                    System.out.print("Enter Full Name: ");
                    String name = sc.nextLine();
                    while (!name.matches("[a-zA-Z ]+")) {
                        System.out.println("Name can only contain letters and spaces.");
                        System.out.print("Enter Full Name Again: ");
                        name = sc.nextLine();
                    }

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    while (age < 18) {
                        System.out.println("Age must be 18 or older to vote.");
                        System.out.print("Enter Age Again: ");
                        age = sc.nextInt();
                    }
                    sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter CNIC: ");
                    String CNIC = sc.nextLine();

                    Voter newVoter = new Voter(voterId, name, age, email, CNIC);
                    system.registerVoter(newVoter);
                    System.out.println("Voter registered successfully.");
                    break;

                case 2:
                    System.out.println("\n-- Cast Vote --");
                    System.out.print("Enter Voter ID: ");
                    String id = sc.nextLine().trim();

                    if (!system.isVoterIdExists(id)) {
                        System.out.println("Voter with ID " + id + " not found. Please Register first.");
                        break;
                    }

                    if (!system.isEligibleToVote(id)) {
                        System.out.println("Voter with ID " + id + " has already casted their vote!");
                        break;
                    }

                    system.displayCandidates();
                    System.out.print("Enter Candidate ID to vote: ");
                    int cid = sc.nextInt();
                    sc.nextLine();

                    if (system.castVote(id, cid)) {
                        System.out.println("Vote cast successfully!");
                    } else {
                        System.out.println("Failed to cast vote. Invalid candidate.");
                    }
                    break;

                case 3:
                    System.out.println("\n-- View Top Candidates --");
                    Faculty faculty = new Faculty("F001", "Dr. Smith", 45, "smith@college.edu", "Election Commission", true);
                    List<Candidate> top = faculty.getTopCandidates(system.getCandidateList(), 3);
                    for (int i = 0; i < top.size(); i++) {
                        Candidate c = top.get(i);
                        System.out.println((i + 1) + ". " + c.getName() + " - " + c.getVotes() + " votes");
                    }
                    break;

                case 4:
                    System.out.println("\n-- Vote History --");
                    system.viewVoteHistory();
                    break;

                case 5:
                    Faculty admin = new Faculty("F001", "Dr. Smith", 45, "smith@college.edu", "Election Commission", true);
                    while (true) {
                        System.out.println("\n-- Faculty Panel --");
                        System.out.println("1. View All Candidates");
                        System.out.println("2. Sort by Name");
                        System.out.println("3. Sort by ID");
                        System.out.println("4. Sort by Votes");
                        System.out.println("5. Add Candidate");
                        System.out.println("6. Remove Candidate");
                        System.out.println("7. Reset All Votes");
                        System.out.println("8. Back to Main Menu");
                        System.out.print("Enter choice: ");
                        int fChoice = sc.nextInt();
                        sc.nextLine();

                        List<Candidate> cList = system.getCandidateList();

                        switch (fChoice) {
                            case 1:
                                for (int i = 0; i < cList.size(); i++) {
                                    System.out.println(cList.get(i));
                                }
                                break;

                            case 2:
                                admin.sortCandidatesByName(cList);
                                System.out.println("Sorted by Name:");
                                for (int i = 0; i < cList.size(); i++) {
                                    System.out.println(cList.get(i));
                                }
                                break;

                            case 3:
                                admin.sortCandidatesById(cList);
                                System.out.println("Sorted by ID:");
                                for (int i = 0; i < cList.size(); i++) {
                                    System.out.println(cList.get(i));
                                }
                                break;

                            case 4:
                                system.sortCandidatesByVotes();
                                System.out.println("Sorted by Votes:");
                                for (int i = 0; i < cList.size(); i++) {
                                    System.out.println(cList.get(i));
                                }
                                break;

                            case 5:
                                System.out.print("Enter New Candidate ID: ");
                                int newId = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter Name: ");
                                String cname = sc.nextLine();
                                System.out.print("Enter Party: ");
                                String party = sc.nextLine();
                                System.out.print("Enter Agenda: ");
                                String agenda = sc.nextLine();
                                admin.addCandidate(cList, new Candidate(newId, cname, party, agenda));
                                System.out.println("Candidate added.");
                                break;

                            case 6:
                                System.out.print("Enter Candidate ID to Remove: ");
                                int remId = sc.nextInt();
                                sc.nextLine();
                                admin.removeCandidate(cList, remId);
                                System.out.println("Candidate removed.");
                                break;

                            case 7:
                                admin.resetAllVotes(cList, system.getVoterMap());
                                System.out.println("All votes reset.");
                                break;

                            case 8:
                                System.out.println("Returning to Main Menu...");
                                break;

                            default:
                                System.out.println("Invalid choice.");
                        }

                        if (fChoice == 8) {
                            break;
                        }
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using the E-Voting System!");
                    return;

                case 7:
                    System.out.println("\n-- Exporting Data to Files --");
                    DataExporter.saveCandidates(system.getCandidateList());
                    DataExporter.saveVoters(system.getVoterMap());
                    DataExporter.saveHistory(system.getVoteHistory());
                    System.out.println("Data exported successfully.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
