import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), res);
        return res;
        }
    
    private void dfs(int[] nums, int start, int remain, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            int v = nums[i];
            if (v > remain) break;        // pruning
            path.add(v);
            dfs(nums, i, remain - v, path, res); // i: can reuse same number
            path.remove(path.size() - 1);
        }
    }
}
