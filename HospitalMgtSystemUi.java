/*
How to read data from netbean using scanner
*/


package view;

import DAO.PatientDao;
import java.util.Scanner;
import model.Patient;
//import java.sql.DriverManager;
import java.util.List;

public class HospitalMgtSystemUi {
    public static void main(String[] args) {
        boolean condition = true;
        int patientId;
         String answer;
        String names;
        String feedback;
        PatientDao dao = new PatientDao();
        Patient thePatient = new Patient();
        Scanner sc = new Scanner(System.in);

        while (condition) {
            System.out.println("=================");
            System.out.println("1. Create a Patient");
            System.out.println("2. Update a Patient");
            System.out.println("3. Delete a Patient");
            System.out.println("4. Retrieve all Patients");
            System.out.println("5. Search Patient by ID");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    patientId = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter Name: ");
                    names = sc.nextLine();

                    if (names.length() < 3) {
                        System.out.println("Patient's name must have a minimum of 3 characters.");
                    } else {
                        thePatient.setPatientId(patientId);
                        thePatient.setPatientNames(names);

                        feedback = dao.createPatientPrepared(thePatient);
                        System.out.println(feedback);

                        System.out.print("Enter 'Yes' or 'No' to continue: ");
                         answer = sc.nextLine();

                        if (answer.equalsIgnoreCase("No")) {
                            System.out.println("Thank you for using the system");
                            condition = false;
                        }
                    }
                    break;


                case 2:
                    System.out.print("Enter Patient ID to update: ");
                    patientId = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter new Name: ");
                    names = sc.nextLine();

                    thePatient.setPatientId(patientId);
                    thePatient.setPatientNames(names);

                    feedback = dao.updatePatient(thePatient); 
                    System.out.println(feedback);

                    System.out.print("Enter 'Yes' or 'No' to continue: ");
                    answer = sc.nextLine();

                    if (answer.equalsIgnoreCase("No")) {
                        System.out.println("Thank you for using the system");
                        condition = false;
                    }
                    break;


                case 3:
                    System.out.print("Enter Patient ID to delete: ");
                    patientId = sc.nextInt();
                    sc.nextLine();

                    thePatient.setPatientId(patientId);

                    feedback = dao.deletePatient(thePatient);
                            
                    System.out.println(feedback);

                    System.out.print("Enter 'Yes' or 'No' to continue: ");
                    answer = sc.next();

                    if (answer.equalsIgnoreCase("No")) {
                        System.out.println("Thank you for using the system");
                        condition = false;
                    }
                    break;


                case 4:
                    List<Patient> patients = dao.getAllPatients(); 
                    if (patients.isEmpty()) {
                        System.out.println("No patients found.");
                    } else {
                        System.out.println("All Patients:");
                        for (Patient patient : patients) {
                            System.out.println("Patient ID: " + patient.getPatientId());
                            System.out.println("Patient Name: " + patient.getPatientNames());
                            System.out.println("=================");
                        }
                    }
                    break;


                case 5:
                        System.out.print("Enter Patient ID to search: ");
                        patientId = sc.nextInt();
                        sc.nextLine();

                        Patient foundPatient = dao.getPatientById(patientId); 

                        if (foundPatient != null) {
                            System.out.println("Patient ID: " + foundPatient.getPatientId());
                            System.out.println("Patient Name: " + foundPatient.getPatientNames());
                        } else {
                            System.out.println("Patient not found.");
                        }
                        break;


                case 0:
                    System.out.println("Thank you for using our system");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}