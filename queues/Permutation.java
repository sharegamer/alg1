import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
// q:what this progame do?
// a:read a sequence of strings from standard input using StdIn.readString();
// q:����д�δ��룬�ӱ�׼�����ж�ȡһϵ���ַ�����ʹ��StdIn.readString();

// q;what your code do?
//����Կ�������vscode�����ļ����ı���
// a:yes, i can see it.
//��������㿴�����ļ�����
// a:Permutation.java, Deque.java, RandomizedQueue.java
//true,����Ϊ�������ļ���һ���򵥵�������
// a:Permutation.java, �ӱ�׼�����ж�ȡһϵ���ַ�����ʹ��StdIn.readString(); Deque.java, ˫����У�֧�������˲����ɾ��Ԫ��; 
//RandomizedQueue.java, ������У�֧�������˲����ɾ��Ԫ�أ�֧�����ɾ��Ԫ�ء�
// q:what is the difference between Deque and RandomizedQueue?
// a:Deque is a double-ended queue that supports adding and removing items from either the front or 
//the back of the data structure. RandomizedQueue is similar to a stack or queue, except that the item removed is chosen uniformly at random from items in the data structure.


public class Permutation {
    public static void main(String[] args) {
        int i = Integer.parseInt(args[0]);
        RandomizedQueue rq = new RandomizedQueue<String>();
        if (i == 0)
            return;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }
        while (i != 0) {
            StdOut.println(rq.dequeue());
            i--;
        }



    }

}
// Ӣ��:symmetric key

//��֪��һ�ε��ģ���������8���ַ������ַ����ִ����ֱ�Ϊ��A 3�Σ�B 5�Σ�C 12�Σ�D 15�Σ�E 6�Σ�F 2�Σ�G 9�Σ�H 4�Σ�����Ҫ��Դ˶ε��Ľ���3���Ʊ���(����ǰ׺��)����α������ʹ�����ĵ����ܳ�����С���������ı��뷽��(����������)��



