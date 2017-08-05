import java.util.Arrays;

public class CalcIP {

    public static char getClass(String ip)
    {
        String[] parts = ip.split("\\.");

        int i = Integer.parseInt(parts[0]);

        int[] bits = consertBitStringToInt(Integer.toBinaryString(i));

        if(bits.length > 8) {
            System.out.println(ip);
            return 'F';
        }

        if(bits[0] == 0)
            return 'A';
        if(bits[1] == 0)
            return 'B';
        if(bits[2] == 0)
            return 'C';
        if(bits[3] == 0)
            return 'D';

        return 'E';
    }

    public static String info(String ip)
    {
        String[] parts = ip.split("\\.");
        switch (getClass(ip))
        {
            case 'A':
                return "Class:A Networks: " + (int) Math.pow(2,7) + " Hosts: " + ((int) Math.pow(2,24)-2) + " Net-IP: " + parts[0] + ".0.0.0 Netmask: 255.0.0.0";
            case 'B':
                return "Class:B Networks: " + (int) Math.pow(2,14) + " Hosts: " + ((int) Math.pow(2,16)-2) + " Net-IP: " + parts[0] + "." + parts[1] + ".0.0 Netmask: 255.255.0.0";
            case 'C':
                return "Class:c Networks: " + (int) Math.pow(2,21) + " Hosts: " + ((int) Math.pow(2,8)-2) + " Net-IP: " + parts[0] + "." + parts[1] + "." + parts[2] +  ".0 Netmask: 255.255.255.0";
            case 'D':
                return "Class:D Multicast";
            case 'E':
                return "Class:E Reserved";
            default:
                return "Not a valid ip!";
        }
    }

    public static boolean isValidIP(String ip)
    {
        String[] parts = ip.split("\\.");

        return parts.length == 4 && Integer.parseInt(parts[0]) < 256 && Integer.parseInt(parts[1]) < 256
                && Integer.parseInt(parts[2]) < 256 && Integer.parseInt(parts[3]) < 256;

    }

    private static int[] consertBitStringToInt(String bitString) {
        int size = bitString.length();

        if(size > 8) {
            return new int[9];
        }

        int[] out = new int[size];
        char[] in = bitString.toCharArray();

        for (int i = 0; i < 8 - size; i++) {
            out[i] = 0;
        }

        for (int i = 8 - size; i < size; i++) {
            out[i] = Integer.parseInt(Character.toString(in[i]));
        }
        return out;
    }

    public static void main(String[] args) {
        System.out.println(getClass("10.0.27.1"));
        System.out.println(getClass("172.16.27.1"));
        System.out.println(getClass("192.168.27.1"));
        System.out.println(getClass("230.168.27.1"));
        System.out.println(getClass("250.168.27.1"));
        System.out.println(getClass("450.168.27.1"));
        System.out.println(isValidIP("450.168.27.1"));
        System.out.println(isValidIP("10.0.27.1"));


        System.out.println(info("172.16.27.1"));
    }

}
