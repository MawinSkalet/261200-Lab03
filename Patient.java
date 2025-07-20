public class Patient {
    private int id;             // patient's ID
    private String name;        // patient's full name
    private int birthYear;      // patient's year of birth (CE)
    private double height;      // patient's height in centimeters
    private double weight;      // patient's weight in kilograms
    private String bloodGroup;  // patient's blood group
    private String phoneNumber; // patient's phone number

    // Constructor with all parameters
    public Patient(int id, String name, int birthYear, double height, double weight,
                   String bloodGroup, String phoneNumber) {
        // Initialize fields with validation
        this.id = id;
        this.name = name;

        // Validate and set birth year
        if (birthYear < 2025) {
            this.birthYear = birthYear;
        } else {
            this.birthYear = 2000;
        }

        // Validate and set height (30-300 cm range)
        if (height < 30 || height > 300) {
            System.out.println("!height: Must be positive and within reasonable range 30-300 cm");
            this.height = 30;
        } else {
            this.height = height;
        }

        // Validate and set weight (0.5-500 kg range)
        if (weight < 0.5 || weight > 500) {
            System.out.println("!weight: Must be positive and within reasonable range 0.5-500 kg");
            this.weight = 0.5;
        } else {
            this.weight = weight;
        }

        // Validate and set blood group
        if (isValidBloodGroup(bloodGroup)) {
            this.bloodGroup = bloodGroup;
        } else {
            System.out.println("!bloodGroup: Invalid blood group. Using default 'O'");
            this.bloodGroup = "O";
        }

        // Set phone number
        this.phoneNumber = phoneNumber;
    }

    // Constructor chaining - original constructor calls the new one with default values
    public Patient(int id, String name, int birthYear, double height, double weight) {
        this(id, name, birthYear, height, weight, "O", "N/A");
    }

    // Helper method to validate blood group
    private boolean isValidBloodGroup(String bloodGroup) {
        return bloodGroup.equals("A") || bloodGroup.equals("B") ||
                bloodGroup.equals("AB") || bloodGroup.equals("O");
    }

    // Getter methods
    public int getId() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public int getBirthYear() {
        return this.birthYear;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWeight() {
        return this.weight;
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    // Calculate and return BMI
    public double getBMI() {
        // Convert height from centimeters to meters
        double heightInMeters = this.height / 100.0;

        // Calculate BMI using the formula: BMI = weight (kg) / (height (m))^2
        double bmi = this.weight / (heightInMeters * heightInMeters);

        return bmi;
    }

    // Get age calculation with proper validation
    public int getAge(int currentYear) {
        // Validation: currentYear should be reasonable
        if (currentYear < 1900 || currentYear > 2025) {
            System.out.println("!currentYear: Current year must be between 1900 and 2025");
            return 0;
        }

        // Validation: currentYear should not be before birthYear
        if (currentYear < birthYear) {
            System.out.println("!currentYear: Current year cannot be before birth year");
            return 0;
        }

        return currentYear - birthYear;
    }

    // Display patient details
    public void displayDetails(int currentYear) {
        System.out.println("=== Patient Details ===");
        System.out.println("Patient ID: " + this.id);
        System.out.println("Patient Name: " + this.name);
        System.out.println("Patient Age: " + this.getAge(currentYear));
        System.out.println("Patient Height (cm): " + this.height);
        System.out.println("Patient Weight (kg): " + this.weight);
        System.out.println("Blood Group: " + this.bloodGroup);
        System.out.println("Phone Number: " + this.phoneNumber);
        System.out.printf("Patient BMI: %.2f%n", this.getBMI());
        System.out.println("======================");
    }
}