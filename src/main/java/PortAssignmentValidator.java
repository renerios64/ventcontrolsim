public class PortAssignmentValidator {
    public PortAssignmentValidator(){

    }

    public boolean ValidatePortAssignment(String port){
        return ValidatePortAssignmentIsNotDefault(port) &&
                ValidatePortIsLessThan65535(port) &&
                ValidatePortAssignmentIsPositive(port);
    }

    private boolean ValidatePortAssignmentIsNotDefault(String data) {
        return !data.equals("Enter Port here!");
    }


    private boolean ValidatePortAssignmentIsPositive(String data) {
        return Integer.valueOf(data) > 0;
    }

    private boolean ValidatePortIsLessThan65535(String data) {
        try {
            int workingData = Integer.valueOf(data);
            return workingData <= 65535;
        }catch (NumberFormatException e){
            return false;
        }
    }
}


