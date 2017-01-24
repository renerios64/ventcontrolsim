public class IpAddressValidator {
    public IpAddressValidator(){

    }

    public boolean ValidateIpAddress (String ipAddress){
        return ValidateIpAddressIsNotDefault(ipAddress) &&
                ValidateIpAddressHaveFourSubNets(ipAddress) &&
                ValidateIpAddressHaveAtMostThreeDigitsInSpace(ipAddress);
    }

    private boolean ValidateIpAddressIsNotDefault(String data) {
        return data != "Enter IP Address here!";
    }

    private boolean ValidateIpAddressHaveFourSubNets(String data){
        String[] workingData = SeparateNets(data);
        return workingData.length == 4;
    }

    private boolean ValidateIpAddressHaveAtMostThreeDigitsInSpace(String data){
        boolean returnVal = true;
        String[] workingData = SeparateNets(data);
        for(String net: workingData){
            if (Integer.valueOf(net) > 999){
                return false;
            }
        }
        return returnVal;
    }

    private String[] SeparateNets(String ipAddress){
        return ipAddress.split("\\.");
    }


}
