package solutions;

import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {
    public Integer solve(int k, int[] cookies) {

        Queue<Integer> newCookies = new PriorityQueue<>();

        for (int cookie : cookies) {
            newCookies.offer(cookie);
        }

        int steps = 0;
        int currentMinSweetness = newCookies.peek();
        while (currentMinSweetness < k && newCookies.size() > 1) {
            int leastSweetCookie = newCookies.poll();
            int secondLeastSweetCookie = newCookies.poll();

            int combinedSweetness =  leastSweetCookie + 2 * secondLeastSweetCookie;

            newCookies.add(combinedSweetness);

            currentMinSweetness = newCookies.peek();
            steps++;
        }

        return currentMinSweetness > k ? steps : -1;
    }
}
