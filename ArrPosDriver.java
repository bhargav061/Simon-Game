/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignments.Assignment_1.Assignment_2;

/**
 *
 * @author Bhargav Patel
 * 3098320
 */
public class ArrPosDriver {

    public static void main(String[] args) {
        PositionalList<Integer> list = new ArrayPositionalList<>();
        PositionalList<Integer> list2 = new ArrayPositionalList<>();

        list.addLast(5);
        list.addFirst(6);
        list.addLast(8);
        list.addLast(15);
        list.addLast(64);
        list.addLast(34);
        list.addLast(12);
        list.addLast(1);
        list.addLast(90);
        
           
        list2.addLast(8);
        list2.addLast(6);
        list2.addLast(7);
        list2.addLast(5);
        list2.addLast(3);
        list2.addLast(0);
        list2.addLast(9);
     
        
        System.out.println("Exercise A: Demonstrate all methods of ArrayPositional List with some initial data items.");
        System.out.println("Solution to Exercise A: ");
        Position<Integer> myCursor = list.first();
        Position<Integer> myPosition = list.last();
        list.addBefore(myCursor,45);
        list.addAfter(myPosition, 25);
        System.out.println("List is: "+list);
        System.out.println("The size of my List is: "+list.size());
        System.out.println("True/False: Is my List empty? \nAns:"+list.isEmpty());
        System.out.println("What is the first element of my list?\nAns: "+list.first());
        System.out.println("What is the last element of my list?\nAns: "+list.last());
        System.out.println("What is After "+ myCursor+ " element?\nAns: "+list.after(myCursor));
        System.out.println("What is Before "+myPosition+ " element?\nAns: "+list.before(myPosition));
        int x = list.set(myCursor, 10);
        System.out.println("\nThe element "+x+" is now "+myCursor);
        System.out.println("\nWhich element was removed?\nAns: "+list.remove(myCursor));
        
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        
        System.out.println("Exercise B: Sort the following:\n a)"+ list+ "\n b)"+list2);
        
        InsertionSort(list);
        System.out.println("Ans: The sorted list for a) is "+list);
       
        InsertionSort(list2);
        System.out.println("Ans: The sorted list for b) is "+list2);
        
    }
    
    /**
     * This method sorts the list
     * @param list the list which is supposed to be sorted.
     */
    
    public static void InsertionSort(PositionalList<Integer> list){
        int n = list.size();
        Position<Integer> first = list.first();
        Position<Integer> after = list.after(first);
        Position<Integer> temp;
        for(int k = 1; k < n; k++){
            if(after != null){
                int current = after.getElement();
                int j = k;
                temp = after;
                
                while( j > 0 && list.before(temp).getElement() > current && temp != null){
                    list.set(temp, list.before(temp).getElement());
                    j--;
                    temp = list.before(temp);
                }
                list.set(temp, current); 
                
                after = list.after(after);
            }
   //         else 
     //           System.exit(0);
        }
    }
}
