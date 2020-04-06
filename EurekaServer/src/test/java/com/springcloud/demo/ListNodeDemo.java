package com.springcloud.demo;

import com.sun.javaws.Main;
import org.bouncycastle.asn1.mozilla.PublicKeyAndChallenge;

import java.util.ArrayList;

/***
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
public class ListNodeDemo {


   public static class ListNode {
       int val;
       ListNode next = null;

       ListNode(int val) {
           this.val = val;
       }
       public void add(int newval) {
           ListNode newNode = new ListNode(newval);
           if(this.next == null)
               this.next = newNode;
           else
               this.next.add(newval);
       }
       public void print() {
           System.out.print(this.val);
           if(this.next != null)
           {
               System.out.print("-->");
               this.next.print();
           }
       }
   }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
       // ListNode newL= reverseListNode(listNode);
        ArrayList<Integer> list=new ArrayList<Integer>();
       // listNode.print();
        while (listNode!= null) {

            list.add(listNode.val);
            listNode = listNode.next;
        }
       return  list;
    }

    public static ListNode reverseListNode(ListNode currentNode){
        if (currentNode == null) {
            return currentNode;
        }

        ListNode nextNode=currentNode.next;
        currentNode.next=null;
        ListNode finalNode=currentNode;
        ListNode temp;
        while (nextNode !=null){
            temp=nextNode.next;
            nextNode.next=finalNode;
            finalNode=nextNode;
            nextNode=temp;
        }

       return finalNode;
    }

    public static void main(String[] args) {
        ListNode ln=new ListNode(1);
        ln.add(2);
        ln.add(3);
        ln.add(4);
        ln.add(5);
       // ln.print();
        ListNode nln=reverseListNode(ln);
       // nln.print();
        ArrayList<Integer> list=printListFromTailToHead(nln);
        for (int i:list){
            System.out.println("ggggg"+i);
        }
    }


}
