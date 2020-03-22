/* Program created by Mark Reinke to solve problem 11 of HW4 in CSCI5806 */

import java.util.ArrayList;
import java.util.Collections;

public class ShowProcessPerms {

    /* This is a helper class that holds onto one permutation of five processes, and stores
    said permutation's runtime.
     */
    private class ProcessPermFive implements Comparable<ProcessPermFive> {
        double averageTime;
        int processOneTime;
        int processTwoTime;
        int processThreeTime;
        int processFourTime;
        int processFiveTime;

        public ProcessPermFive(int processOneTime, int processTwoTime, int processThreeTime, int processFourTime, int processFiveTime) {
            this.processOneTime = processOneTime;
            this.processTwoTime = processTwoTime;
            this.processThreeTime = processThreeTime;
            this.processFourTime = processFourTime;
            this.processFiveTime = processFiveTime;
            this.averageTime = computeAve(processOneTime, processTwoTime, processThreeTime, processFourTime, processFiveTime);
        }

        public double computeAve(int first, int second, int third, int fourth, int fifth) {
            return (5*(double)first + 4*(double)second + 3*(double)third + 2*(double)fourth + (double)fifth) / 5;
        }

        @Override
        public int compareTo(ProcessPermFive o) {
            if(this.averageTime < o.averageTime){
                return -1;
            } else if(this.averageTime == o.averageTime) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public String toString(){
            return "Permutation(" + processOneTime + ", " + processTwoTime + ", " + processThreeTime + ", " + processFourTime + ", " + processFiveTime + "): " + averageTime;
        }
    }

    public static void main(String args[]) {
        int[] processes = {1, 2, 3, 4, 5};
        ShowProcessPerms pperms = new ShowProcessPerms(processes);
        pperms.displayPerms();
    }

    private ArrayList<ProcessPermFive> processPermFives;
    private int[] processes;

    public ShowProcessPerms(int[] processes) {
        this.processes = processes;
        this.processPermFives = new ArrayList<ProcessPermFive>(); // Holds each permutation

        createPerms(processes.length, processes);
        Collections.sort(processPermFives);
    }

    /* This method creates the permutations using Heap's Algorithm */
    private void createPerms(int n, int[] processes){
        if(n == 1){
            ProcessPermFive temp = new ProcessPermFive(processes[0], processes[1], processes[2], processes[3], processes[4]);
            processPermFives.add(temp);
        } else {
            for(int i = 0; i < n-1; i++){
                createPerms(n-1, processes);
                if(n%2 == 0){
                   swap(processes, i, n-1);
                } else {
                    swap(processes, 0, n-1);
                }
            }
            createPerms(n-1, processes);
        }
    }

    /* Helper method to Heap's Algorithm */
    private void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    /* Displays each permutation. */
    public void displayPerms(){
        for(ProcessPermFive perm : processPermFives) {
            System.out.println(perm.toString());
        }
    }
}