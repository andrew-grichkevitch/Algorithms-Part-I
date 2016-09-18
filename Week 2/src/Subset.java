import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]), count = 0;
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            count++;
            String s = StdIn.readString();

            if (queue.size() < k) {
                queue.enqueue(s);
            } else if (StdRandom.uniform() < (double) k / count) {
                queue.dequeue();
                queue.enqueue(s);
            }
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
