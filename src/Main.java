
import java.util.*;

public class Main {

    static int getPath(int[][] nums, int n, int m){
        int[][] arr = new int[n][m];
        for (int i=0;i<n; i++) {
            for (int j=0; j<m; j++) {
                arr[i][j] =Integer.MAX_VALUE;
            }
        }
        arr[n-1][m-1] = 0;
        for (int i=n-1; i>=0; i--) {
            for (int j=m-1; j>=0; j--) {
                if (nums[i][j] == -1 || arr[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                int count = nums[i][j] + arr[i][j];
                helper(arr,i-1,j,count);
                helper(arr,i,j-1,count);
                helper(arr,i+1,j,count);
                helper(arr,i,j+1,count);
            }
        }
        return  arr[0][0];
    }

    public static void  helper(int[][] arr,int i, int j,int count) {
        if (i < 0 || j< 0 || i >= arr.length || j >=arr[0].length) {
            return;
        }
        if (arr[i][j] == 0) {
            arr[i][j] = count;
            return;
        }
        if (arr[i][j] > count) {
            arr[i][j] =count;
        }
    }


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        int[][] nums = new int[n][m];
        for (int i=0; i<n ; i++) {
            for (int j=0; j<m; j++) {
                nums[i][j] = in.nextInt();
            }
        }

        System.out.println(getPath(nums,n,m));

    }
}



//    int n = in.nextInt();
//
//        while (n-- > 0) {
//        int k = in.nextInt();
//        int l = in.nextInt();
//        int r = in.nextInt();
//        int cur = 0;
//        int tmp = k - 1;
//        while (cur < r) {
//            cur += tmp;
//            tmp *= k;
//        }
//        System.out.println(cur / k);
//    }
//}
//    int r = -1;
//        if (n < 3) {
//
//    }
//        for (int i=0; i<n; i++) {
//
//    }
//    int m = in.nextInt();
//    int p = in.nextInt();
//    int[] nums = new int[n];
//        for (int i=0; i<n; i++) {
//        nums[i] = in.nextInt();
//    }
//
//        for (int i=0; i<m; i++) {
//        String s = in.next();
//        int idx = in.nextInt()-1;
//        if ("A".equals(s)) {
//            nums[idx]++;
//        }else {
//            nums[idx]--;
//        }
//    }
//
//    int rank = 1;
//    p = nums[p-1];
//        for (int i: nums) {
//        if (i > p) {
//            rank++;
//        }
//    }
//        System.out.println(rank);
//}
//    static  int count = 0;
//    static  int  min = Integer.MAX_VALUE;
//    static  int[] nums = new int[6];
//    static  int[] arr = new int[6];
//    public static void find(int t) {
//        if (t >= 6) {
//            if (count < min) {
//                int l =0 ,r = 0,i=0;
//                for (; i<3; i++) {
//                    l += arr[i];
//                }
//                for (;i<6;i++){
//                    r+= arr[i];
//                }
//                if (l == r) {
//                    min = count;
//                }
//            }
//            return ;
//        }
//
//        for (int i=0;i<10; i++) {
//            if (i != nums[t]) {
//                count++;
//            }
//            if (count > min) {
//                if (i != nums[t]) {
//                    count--;
//                }
//                continue;
//            }
//            arr[t] = i;
//            find(t+1);
//            if (i != nums[t]) {
//                count--;
//            }
//        }
//    }

//
//    int[] counter = new int[52];
//    int idx = 0;
//        for (char i : s.toCharArray()) {
//                if (Character.isLetter(i)) {
//                if (Character.isUpperCase(i)) {
//                idx = i - 'A';
//                }
//                if (Character.isLowerCase(i)) {
//                idx = i - 'a'+26;
//                }
//                counter[idx]++;
//                if (counter[idx] == 3) {
//                System.out.println(i);
//                break;
//                }
//                }
//                }
//    String[] tokens = in.nextLine().split(" ");
//    StringBuilder builder = new StringBuilder();
//
//        for (int i=tokens.length-1; i>0; i--) {
//                builder.append(tokens[i]).append(" ");
//                }
//                if (tokens.length > 0)
//                builder.append(tokens[0]);
//                System.out.println(builder);
//    String str;
//    String cur;
//        while (in.hasNext()) {
//                str = in.nextLine();
//                cur = str = str.replaceAll(" ","");
//                if (str.length() > 14) {
//                cur = str.substring(0,14)+" "+str.substring(14);
//                }
//                if (str.length() > 6) {
//                cur = str.substring(0,6)+" "+cur.substring(6);
//                }
//                System.out.println(cur);
//                }
//
//    public static String is(Scanner in) {
//
//        int n = in.nextInt();
//        int m = in.nextInt();
//
//        class Node{
//            int x;
//            int y;
//
//            Node(){}
//            Node(int x,int y){this.x=x;this.y=y;}
//            @Override
//            public String toString() {
//                return "Node{" +
//                        "x=" + x +
//                        ", y=" + y +
//                        '}';
//            }
//        }
//
//        List<Node> list = new ArrayList(m*2);
//        for (int i=0; i<m; i++) {
//            Node node = new Node();
//            node.x = in.nextInt();
//            node.y = in.nextInt();
//            list.add(node);
//            list.add(new Node(node.y,node.x));
//        }
//        list.sort((e1,e2)->{
//            if (e1.x == e2.x) {
//                return e1.y > e2.y ? 1 : (e1.y == e2.y ? 0: -1);
//            }
//            return e1.x > e2.x ? 1: -1;
//        });
//
//        List<Node> other = new ArrayList<>(m*2);
//
//        Map map = new HashMap<>();
//        for (int i = 0; i<list.size(); i++) {
//            int num = list.get(i).x;
//            for (int j=i; j<list.size(); j++) {
//                if (num != list.get(j).x){
//                    break;
//                }
//                map.put(list.get(j).y,1);
//                i++;
//            }
//            for (int z=1; z<=n; z++) {
//                if(z!=num && !map.containsKey(z)) {
//                    other.add(new Node(num>z?num:z,num>z?z:num));
//                }
//            }
//            map.clear();
//        }
//        other.sort((e1,e2)->{
//            if (e1.x == e2.x) {
//                return e1.y > e2.y ? 1 : (e1.y == e2.y ? 0: -1);
//            }
//            return e1.x > e2.x ? 1: -1;
//        });
//        if(other.size()%2 != 0) {
//            return "No";
//        }
//        for (int i=0; i<other.size(); i+=2) {
//            if (list.get(i).x!=list.get(i+1).x && list.get(i).y != list.get(i+1).y){
//                return  "No";
//            }
//        }
//        return "Yes";
//    }
//
//
//class Node {
//    int x;
//    int y;
//    int z;
//
//    @Override
//    public String toString() {
//        return "Node{" +
//                "x=" + x +
//                ", y=" + y +
//                ", z=" + z +
//                '}';
//    }
//}
//
//
//    List<Node> list = new ArrayList(n);
//        for (int i=0; i<n; i++) {
//        Node node = new Node();
//        node.x = in.nextInt();
//        node.y = in.nextInt();
//        node.z = in.nextInt();
//        list.add(node);
//        }
//        list.sort((e1,e2)->{
//        if (e1.x == e2.x) {
//        if (e1.y == e2.y) {
//        return  e1.z > e2.z ? -1 : (e1.z == e2.z ? 0 : 1);
//        }
//        return e1.y > e2.y ? -1 : 1;
//        }
//        return e1.x > e2.x ? -1 : 1;
//        });
//
//        System.out.println(list);
//        int count = 0;
//        for (int i=1; i<n; i++) {
//        Node cur = list.get(i);
//        System.out.println(cur);
//        for (int j=0; j<i; j++) {
//        System.out.println(list.get(j));
//        if (cur.x < list.get(j).x && cur.y<list.get(j).y && cur.z < list.get(j).z){
//        count++;
//        }
//        }
//        }
//        System.out.println(count);
//    int n = in.nextInt();
//    int m = in.nextInt();
//
//        for (int i=0; i<n; i++) {
//        int x = in.nextInt();
//        int y = in.nextInt();
//
//        }
//        int t = in.nextInt();
//        int min = 0;
//        int max = 0;
//        for (int i=0; i<t; i++) {
//        int n = in.nextInt();
//        int k = in.nextInt();
//
//        if(n > 2 && n>k) {
//        if (k > n/2)
//        max = n-k;
//        else
//        max = k-1;
//        }
//        System.out.println(min+" "+max);
//
//    int min = 0;
//
//    int yes = 0;
//
//        str = in.nextLine();
//
//                String[] arr = str.split(",");
//                str+= arr[0]+","+arr[1];
//                arr = str.split(",");
//
//                for (int i=0; i<arr.length-1; i+=2) {
//        int x1 = Integer.valueOf(arr[i]);
//        int y1 = Integer.valueOf(arr[i+1]);
//        int x2 = Integer.valueOf(arr[i+2]);
//        int y2 = Integer.valueOf(arr[i+3]);
//        if ( x1 <= x) {
//        yes = yes | 1;
//        }
//        if (x1 >= x) {
//        yes = yes | 2;
//        }
//        if (y1 >= y) {
//        yes = yes | 4;
//        }
//        if (y1 <= y) {
//        yes = yes | 8;
//        }
//        //求出垂直距离
//        int k = (x1-x2) / (y1-y2);
//
//
//        }
//
//        if ( yes == 15 ) {
//        System.out.println("yes,0");
//        }else {
//        System.out.println("no,"+min);
//        }
//    int nums[] = new int[n];
//        for (int i=0; i<n; i++) {
//        nums[i] = in.nextInt();
//        }


////n 个 节点有n-1条边 说明不可能存在环的情况
//
//    // 每个路走两边，再减去做高的枝
//    int count = (n-1)>>1;
//
//    int[] arr =new int[n+1];
//    int max = 0;
//        for (int i=0; i<n-1; i++) {
//        int a = in.nextInt();
//        int b = in.nextInt();
//        arr[b] = 1+ arr[a];
//        if(arr[b] > max) {
//        max = arr[b];
//        }
//        }
//
//        System.out.println(((n-1)<<1)-max);

//        int n = in.nextInt();
//        int k = in.nextInt();
//        int t = in.nextInt();
//        int[] arr = new int[n];
//
//        for (int i=0; i<n; i++) {
//            arr[i] = in.nextInt();
//        }
//        int count = 0;
//        //长为k 的区间中存在出现次数大于t的数字
//        HashMap<Integer,Integer> map = new HashMap();
//
//        for (int i=0;i<n-t; i++) {
//            for(int j=i;j<i+k; j++) {
//                map.put(arr[j],map.getOrDefault(arr[j],0)+1);
//                if(map.get(arr[j]) >=t ){
//                    count++;
//                    break;
//                }
//            }
//            map.clear();
//        }
//        System.out.println(count);
