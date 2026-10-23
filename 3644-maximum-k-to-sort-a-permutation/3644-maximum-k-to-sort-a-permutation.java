class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                k |= i;
                k |= nums[i];
            }
        }

        return k == 0 ? 0 : Integer.lowestOneBit(k) == 0 ? 0 : getAnd(nums);
    }

    private int getAnd(int[] nums) {
        int result = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                result &= nums[i] & i;
            }
        }

        return result;
    }
}