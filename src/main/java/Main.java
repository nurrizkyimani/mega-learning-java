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
}
