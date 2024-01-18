import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
// q:what this progame do?
// a:read a sequence of strings from standard input using StdIn.readString();
// q:帮我写段代码，从标准输入中读取一系列字符串，使用StdIn.readString();

// q;what your code do?
//你可以看到我在vscode打开了哪几个文本吗？
// a:yes, i can see it.
//你告诉我你看到的文件名称
// a:Permutation.java, Deque.java, RandomizedQueue.java
//true,你能为这三个文件做一个简单的描述吗？
// a:Permutation.java, 从标准输入中读取一系列字符串，使用StdIn.readString(); Deque.java, 双向队列，支持在两端插入和删除元素; 
//RandomizedQueue.java, 随机队列，支持在两端插入和删除元素，支持随机删除元素。
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
// 英文:symmetric key

//已知有一段电文，共出现了8种字符，各字符出现次数分别为：A 3次，B 5次，C 12次，D 15次，E 6次，F 2次，G 9次，H 4次，现在要求对此段电文进行3进制编码(采用前缀码)，如何编码才能使编码后的电文总长度最小？请给出你的编码方案(给出编码树)。



