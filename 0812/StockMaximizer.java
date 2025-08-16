import java.util.*;

public class StockMaximizer {
    public int maxProfit(int k, int[] prices) {
        if (prices.length < 2 || k <= 0) return 0;
        List<Integer> profits = new ArrayList<>();

        int i = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) i++;
            int buy = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) i++;
            int sell = prices[i];
            profits.add(sell - buy);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(profits);

        int total = 0;
        while (k-- > 0 && !maxHeap.isEmpty()) total += maxHeap.poll();
        return total;
    }

    public static void main(String[] args) {
        StockMaximizer sm = new StockMaximizer();
        System.out.println(sm.maxProfit(2, new int[]{2,4,1})); // 2
        System.out.println(sm.maxProfit(2, new int[]{3,2,6,5,0,3})); // 7
        System.out.println(sm.maxProfit(2, new int[]{1,2,3,4,5})); // 4
    }
}