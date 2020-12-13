import java.util.ArrayList;

public class Solution {
    int[] permutation;
    int n;
    int size;
    int eval;
    int sumWeight;

    Solution(int n) {
        this.n = n;
        permutation = new int[n];
        for (int i = 0; i < n; i++)
            permutation[i] = -1;
        size = 0;
        eval = 0;
        sumWeight = 0;
    }


    public void show() {
        for (int j = 0; j < this.n; j++) {
            if (this.permutation[j] != -1)
                System.out.print(this.permutation[j] + " ");
            else
                System.out.print("- ");
        }
        System.out.println();
        System.out.print("Size: " + this.size + " ");
        System.out.print("Eval: " + this.eval + " ");
        System.out.println("Weight Sum: " + this.sumWeight);

    }

    public ArrayList<Integer> get() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int j = 0; j < this.n; j++) {
            if (this.permutation[j] != -1)
                arrayList.add(this.permutation[j]);
        }
        /*System.out.println("ArrayList: " + arrayList);
        System.out.print("Size: " + this.size + " ");
        System.out.print("Eval: " + this.eval + " ");
        System.out.println("Weight Sum: " + this.sumWeight);*/
        return arrayList;
    }


    public void setList(int[] permutation) {
        for (int i = 0; i < n; i++)
            this.permutation[i] = permutation[i];
    }


    public void setListPosition(int pos, int val) {
        this.permutation[pos] = val;
    }


    public void copy(Solution s) {
        this.setList(s.permutation);
        this.size = s.size;
        this.eval = s.eval;
        this.sumWeight = s.sumWeight;
    }


    public Solution clone(Solution s) {
        Solution s1 = new Solution(s.n);
        s1.setList(s.permutation);
        s1.size = s.size;
        s1.eval = s.eval;
        s1.sumWeight = s.sumWeight;
        return s1;
    }


}
