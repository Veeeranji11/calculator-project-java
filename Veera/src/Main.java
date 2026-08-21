import java.sql.SQLOutput;
import java.util.*;
public class Main{
    public static void main(String[] args){
        int[] a=new int[5];
        Scanner s=new Scanner(System.in);
        int temp;
        System.out.println("enter the elements");
        for(int i=0;i<a.length;i++)
        {
            a[i]=s.nextInt();
        }
        for(int i=0;i<a.length-1;i++)
        {
            for(int j=i+1;j<a.length;j++) {
                if (a[i] > a[j]) {
                    temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }

            }
        }
        for(int i:a)
        {
            System.out.println(i);
        }
    }
}