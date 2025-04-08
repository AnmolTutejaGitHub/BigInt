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

    BigInt(String data){
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

    public BigInt add(BigInt a,BigInt b){
        BigInt sum = new BigInt("");

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

        if(a.isNeg && b.isNeg) return sum;
        if(a.isNeg || b.isNeg) sum.isNeg = true;
       
        return sum;
    }


    public BigInt subT(BigInt a,BigInt b){
        return null;
    }

}

