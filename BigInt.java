class Node{
    int data;
    Node prev;
    Node next;
    Node(int data){
        this.data = data;
    }
}

public class BigInt{
    Node head = null;
    Node tail = null;
    int size = 0;
    boolean isNeg = false;

    public BigInt() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.isNeg = false;
    }

    public BigInt(String data){
        if (!checkValid(data)) throw new IllegalArgumentException(data);
        this.head = construct(data);
    }



    private boolean checkValid(String data){
        int sign = data.charAt(0);
        int i = 0;
        if(sign=='-') {
            i=1;
            this.isNeg = true;
        }

        while(i<data.length()){
            char digit = data.charAt(i);
            if(!Character.isDigit(digit)) return false;
            i++;
        }
        return true;
    }

    private Node construct(String data) {
        Node dummy = new Node(-1);
        this.tail = dummy;
        int i = isNeg ? 1 : 0;
    
        for (; i < data.length(); i++) {
            int digit = data.charAt(i) - '0';
            Node node = new Node(digit);
            node.prev = tail;
            tail.next = node;
            tail = node;
            this.size++;
        }
    
        if (dummy.next != null) dummy.next.prev = null;
        return dummy.next;
    }

    public static BigInt addT(BigInt a,BigInt b){
        BigInt res = null;
        if(!a.isNeg && !b.isNeg) res=add(a,b);
        else if(a.isNeg && b.isNeg) {
            res=add(a,b);
            res.isNeg = true;
        }
        else if(a.isNeg && !b.isNeg) {  // -a+b = b-a
            res=subT(b,a);
        }
        else if(!a.isNeg && b.isNeg) res=subT(a,b); // a+(-b) = a-b

        return res;
    }

    public static BigInt subT(BigInt a , BigInt b){
        boolean agteb = false;// abs
        BigInt res = null;
        if(a.size > b.size) {
            agteb = true;
        } else if(a.size == b.size && a.head != null) {
            Node tempA = a.head;
            Node tempB = b.head;
            
          
            while(tempA != null && tempB != null) {
                if(tempA.data > tempB.data) {
                    agteb = true;
                    break;
                } else if(tempA.data < tempB.data) {
                    agteb = false;
                    break;
                }
                tempA = tempA.next;
                tempB = tempB.next;
            }
            
           
            if(tempA == null && tempB == null) {
                agteb = true;
            }
        }

        if(agteb) {
            if(!a.isNeg && !b.isNeg) {
                res = sub(a,b); // a-b , a>=b , a>=0 && b>=0
                res.isNeg = false;
            }
            else if(b.isNeg && !a.isNeg) { // 
                res = add(a,b);
                res.isNeg = false;
            }
            else if(a.isNeg && !b.isNeg){ // a=-12 b=10 // -a-b = -(a+b)
                res = add(a,b);
                res.isNeg = true;
            }
            else if(a.isNeg && b.isNeg){ // a=-12,b=-10 // -a-(-b) = -a+b = -2 // 12-10 = 2 , -2 neg
                res = sub(a,b);
                res.isNeg = true;
            }
        }
        else { //a<b
            if(!a.isNeg && !b.isNeg) {
                res = sub(b,a); // a=10,b=12 // b-a
                res.isNeg = true;
            }
            else if(b.isNeg && !a.isNeg) { // a = 10 b=-12 // a-(-b) = a+b
                res = add(a,b);
                res.isNeg = false;
            }
            else if(a.isNeg && !b.isNeg){ // a=-10 b=12 // -a-b = -(a+b)
                res = add(a,b);
                res.isNeg = true;
            }
            else if(a.isNeg && b.isNeg){  // a=-10 b=-12 // (-a)-(-b) = -a+b = b-a // 12-10=2 
                res = sub(b,a);
                res.isNeg = false;
            }
        }

        return res;
    }
    private static BigInt add(BigInt a,BigInt b){ // a,b +ve
        BigInt sum = new BigInt();

        Node tempA = a.tail;
        Node tempB = b.tail;
        int carry = 0;

        while (tempA != null || tempB != null || carry != 0) {
            int digitA = (tempA != null) ? tempA.data : 0;
            int digitB = (tempB != null) ? tempB.data : 0;
    
            int total = digitA + digitB + carry;
            int digit = total % 10;
            carry = total / 10;
    
            Node node = new Node(digit);
            if (sum.head == null) {
                sum.head = node;
                sum.tail = node;
            } else {
                node.next = sum.head;
                sum.head.prev = node;
                sum.head = node;
            }
    
            sum.size++;
    
            if (tempA != null) tempA = tempA.prev;
            if (tempB != null) tempB = tempB.prev;
        }
       
        while (sum.head != null && sum.head.data == 0 && sum.head != sum.tail) {
            sum.head = sum.head.next;
            sum.head.prev = null;
            sum.size--;
        }

        return sum;
    }



    private static BigInt sub(BigInt a, BigInt b) {// a-b , a>=b , a>=0 && b>=0
        BigInt diff = new BigInt(); 
        Node tempA = a.tail;
        Node tempB = b.tail;
    
        int borrow = 0;
    
        while (tempA != null || tempB != null) {
            int digitA = (tempA != null) ? tempA.data : 0;
            int digitB = (tempB != null) ? tempB.data : 0;
    
            int sub = digitA - digitB - borrow;
    
            if (sub < 0) {
                borrow = 1;
                sub += 10;
            } else  borrow = 0;
    
            Node node = new Node(sub);
            if (diff.head == null) {
                diff.head = node;
                diff.tail = node;
            } else {
                node.next = diff.head;
                diff.head.prev = node;
                diff.head = node;
            }
    
            diff.size++;
    
            if (tempA != null) tempA = tempA.prev;
            if (tempB != null) tempB = tempB.prev;
        }
    
        while (diff.head != null && diff.head.data == 0 && diff.head != diff.tail) {
            diff.head = diff.head.next;
            diff.head.prev = null;
            diff.size--;
        }
    
        return diff;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isNeg) sb.append('-');
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data);
            curr = curr.next;
        }
    return sb.toString();
}

}

