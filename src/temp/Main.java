package temp;//package temp;

import com.sun.xml.internal.fastinfoset.util.CharArray;
import org.omg.PortableInterceptor.INACTIVE;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap<>((int) (Math.min(nums1.length,nums2.length)/0.75+1));
        int[] a,b;
        if (nums1.length < nums2.length) {
            a = nums1;
            b = nums2;
        } else {
            a = nums2;
            b = nums1;
        }

        for (int i=0; i<a.length; i++) {
          map.put(a[i],map.getOrDefault(a[i],1));
        }

        List<Integer> list = new ArrayList();
        for (int i=0; i<b.length; i++) {
            if (map.getOrDefault(b[i],0) != 0) {
                map.put(b[i],map.get(b[i])-1);
                list.add(b[i]);
            }
        }
        int[] r = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            r[i] =list.get(i);
        }
        Arrays.sort(r);
        return r;
    }
    public int[] plusOne(int[] digits) {
        for (int i=digits.length-1; i>=0; i--) {
            if (digits[i] == 9){
                digits[i] = 0;
            }else {
                digits[i] += 1;
                return digits;
            }
        }
        return new int[digits.length+1];
    }
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap((int) (nums.length/0.75+1));
        for (int i=0; i<nums.length; i++) {
            map.put(nums[i],i);
        }
        int[] result = new int[2];
        for (int i=0; i<nums.length; i++) {
            if (map.containsKey(target-nums[i])){
                result[0] = i;
                result[1] = map.get(target-nums[i]);
            }
        }
        return result;
    }
     static public class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<TreeNode> cur = new ArrayList(), pre = new ArrayList();
        cur.add(root);
        //添加主节点
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        result.add(list);

        while (cur.size() > 0) {
            list = new ArrayList<>();
            List tmp = pre;
            pre = cur;
            cur = tmp;
            cur.clear();

            for (int i=0; i<pre.size(); i++) {
                if (pre.get(i).left != null) {
                    cur.add(pre.get(i).left);
                    list.add(pre.get(i).left.val);
                }
                if(pre.get(i).right != null) {
                    cur.add(pre.get(i).right);
                    list.add(pre.get(i).right.val);
                }
            }
            if(list.size() > 0){
                result.add(list);
            }

        }
        Random rand = new Random();
        return result;
    }
}
//
//import org.omg.Messaging.SYNC_WITH_TRANSPORT;
//
//import java.util.Scanner;
//
///**
// * 采用排列树穷举
// */
//public class Main {
//    static int m;
//    static Node[] arr;
//    static int max;
//    static class Node{
//        int x,y;
//        int w;// 0 未取， 1 a,2 b
//        Node(int x,int y){
//            this.x = x;
//            this.y = y;
//        }
//    }
//
//    static void getTeamScore(){
//        int a=0,b=0,t=0;
//        for (int i=0; i<m; i++){
//            if (arr[i].w != 0){
//                t+=arr[i].y;
//                if(arr[i].w == 1){
//                    a += arr[i].x;
//                }
//                if(arr[i].w == 2){
//                    b += arr[i].x;
//                }
//            }
//        }
//        if (a == b && a!=0){
//            if (max < t){
//                max = t;
//            }
//        }
//    }
//
//    static void cal(int t){
//        System.out.println(t);
//        if(t>=m){
//            getTeamScore();
//            return;
//        }
//        cal(t+1);
//        arr[t].w = 1;
//        cal(t+1);
//        arr[t].w = 2;
//        cal(t+1);
//        arr[t].w = 0;
//    }
//
//    public static void main(String[] args) {
////
////        // 录入数据
////        Scanner in = new Scanner(System.in);
////
////        m = Integer.valueOf(in.nextLine());
////        arr = new Node[m];
////
////        for (int i=0; i<m; i++){
////            System.out.println(i);
////            int x = in.nextInt();
////            int y = in.nextInt();
////            arr[i] = new Node(x,y);
////        }
////        cal(0);
////        System.out.println(max);
////    }
////}

public class Main{

    static class Node{
        int start,end;
        Node(int start,int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return start + "," + end;
        }
    }
    public List<List<Integer>> generate(int numRows) {

        return new java.util.AbstractList<List<Integer>>() {

            @Override
            public List<Integer> get(int index) {
                return new java.util.AbstractList<Integer>() {
                    int c = 1,i=index;
                    @Override
                    public Integer get(int index) {
                        if(index != 0) {
                            c = c *  (i-index+1)/ index;
                        }
                        return c;
                    }
                    @Override
                    public int size() {
                        return i;
                    }
                };
            }

            @Override
            public int size() {
                return numRows;
            }
        };
    }
    public boolean isValid(String s) {
        char[] stack= new char[s.length()];
        int i=0;
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack[i++] = ch;
            } else {
                if (i > 0 && (stack[i-1] == ch-2 || stack[i-1] == ch-1 )) {
                    i--;
                } else {
                    return false;
                }
            }
        }
        return i == 0;
    }
    public int romanToInt(String s) {
        int c = 0;
        char[] ch = s.toCharArray();
        c = romanItr(ch[ch.length-1],' ');
        for (int i=ch.length-2; i>=0; i--) {
            c += romanItr(ch[i],ch[i+1]);
            System.out.println(c);
        }
        return c;
    }
    private int romanItr(char cur,char pre) {
        switch (cur) {
            case 'I': if (pre == 'V' || pre == 'X') return -1; return 1;
            case 'V': return 5;
            case 'X': if (pre == 'L' || pre == 'C') return  -10; return 10;
            case 'L': return 50;
            case 'C': if (pre == 'D' || pre == 'M') return -100; return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        int len = nums.length;
        List<List<Integer>> list = new ArrayList<>();

        for (int i=0; i < len && nums[i] <= 0; i++) {
            if (i != 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int j = i+1;
            int k = len-1;

            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> l = new ArrayList();
                    l.add(nums[i]);
                    l.add(nums[j]);
                    l.add(nums[k]);
                    if (!(list.size() > 0 && list.get(list.size()-1).equals(l)))
                        list.add(l);
                    k--;
                    j++;
                } else if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return list;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new ArrayList();
        Map<String, List<String>> map = new HashMap();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            if (map.containsKey(s)) {
                map.get(s).add(str);
            } else {
                List tmp = new ArrayList();
                tmp.add(str);
                list.add(tmp);
                map.put(s,tmp);
            }
        }
        return list;
    }

//    public int lengthOfLongestSubstring(String s) {
//        Map<Character,Integer> map = new HashMap<>();
//        int max = 0;
//        int pre = 0;
//        int cur = 0;
//
//        for (int i=0; i < s.length(); i++) {
//
//            if (map.getOrDefault(s.charAt(i),0) == 1) {
//                int j = pre;
//                for (; s.charAt(j) != s.charAt(i); j++) {
//                    map.put(s.charAt(j),0);
//                    cur--;
//                }
//                pre = j+1;
//            } else {
//                map.put(s.charAt(i),1);
//                cur++;
//            }
//
//            if(max < cur) {
//                max = cur;
//            }
//        }
//        return max;
//    }

    public int lengthOfLongestSubstring(String s) {
        int[] idx = new int[128];
        int max = 0;
        int pre = 0;
        for (int i=0; i< s.length(); i++) {
            if (idx[s.charAt(i)] > pre) {
                pre = idx[s.charAt(i)];
            }
            idx[s.charAt(i)] = i+1;
            if (i-pre+1 > max) {
                max = i - pre +1;
            }
        }
        return max;
    }

    class LRUCache {

        Integer[] queue = null;
        int cur = 0;
        int capacity = 0;

        class Value<T>{
            int idx;
            T data;
        }
        Map<Integer,Value<Integer>> cache = null;

        //
        protected boolean setValueToQueue(Value<Integer> value){
            if (value.idx == -1) {
                queue[cur] = value.data;
                value.idx = cur;
                cur = (cur + 1)%capacity;
            } else {

            }
            return true;
        }

        public LRUCache(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException();
            }
            queue  = new Integer[capacity];
            cache = new HashMap<>(capacity);
            this.capacity = capacity;
        }
        public int get(int key) {
            Value<Integer> value = cache.get(key);
            if (value == null) {
                return -1;
            }
            return 0;
        }

        public void put(int key, int value) {

        }
    }

    class LRUCache1 {

        Map<Integer,Integer> cache;
        public LRUCache1(int capacity) {
            cache = new LinkedHashMap<Integer, Integer>(capacity,0.75f,true){
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
                    if(size() > capacity) {
                        return true;
                    }
                    return false;
                }
            };

        }

        public int get(int key) {
            return cache.getOrDefault(key,-1);
        }

        public void put(int key, int value) {
            cache.put(key,value);
        }
    }

    //最长回文子串
    public static String longestPalindrome(String s) {
        if(s.length()== 0) return s;
        int left = 0;
        int right = 0;
        for (int i=0; i<s.length(); i++) {
            int l = i;
            int r = i;
            for (; r+1<s.length() && s.charAt(i)==s.charAt(r+1); r++);
            for (; l>0 && r+1<s.length() && s.charAt(l-1) == s.charAt(r+1);r++,l--);
            if (r-l > right - left) {
                left = l;
                right = r;
            }
            if(r != i)//兼容i++
                i = r-1;
        }
        return s.substring(left,right+1);
//        StringBuilder str = new StringBuilder();
//
//        // 补为奇数个字符
//        str.append('#');
//        for (int i=0; i<s.length(); i++){
//            str.append(s.charAt(i));
//            str.append('#');
//        }
//
//        int len = str.length();
//        int[] count = new int[len];
//        int max = 0;//最大值
//        int mid = 0;//最大值的字符串中心
//        int right = 0;//最近回文字符串的最右端
//        int rightMid = 0; //最近回文字符串的中心
//        for (int i=0; i<len; i++) {
//            //当当前节点在回文字符串内部，说明对称节点有一个相同的的节点，并且该节点的最长回文串已计算，当该节点的最长回文串未超出回文串内部，则对称相同
//            if (i<right && count[(rightMid<<1)-i]+i < right) {
//                count[i] = count[(rightMid<<1)-i];
//                continue;
//            }
//            // 否则 重新计算
//            for (int j=i; j<len && (i<<1)-j>=0 && str.charAt(j)==str.charAt((i<<1)-j); count[i]++,j++);
//            if (count[i] > max) {
//                max = count[i];
//                mid = i;
//            }
//            // 修改最近最右的回文串
//            if(count[i] > 1 && mid + max -1 > right) {
//                right = mid+max -1;
//                rightMid = mid;
//            }
//        }
//        max = max - 1;
//        return s.substring((mid-max)>>1,((mid-max)>>1)+max);
    }

    // 递增三元子序列
    public boolean increasingTriplet(int[] nums) {
        int mid = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] > mid) {
                return true;
            }
            if (nums[i] > min && nums[i] < mid) {
                mid = nums[i];
            }

            if (i+1<nums.length && nums[i] < nums[i+1] && nums[i] < min && nums[i+1] <= mid) {
                min = nums[i];
                mid = nums[i+1];
            }
        }
        return false;
    }


    static int[] arr ;
    protected int get1(int n){
        if (arr[n-1] == 0) {
            arr[n-1] = get1(n-1) + get1(n-2);
        }
        return arr[n-1];
    }
    public int get(int n){
        arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;
        return get1(n);
    }
     static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
     TreeNode(int x) { val = x; }
    }
    // 二叉树锯齿遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<List<Integer>> list = new ArrayList();
        if (root == null) return list;

        ArrayDeque<TreeNode> odd = new ArrayDeque();
        ArrayDeque<TreeNode> even = new ArrayDeque();
        odd.push(root);
        TreeNode cur;

        while(odd.size() > 0 || even.size() > 0) {
            List<Integer> it = new ArrayList(odd.size());

            while (odd.size() > 0) {
                cur = odd.pop();
                it.add(cur.val);
                if (cur.left != null) {
                    even.push(cur.left);
                }
                if (cur.right != null) {
                    even.push(cur.right);
                }
            }
            if (it.size() > 0) {
                list.add(it);
            }

            it = new ArrayList(even.size());

            while (even.size() > 0) {
                cur = even.pop();
                it.add(cur.val);
                if (cur.right != null) {
                    odd.push(cur.right);
                }
                if (cur.left != null) {
                    odd.push(cur.left);
                }
            }
            if(it.size() > 0) {
                list.add(it);
            }
        }

        return list;
    }
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return createTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    protected static TreeNode createTree(int[] preorder, int i, int j, int[] inorder, int m, int n) {
        if (i > j || m > n) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[i]);

        int k=m;
        for (; k <= n && preorder[i] != inorder[k]; k++);

        node.left = createTree(preorder, i+1, i+n-k, inorder, m, k-1);
        node.right = createTree(preorder,i+n-k+1,j, inorder,k+1, n);

        return node;
    }

    static final char[][] map = {
            {'a','b','c'},
            {'d','e','f'},
            {'g','h','i'},
            {'j','k','l'},
            {'m','n','o'},
            {'p','q','r','s'},
            {'t','u','v'},
            {'w','x','y','z'}
    };
    // 电话号码的字母组合
    public static List<String> letterCombinations(String digits) {
        return new AbstractList<String>() {

            @Override
            public String get(int index) {
                StringBuilder builder = new StringBuilder();
                int idx;
                for (int i=0; i<digits.length(); i++) {
                    idx = digits.charAt(i)-'2';
                    builder.append(map[idx][index % map[idx].length]);
                    index /= map[idx].length;
                }
                return builder.toString();
            }

            @Override
            public int size() {
                int size = 1;
                for (int i=0; i<digits.length(); i++) {
                    size *= map[digits.charAt(i)-'2'].length;
                }
                return size == 1 ? 0 : size;
            }
        };
    }

    //子集
//    boolean[] mark;
//    int[] nums;
//    int n;
//    List<List<Integer>> list = new ArrayList();
//
//    public List<List<Integer>> subsets(int[] nums) {
//        n = nums.length;
//        mark = new boolean[n];
//        this.nums = nums;
//        helper(0);
//        return list;
//    }
//    void helper(int t) {
//        if (t == n) {
//            List<Integer> it = new ArrayList();
//            for (int i=0; i<n; i++) {
//                if(mark[i]) {
//                    it.add(nums[i]);
//                }
//            }
//            list.add(it);
//            return ;
//        }
//        mark[t] = true;
//        helper(t+1);
//        mark[t] = false;
//        helper(t+1);
//    }
    boolean[][] mark;
    int m;
    int n;
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        mark = new boolean[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfs(char[][] board, int i, int j, String word, int t) {
        if (!(i>=0 && j>=0 && i<m && j<n && board[i][j] == word.charAt(t) && mark[i][j] == false)) {
            return false;
        }
        if( t== word.length()-1) {
            return true;
        }
        mark[i][j] = true;
        if (dfs(board,i-1,j,word,t+1)) return true;
        if (dfs(board,i,j-1,word,t+1)) return true;
        if (dfs(board,i+1,j,word,t+1)) return true;
        if (dfs(board,i,j+1,word,t+1)) return true;
        mark[i][j] = false;
        return false;
    }
    //前K个高频元素
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i:nums){
            if (min > i) {
                min = i;
            }
            if (max < i) {
                max = i;
            }
        }
        int[] count = new int[max-min+1];
        for (int i: nums){
            count[i-min]++;
        }

        class Node {
            int x;
            int c;
            public Node(int x,int c) {
                this.x = x;
                this.c = c;
            }
        }
        MinHeap<Node> heap = new MinHeap<>(k, Comparator.comparingInt(o -> o.c));

        for (int i=0; i<count.length; i++) {
            if(count[i] > 0)
                heap.add(new Node(i+min,count[i]));
        }
        Arrays.stream(heap.arr).forEach(e->list.add(((Node)e).x));
        return list;
    }
    public int findKthLargest(int[] nums, int k) {
        MinHeap<Integer> heap = new MinHeap<>(k,Comparator.comparingInt(e->e));
        for (int i : nums) {
            heap.add(i);
        }
        return (int)heap.arr[k-1];
    }
    class MinHeap<T>{
        Object[] arr;
        int n;
        int size=0;
        Comparator<T> comp;
        public MinHeap(int initialCapacity,Comparator<T> comparator){
            if(initialCapacity < 0) {}
            arr = new Object[initialCapacity];
            n = initialCapacity;
            this.comp = comparator;
        }
        public void add(T e){
            if (size < n) {
                arr[size++] = e;
            } else {
                if (comp.compare(e,(T)arr[size-1]) > 0) {
                    arr[size-1] = e;
                } else{
                    return;
                }
            }
            adjust(size-1);
        }
        protected void adjust(int i){
            int l = size - ((size-i-1)*2+1) -1,min = i;
            if (l >= 0 && comp.compare((T)arr[min],(T)arr[l]) > 0) {
                min = l;
            }
            if (l-1 >= 0 && comp.compare((T)arr[min],(T)arr[l-1]) > 0) {
                min = l-1;
            }
            if(min != i) {
                swap(min,i);
                adjust(min);
            }
        }
        protected void swap(int i, int j) {
            Object tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
    public int[] searchRange(int[] nums, int target) {

        int i=0, j = nums.length-1, mid;
        int l=-1, r=-1;

        while (i < j) {
            mid = (i+j)/2;

            if (nums[mid] == target) {
                break;
            }
            if (nums[mid] > target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        mid = (i+j)/2;
        if (mid <nums.length && nums[mid] == target) {
            int k = mid;
            int m;
            while (i < k) {
                m = (i+k)/2;
                if (nums[m] == target) {
                    k = m;
                } else {
                    i = m+1;
                }
            }
            l = i;
            k = mid;
            while (k < j) {
                m = (j+k)/2;
                if (nums[m] == target) {
                    k = m+1;
                } else {
                    j = m-1;
                }
            }
            r = nums[j] == target ? j : j-1;
        }
        return new int[]{l,r};
    }
    // 路径
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        int s = m > n ? n : m;
        int b = m > n ? m : n;
        int count = 1;
        int c= 1;
        for (int i = 1; i < s; i++) {
            c *= i;
            count *= b+s-1-i;
        }
        return count/c;
    }

    // 分数转小数
    public String fractionToDecimal(int numerator, int denominator) {
        if(denominator == 0) return "NaN";
        if(numerator == 0) return "0";
        int n = greatestCommonDivisor(numerator,denominator);
        long a = Math.abs((long)numerator/n);
        long b = Math.abs((long)denominator/n);
        StringBuilder s = new StringBuilder();
        if ((numerator ^ denominator)< 0) {
            s.append('-');
        }
        s.append(a/b);
        a %= b;
        if (a != 0) {
            s.append('.');
            ArrayList<Integer> list = new ArrayList();
            ArrayList<Long> map = new ArrayList<>();
            int head = -1;
            while(a != 0) {
                if ((head = map.indexOf(a)) != -1)
                    break;
                map.add(a);
                a *= 10;
                list.add((int)(a/b));
                a %= b;
            }
            for (int i=0; i<list.size(); i++) {
                if(i == head)
                    s.append('(');
                s.append(list.get(i));
            }
            if(head != -1)
                s.append(')');
        }
        return s.toString();
    }
    // 两数相除
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (dividend == Integer.MIN_VALUE && divisor == 1) return  Integer.MIN_VALUE;
        long n = greatestCommonDivisor(dividend,divisor);
        long a = Math.abs((long)dividend/n);
        long b = Math.abs((long)divisor/n);
        n = 0;
        long i = 1;
        while (a >= b) {
            a -= b;
            n += i;
            b = b<<1;
            i = i<<1;
        }
        if ((dividend ^ divisor) <0) {
            n = -n;
        }
        if (n > Integer.MAX_VALUE) {
            n = Integer.MAX_VALUE;
        }
        if (n < Integer.MIN_VALUE) {
            n = Integer.MIN_VALUE;
        }
        return (int)n;
    }
    protected int greatestCommonDivisor(int a, int b) {
        int c = 0;
        while (b != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    // 771. 宝石与石头
    public int numJewelsInStones(String J, String S) {
        boolean[] mark = new boolean[26*2];
        for (char ch : J.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                mark[ch-'a'] = true;
            }
            if (Character.isUpperCase(ch)) {
                mark[ch-'A'+26] = true;
            }
        }
        int count = 0;
        for (char ch : S.toCharArray()) {
            if (Character.isLowerCase(ch) && mark[ch-'a']) {
                count++;
            }
            if (Character.isUpperCase(ch) && mark[ch-'A'+26]) {
                count++;
            }
        }
        return count;
    }
    public String toLowerCase(String str) {
        return str.toLowerCase();
    }

    // 905. 按奇偶排序数组
    public int[] sortArrayByParity(int[] A) {
        int j = 0,t;
        for (int i=0; i<A.length; i++) {
            if (A[i] % 2 == 0) {
                t = A[i];
                A[i] = A[j];
                A[j] = t;
                j++;
            }
        }
        return A;
    }

    // 832. 翻转图像
    public int[][] flipAndInvertImage(int[][] A) {
        if (A.length == 0) {
            return A;
        }
        int[][] arr = new int[A.length][A[0].length];

        for (int i=0; i<A.length; i++) {
            for (int j= A[i].length-1; j>=0; j--) {
                arr[i][A[i].length-1-j] = A[i][j] == 1?0:1;
            }
        }
        return arr;
    }

    // 657. 机器人能否返回原点
    public boolean judgeCircle(String moves) {
        int r = 0 , l = 0, u = 0 , d = 0;
        for (char ch : moves.toCharArray()) {
            switch (ch) {
                case 'U':u++;break;
                case 'D':d++;break;
                case 'R':r++;break;
                case 'L':l++;break;
                default:
            }
        }
        return r == l && u == d;
    }

    // 852. 山脉数组的峰顶索引
    public int peakIndexInMountainArray(int[] A) {
        int idx = -1,max = A[0];

        for (int i=1; i<A.length; i++) {
            if (A[i] > max) {
                max = A[i];
                idx = i;
            }
        }
        return idx == A.length-1 ? -1 : idx;
    }

    // 617. 合并二叉树
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        TreeNode cur = new TreeNode( (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val));
        cur.left = mergeTrees(t1 != null ? t1.left : null , t2 != null ? t2.left : null);
        cur.right = mergeTrees(t1 != null ? t1.right : null , t2 != null ? t2.right : null);
        return cur;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        Executors
//        //Fucture
//        FutureTask<String> task = new FutureTask<>(()-> ""+Thread.currentThread());
//        new Thread(task).start();
//        System.out.println(task.get());

//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.stream().filter(e->"1".equals(e)).collect(Collectors.toList());
//        System.out.println(list);
//        Main main = new Main();
//        System.out.println(main.divide(7,-3));
//        ArrayDeque deque = new ArrayDeque();
        //deque.addLast(null);


//        System.out.println(main.searchRange(new int[]{1,4},4)[0]);
//        int i=0;
//        final int[] ints = {i, i};
//        List<Integer> list = new ArrayList();
//        list.sort(Comparator.comparingInt(e -> e));
        //System.out.println(main.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ASFCS"));
//        int[] a = {2,3,4};
//        List<Integer> list  = new ArrayList<>();
//        Arrays.stream(a).forEach(e->list.add(Integer.valueOf(e)));
//        System.out.println(list);
        ///System.out.println(letterCombinations("23"));
        //System.out.println(longestPalindrome("ababababababa"));

    //        int[] r = new int[0];
//            System.out.println(r.length);
//            int n = 43261596;
//        n = n<<16 & 0xFFFF0000 | n>>16 & 0x0000FFFF; //前一半和后一半交换
//        n = n<<8  & 0xFF00FF00 | n>>8  & 0x00FF00FF; //一半的前后交换交换（2个16位 前后小环）
//        n = n<<4  & 0xF0F0F0F0 | n>>4  & 0x0F0F0F0F; // 4 个 8 位前后交换
//        n = n<<2  & 0xCCCCCCCC | n>>2  & 0x33333333; // 8 个 4 位前后交换
//        n = n<<1  & 0xAAAAAAAA | n>>1  & 0x55555555 ; // 16个 2 位前后交换
//        System.out.println(n);


           // System.out.println(new Main().romanToInt("MCMXCIV"));

//        // 录入数据
//        Scanner in = new Scanner(System.in);
//
//        int n = Integer.valueOf(in.nextLine());
//        int m = Integer.valueOf(in.nextLine());
//
//        ArrayList<Node> list = new ArrayList(n);
//
//        for(int i=0; i<n; i++){
//            int start = in.nextInt();
//            int end = in.nextInt();
//            Node node = new Node(start,end);
//            list.add(node);
//        }
//
//        list.sort((e1,e2)->{
//            if (e1.start < e2.start){
//                return -1;
//            }else if(e1.start > e2.start){
//                return 1;
//            }
//            return 0;
//        });
//
//        Iterator it = list.iterator();
//        Node cur =null;
//        ArrayList<Node> result = new ArrayList();
//        while (it.hasNext()){
//            Node node = (Node) it.next();
//            if(cur == null){
//                cur = node;
//                continue;
//            }
//            if(cur.end <= node.start){
//                result.add(cur);
//                cur = node;
//                continue;
//            }
//        }
//        if(cur!=null&&result.size()>0){
//            if(cur.end<=result.get(0).start){
//                result.add(cur);
//            }
//        }
//
//
//
//        System.out.println(result);
    }
}