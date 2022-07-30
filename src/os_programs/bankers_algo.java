package os_programs;
import java.util.*;
public class bankers_algo {
    public int need[][], allocate[][],max[][],avail[][],np,nr;
    Scanner sc=new Scanner(System.in);
    private void input(){
        //Scanner sc=new Scanner(System.in);
        System.out.println("enter the number of process and resources");
        np=sc.nextInt();
        nr=sc.nextInt();

        System.out.println("Enter the allocate matrix");
        allocate=new int[np][nr];
        max=new int[np][nr];
        need=new int[np][nr];
        avail=new int[1][nr];

        for(int i=0;i<np;i++){
            for(int j=0;j<nr;j++){
                allocate[i][j]=sc.nextInt();
            }
        }
        System.out.println("Enter the max matrix");

        for(int i=0;i<np;i++){
            for(int j=0;j<nr;j++){
                max[i][j]=sc.nextInt();
            }
        }
        System.out.println("Enter the avail matrix");
        for(int j=0;j<nr;j++)
            avail[0][j]=sc.nextInt();

        //sc.close();


    }

    private int[][] calc_need(){
        for(int i=0;i<np;i++){
            for(int j=0;j<nr;j++){
                need[i][j]=max[i][j]-allocate[i][j];
            }
        }
        return need;
    }

    private boolean check(int i){
        for(int j=0;j<nr;j++){
            if(avail[0][j]<need[i][j])
                return false;

        }
        return true;
    }

    public void isSafe(){
        input();
        calc_need();
        boolean[] done=new boolean[np];
        int j=0;
        while(j<np){
            boolean allocated =false;
            for(int i=0;i<np;i++)
                if(!done[i]&&check(i)){
                    for (int k=0;k<nr;k++)
                        avail[0][k]=avail[0][k]-need[i][k]+max[i][k];
                    System.out.println("Process allocated is P"+i);
                    allocated=done[i]=true;
                    j++;
                }
            if(!allocated)break;
        }
        if(j==np)
            System.out.println("All process safely executed");
        else System.out.println("All process cant be allocated safely");

    }

    public static void main(String[] args){
        new bankers_algo().isSafe();
    }

}
