/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
       if(head==null || k==0)
       return head;
       ListNode tail = head;
       int len = 1;
       while(tail.next != null){
        tail = tail.next;
        len++;
       }
       k = k%len;
       if (k== 0) return head;
       tail.next = head;
       ListNode newLastNode = head;
       for(int i =1;i<len-k;i++){
        newLastNode = newLastNode.next;

       }
       head = newLastNode.next;
       newLastNode.next = null;
       return head;
    }
        
    }
