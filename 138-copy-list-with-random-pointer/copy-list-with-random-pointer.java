/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution{
    public Node copyRandomList(Node head){
     if(head==null)return null;
     Node temp = head;
   
     while(temp!=null){
          Node newNode = new Node(temp.val);
        newNode.next= temp.next;
        temp.next=newNode;
        temp = newNode.next;
     }
        temp = head;
        while(temp!=null){
            if(temp.random != null){
            temp.next.random = temp.random.next;
            }
            temp = temp.next.next;
        }
            temp = head;
            Node newHead = head.next;
            Node newtemp = newHead;
            while(temp!= null){
              temp.next = newtemp.next;
              temp = temp.next;
              if(temp != null){  
                  newtemp.next = temp.next;
                  newtemp = newtemp.next;
            }
            }
            return newHead;
        }
     }
    