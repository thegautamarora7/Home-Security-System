import java.util.*;

// Class representing the Security System
class SecuritySystem {
    private String currentMode;
    private boolean systemStatus;
    private List<User> users;
    private MonitoringService monitoringService;

    public SecuritySystem() {
        currentMode = "Home"; // Default mode
        systemStatus = false; // System is initially off
        users = new ArrayList<>();
        monitoringService = new MonitoringService();
    }

    // Set the mode for the security system
    public void setMode(String mode) {
        if (!mode.equals("Home") && !mode.equals("Away") && !mode.equals("Night")) {
            throw new IllegalArgumentException("Invalid mode!");
        }
        currentMode = mode;
        systemStatus = true;
        System.out.println("System set to " + mode + " mode.");
    }

    // Trigger alarm if suspicious activity is detected
    public void triggerAlarm() {
        if (systemStatus) {
            Alarm alarm = new Alarm();
            alarm.activate();
            monitoringService.notifyAuthorities();
        } else {
            throw new IllegalStateException("System is not armed!");
        }
    }

    // Authenticate the user
    public boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        throw new SecurityException("Authentication failed!");
    }

    public void addUser(User user) {
        users.add(user);
    }

    public String getCurrentMode() {
        return currentMode;
    }

    public boolean getSystemStatus() {
        return systemStatus;
    }
}

// Class representing a User
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

// Class representing an Alarm
class Alarm {
    public void activate() {
        System.out.println("Alarm triggered!");
    }
}

// Class representing the Monitoring Service
class MonitoringService {
    public void notifyAuthorities() {
        System.out.println("Notifying authorities...");
    }
}

// Class representing a Sensor
class Sensor {
    private boolean motionDetected;

    public Sensor() {
        motionDetected = false;
    }

    // Simulate detecting motion
    public void detectMotion() {
        motionDetected = true;
        System.out.println("Motion detected!");
    }

    public boolean isMotionDetected() {
        return motionDetected;
    }

    public void reset() {
        motionDetected = false;
    }
}

// Main class to simulate the system
public class HomeSecuritySystem {
    public static void main(String[] args) {
        // Create the security system
        SecuritySystem securitySystem = new SecuritySystem();
        
        // Create users and add them to the system
        User user1 = new User("admin", "password123");
        securitySystem.addUser(user1);

        // Simulate user login and authentication
        try {
            if (securitySystem.authenticateUser("admin", "password123")) {
                System.out.println("User authenticated!");
            }
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }

        // Setting the mode to Away
        try {
            securitySystem.setMode("Away");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Create and test the sensor and alarm system
        Sensor sensor = new Sensor();
        try {
            sensor.detectMotion();
            if (sensor.isMotionDetected()) {
                securitySystem.triggerAlarm();
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // Simulate the deactivation of the system (for example, during "Home" mode)
        try {
            securitySystem.setMode("Home");
            sensor.reset();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
