public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
  }
}



// 33. Search in Rotated Sorted Array
// https://leetcode.com/problems/search-in-rotated-sorted-array/description/
class Solution {
  public int search(int[] nums, int target) {
      int left = 0; 
      int right = nums.length -1; 

      while(left <= right){
          int mid = left + (right - left) /2; 

          if(nums[mid] == target){
              return mid; 
          } else if (nums[mid] >= nums[left]){
              if(target >= nums[left]  && target < nums[mid]){
                  right = mid -1; 
              } else {
                  left = mid +1; 
              }
          } else {
              if(target <= nums[right] && target > nums[mid]){
                  left = mid + 1; 
              } else {
                  right = mid - 1; 
              }
          }

      }

      return -1; 
  }

  // https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/description/
  // 2958. Length of Longest Subarray With at Most K Frequency
  public int maxSubarrayLength(int[] nums, int k) {

      HashMap<Integer, Integer> map = new HashMap<>();

      int left = -1;
      int n = nums.length; 
      int res = 0; 
      for(int right =0; right < n; right++){
          map.put(nums[right], map.getOrDefault(nums[right], 0) + 1); 

          while(map.get(nums[right]) > k){

              left++; 

              map.put(nums[left], map.get(nums[left]) - 1);

          }

          res = Math.max(res, right - left);
      }

      return res; 


  }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0;
        int j = 0; 
        int startMax = 0, endMin = 0;
    
        List<int[]> ans = new ArrayList<>();
        while(i < firstList.length && j < secondList.length){
            startMax = Math.max(firstList[i][0],secondList[j][0]);
            endMin = Math.min(firstList[i][1],secondList[j][1]);
    
            //you have end greater than start and you already know that this interval is sorrounded with startMin and endMax so this must be the intersection
            if(endMin>=startMax){           
                ans.add(new int[]{startMax,endMin});
            }
    
            //the interval with min end has been covered completely and have no chance to intersect with any other interval so move that list's pointer
            if(endMin == firstList[i][1]) i++;       
            if(endMin == secondList[j][1]) j++;
        }
    
        return ans.toArray(new int[ans.size()][2]);
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {

        int n = nums.length; 
        int[] l = new int[n];
        int[] prev = new int[n];


        Arrays.sort(nums);

        int max = 0;
        int index = -1;

        for(int i=0; i < n; i++){
            l[i] = 1;
            prev[i] = -1;
            for(int j=i-1; j >= 0; j--){
                if(nums[i] % nums[j] == 0 && l[j] + 1 > l[i]){
                    l[i] = l[j] + 1;
                    prev[i] = j; 
                }
            }

            if (l[i] > max){
                max = l[i];
                index = i; 
            }
        }

        List<Integer> res = new ArrayList<>();
        while(index != -1){
            res.add(nums[index]);
            index = prev[index];
        }

        return res; 
    }

    // https://leetcode.com/problems/shortest-path-in-binary-matrix/
    // Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

    // A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

    // All the visited cells of the path are 0.
    // All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
    // The length of a clear path is the number of visited cells of this path.

    private static final int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }

           Queue<int[]> q = new ArrayDeque<int[]>(); 
           grid[0][0] = 1; 
           q.add(new int[]{0,0});

            while(!q.isEmpty()){
                int[] cell = q.remove();
                int row = cell[0];
                int col = cell[1];


                int dist = grid[row][col];
                if(row == grid.length -1 && col == grid[0].length -1) return dist; 


                for(int[] neigh: getNeighboList(grid, row, col)){
                    int neighRow = neigh[0];
                    int neighCol = neigh[1];

                    q.add(new int[]{neighRow, neighCol});
                    grid[neighRow][neighCol] = dist + 1; 
                }
           }

           return -1; 


    }

    public List<int[]> getNeighboList(int[][] grid, int row, int col){

        List<int[]>  neigh = new ArrayList<>();

        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if(newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length || grid[newRow][newCol] != 0){
                continue; 
            }

            neigh.add(new int[]{newRow, newCol});
        }

        return neigh; 
    }


    public int maxDistance(int[] position, int m) { 
        int answer = 0;
        int n = position.length; 
        Arrays.sort(position);

        int low = 1; 
        int high = (int) Math.ceil(position[n-1] / (m - 1.0));

        while(low <= high){
            int mid = low + (high - low) / 2; 
            if(canPlaceBall(mid, position, m)){
                answer = mid; 
                low = mid + 1; 
            } else {
                high = mid - 1; 
            }
        }

        return answer; 

    }

    // https://leetcode.com/problems/magnetic-force-between-two-balls/

    public boolean canPlaceBall(int x, int[] position, int m){

        int prevBallPos = position[0];
        int ballsPlaced = 1; 

        for(int i=1; i < position.length && ballsPlaced <m; i++){
            int currPos = position[i];

            if(currPos - prevBallPos >= x){
                ballsPlaced += 1; 
                prevBallPos = currPos; 
            }
        }

        return ballsPlaced == m; 
    }

    // 1282. Group the People Given the Group Size They Belong To
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        ArrayList<List<Integer>> res = new ArrayList<>();

        Map<Integer, List<Integer>>  map = new HashMap<>(); 

        for(int i=0; i < groupSizes.length; i++){

            int key = groupSizes[i];

            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }               

            List<Integer> g = map.get(key);
            g.add(i);

            if(g.size() == key){
                res.add(g);
                map.remove(key);
            }
        }

        return res;
    }

    // https://leetcode.com/problems/optimal-partition-of-string/
    public int partitionString(String s) {

        int n  = s.length();
        int[] freq = new int[26];

        int left = 0; 
        int count = 0; 
        for(int right=0; right < n; right++ ){
            char rightChar = s.charAt(right);

            freq[rightChar - 'a']++; 

            if(freq[rightChar - 'a'] > 1){
                count++; 
                while(left < right){
                    freq[ s.charAt(left) - 'a']++;

                    left++; 
                }   
            }

        }


        return count + 1; 

    }

    //https://leetcode.com/problems/minimum-substring-partition-of-equal-character-frequency/
    public int minimumSubstringsInPartition(String s) {

        int n = s.length();
        int[] dp = new int[n+1];
        Arrays.fill(dp, n);
        dp[n] = 0;


        for(int i=n -1; i >=0; i--){
            int[] cnt = new int[26];
            int unique=0, maxCnt=0;

            for(int sz=1; i + sz <= s.length(); sz++){
                int index = s.charAt(i + sz -1) - 'a';
                if(++cnt[index] == 1) unique++; 
                maxCnt = Math.max(maxCnt, cnt[index]);

                if(sz == unique * maxCnt){
                    dp[i] = Math.min(dp[i], 1 + dp[i + sz]);
                }
            }
        }

        return dp[0];
    }

    
    // https://leetcode.com/problems/find-the-duplicate-number/
    public int findDuplicate(int[] nums) {

        int low = 1, high = nums.length -1;
        int duplicate = -1;


        while(low <= high){
            int cur = (low + high) / 2;

            int count = 0;
            for(int num: nums){
                if(num <= cur){
                    count++;
                }
            }

            if(count > cur){
                duplicate = cur;
                high = cur -1;
            } else {
                low = cur + 1;
            }
        }

        return duplicate;

    }

    // https://leetcode.com/problems/k-th-smallest-prime-fraction/
    public int[] kthSmallestPrimeFraction(int[] arr, int k){

        int n = arr.length;
        double left = 0, right = 1.0;

        while(left < right){
            double mid = (left + right) / 2;

            double maxFraction = 0.0;
            int totalSmallerFractions = 0, numeratorIdx = 0, denominatorIdx = 0;
            int j = 1;

            for(int i=0; i < n - 1; i++){
                while(j < n && arr[i] >= mid * arr[j]){
                    j++;
                }

                totalSmallerFractions += (n - j);

                if (j == n) break;

                double fraction = (double) arr[i] / arr[j];

                if(fraction > maxFraction){
                    numeratorIdx = i;
                    denominatorIdx = j;
                    maxFraction = fraction;
                }
            }

            if(totalSmallerFractions == k){
                return new int[]{arr[numeratorIdx], arr[denominatorIdx]};
            } else if (totalSmallerFractions > k) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return new int[]{};
    }
}
