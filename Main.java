import java.math.BigInteger;

public class Main {
    public static void main(String[] args){
        // BigInt a  = new BigInt("223479299713707377104774");
        // BigInt b = new BigInt("23498031037483376470171374");
        // BigInt c = new BigInt("-29757438289172398179137737373");
        // BigInt d = new BigInt("-890203707810137103701337139731");

        // BigInt res1 = BigInt.addT(a,b);
        // BigInt res2 = BigInt.addT(c,d);
        // BigInt res3 = BigInt.addT(d,a);
        // BigInt res4 = BigInt.addT(c,a);

        // BigInt res5 = BigInt.subT(a,b);
        // BigInt res6 = BigInt.subT(c,d);
        // BigInt res7 = BigInt.subT(d,a);
        // BigInt res8 = BigInt.subT(c,a);

        // System.out.println(res1.toString());
        // System.out.println(res2.toString());
        // System.out.println(res3.toString());
        // System.out.println(res4.toString());

        // System.out.println(res5.toString());
        // System.out.println(res6.toString());
        // System.out.println(res7.toString());
        // System.out.println(res8.toString());

        BigInteger a = new BigInteger("223479299713707377104774");
        BigInteger b = new BigInteger("23498031037483376470171374");
        BigInteger c = new BigInteger("-29757438289172398179137737373");
        BigInteger d = new BigInteger("-890203707810137103701337139731");
        
       
        BigInteger sum1 = a.add(b);
        BigInteger sum2 = c.add(d);
        BigInteger sum3 = d.add(a);
        BigInteger sum4 = c.add(a);
        
     
        BigInteger diff1 = a.subtract(b);
        BigInteger diff2 = c.subtract(d);
        BigInteger diff3 = d.subtract(a);
        BigInteger diff4 = c.subtract(a);
        
        System.out.println("Sum 1: " + sum1);
        System.out.println("Sum 2: " + sum2);
        System.out.println("Sum 3: " + sum3);
        System.out.println("Sum 4: " + sum4);
        
        System.out.println("Diff 1: " + diff1);
        System.out.println("Diff 2: " + diff2);
        System.out.println("Diff 3: " + diff3);
        System.out.println("Diff 4: " + diff4);
    }
}
