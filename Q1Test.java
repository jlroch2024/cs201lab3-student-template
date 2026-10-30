public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     }
     
     static class Node {
          int v;
          Node l, r;
          Node(int v) { this.v = v; }
     }

     static Node insert(Node n, int v) {
          if (n == null) return new Node(v);
          if (v < n.v) n.l = insert(n.l, v);
          else n.r = insert(n.r, v);
          return n;
     }

     // type: 0 = inorder, 1 = preorder, 2 = postorder
     static void walk(Node n, int type, StringBuilder sb) {
          if (n == null) return;
          if (type == 1) sb.append("-").append(n.v);
          walk(n.l, type, sb);
          if (type == 0) sb.append("-").append(n.v);
          walk(n.r, type, sb);
          if (type == 2) sb.append("-").append(n.v);
     }

     static String order(Node n, int type) {
          StringBuilder sb = new StringBuilder();
          walk(n, type, sb);
          return sb.length() == 0 ? "" : sb.substring(1);   // drop leading "-"
     }

     public static String verify(String traversal1, String traversal2, String traversal3) {
          String[] t = {traversal1, traversal2, traversal3};
          int[][] perms = {{0,1,2},{0,2,1},{1,0,2},{1,2,0},{2,0,1},{2,1,0}}; // {in, pre, post}

          for (int[] p : perms) {
               Node root = null;
               for (String s : t[p[1]].split("-")) root = insert(root, Integer.parseInt(s));

               if (order(root, 0).equals(t[p[0]])
                         && order(root, 1).equals(t[p[1]])
                         && order(root, 2).equals(t[p[2]])) {
                    String[] names = new String[3];
                    names[p[0]] = "Inorder";
                    names[p[1]] = "Preorder";
                    names[p[2]] = "Postorder";
                    return "Traversal 1 - " + names[0] + ", Traversal 2 - " + names[1]
                              + ", Traversal 3 - " + names[2];
               }
          }
          return "Invalid traversals";
     }
}